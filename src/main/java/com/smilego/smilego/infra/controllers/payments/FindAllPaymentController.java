package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class FindAllPaymentController {
    private final FindAllPaymentUseCase findAllPaymentUseCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PaymentResponse> handle() {
        return findAllPaymentUseCase.execute().stream().map(PaymentDTOMapper::toResponse).toList();
    }
}
