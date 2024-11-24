package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class UpdateSubscriptionController {
    private final UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse handle(@PathVariable Long id, @RequestBody UpdateSubscriptionRequest request) {
        return SubscriptionDTOMapper.toResponse(updateSubscriptionUseCase.execute(SubscriptionDTOMapper.updateSubscriptionToDomain(id, request)));
    }
}
