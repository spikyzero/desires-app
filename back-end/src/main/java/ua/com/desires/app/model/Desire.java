package ua.com.desires.app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import ua.com.desires.app.model.util.Currency;
import ua.com.desires.app.model.util.Priority;

import java.math.BigDecimal;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_user_id_priority", columnList = "user_id, priority")
        }
)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Desire extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Column(length = 100)
    String description;

    @Column
    String siteURL;

    @Column
    String imageURL;

    @Column(nullable = false)
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_desire_user"))
    private User user;

}