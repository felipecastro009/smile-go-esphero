package com.smilego.smilego.application.repositories;

import com.smilego.smilego.domain.Payment;

import java.util.List;

public interface PaymentRepository {
    Payment create(Payment payment);
    Payment update(Payment payment);
    List<Payment> find();
    void delete(Long id);
    List<Payment> findBySubscriptionId(Long id);
}
