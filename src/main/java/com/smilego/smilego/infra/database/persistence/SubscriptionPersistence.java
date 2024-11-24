package com.smilego.smilego.infra.database.persistence;

import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.database.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionPersistence extends JpaRepository<SubscriptionEntity, Long>
{
    @Query("SELECT s FROM subscriptions s LEFT JOIN FETCH s.payments WHERE s.status = :status")
    List<SubscriptionEntity> findAllByStatusWithPayments(SubscriptionStatusEnum status);
}
