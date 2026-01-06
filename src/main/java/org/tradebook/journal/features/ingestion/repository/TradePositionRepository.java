package org.tradebook.journal.features.ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tradebook.journal.common.enums.PositionDirection;
import org.tradebook.journal.common.enums.PositionStatus;
import org.tradebook.journal.features.ingestion.entity.TradePositions;

import java.util.Collection;
import java.util.List;

public interface TradePositionRepository extends JpaRepository<TradePositions, Long> {

    List<TradePositions> findBySymbolAndDirectionAndStatusInOrderByCreatedAtAsc(String symbol, PositionDirection direction, Collection<PositionStatus> statuses);
}
