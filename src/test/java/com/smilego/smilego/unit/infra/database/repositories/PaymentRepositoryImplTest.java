package com.smilego.smilego.unit.infra.database.repositories;

import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.database.entities.PaymentEntity;
import com.smilego.smilego.infra.database.mappers.PaymentMapper;
import com.smilego.smilego.infra.database.persistence.PaymentPersistence;
import com.smilego.smilego.infra.database.repositories.PaymentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryImplTest {
    @InjectMocks
    private PaymentRepositoryImpl subscriptionRepository;

    @Mock
    private PaymentPersistence paymentPersistence;

    @BeforeEach
    void setUp() {
        subscriptionRepository = new PaymentRepositoryImpl(paymentPersistence);
        Mockito.reset(paymentPersistence);
    }

    @Test
    void shouldDeleteById() {
        subscriptionRepository.delete(1L);
        verify(paymentPersistence).deleteById(1L);
    }

    @Test
    void shouldFindBySubscriptionId() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        when(paymentPersistence.findAllBySubscriptionId(1L)).thenReturn(Collections.singletonList(paymentEntity));
        List<Payment> result = subscriptionRepository.findBySubscriptionId(1L);
        verify(paymentPersistence).findAllBySubscriptionId(1L);
        assertEquals(1, result.size());
        assertEquals(payment.getId(), result.get(0).getId());
    }

    @Test
    void shouldFindAll() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        when(paymentPersistence.findAll()).thenReturn(Collections.singletonList(paymentEntity));
        List<Payment> result = subscriptionRepository.find();
        verify(paymentPersistence).findAll();
        assertEquals(1, result.size());
        assertEquals(payment.getId(), result.get(0).getId());
    }

    @Test
    void shouldCreatePayment() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        when(paymentPersistence.save(any(PaymentEntity.class))).thenReturn(paymentEntity);
        Payment createdPayment = subscriptionRepository.create(payment);
        verify(paymentPersistence).save(any(PaymentEntity.class));
        assertEquals(payment.getId(), createdPayment.getId());
    }

    @Test
    void shouldUpdatePayment() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        PaymentEntity paymentEntity = PaymentMapper.toEntity(payment);
        when(paymentPersistence.save(any(PaymentEntity.class))).thenReturn(paymentEntity);
        Payment updatedPayment = subscriptionRepository.update(payment);
        verify(paymentPersistence).save(any(PaymentEntity.class));
        assertEquals(payment.getId(), updatedPayment.getId());
    }
}
