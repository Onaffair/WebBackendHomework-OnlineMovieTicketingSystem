package com.example.onaffair.mapper;

import com.example.onaffair.entity.Screening;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreeningMapper {
    List<Screening> getAll();

    boolean insert(Screening screening);

    boolean update(Screening screening);

    boolean delete(Integer screeningId);

    List<Screening> selectByIf(Screening screening);



}
