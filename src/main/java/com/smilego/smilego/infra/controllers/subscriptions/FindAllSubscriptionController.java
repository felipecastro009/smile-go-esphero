package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.FindAllSubscriptionUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.SubscriptionDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
@Tag(name = "Assinaturas")
public class FindAllSubscriptionController {
    private final FindAllSubscriptionUseCase findAllSubscriptionUseCase;

    @Tag(name = "Busca todas as assinaturas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionResponse> handle() {
        return findAllSubscriptionUseCase.execute().stream().map(SubscriptionDTOMapper::toResponse).toList();
    }
}
