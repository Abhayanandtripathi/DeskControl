package com.example.RemotePeriControlServer.service;

import com.example.RemotePeriControlServer.data.dto.KeystrokeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Objects;

import static com.example.RemotePeriControlServer.commons.Constants.KEY_DELAY;

@Service
@Slf4j
public class KeyboardService {

    @Autowired
    Robot robot;

    public void performKeyEvent(KeystrokeDTO keystroke) {
        Integer code = keystroke.getKeyPressed();
        if(Objects.isNull(code)){
            log.warn("Got null as key press event");
            return;
        }

        try {
            robot.keyPress(code);
            Thread.sleep(KEY_DELAY);
            robot.keyRelease(code);
        } catch (InterruptedException interruptedException) {
            log.warn("Interrupted while trying to type {}", code);
        }

    }

}
