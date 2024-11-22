package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public void execute(Long id) {
        subscriptionRepository.delete(id);
    }
}
