package com.smilego.smilego.unit.infra.database.repositories;

import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.database.entities.SubscriptionEntity;
import com.smilego.smilego.infra.database.mappers.SubscriptionMapper;
import com.smilego.smilego.infra.database.persistence.SubscriptionPersistence;
import com.smilego.smilego.infra.database.repositories.SubscriptionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionRepositoryImplTest {
    @InjectMocks
    private SubscriptionRepositoryImpl subscriptionRepository;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;

    @BeforeEach
    void setUp() {
        subscriptionRepository = new SubscriptionRepositoryImpl(subscriptionPersistence);
        Mockito.reset(subscriptionPersistence);
    }

    @Test
    void shouldFindAllByStatusWithPaymentsWithNoSubscriptions() {
        when(subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE)).thenReturn(new ArrayList<>());
        List<Subscription> subscriptions = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);

        verify(subscriptionPersistence).findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);
        assertEquals(0, subscriptions.size());
    }

    @Test
    void shouldFindAllByStatusWithPaymentsWithSubscriptions() {
        SubscriptionEntity entity1 = new SubscriptionEntity();
        entity1.setId(1L);
        entity1.setStatus(SubscriptionStatusEnum.ACTIVE);
        entity1.setPlan(SubscriptionPlanEnum.BASIC);
        List<SubscriptionEntity> entities = List.of(entity1);
        when(subscriptionPersistence.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE))
                .thenReturn(entities);
        List<Subscription> result = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);
        Subscription expectedSubscription = new Subscription(
                1L,
                null,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                null,
                null,
                null,
                null
        );
        assertEquals(1, result.size());
        Subscription resultSubscription = result.get(0);
        assertEquals(expectedSubscription.getId(), resultSubscription.getId());
        assertEquals(expectedSubscription.getStatus(), resultSubscription.getStatus());
        assertEquals(expectedSubscription.getPlan(), resultSubscription.getPlan());
        verify(subscriptionPersistence).findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);
    }

    @Test
    void shouldFindById() {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setId(1L);
        entity.setStatus(SubscriptionStatusEnum.ACTIVE);
        entity.setPlan(SubscriptionPlanEnum.BASIC);
        when(subscriptionPersistence.findById(1L)).thenReturn(Optional.of(entity));
        Subscription result = subscriptionRepository.findById(1L);
        Subscription expectedSubscription = new Subscription(
                1L,
                null,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                null,
                null,
                null,
                null
        );
        assertEquals(result.getId(), expectedSubscription.getId());
        assertEquals(result.getStatus(), expectedSubscription.getStatus());
        assertEquals(result.getPlan(), expectedSubscription.getPlan());
        verify(subscriptionPersistence).findById(1L);
    }

    @Test
    void shouldFindByIdIfSubscriptionDoesNotExist() {
        when(subscriptionPersistence.findById(1L)).thenReturn(Optional.empty());
        Subscription result = subscriptionRepository.findById(1L);
        assertEquals(null, result);
        verify(subscriptionPersistence).findById(1L);
    }

    @Test
    void shouldDeleteById() {
        subscriptionRepository.delete(1L);
        verify(subscriptionPersistence).deleteById(1L);
    }

    @Test
    void shouldFindSubscriptions() {
        when(subscriptionPersistence.findAll()).thenReturn(new ArrayList<>());
        List<Subscription> result = subscriptionRepository.find();
        assertEquals(0, result.size());
        verify(subscriptionPersistence).findAll();
    }

    @Test
    void shouldUpdateSubscription() {
        Subscription subscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        SubscriptionEntity entity = SubscriptionMapper.toEntity(subscription);
        when(subscriptionPersistence.save(any(SubscriptionEntity.class))).thenReturn(entity);
        Subscription updatedSubscription = subscriptionRepository.update(subscription);
        verify(subscriptionPersistence).save(any(SubscriptionEntity.class));
        assertEquals(subscription.getId(), updatedSubscription.getId());
        assertEquals(subscription.getPlan(), updatedSubscription.getPlan());
        assertEquals(subscription.getStatus(), updatedSubscription.getStatus());
        assertEquals(subscription.getId(), updatedSubscription.getId());
        assertEquals(subscription.getPlan(), updatedSubscription.getPlan());
        assertEquals(subscription.getStatus(), updatedSubscription.getStatus());
    }

    @Test
    void shouldCreateSubscription() {
        Subscription subscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        SubscriptionEntity entity = SubscriptionMapper.toEntity(subscription);
        when(subscriptionPersistence.save(any(SubscriptionEntity.class))).thenReturn(entity);
        Subscription createdSubscription = subscriptionRepository.create(subscription);
        verify(subscriptionPersistence).save(any(SubscriptionEntity.class));
        assertEquals(subscription.getId(), createdSubscription.getId());
        assertEquals(subscription.getPlan(), createdSubscription.getPlan());
        assertEquals(subscription.getStatus(), createdSubscription.getStatus());
        assertEquals(subscription.getId(), createdSubscription.getId());
        assertEquals(subscription.getPlan(), createdSubscription.getPlan());
        assertEquals(subscription.getStatus(), createdSubscription.getStatus());
    }
}
