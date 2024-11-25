package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletePaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private DeletePaymentUseCase deletePaymentUseCase;

    @Test
    void testExecute() {
        Long paymentId = 1L;
        deletePaymentUseCase.execute(paymentId);
        verify(paymentRepository, times(1)).delete(paymentId);
    }
}
