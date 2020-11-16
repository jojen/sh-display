package com.jojen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeatherForecast {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDateTime date;

	private double morningTemp;
	private double dayTemp;
	private double eveningTemp;
	private double nightTemp;

	private String icon;

}
