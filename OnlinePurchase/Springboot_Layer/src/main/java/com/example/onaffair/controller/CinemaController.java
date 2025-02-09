package com.example.onaffair.controller;

import com.example.onaffair.dto.CinemaDetailDto;
import com.example.onaffair.dto.CinemaGetDto;
import com.example.onaffair.dto.MovieDetailDto;
import com.example.onaffair.entity.Cinema;
import com.example.onaffair.entity.Movie;
import com.example.onaffair.entity.Screening;
import com.example.onaffair.entity.ScreeningRoom;
import com.example.onaffair.mapper.CinemaMapper;
import com.example.onaffair.service.CinemaService;
import com.example.onaffair.service.MovieService;
import com.example.onaffair.service.ScreeningRoomService;
import com.example.onaffair.service.ScreeningService;
import com.example.onaffair.utils.Result;
import com.example.onaffair.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/cinema")
@Transactional
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private ScreeningRoomService screeningRoomService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    public Result<List<Cinema>> getCinemaList(@RequestParam(defaultValue = "0") Integer movieId,
                                              @RequestParam(defaultValue = "全部") String brand,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "12") Integer size) {
        CinemaGetDto dto = new CinemaGetDto(){{
            setMovieId(movieId==0 ? null : movieId);
            setBrand(brand);
            setPage(page);
            setSize(size);
        }};
        try {
            List<Cinema> res = cinemaService.getCinemaByPage(dto);
            return new Result<>(Result.success().getCode(), "请求成功", res);
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR.getCode(), "请求失败");
        }
    }
    @GetMapping("/screening")
    public Result<CinemaDetailDto> getCinemaMovieScreening(@RequestParam(defaultValue = "null") String cinemaId) {
        if (cinemaId.equals("null")) return null;
        //获取影院信息
        Cinema cinema = new Cinema();
        cinema.setCinemaId(Integer.parseInt(cinemaId));
        cinema = cinemaService.findCinemasByCondition(cinema).get(0);

        List<ScreeningRoom> screeningRooms = screeningRoomService.findScreeningRoomsByCondition(new ScreeningRoom(){{
            setCinemaId(Integer.parseInt(cinemaId));
        }});
        Map<Movie,List<CinemaDetailDto.ScreeningDetail>> movieToScreeningDetailsMap = new HashMap<>();
        for (ScreeningRoom screeningRoom : screeningRooms){
            List<Screening> screenings = screeningService.findScreeningsByCondition(new Screening(){{
                setRoomId(screeningRoom.getRoomId());
            }});
            for (Screening screening : screenings){
                Movie movie = movieService.findMoviesByCondition(new Movie(){{
                    setMovieId(screening.getMovieId());
                }}).get(0);


                movieToScreeningDetailsMap.putIfAbsent(movie, new ArrayList<>() );
                movieToScreeningDetailsMap.get(movie).add(new CinemaDetailDto.ScreeningDetail(screeningRoom, screening));
            }
        }
        for (List<CinemaDetailDto.ScreeningDetail> x :movieToScreeningDetailsMap.values()){
            x.sort(Comparator.comparing(detail -> detail.getScreening().getScreeningTime())); //按照时间排序
        }
        Map<Cinema,List<CinemaDetailDto.MovieDetail> > cinemaToMovieDetailsMap = new HashMap<>();
        cinemaToMovieDetailsMap.put(cinema, new ArrayList<>());
        for (Map.Entry<Movie, List<CinemaDetailDto.ScreeningDetail>> entry : movieToScreeningDetailsMap.entrySet()) {
            cinemaToMovieDetailsMap.get(cinema).add(new CinemaDetailDto.MovieDetail(entry.getKey(), entry.getValue()));
        }
        return new Result<>(Result.success().getCode(), "请求成功", new CinemaDetailDto(cinema, cinemaToMovieDetailsMap.get(cinema)));
    }
}
