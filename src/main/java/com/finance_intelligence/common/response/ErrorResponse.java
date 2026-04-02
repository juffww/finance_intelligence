package com.finance_intelligence.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Error tong tra ve
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    @Builder.Default
    boolean success = false;
    @Builder.Default
    String message = "Error message";
    FieldError fieldError;
}