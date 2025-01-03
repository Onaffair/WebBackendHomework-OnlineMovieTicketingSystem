package com.example.onaffair.service;

import com.example.onaffair.entity.Screening;
import java.util.List;

public interface ScreeningService {
    List<Screening> getAllScreenings();

    boolean addScreening(Screening screening);

    boolean updateScreening(Screening screening);

    boolean deleteScreening(Integer screeningId);

    List<Screening> findScreeningsByCondition(Screening screening);
}
