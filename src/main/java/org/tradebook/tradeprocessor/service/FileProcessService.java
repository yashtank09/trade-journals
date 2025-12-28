package org.tradebook.tradeprocessor.service;

import org.springframework.web.multipart.MultipartFile;
import org.tradebook.tradeprocessor.dto.request.FileUploadRequest;
import org.tradebook.tradeprocessor.entity.FileProcessor;

public interface FileProcessService {
    FileProcessor uploadFile(FileUploadRequest fileRequest, MultipartFile file);
}
