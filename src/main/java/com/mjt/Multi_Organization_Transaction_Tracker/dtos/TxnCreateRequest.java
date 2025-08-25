package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.TxnType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TxnCreateRequest {

    @NotNull
    private TxnType type;

    @NotNull @Positive
    private BigDecimal amount;

    @NotNull @Size(min = 1)
    private String item;

    @NotNull
    private LocalDate date;

    private String party;
}
