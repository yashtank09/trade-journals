package org.tradebook.journal.features.journal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {
    private Long id;
    private String symbol;
    private String exchange;

    // "EQUITY", "OPTION", "FUTURE"
    private String type;

    // "LONG", "SHORT"
    private String direction;
    private String status;
    private LocalDate date;

    private BigDecimal quantity;
    private BigDecimal entryPrice;
    private BigDecimal exitPrice;

    private BigDecimal grossPnl;
    private BigDecimal netPnl;
    private BigDecimal fees;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
