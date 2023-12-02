package ru.netology.filatov_alexandr.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OperationsGetReply {
    private final List<OperationsDTO> operations;

}
