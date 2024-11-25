package com.smilego.smilego.infra.database.entities;

import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "subscriptions")
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "plan")
    @Enumerated(EnumType.STRING)
    private SubscriptionPlanEnum plan;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SubscriptionStatusEnum status;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER)
    private List<PaymentEntity> payments;
}
