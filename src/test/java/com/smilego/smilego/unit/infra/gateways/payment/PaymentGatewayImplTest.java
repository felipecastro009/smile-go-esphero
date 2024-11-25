package com.smilego.smilego.unit.infra.gateways.payment;

import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.gateways.payment.PaymentGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentGatewayImplTest {
    @Mock
    private WebClient webClient;

    @InjectMocks
    private PaymentGatewayImpl paymentGatewayImpl;

    private Payment payment;

    @BeforeEach
    public void setup() {
        payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void shouldCreateTransactionSuccessfully() {
        paymentGatewayImpl.createTransaction(payment);
        verify(webClient, times(0)).post();
    }

    @Test
    public void shouldUpdateTransactionSuccessfully() {
        paymentGatewayImpl.updateTransaction(payment);
        verify(webClient, times(0)).put();
    }
}
