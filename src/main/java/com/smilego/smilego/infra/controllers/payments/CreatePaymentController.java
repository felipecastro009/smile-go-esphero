package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.CreatePaymentUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.CreatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class CreatePaymentController {
    private final CreatePaymentUseCase createPaymentUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse handle(@RequestBody CreatePaymentRequest request) {
        return PaymentDTOMapper.toResponse(createPaymentUseCase.execute(PaymentDTOMapper.createPaymentToDomain(request)));
    }
}