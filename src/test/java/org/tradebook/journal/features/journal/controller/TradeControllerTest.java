package org.tradebook.journal.features.journal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.tradebook.journal.features.auth.entity.User;
import org.tradebook.journal.features.auth.repository.UserRepository;
import org.tradebook.journal.features.journal.dto.request.CreateTradeRequest;
import org.tradebook.journal.features.journal.dto.response.TradeResponse;
import org.tradebook.journal.features.journal.service.TradeService;

import java.security.Principal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TradeController.class)
@AutoConfigureMockMvc(addFilters = false)
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private org.tradebook.journal.config.security.JwtService jwtService;

    @MockBean
    private org.tradebook.journal.config.security.JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTrade_ShouldReturnCreated_WhenRequestIsValid() throws Exception {
            CreateTradeRequest request = CreateTradeRequest.builder()
                            .symbol("AAPL")
                            .build(); // Add other necessary fields

            Principal mockPrincipal = mock(Principal.class);
            when(mockPrincipal.getName()).thenReturn("test@example.com");

            User mockUser = new User();
            mockUser.setId(1L);
            mockUser.setEmail("test@example.com");

            when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));
            when(tradeService.createTrade(eq(1L), any(CreateTradeRequest.class)))
                            .thenReturn(TradeResponse.builder().id(100L).build());

            mockMvc.perform(post("/trades")
                            .principal(mockPrincipal)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isCreated());
    }
}
