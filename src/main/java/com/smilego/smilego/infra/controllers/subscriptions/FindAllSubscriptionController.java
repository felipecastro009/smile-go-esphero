package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.SubscriptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class FindAllSubscriptionController {
    private final FindAllSubscriptionUseCase findAllSubscriptionUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionResponse> handle() {
        return findAllSubscriptionUseCase.execute().stream().map(SubscriptionDTOMapper::toResponse).toList();
    }
}
