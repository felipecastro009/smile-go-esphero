package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.DeletePaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
@Tag(name = "Pagamentos")
public class DeletePaymentController {
    private final DeletePaymentUseCase deletePaymentUseCase;

    @Operation(summary = "Deleta um pagamento", description = "Deleta um pagamento")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handle(@PathVariable Long id) {
        deletePaymentUseCase.execute(id);
    }
}
