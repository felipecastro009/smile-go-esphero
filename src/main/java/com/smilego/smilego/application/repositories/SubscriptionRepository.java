package com.smilego.smilego.application.repositories;

import com.smilego.smilego.domain.Subscription;

import java.util.List;

public interface SubscriptionRepository {
    public Subscription create(Subscription subscription);
    public Subscription update(Subscription subscription);
    public List<Subscription> find();
    public void delete(Long id);
}
