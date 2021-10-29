package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.springdata.aop.AppLoggingAspect;
import ru.gb.springdata.dtos.StatMethDto;
import ru.gb.springdata.dtos.StatisticDto;
import ru.gb.springdata.utils.StatisticUtil;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private StatisticUtil statisticUtil;
    private final AppLoggingAspect app;

    @PostConstruct
    public void init() {
        this.statisticUtil = app.getStatisticUtil();
    }

    public List<StatisticDto> getStatistic() {
        Long sum = 0L;
        List<StatisticDto> list = new ArrayList<>();
        List<StatMethDto> statMethDtos = new ArrayList<>();
        for (Map.Entry<String, ConcurrentHashMap<String, Long>> s: statisticUtil.getStatistic().entrySet()) {
           for (Map.Entry<String, Long> m: s.getValue().entrySet()) {
                //statMethDtos.add(new StatMethDto(m.getKey(), m.getValue()));
               // System.out.println(s.getKey() + "  " +  m.getKey() + "  " + m.getValue());
                sum += m.getValue();
            }
            list.add(new StatisticDto(s.getKey(), sum));
        }
        return list;
    }


}
