package org.tradebook.journal.features.ingestion.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tradebook.journal.features.ingestion.dto.request.FileUploadRequest;
import org.tradebook.journal.features.ingestion.dto.response.DataApiResponse;
import org.tradebook.journal.features.ingestion.dto.response.FileUploadResponse;
import org.tradebook.journal.features.ingestion.entity.FileProcessor;
import org.tradebook.journal.features.ingestion.service.LocalFileProcessService;

@RestController
@RequestMapping("/file")
public class FileController {

    private final LocalFileProcessService storageService;

    public FileController(LocalFileProcessService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataApiResponse<FileUploadResponse>> uploadFile(@RequestPart("file-metadata") FileUploadRequest fileRequest, @RequestPart("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(new DataApiResponse<>("error", 400, "File or file type is missing"), HttpStatus.BAD_REQUEST);
        }

        FileProcessor fileProcessor = storageService.uploadFile(fileRequest, file);

        return new ResponseEntity<>((new DataApiResponse<>("success", 200, "File uploaded successfully", new FileUploadResponse(fileProcessor.getOriginalFileName(), fileProcessor.getFileType(), fileProcessor.getStoredPath(), fileProcessor.getStatus(), fileProcessor.getCreatedAt(), fileProcessor.getUpdatedAt()))), HttpStatus.OK);
    }
}
