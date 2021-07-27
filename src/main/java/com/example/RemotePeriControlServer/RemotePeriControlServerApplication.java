package com.example.RemotePeriControlServer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class RemotePeriControlServerApplication {

    public static final boolean CHAOS_MODE = false;
    public static final boolean INTERFERE_MODE = false;
    public static final long CHAOTIC_DELAY = 1000L;
    public static final long NORMAL_DELAY = 9*60*1000L;
    
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RemotePeriControlServerApplication.class);
        builder.headless(false);
        builder.run(args);
    }

}
