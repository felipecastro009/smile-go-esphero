package com.smilego.smilego.infra.controllers.payments;

import com.smilego.smilego.application.events.SendUpdatePaymentEvent;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdatePaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class UpdatePaymentController {
    private final SendUpdatePaymentEvent sendUpdatePaymentEvent;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handle(@PathVariable Long id, @RequestBody UpdatePaymentRequest request) {
        sendUpdatePaymentEvent.send(PaymentDTOMapper.updatePaymentToDomain(id, request));
    }
}
