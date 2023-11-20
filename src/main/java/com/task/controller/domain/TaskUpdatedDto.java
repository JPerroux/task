package com.task.controller.domain;

import com.task.controller.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdatedDto {

    private String title;
    private String description;
    private String ownerUuid;
    private String assigneeUuid;
    private Status status;
}