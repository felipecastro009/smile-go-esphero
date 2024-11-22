package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentUseCase;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class FindAllPaymentController {
    private final FindAllPaymentUseCase findAllPaymentUseCase;

    @GetMapping
    public List<Payment> handle() {
        return findAllPaymentUseCase.execute();
    }
}
