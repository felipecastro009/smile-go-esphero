package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CreateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription execute(Subscription subscription) {
        return subscriptionRepository.create(subscription);
    }
}
