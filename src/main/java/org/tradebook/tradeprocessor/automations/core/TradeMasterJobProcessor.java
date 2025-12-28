package org.tradebook.tradeprocessor.automations.core;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.tradebook.tradeprocessor.automations.service.TradeMasterIngestionService;
import org.tradebook.tradeprocessor.dto.AggregatedTradeBookDataDto;
import org.tradebook.tradeprocessor.repository.TradeBookDataRepository;

import java.util.List;

@Component
public class TradeMasterJobProcessor implements Runnable {

    private final TradeBookDataRepository tradeBookDataRepository;
    private final TradeMasterIngestionService tradeMasterIngestionService;

    public TradeMasterJobProcessor(TradeBookDataRepository tradeBookDataRepository, TradeMasterIngestionService tradeMasterIngestionService) {
        this.tradeBookDataRepository = tradeBookDataRepository;
        this.tradeMasterIngestionService = tradeMasterIngestionService;
    }

    @Override
    @Transactional
    public void run() {
        List<AggregatedTradeBookDataDto> tradeBookDataList = tradeBookDataRepository.fetchAggregatedTrades();
        if (!tradeBookDataList.isEmpty()) {
            tradeMasterIngestionService.processTradeBookData(tradeBookDataList);
        }
    }
}
