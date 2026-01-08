package org.tradebook.journal.features.ingestion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.tradebook.journal.common.enums.FileCategory;
import org.tradebook.journal.config.security.JwtAuthenticationFilter;
import org.tradebook.journal.config.security.JwtService;
import org.tradebook.journal.features.ingestion.dto.request.FileUploadRequest;
import org.tradebook.journal.features.ingestion.entity.FileProcessor;
import org.tradebook.journal.features.ingestion.service.FileProcessService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
@AutoConfigureMockMvc(addFilters = false)
class FileControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private FileProcessService fileProcessService;

        @MockBean
        private JwtService jwtService;

        @MockBean
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void uploadFile_ShouldReturnOk_WhenFileAndMetadataAreValid() throws Exception {
                MockMultipartFile file = new MockMultipartFile(
                                "file",
                                "test.csv",
                                MediaType.TEXT_PLAIN_VALUE,
                                "header1,header2\nval1,val2".getBytes());

                FileUploadRequest metadata = new FileUploadRequest();
                metadata.setFileType("CSV");
                metadata.setSourceSystem("UI");
                metadata.setFileCategory(FileCategory.TRADE_BOOK);

                MockMultipartFile metadataPart = new MockMultipartFile(
                                "file-metadata",
                                "",
                                MediaType.APPLICATION_JSON_VALUE,
                                objectMapper.writeValueAsBytes(metadata));

                FileProcessor mockProcessor = new FileProcessor();
                mockProcessor.setOriginalFileName("test.csv");

                Long userId = 1L;

                when(fileProcessService.uploadFile(userId, any(FileUploadRequest.class), any())).thenReturn(mockProcessor);

                mockMvc.perform(multipart("/api/v1/file/upload")
                                .file(file)
                                .file(metadataPart))
                                .andExpect(status().isOk());
        }
}
