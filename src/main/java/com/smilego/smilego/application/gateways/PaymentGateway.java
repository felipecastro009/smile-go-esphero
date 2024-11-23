package com.smilego.smilego.application.gateways;

import com.smilego.smilego.domain.Payment;

public interface PaymentGateway {
    void createTransaction(Payment payment);
    void updateTransaction(Payment payment);
}
