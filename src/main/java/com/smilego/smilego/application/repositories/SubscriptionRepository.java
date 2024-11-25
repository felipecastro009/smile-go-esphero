package com.smilego.smilego.application.repositories;

import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository {
    Subscription create(Subscription subscription);
    Subscription update(Subscription subscription);
    List<Subscription> find();
    void delete(Long id);
    List<Subscription> findAllByStatusWithPayments(SubscriptionStatusEnum status);
    List<Subscription> findAllByStatusWithPaymentsBetweenDate(LocalDateTime startDate, LocalDateTime endDate, SubscriptionStatusEnum status);
    Subscription findById(Long id);
}
