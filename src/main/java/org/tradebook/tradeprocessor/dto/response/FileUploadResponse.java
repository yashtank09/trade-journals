package org.tradebook.tradeprocessor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tradebook.tradeprocessor.enums.JobStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String fileName;
    private String fileType;
    private String storedPath;
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}
