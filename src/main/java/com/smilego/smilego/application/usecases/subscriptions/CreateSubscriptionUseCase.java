package com.smilego.smilego.application.usecases.subscriptions;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CreateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;
    private final CacheAdapter<List<Subscription>> cacheAdapter;
    private final CacheAdapter<Report> cacheReportAdapter;

    public Subscription execute(Subscription subscription) {
        Subscription result = subscriptionRepository.create(subscription);
        cacheAdapter.evict("subscriptions", "all");
        cacheReportAdapter.clear("report");
        return result;
    }
}
