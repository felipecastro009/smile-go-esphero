package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllPaymentUseCase {
    private final PaymentRepository paymentRepository;

    public List<Payment> execute() {
        return paymentRepository.find();
    }
}
