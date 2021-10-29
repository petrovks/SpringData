package ru.gb.springdata.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class StatisticDto {
    private String className;
    private Long totalDuration;


    public StatisticDto(String className, Long totalDuration) {
        this.className = className;
        this.totalDuration = totalDuration;
    }
}
