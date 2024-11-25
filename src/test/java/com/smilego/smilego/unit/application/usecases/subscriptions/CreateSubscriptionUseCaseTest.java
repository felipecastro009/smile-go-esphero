package com.smilego.smilego.unit.application.usecases.subscriptions;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSubscriptionUseCaseTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private CacheAdapter cacheAdapter;

    @InjectMocks
    private CreateSubscriptionUseCase createSubscriptionUseCase;

    @Test
    void testExecute() {
        Subscription subscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(subscriptionRepository.create(subscription)).thenReturn(subscription);
        Subscription result = createSubscriptionUseCase.execute(subscription);
        assertEquals(subscription, result);
        verify(subscriptionRepository, times(1)).create(subscription);
        verify(cacheAdapter, times(1)).evict("subscriptions", "all");
        verify(cacheAdapter, times(1)).clear("report");
    }
}
