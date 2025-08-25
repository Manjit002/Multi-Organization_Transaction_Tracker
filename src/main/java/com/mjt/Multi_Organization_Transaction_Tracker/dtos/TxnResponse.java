package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.TxnType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TxnResponse {
    private Long id;
    private TxnType type;
    private BigDecimal amount;
    private String item;
    private LocalDate date;
    private String party;
}
