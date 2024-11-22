package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;
    public List<Subscription> execute() {
        return subscriptionRepository.find();
    }
}
