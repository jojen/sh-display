package com.jojen.repo;

import com.jojen.model.TemperatureForecast;
import com.jojen.model.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureForecastRepository extends JpaRepository<TemperatureForecast, Long> {
    Optional<TemperatureForecast> findByTime(LocalDateTime date);
    List<TemperatureForecast> findByTimeBetween(LocalDateTime from, LocalDateTime to);
}
