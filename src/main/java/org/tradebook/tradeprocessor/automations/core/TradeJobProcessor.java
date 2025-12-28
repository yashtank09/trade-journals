package org.tradebook.tradeprocessor.automations.core;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.tradebook.tradeprocessor.automations.service.TradeDataIngestionService;
import org.tradebook.tradeprocessor.entity.FileProcessor;
import org.tradebook.tradeprocessor.repository.FileJobProcessRepository;
import org.tradebook.tradeprocessor.enums.JobStatus;

import java.util.List;

@Component
public class TradeJobProcessor implements Runnable {

    private final FileJobProcessRepository fileJobProcessRepository;
    private final TradeDataIngestionService tradeDataIngestionService;

    public TradeJobProcessor(FileJobProcessRepository fileJobProcessRepository, TradeDataIngestionService tradeDataIngestionService) {
        this.fileJobProcessRepository = fileJobProcessRepository;
        this.tradeDataIngestionService = tradeDataIngestionService;
    }

    @Override
    @Transactional
    public void run() {
        List<FileProcessor> pendingFileJobs = fileJobProcessRepository.findPendingFiles(JobStatus.PENDING, PageRequest.of(0, 10));
        for (FileProcessor job: pendingFileJobs) {
            tradeDataIngestionService.processFile(job);
        }
    }
}
