package com.smilego.smilego.unit.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.requests.CreateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import com.smilego.smilego.infra.controllers.subscriptions.CreateSubscriptionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateSubscriptionControllerTest {
    @Mock
    private CreateSubscriptionUseCase createSubscriptionUseCase;

    @InjectMocks
    private CreateSubscriptionController createSubscriptionController;

    private CreateSubscriptionRequest request;
    private Subscription subscription;
    private SubscriptionResponse expectedResponse;

    @BeforeEach
    void setup() {
        request = new CreateSubscriptionRequest(1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
        subscription = new Subscription(1L, 1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, new ArrayList<>(),LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        expectedResponse = new SubscriptionResponse(1L, 1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void shouldCreateSubscriptionSuccessfully() {
        when(createSubscriptionUseCase.execute(any(Subscription.class))).thenReturn(subscription);
        SubscriptionResponse response = createSubscriptionController.handle(request);
        assertEquals(expectedResponse.id(), response.id());
    }
}
