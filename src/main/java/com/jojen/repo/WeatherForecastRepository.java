package com.jojen.repo;

import com.jojen.model.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {
    List<WeatherForecast> findByDateBetween(LocalDateTime from, LocalDateTime to);
    Optional<WeatherForecast> findByDate(LocalDateTime date);
}
