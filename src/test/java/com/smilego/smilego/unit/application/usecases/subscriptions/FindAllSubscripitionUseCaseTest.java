package com.smilego.smilego.unit.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
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
class FindAllSubscriptionUseCaseTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private FindAllSubscriptionUseCase findAllSubscriptionUseCase;

    @Test
    void testExecute() {
        // Mock data
        Subscription subscription1 = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                List.of(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Subscription subscription2 = new Subscription(
                2L,
                1L,
                SubscriptionPlanEnum.PREMIUM,
                SubscriptionStatusEnum.INACTIVE,
                List.of(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        List<Subscription> mockDatabase = List.of(subscription1, subscription2);
        when(subscriptionRepository.find()).thenReturn(mockDatabase);
        List<Subscription> result = findAllSubscriptionUseCase.execute();
        assertEquals(mockDatabase, result);
        verify(subscriptionRepository, times(1)).find();
    }
}
