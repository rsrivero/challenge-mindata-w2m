package com.project.challenge.infrastructure.configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class NegativeIdLoggingAspect {

    @Before("execution(* com.project.challenge.application.adapter.SpaceshipQueryService.findSpaceship(..)) && args(id)")
    public void logNegativeId(JoinPoint joinPoint, Integer id) {
        if (id != null && id < 0) {
            log.info("Se intentó buscar una nave con un ID negativo: {}", id);
        }
    }
}
