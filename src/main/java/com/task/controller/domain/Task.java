package com.task.controller.domain;

import com.task.controller.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private UUID uuid;
    private String title;
    private String description;
    private String ownerUuid;
    private String assigneeUuid;
    private LocalDateTime createdAt;
    private Status status;
}