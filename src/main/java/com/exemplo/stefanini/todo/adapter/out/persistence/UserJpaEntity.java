package com.exemplo.stefanini.todo.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserJpaEntity extends BaseJpaEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
}
