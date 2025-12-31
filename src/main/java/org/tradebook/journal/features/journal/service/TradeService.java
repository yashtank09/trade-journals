package org.tradebook.journal.features.journal.service;

import org.tradebook.journal.features.journal.dto.request.CreateTradeRequest;
import org.tradebook.journal.features.journal.dto.request.UpdateTradeRequest;
import org.tradebook.journal.features.journal.dto.response.TradeResponse;

import java.time.LocalDate;
import java.util.List;

public interface TradeService {
    TradeResponse createTrade(Long userId, CreateTradeRequest request);

    TradeResponse updateTrade(Long tradeId, Long userId, UpdateTradeRequest request);

    TradeResponse getTrade(Long tradeId, Long userId);

    List<TradeResponse> getTrades(Long userId, LocalDate startDate, LocalDate endDate);
}
