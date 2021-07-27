package com.example.RemotePeriControlServer.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class RobotBean {

    @Bean
    public Robot getRobot() throws AWTException {
        return new Robot();
    }
}
