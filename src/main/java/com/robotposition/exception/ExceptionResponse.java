package com.robotposition.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();

    @Builder.Default
    String errorMessage = "Something wrong happened";

    @Builder.Default
    String errorResolution = "Please contact the IT support team";
}
