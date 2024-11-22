package com.smilego.smilego.application.repositories;

import com.smilego.smilego.domain.Payment;

import java.util.List;

public interface PaymentRepository {
    public Payment create(Payment payment);
    public Payment update(Payment payment);
    public List<Payment> find();
    public void delete(Long id);
    public List<Payment> findBySubscriptionId(Long id);
}
