package com.smilego.smilego.application.events;

import com.smilego.smilego.domain.Payment;

public interface SendUpdatePaymentEvent {
    void send(Payment payment);
}
