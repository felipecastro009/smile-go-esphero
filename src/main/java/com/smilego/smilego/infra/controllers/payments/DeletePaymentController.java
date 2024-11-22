package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.DeletePaymentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class DeletePaymentController {
    private final DeletePaymentUseCase deletePaymentUseCase;

    @DeleteMapping("/{id}")
    public void handle(@PathVariable Long id) {
        deletePaymentUseCase.execute(id);
    }
}
