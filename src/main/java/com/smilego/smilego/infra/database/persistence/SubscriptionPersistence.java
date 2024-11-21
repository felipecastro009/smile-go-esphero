package com.smilego.smilego.infra.database.persistence;

import com.smilego.smilego.infra.database.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPersistence extends JpaRepository<SubscriptionEntity, Long> {}
