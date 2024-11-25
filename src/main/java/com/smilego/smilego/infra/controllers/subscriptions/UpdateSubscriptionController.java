package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdateSubscriptionRequest;
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
public class UpdateSubscriptionController {
    private final UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @Operation(summary = "Atualiza uma assinatura", description = "Atualiza uma assinatura")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse handle(@PathVariable Long id, @RequestBody UpdateSubscriptionRequest request) {
        return SubscriptionDTOMapper.toResponse(updateSubscriptionUseCase.execute(SubscriptionDTOMapper.updateSubscriptionToDomain(id, request)));
    }
}
