package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;
}
