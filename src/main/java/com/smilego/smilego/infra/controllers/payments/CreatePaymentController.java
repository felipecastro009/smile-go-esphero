package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.CreatePaymentUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.CreatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
@Tag(name = "Pagamentos")
public class CreatePaymentController {
    private final CreatePaymentUseCase createPaymentUseCase;

    @Operation(summary = "Cria um pagamento", description = "Cria um pagamento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse handle(@RequestBody CreatePaymentRequest request) {
        return PaymentDTOMapper.toResponse(createPaymentUseCase.execute(PaymentDTOMapper.createPaymentToDomain(request)));
    }
}
