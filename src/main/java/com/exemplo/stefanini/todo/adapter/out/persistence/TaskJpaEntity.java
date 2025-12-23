package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.domain.model.TaskStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TaskJpaEntity extends BaseJpaEntity {

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;
}
