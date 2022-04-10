package com.santander.banco811.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ErrorResponse {
    private List<String> errorMessages;
}
