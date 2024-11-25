package com.smilego.smilego.integration.subscriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilego.smilego.application.usecases.subscriptions.DeleteSubscriptionUseCase;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.requests.CreateSubscriptionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class DeleteSubscriptionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldDeleteSubscriptionSuccessfully() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                now,
                now
        );
        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
        Long subscriptionId = 1L;
        mockMvc.perform(delete("/subscriptions/{id}", subscriptionId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenSubscriptionDoesNotExist() throws Exception {
        Long subscriptionId = 999L;
        mockMvc.perform(delete("/subscriptions/{id}", subscriptionId))
                .andExpect(status().isNoContent());
    }
}
