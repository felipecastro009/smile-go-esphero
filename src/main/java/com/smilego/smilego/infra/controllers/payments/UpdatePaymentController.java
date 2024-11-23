package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.UpdatePaymentUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.UpdatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class UpdatePaymentController {
    private final UpdatePaymentUseCase updatePaymentUseCase;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse handle(@PathVariable Long id, @RequestBody UpdatePaymentRequest request) {
        return PaymentDTOMapper.toResponse(updatePaymentUseCase.execute(PaymentDTOMapper.updatePaymentToDomain(id, request)));
    }
}