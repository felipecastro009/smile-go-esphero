package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentBySubscriptionIdUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
@Tag(name = "Assinaturas")
public class FindAllPaymentBySubscriptionIdController {
    private final FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase;

    @Operation(summary = "Retorna todos os pagamentos de uma assinatura", description = "Retorna todos os pagamentos de uma assinatura")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/payments")
    public List<PaymentResponse> handle(@PathVariable Long id) {
        return findAllPaymentBySubscriptionIdUseCase.execute(id).stream().map(PaymentDTOMapper::toResponse).toList();
    }
}
