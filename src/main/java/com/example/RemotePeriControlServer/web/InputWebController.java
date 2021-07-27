package com.example.RemotePeriControlServer.web;

import com.example.RemotePeriControlServer.data.dto.KeystrokeDTO;
import com.example.RemotePeriControlServer.data.dto.MouseClickDTO;
import com.example.RemotePeriControlServer.data.dto.MouseMoveDTO;
import com.example.RemotePeriControlServer.service.KeyboardService;
import com.example.RemotePeriControlServer.service.MouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

import static com.example.RemotePeriControlServer.RemotePeriControlServerApplication.*;
import static com.example.RemotePeriControlServer.web.InputWebController.API.*;

@RestController
@RequestMapping(BASE_PATH)
public class InputWebController {

    @Autowired
    KeyboardService keyboardService;

    @Autowired
    MouseService mouseService;

    @PostMapping(path = KEY_PRESS, consumes = "application/json")
    public void pressKey(@RequestBody KeystrokeDTO keystrokeDTO) {
        keyboardService.performKeyEvent(keystrokeDTO);
    }

    @PostMapping(path = MOUSE_MOVE, consumes = "application/json")
    public HttpStatus moveMouse(@RequestBody MouseMoveDTO mouseMoveDTO) {
        mouseService.performMouseMoveEvent(mouseMoveDTO);
        return HttpStatus.OK;
    }

    @PostMapping(path = MOUSE_CLICK, consumes = "application/json")
    public void clickMouse(@RequestBody MouseClickDTO clickDTO) {
        mouseService.performClickEvent(clickDTO);
    }

    @Scheduled(fixedDelay = CHAOS_MODE? CHAOTIC_DELAY: NORMAL_DELAY)
    public void chaosCreator(){
        if(!INTERFERE_MODE){
            Random random = new Random();
            mouseService.performMouseMoveEvent(new MouseMoveDTO(random.nextInt(400), random.nextInt(200)));
        }
    }


    static final class API {
        public static final String BASE_PATH = "/api";
        public static final String KEY_PRESS = "/keyboard";
        public static final String MOUSE_MOVE = "/mousemove";
        public static final String MOUSE_CLICK = "/mouseclick";
    }
}
