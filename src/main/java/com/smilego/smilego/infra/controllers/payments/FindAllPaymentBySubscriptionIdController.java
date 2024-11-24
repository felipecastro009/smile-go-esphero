package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentBySubscriptionIdUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class FindAllPaymentBySubscriptionIdController {
    private final FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/payments")
    public List<PaymentResponse> handle(@PathVariable Long id) {
        return findAllPaymentBySubscriptionIdUseCase.execute(id).stream().map(PaymentDTOMapper::toResponse).toList();
    }
}
