package ru.gb.springdata.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springdata.dtos.StatMethDto;
import ru.gb.springdata.dtos.StatisticDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class StatisticUtil {
   // private List<StatisticDto> statisticDto;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Long>> statistic;

    public StatisticUtil() { this.statistic = new ConcurrentHashMap<>(); }

    public void setStatistic (String serviceName, String methodName, Long duration) {
            if (statistic.containsKey(serviceName) && statistic.get(serviceName).containsKey(methodName)) {
                statistic.get(serviceName).remove(methodName);
                statistic.get(serviceName).put(methodName, duration);
                System.out.println("Duration update");
            }
            else if (statistic.containsKey(serviceName) && !statistic.get(serviceName).containsKey(methodName)) {
                statistic.get(serviceName).put(methodName, duration);
                System.out.println("Add new method");
            }
            else {
                statistic.put(serviceName, new ConcurrentHashMap<>());
                statistic.get(serviceName).put(methodName, duration);
                System.out.println("NEW");
            }





//        if (statisticDto.contains()) {
//            StatisticDto temp;
//            for (StatisticDto s: statisticDto) {
//                if (s.getClassName().equals(serviceName)){
//                    if (!s.getStatMethDtos().contains(methodName)){
//                        List<StatMethDto> list = s.getStatMethDtos();
//                        list.add(new StatMethDto(methodName, duration));
//                        s.setStatMethDtos(list);
//                        return;
//                    }
//                    else {
//                        for (StatMethDto m: s.getStatMethDtos()) {
//                            if(m.getMethodName().equals(methodName)) {
//                                m.setDuration(duration);
//                                return;
//                            }
//                        }
//                    }
//                }
//            }
//
//
//        }
//        else {
//            List<StatMethDto> listTemp = new ArrayList<>();
//            listTemp.add(new StatMethDto(methodName, duration));
//            // temp.put(methodName, duration);
//            //statistic.put(serviceName, temp);
//            statisticDto.add(new StatisticDto(serviceName, listTemp));
//        }
    }
}
