package com.task.controller.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("task")
public class TaskEntity {

    @Id
    private UUID uuid;
    private AtomicInteger index;
    private String title;
    private String description;
    private String ownerUuid;
    private String assigneeUuid;
    private LocalDateTime createdAt;
    private String status;
}