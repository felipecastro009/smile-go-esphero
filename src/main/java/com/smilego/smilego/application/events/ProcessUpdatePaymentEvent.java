package com.smilego.smilego.application.events;

import com.smilego.smilego.domain.Payment;

public interface ProcessUpdatePaymentEvent {
    public void process(Payment payment);
}
