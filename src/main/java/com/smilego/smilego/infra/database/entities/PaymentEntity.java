package com.smilego.smilego.infra.database.entities;

import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "subscription_id")
    private Long subscriptionId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum method;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SubscriptionEntity subscription;
}
