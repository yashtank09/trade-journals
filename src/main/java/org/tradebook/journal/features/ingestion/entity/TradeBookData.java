package org.tradebook.journal.features.ingestion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tradebook.journal.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "trade_book_data",
        indexes = {
                // Processing
                @Index(name = "idx_trade_processed_flag", columnList = "processed_flag"),
                @Index(name = "idx_trade_processed_batch", columnList = "processed_batch_id"),
                @Index(name = "idx_trade_unprocessed_date", columnList = "processed_flag, trade_date"),

                // Trade grouping
                @Index(name = "idx_trade_order_id", columnList = "order_id"),
                @Index(name = "idx_trade_trade_id", columnList = "trade_id"),

                // Analytics
                @Index(name = "idx_trade_symbol", columnList = "symbol"),
                @Index(name = "idx_trade_exchange_segment", columnList = "exchange, segment")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeBookData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "trade_id", nullable = false)
    private Long tradeId;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "isin")
    private String isin;

    @Column(name = "trade_date", nullable = false)
    private LocalDate tradeDate;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "segment")
    private String segment;

    @Column(name = "series")
    private String series;

    @Column(name = "trade_type", nullable = false)
    private String tradeType;

    @Column(name = "auction")
    private Boolean auction;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 4, nullable = false)
    private BigDecimal price;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "processed_flag", nullable = false)
    private Boolean processedFlag = false;

    @Column(name = "processed_batch_id", columnDefinition = "BINARY(16)")
    private UUID processedBatchId;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "source_file_id", nullable = false)
    private Long sourceFileId;

    @Column(name = "order_execution_time", nullable = false)
    private LocalDateTime orderExecutionTime;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;
}