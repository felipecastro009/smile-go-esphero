package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSubscriptionUseCaseTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private DeleteSubscriptionUseCase deleteSubscriptionUseCase;

    @Test
    void testExecute() {
        Long subscriptionId = 1L;
        deleteSubscriptionUseCase.execute(subscriptionId);
        verify(subscriptionRepository, times(1)).delete(subscriptionId);
    }
}
