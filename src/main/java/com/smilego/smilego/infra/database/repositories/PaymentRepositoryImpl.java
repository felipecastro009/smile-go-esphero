package com.smilego.smilego.infra.database.repositories;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.infra.database.entities.PaymentEntity;
import com.smilego.smilego.infra.database.mappers.PaymentMapper;
import com.smilego.smilego.infra.database.persistence.PaymentPersistence;

import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentPersistence paymentPersistence;

    public PaymentRepositoryImpl(PaymentPersistence paymentPersistence) {
        this.paymentPersistence = paymentPersistence;
    }

    @Override
    public Payment create(Payment payment) {
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        return PaymentMapper.toDomain(paymentPersistence.save(paymentEntity));
    }

    @Override
    public Payment update(Payment payment) {
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        return PaymentMapper.toDomain(paymentPersistence.save(paymentEntity));
    }

    @Override
    public List<Payment> find() {
        return paymentPersistence.findAll().stream().map(PaymentMapper::toDomain).toList();
    }

    @Override
    public Void delete(Long id) {
        paymentPersistence.deleteById(id);
        return null;
    }
}
