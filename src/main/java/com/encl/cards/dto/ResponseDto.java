package com.encl.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Response holds the status code and message for the API response"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String status;

    @Schema(
            description = "Status message in the response"
    )
    private Object message;
}
