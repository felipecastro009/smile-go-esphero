package com.smilego.smilego.unit.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.DeleteSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.subscriptions.DeleteSubscriptionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteSubscriptionControllerTest {
    @Mock
    private DeleteSubscriptionUseCase deleteSubscriptionUseCase;

    @InjectMocks
    private DeleteSubscriptionController deleteSubscriptionController;

    @Test
    void shouldDeleteSubscriptionSuccessfully() {
        Long subscriptionId = 1L;
        doNothing().when(deleteSubscriptionUseCase).execute(subscriptionId);
        deleteSubscriptionController.handle(subscriptionId);
        verify(deleteSubscriptionUseCase, times(1)).execute(subscriptionId);
    }
}
