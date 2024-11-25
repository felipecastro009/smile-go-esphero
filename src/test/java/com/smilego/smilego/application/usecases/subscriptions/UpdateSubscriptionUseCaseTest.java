package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateSubscriptionUseCaseTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @Test
    void testExecute() {
        Subscription subscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.PREMIUM,
                SubscriptionStatusEnum.ACTIVE,
                List.of(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(subscriptionRepository.update(subscription)).thenReturn(subscription);
        Subscription result = updateSubscriptionUseCase.execute(subscription);
        assertEquals(subscription, result);
        verify(subscriptionRepository, times(1)).update(subscription);
    }
}
