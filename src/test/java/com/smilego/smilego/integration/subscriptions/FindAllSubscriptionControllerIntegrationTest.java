package com.smilego.smilego.integration.subscriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class FindAllSubscriptionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FindAllSubscriptionUseCase findAllSubscriptionUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllSubscriptions() throws Exception {
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
        List<Subscription> mockSubscriptions = Arrays.asList(
                new Subscription(
                        1L,
                        1L,
                        request.plan(),
                        request.status(),
                        null,
                        request.startDate(),
                        now,
                        now,
                        now
                )
        );
        when(findAllSubscriptionUseCase.execute()).thenReturn(mockSubscriptions);
        mockMvc.perform(get("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].plan", is("BASIC")));
    }

    @Test
    void shouldReturnEmptyListWhenNoSubscriptionsExist() throws Exception {
        when(findAllSubscriptionUseCase.execute()).thenReturn(List.of());
        mockMvc.perform(get("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
