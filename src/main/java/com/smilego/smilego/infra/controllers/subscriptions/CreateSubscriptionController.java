package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.CreateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.SubscriptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class CreateSubscriptionController {
    private final CreateSubscriptionUseCase createSubscriptionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse handle(@RequestBody CreateSubscriptionRequest request) {
        return SubscriptionDTOMapper.toResponse(createSubscriptionUseCase.execute(SubscriptionDTOMapper.createSubscriptionToDomain(request)));
    }
}