package com.smilego.smilego.infra.gateways.payment;

import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.domain.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * poderia utilizar o WireMock, mas preferi uma abordagem mais simples e que cumpra o objetivo
 */

@Slf4j
@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {
    private final WebClient webClient;
    private final String baseUrl = "https://mock-api/payments";
    private final Boolean isMock = true;

    @Override
    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "fallbackPayment")
    public void createTransaction(Payment payment) {
        try {
            if (!isMock) {
                webClient.post()
                        .uri(baseUrl)
                        .bodyValue(payment)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "fallbackPayment")
    public void updateTransaction(Payment payment) {
        String url = baseUrl + "/" + payment.getId();
        try {
            if (!isMock) {
                webClient.put()
                        .uri(url)
                        .bodyValue(payment)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fallbackPayment(Throwable throwable) {
        log.error("paymentServiceCircuitBreaker: {}", throwable.getMessage());
    }
}
