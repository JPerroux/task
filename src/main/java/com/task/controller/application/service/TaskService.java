package com.task.controller.application.service;

import com.task.controller.application.port.in.TaskUseCase;
import com.task.controller.application.port.out.TaskPort;
import com.task.controller.common.Status;
import com.task.controller.common.TaskMapper;
import com.task.controller.common.UserType;
import com.task.controller.domain.Task;
import com.task.controller.domain.TaskDto;
import com.task.controller.domain.TaskViewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TaskService implements TaskUseCase {

    private final TaskPort taskPort;
    private final TaskMapper taskMapper;

    public TaskService(TaskPort taskPort, TaskMapper taskMapper) {

        this.taskPort = taskPort;
        this.taskMapper = taskMapper;
    }

    @Override
    public void saveTask(TaskDto taskDto) {

        log.info("Adapter- Start method to save a task {}", taskDto);
        Task task = this.taskMapper.fromDtoToDomain(taskDto);
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Status.TO_DO);
        this.taskPort.saveTask(task);
        log.info("Adapter- Finish method to save a task {}", taskDto);
    }

    @Override
    public TaskDto getTask(UUID uuid) {

        log.info("Adapter- Start method to retrieve a task with uuid {}", uuid);
        return this.taskMapper.fromDomainToDto(this.taskPort.getTask(uuid));
    }

    @Override
    public List<TaskViewDto> getTasks() {

        log.info("Adapter- Start method to retrieve a list of tasks");
        return this.taskMapper.fromDomainToDtoList(this.taskPort.getTasks());
    }

    @Override
    public void updateTask(String uuid, TaskDto taskDto) {

        log.info("Adapter- Start method to update a task {} with uuid {}", taskDto, uuid);
        this.taskPort.updateTask(uuid, taskMapper.fromDtoToDomain(taskDto));
        log.info("Adapter- Finish method to update a task {} with uuid {}", taskDto, uuid);
    }

    @Override
    public List<TaskViewDto> getTasks(UserType userType, String ownerUuid, LocalDateTime startDate, LocalDateTime endDate) {

        log.info("Adapter- Start method to retrieve a list of task by uuid {}", ownerUuid);
        return this.taskMapper.fromDomainToDtoList(this.taskPort.getTasks(userType, ownerUuid, startDate, endDate));
    }


    @Override
    public void changeAssignee(String uuid, String assigneeUuid) {

        log.info("Adapter- Start method to change a task assignee by uuid {}", uuid);
        this.taskPort.changeAssignee(uuid, assigneeUuid);
        log.info("Adapter- Finish method to change a task assignee by uuid {}", uuid);
    }

    @Override
    public void deleteTask(String uuid) {

        log.info("Adapter- Start method to delete a task with uuid {}", uuid);
        this.taskPort.deleteTask(uuid);
        log.info("Adapter- Finish method to delete a task with uuid {}", uuid);
    }

    @Override
    public void changeOwner(String uuid, String ownerUuid) {

        log.info("Adapter- Start method to change a task assignee by uuid {}", uuid);
        this.taskPort.changeOwner(uuid, ownerUuid);
        log.info("Adapter- Finish method to change a task assignee by uuid {}", uuid);
    }

    @Override
    public void changeStatus(String uuid, Status status) {

        log.info("Adapter- Start method to change a task assignee by uuid {}", uuid);
        this.taskPort.changeStatus(uuid, status);
        log.info("Adapter- Finish method to change a task assignee by uuid {}", uuid);
    }
}