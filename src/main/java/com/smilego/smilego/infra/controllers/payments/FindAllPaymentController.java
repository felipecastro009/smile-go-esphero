package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pagamentos")
public class FindAllPaymentController {
    private final FindAllPaymentUseCase findAllPaymentUseCase;

    @Operation(summary = "Retorna todos os pagamentos", description = "Retorna todos os pagamentos")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PaymentResponse> handle() {
        return findAllPaymentUseCase.execute().stream().map(PaymentDTOMapper::toResponse).toList();
    }
}
