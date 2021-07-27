package com.example.RemotePeriControlServer.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MouseMoveDTO {
    Integer abs;
    Integer ord;
}
