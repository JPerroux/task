package com.task.controller.application.port.in;

import com.task.controller.common.Status;
import com.task.controller.common.UserType;
import com.task.controller.domain.TaskDto;
import com.task.controller.domain.TaskViewDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskUseCase {

    void saveTask(TaskDto taskDto);
    TaskDto getTask(UUID uuid);
    List<TaskViewDto> getTasks();
    void updateTask(String uuid, TaskDto taskDto);
    List<TaskViewDto> getTasks(UserType userType, String ownerUuid, LocalDateTime startDate, LocalDateTime endDate);
    void changeAssignee(String uuid, String assigneeUuid);
    void deleteTask(String uuid);
    void changeOwner(String uuid, String ownerUuid);
    void changeStatus(String uuid, Status status);
}