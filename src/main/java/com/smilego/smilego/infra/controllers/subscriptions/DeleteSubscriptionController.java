package com.smilego.smilego.infra.controllers.subscriptions;

import com.smilego.smilego.application.usecases.subscriptions.DeleteSubscriptionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
@Tag(name = "Assinaturas")
public class DeleteSubscriptionController {
    private final DeleteSubscriptionUseCase deleteSubscriptionUseCase;

    @Operation(summary = "Deleta uma assinatura", description = "Deleta uma assinatura")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handle(@PathVariable Long id) {
        deleteSubscriptionUseCase.execute(id);
    }
}
