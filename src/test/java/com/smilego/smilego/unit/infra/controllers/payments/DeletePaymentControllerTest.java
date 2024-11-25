package com.smilego.smilego.unit.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.DeletePaymentUseCase;
import com.smilego.smilego.infra.controllers.payments.DeletePaymentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePaymentControllerTest {
    @Mock
    private DeletePaymentUseCase deletePaymentUseCase;

    @InjectMocks
    private DeletePaymentController deletePaymentController;

    @Test
    void shouldDeleteSubscriptionSuccessfully() {
        Long paymentId = 1L;
        doNothing().when(deletePaymentUseCase).execute(paymentId);
        deletePaymentController.handle(paymentId);
        verify(deletePaymentUseCase, times(1)).execute(paymentId);
    }
}
