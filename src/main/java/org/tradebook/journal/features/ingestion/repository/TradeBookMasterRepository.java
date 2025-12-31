package org.tradebook.journal.features.ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tradebook.journal.features.ingestion.entity.TradeBookMaster;

public interface TradeBookMasterRepository extends JpaRepository<TradeBookMaster, Long> {
}
