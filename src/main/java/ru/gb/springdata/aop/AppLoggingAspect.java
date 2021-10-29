package ru.gb.springdata.aop;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.gb.springdata.services.StatisticService;
import ru.gb.springdata.utils.StatisticUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Aspect
@Component
@Data
public class AppLoggingAspect {
    private StatisticUtil statisticUtil = new StatisticUtil();

    @Around("execution(public * ru.gb.springdata.services.*.*(..))")
    public Object methodProfilingCartService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String serviceName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
       //System.out.println(serviceName + " " + methodName + " duration = " + duration);
        statisticUtil.setStatistic(serviceName, methodName, duration);
//        for (Map.Entry<String, ConcurrentHashMap<String, Long>> e: statisticUtil.getStatistic().entrySet()) {
//            System.out.println("className = " + e.getKey());
//            for (Map.Entry<String, Long> m: e.getValue().entrySet()) {
//                System.out.println("          methodName = " + m.getKey() + ". Duration = " + m.getValue());
//            }
//
//        }
        return out;
    }


}
