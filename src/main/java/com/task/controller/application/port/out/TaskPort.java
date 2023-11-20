package com.task.controller.application.port.out;

import com.task.controller.adapter.out.TaskEntity;
import com.task.controller.common.Status;
import com.task.controller.common.UserType;
import com.task.controller.domain.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskPort {

    Task getTask(UUID uuid);
    List<Task> getTasks();
    void saveTask(Task fromDtoToDomain);
    void updateTask(String uuid, Task fromDtoToDomain);
    List<Task> getTasks(UserType userType, String ownerUuid, LocalDateTime startDate, LocalDateTime endDate);
    void changeAssignee(String uuid, String assigneeUuid);
    void deleteTask(String uuid);
    void changeOwner(String uuid, String ownerUuid);
    void changeStatus(String uuid, Status status);
}