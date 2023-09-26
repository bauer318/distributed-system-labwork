package ru.bmstu.kibamba.distributedsystemlw01.payload.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse {
    private String message;
}
