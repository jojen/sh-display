package com.jojen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Temperature {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    LocalDateTime time;
    double value;
}
