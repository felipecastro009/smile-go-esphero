package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.UpdateSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.UpdateSubscriptionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class UpdateSubscriptionController {
    private final UpdateSubscriptionUseCase updateSubscriptionUseCase;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handle(@PathVariable Long id, @RequestBody UpdateSubscriptionRequest request) {
        updateSubscriptionUseCase.execute(SubscriptionDTOMapper.updateSubscriptionToDomain(id, request));
    }
}
