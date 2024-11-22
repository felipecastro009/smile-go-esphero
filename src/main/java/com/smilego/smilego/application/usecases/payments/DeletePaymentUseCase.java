package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public void execute(Long id) {
        paymentRepository.delete(id);
    }
}
