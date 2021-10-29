package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdata.dtos.StatMethDto;
import ru.gb.springdata.dtos.StatisticDto;
import ru.gb.springdata.services.StatisticService;
import ru.gb.springdata.utils.StatisticUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public List<StatisticDto> stat() {
         return statisticService.getStatistic();
    }
}
