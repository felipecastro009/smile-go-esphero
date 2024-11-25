package com.smilego.smilego.unit.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import com.smilego.smilego.infra.controllers.subscriptions.UpdateSubscriptionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateSubscriptionControllerTest {

    @Mock
    private UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @InjectMocks
    private UpdateSubscriptionController updateSubscriptionController;

    @Test
    void shouldUpdateSubscriptionSuccessfully() {
        Long subscriptionId = 1L;
        UpdateSubscriptionRequest request = new UpdateSubscriptionRequest(1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
        Subscription updatedSubscription = new Subscription(1L, 1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        when(updateSubscriptionUseCase.execute(any(Subscription.class))).thenReturn(updatedSubscription);
        SubscriptionResponse expectedResponse = SubscriptionDTOMapper.toResponse(updatedSubscription);
        SubscriptionResponse actualResponse = updateSubscriptionController.handle(subscriptionId, request);
        assertEquals(expectedResponse, actualResponse);
        verify(updateSubscriptionUseCase, times(1)).execute(any(Subscription.class));
    }
}
