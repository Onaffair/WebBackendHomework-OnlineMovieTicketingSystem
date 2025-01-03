package com.example.onaffair.dto;

import com.example.onaffair.entity.Cinema;
import com.example.onaffair.entity.Movie;
import com.example.onaffair.entity.Screening;
import com.example.onaffair.entity.ScreeningRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDetailDto {
    private Cinema cinema;  // 电影院基本信息
    private List<MovieDetail> movieDetails; // 电影详情列表

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class MovieDetail {
        private Movie movie; // 电影信息
        private List<ScreeningDetail> screeningDetails; // 放映详情列表
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScreeningDetail {
        private ScreeningRoom screeningRoom; // 放映厅信息
        private Screening screening;         // 放映时间和价格信息
    }
}
