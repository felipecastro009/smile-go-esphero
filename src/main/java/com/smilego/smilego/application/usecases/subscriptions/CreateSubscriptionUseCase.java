package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public void execute(Subscription subscription) {
        subscriptionRepository.create(subscription);
    }
}
