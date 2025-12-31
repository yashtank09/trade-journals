package org.tradebook.tradeprocessor.entity;


import jakarta.persistence.*;
import lombok.*;
import org.tradebook.tradeprocessor.enums.PositionDirection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "trade_position",
        indexes = {
                @Index(name = "idx_position_symbol_segment", columnList = "symbol, segment"),
                @Index(name = "idx_position_status", columnList = "status"),
                @Index(name = "idx_position_last_trade", columnList = "last_trade_id")
        }
)
public class TradePositions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", nullable = false, length = 50)
    private String symbol;

    @Column(name = "segment", nullable = false, length = 10)
    private String segment;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false, length = 10)
    private PositionDirection direction;

    @Column(name = "entry_qty", nullable = false)
    private Long entryQty;

    @Column(name = "entry_avg_price", nullable = false)
    private BigDecimal entryAvgPrice;

    @Column(name = "exit_qty", nullable = false)
    private Long exitQty;

    @Column(name = "exit_avg_price", nullable = false)
    private BigDecimal exitAvgPrice;

    @Column(name = "open_qty", nullable = false)
    private Long openQty;

    @Column(name = "status", nullable = false, length = 10)
    private PositionStatus status;

    @Column(name = "opened_at", nullable = false)
    private LocalDateTime openedAt;

    @Column(name = "colsed_at")
    private LocalDateTime closedAt;

    @Column(name = "last_trade_id")
    private Long lastTradeId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
