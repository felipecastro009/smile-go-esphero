package com.smilego.smilego.infra.database.persistence;

import com.smilego.smilego.infra.database.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentPersistence extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAllBySubscriptionId(Long subscriptionId);
}
