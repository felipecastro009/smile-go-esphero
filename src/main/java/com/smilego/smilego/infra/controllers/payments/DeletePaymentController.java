package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.DeletePaymentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class DeletePaymentController {
    private final DeletePaymentUseCase deletePaymentUseCase;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handle(@PathVariable Long id) {
        deletePaymentUseCase.execute(id);
    }
}
