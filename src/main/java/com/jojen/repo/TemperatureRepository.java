package com.jojen.repo;

import com.jojen.model.Temperature;
import com.jojen.model.TemperatureForecast;
import com.jojen.model.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Optional<Temperature> findByTime(LocalDateTime date);
    List<Temperature> findByTimeBetween(LocalDateTime from, LocalDateTime to);
}
