package com.smilego.smilego.infra.database.repositories;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.database.entities.SubscriptionEntity;
import com.smilego.smilego.infra.database.mappers.SubscriptionMapper;
import com.smilego.smilego.infra.database.persistence.SubscriptionPersistence;

import java.util.List;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final SubscriptionPersistence subscriptionPersistence;

    public SubscriptionRepositoryImpl(SubscriptionPersistence subscriptionPersistence) {
        this.subscriptionPersistence = subscriptionPersistence;
    }

    @Override
    public Subscription create(Subscription subscription) {
        SubscriptionEntity entity = SubscriptionMapper.toEntity(subscription);
        return SubscriptionMapper.toDomain(subscriptionPersistence.save(entity));
    }

    @Override
    public Subscription update(Subscription subscription) {
        SubscriptionEntity entity = SubscriptionMapper.toEntity(subscription);
        return SubscriptionMapper.toDomain(subscriptionPersistence.save(entity));
    }

    @Override
    public List<Subscription> find() {
        return subscriptionPersistence.findAll().stream().map(SubscriptionMapper::toDomain).toList();
    }

    @Override
    public Void delete(Long id) {
        subscriptionPersistence.deleteById(id);
        return null;
    }
}
