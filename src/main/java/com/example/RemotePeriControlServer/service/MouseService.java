package com.example.RemotePeriControlServer.service;

import com.example.RemotePeriControlServer.data.dto.MouseClickDTO;
import com.example.RemotePeriControlServer.data.dto.MouseMoveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Objects;
import java.util.Random;

import static com.example.RemotePeriControlServer.commons.Constants.MOUSE_DELAY_SINGLE;
import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;

@Service
@Slf4j
public class MouseService {

    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    @Autowired
    Robot robot;

    public void performMouseMoveEvent(MouseMoveDTO mouseMove) {
        Point current = MouseInfo.getPointerInfo().getLocation();
        Integer abs = mouseMove.getAbs() + current.x;
        Integer ord = mouseMove.getOrd() + current.y;

        if(Objects.isNull(abs) || Objects.isNull(ord)){
            log.warn("Got null in abs or ord: {} {}", abs, ord);
            return;
        }

        try {
            robot.mouseMove(abs, ord);
            Thread.sleep(MOUSE_DELAY_SINGLE);
        } catch (InterruptedException interruptedException) {
            log.warn("Interrupted while trying to move to {} {}", abs, ord);
        }
    }

    public void performClickEvent(MouseClickDTO click) {
        Boolean singleClick = click.getSingleClick();
        if(Objects.isNull(singleClick)){
            log.warn("Got null in click");
            return;
        }

        try {
            robot.mousePress(BUTTON1_DOWN_MASK);
            robot.mouseRelease(BUTTON1_DOWN_MASK);

            if (!singleClick) {
                robot.mousePress(BUTTON1_DOWN_MASK);
                robot.mouseRelease(BUTTON1_DOWN_MASK);
            }
            Thread.sleep(MOUSE_DELAY_SINGLE);
        } catch (InterruptedException interruptedException) {
            log.warn("Interrupted while trying to perform click: singleClick {}", singleClick);
        }
    }
}
