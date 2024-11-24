package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.CreateSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.CreateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
@Tag(name = "Assinaturas")
public class CreateSubscriptionController {
    private final CreateSubscriptionUseCase createSubscriptionUseCase;

    @Operation(summary = "Cria uma assinatura", description = "Cria uma assinatura")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse handle(@RequestBody CreateSubscriptionRequest request) {
        return SubscriptionDTOMapper.toResponse(createSubscriptionUseCase.execute(SubscriptionDTOMapper.createSubscriptionToDomain(request)));
    }
}
