package org.tradebook.journal.features.ingestion.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tradebook.journal.config.security.AppUserDetails;
import org.tradebook.journal.features.ingestion.dto.request.FileUploadRequest;
import org.tradebook.journal.features.ingestion.dto.response.DataApiResponse;
import org.tradebook.journal.features.ingestion.dto.response.FileUploadResponse;
import org.tradebook.journal.features.ingestion.entity.FileProcessor;
import org.tradebook.journal.features.ingestion.service.FileProcessService;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

        private final FileProcessService storageService;

        public FileController(FileProcessService storageService) {
                this.storageService = storageService;
        }

        @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        public ResponseEntity<DataApiResponse<FileUploadResponse>> uploadFile(@Parameter(description = "Metadata for the file", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestPart("file-metadata") FileUploadRequest fileRequest,
                        @AuthenticationPrincipal AppUserDetails userDetails,
                        @RequestPart("file") MultipartFile file) {

                if (file == null || file.isEmpty()) {
                        return new ResponseEntity<>(new DataApiResponse<>("error", 400, "File or file type is missing"),
                                        HttpStatus.BAD_REQUEST);
                }

                Long userId = userDetails.getId();

                FileProcessor fileProcessor = storageService.uploadFile(userId, fileRequest, file);

                return new ResponseEntity<>((new DataApiResponse<>("success", 200, "File uploaded successfully",
                                new FileUploadResponse(fileProcessor.getOriginalFileName(), fileProcessor.getFileType(),
                                                fileProcessor.getStoredPath(), fileProcessor.getStatus(),
                                                fileProcessor.getCreatedAt(),
                                                fileProcessor.getUpdatedAt()))),
                                HttpStatus.OK);
        }
}
