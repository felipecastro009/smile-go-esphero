package com.smilego.smilego.main;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.DeleteSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.database.persistence.SubscriptionPersistence;
import com.smilego.smilego.infra.database.repositories.SubscriptionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubscriptionConfig {

    @Bean
    public SubscriptionRepository subscriptionRepository(SubscriptionPersistence subscriptionPersistence) {
        return new SubscriptionRepositoryImpl(subscriptionPersistence);
    }

    @Bean
    public CreateSubscriptionUseCase createSubscriptionUseCase(
            SubscriptionRepository subscriptionRepository,
            CacheAdapter<List<Subscription>> cacheAdapter,
            CacheAdapter<Report> cacheReportAdapter
    ) {
        return new CreateSubscriptionUseCase(subscriptionRepository, cacheAdapter, cacheReportAdapter);
    }

    @Bean
    public FindAllSubscriptionUseCase findAllSubscriptionUseCase(
            SubscriptionRepository subscriptionRepository,
            CacheAdapter<List<Subscription>> cacheAdapter
    ) {
        return new FindAllSubscriptionUseCase(subscriptionRepository, cacheAdapter);
    }

    @Bean
    public UpdateSubscriptionUseCase updateSubscriptionUseCase(SubscriptionRepository subscriptionRepository) {
        return new UpdateSubscriptionUseCase(subscriptionRepository);
    }

    @Bean
    public DeleteSubscriptionUseCase deleteSubscriptionUseCase(SubscriptionRepository subscriptionRepository) {
        return new DeleteSubscriptionUseCase(subscriptionRepository);
    }
}
