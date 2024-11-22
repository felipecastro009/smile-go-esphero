package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentBySubscriptionIdUseCase;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class FindAllPaymentBySubscriptionIdController {
    private final FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase;

    public List<Payment> handle(Long id) {
        return findAllPaymentBySubscriptionIdUseCase.execute(id);
    }
}
