package com.smilego.smilego.unit.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import com.smilego.smilego.infra.controllers.subscriptions.FindAllSubscriptionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllSubscriptionControllerTest {
    @Mock
    private FindAllSubscriptionUseCase findAllSubscriptionUseCase;

    @InjectMocks
    private FindAllSubscriptionController findAllSubscriptionController;

    @Test
    void shouldReturnListOfSubscriptions() {
        Subscription subscription1 = new Subscription(1L, 1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Subscription subscription2 = new Subscription(2L, 1L, SubscriptionPlanEnum.BASIC, SubscriptionStatusEnum.ACTIVE, new ArrayList<>(),LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        List<Subscription> mockSubscriptions = List.of(subscription1, subscription2);
        when(findAllSubscriptionUseCase.execute()).thenReturn(mockSubscriptions);
        List<SubscriptionResponse> expectedResponse = mockSubscriptions.stream()
                .map(SubscriptionDTOMapper::toResponse)
                .toList();

        List<SubscriptionResponse> actualResponse = findAllSubscriptionController.handle();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldReturnEmptyListWhenNoSubscriptionsExist() {
        when(findAllSubscriptionUseCase.execute()).thenReturn(List.of());
        List<SubscriptionResponse> actualResponse = findAllSubscriptionController.handle();
        assertEquals(0, actualResponse.size());
    }
}
