package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription execute(Subscription subscription) {
        return subscriptionRepository.update(subscription);
    }
}
