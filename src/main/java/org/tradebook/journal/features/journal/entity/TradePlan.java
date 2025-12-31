package org.tradebook.journal.features.journal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "trade_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradePlan {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @Column(name = "target_price", precision = 19, scale = 4)
    private BigDecimal targetPrice;

    @Column(name = "stop_loss", precision = 19, scale = 4)
    private BigDecimal stopLoss;

    @Column(name = "risk_amount", precision = 19, scale = 2)
    private BigDecimal riskAmount;

    @Column(name = "setup_reason", columnDefinition = "TEXT")
    private String setupReason;

    @Column(name = "img_url")
    private String imgUrl;
}
