package com.smilego.smilego.integration.subscriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.requests.CreateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdateSubscriptionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UpdateSubscriptionControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldUpdateSubscriptionSuccessfully() throws Exception {
        Long subscriptionId = 1L;
        LocalDateTime now = LocalDateTime.now();
        CreateSubscriptionRequest requestCreation = new CreateSubscriptionRequest(
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                now,
                now
        );
        mockMvc.perform(post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCreation)));

        UpdateSubscriptionRequest request = new UpdateSubscriptionRequest(
                1L,
                SubscriptionPlanEnum.PREMIUM,
                SubscriptionStatusEnum.ACTIVE,
                now,
                now
        );

        Subscription mockSubscription = new Subscription(
                subscriptionId,
                1L,
                SubscriptionPlanEnum.PREMIUM,
                SubscriptionStatusEnum.ACTIVE,
                null,
                now,
                now,
                now,
                now
        );

        when(updateSubscriptionUseCase.execute(mockSubscription)).thenReturn(mockSubscription);
        mockMvc.perform(put("/subscriptions/{id}", subscriptionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(subscriptionId.intValue())))
                .andExpect(jsonPath("$.plan", is("PREMIUM")))
                .andExpect(jsonPath("$.status", is("ACTIVE")));
    }
}
