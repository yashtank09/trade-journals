package org.tradebook.tradeprocessor.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.tradebook.tradeprocessor.enums.FileCategory;
import org.tradebook.tradeprocessor.enums.JobStatus;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "file_jobs")
public class FileProcessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_job_id")
    private Long fileJobId;

    @Column(name = "orignal_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "stored_file_name", nullable = false)
    private String storedFileName;

    @Column(name = "stored_path", nullable = false)
    private String storedPath;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private FileCategory fileCategory;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
