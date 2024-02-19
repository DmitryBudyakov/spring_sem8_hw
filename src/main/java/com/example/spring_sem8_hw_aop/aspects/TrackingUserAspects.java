package com.example.spring_sem8_hw_aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class TrackingUserAspects {

    @Around(value = "@annotation(TrackUserAction)")
    public Object aroundTrackingUser(ProceedingJoinPoint joinPoint) throws Throwable {
        // получение названия метода
        String methodName = joinPoint.getSignature().getName();
        // время до выполнения осн. метода
        LocalDateTime beforeTime = LocalDateTime.now();
        // секундомер для подсчета времени выполнения, начало
        long start = System.currentTimeMillis();
        // вывод в консоль сообщения перед началом выполнения осн. метода
        System.out.println("UserActionTracking : метод " + methodName + " : " + beforeTime + " : " +
                "начало выполнения");
        // основной метод
        Object targetedMethod = joinPoint.proceed();

        // время после окончания выполнения осн. метода
        LocalDateTime afterTime = LocalDateTime.now();
        // секундомер для подсчета времени выполнения, окончание
        long end = System.currentTimeMillis();
        // вывод в консоль сообщения об окончании выполнения осн. метода
        System.out.println("UserActionTracking : метод " + methodName + " : " + afterTime + " : " +
                "метод завершен " + ": время выполнения - " + (end - start) + " мсек");

        return targetedMethod;
    }

//    @Before(value = "@annotation(TrackUserAction)")
//    public void beforeUserAction(){
//        LocalDateTime beforeDateTime = LocalDateTime.now();
//        System.out.println("beforeUserAction: " + beforeDateTime + ": действие до выполнения осн. метода");
//    }
}
