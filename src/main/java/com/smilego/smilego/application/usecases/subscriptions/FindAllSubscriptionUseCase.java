package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;
    private final CacheAdapter<List<Subscription>> cacheAdapter;
    public List<Subscription> execute() {
        List<Subscription> cache = cacheAdapter.get("subscriptions", "all");
        if (cache != null) {
            return cache;
        }
        List<Subscription> database = subscriptionRepository.find();
        cacheAdapter.put("subscriptions", "all", database);
        return database;
    }
}
