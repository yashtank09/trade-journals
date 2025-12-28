package org.tradebook.tradeprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tradebook.tradeprocessor.entity.TradeBookMaster;

public interface TradeBookMasterRepository extends JpaRepository<TradeBookMaster, Long> {
}
