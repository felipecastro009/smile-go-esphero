package com.smilego.smilego.main;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.DeleteSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.infra.database.persistence.SubscriptionPersistence;
import com.smilego.smilego.infra.database.repositories.SubscriptionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionConfig {

    @Bean
    public SubscriptionRepository subscriptionRepository(SubscriptionPersistence subscriptionPersistence) {
        return new SubscriptionRepositoryImpl(subscriptionPersistence);
    }

    @Bean
    public CreateSubscriptionUseCase createSubscriptionUseCase(SubscriptionRepository subscriptionRepository) {
        return new CreateSubscriptionUseCase(subscriptionRepository);
    }

    @Bean
    public FindAllSubscriptionUseCase findAllSubscriptionUseCase(SubscriptionRepository subscriptionRepository) {
        return new FindAllSubscriptionUseCase(subscriptionRepository);
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
