package com.smilego.smilego.infra.database.persistence;

import com.smilego.smilego.infra.database.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPersistence extends JpaRepository<PaymentEntity, Long> {}
