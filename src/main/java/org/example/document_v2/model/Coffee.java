package org.example.document_v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "All attributes of Coffee Data")

public class Coffee {

    @Schema(description = "Auto generated ID")
    private String id;

    @Schema(description = "Type of Coffee")
    private String type;

    @Schema(description = "The price of Coffee")
    private int price;

    @Schema(description = "The description about the Coffee")
    private String description;
}
