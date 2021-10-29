package ru.gb.springdata.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatMethDto {
    private String methodName;
    private Long duration;

    public StatMethDto(String methodName, Long duration) {
        this.methodName = methodName;
        this.duration = duration;
    }
}
