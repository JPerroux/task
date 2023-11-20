package com.task.controller.adapter.out;

import com.task.controller.application.port.out.TaskPort;
import com.task.controller.common.Status;
import com.task.controller.common.TaskMapper;
import com.task.controller.common.UserType;
import com.task.controller.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class TaskAdapter implements TaskPort {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskAdapter(TaskRepository taskRepository, TaskMapper taskMapper) {

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task getTask(UUID uuid) {

        log.info("Adapter- Start method to retrieve a task related to id {}", uuid);
        return this.taskMapper.fromEntityToDomain(
                this.taskRepository.findById(uuid).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<Task> getTasks() {

        log.info("Adapter- Start method to retrieve a list tasks");
        return this.taskMapper.fromEntityToDomainList(this.taskRepository.findAll());
    }

    @Override
    public void saveTask(Task task) {

        log.info("Adapter- Start method to save a task {}", task);
        TaskEntity taskEntity = this.taskMapper.fromDomainToEntity(task);
        AtomicInteger index = new AtomicInteger(this.taskRepository.findAll().size());
        taskEntity.setIndex(index);
        taskEntity.setUuid(UUID.randomUUID());
        this.taskRepository.save(taskEntity);
        log.info("Adapter- Finish method to save a task {}", task);
    }

    @Override
    public void updateTask(String uuid, Task task) {

        log.info("Adapter- Start method to update a task {} with id {}", task, uuid);
        TaskEntity taskEntityFounded = this.taskRepository.findById(UUID.fromString(uuid)).orElseThrow(NotFoundException::new);
        TaskEntity taskEntityNew = taskMapper.fromDomainToEntity(task);
        taskEntityNew.setUuid(taskEntityFounded.getUuid());
        taskEntityNew.setStatus(Status.IN_PROCESS.name());
        this.taskRepository.save(taskEntityNew);
        log.info("Adapter- Finish method to save a task {} with id {}", task, uuid);
    }

    @Override
    public List<Task> getTasks(UserType userType, String ownerUuid, LocalDateTime startDate, LocalDateTime endDate) {

        log.info("Adapter- Start method to retrieve a list tasks for owner {}", ownerUuid);
        if (UserType.ASSIGNEE.equals(userType))
            return taskMapper.fromEntityToDomainList(this.taskRepository.findByAssignee(ownerUuid, startDate, endDate));
        return taskMapper.fromEntityToDomainList(this.taskRepository.findByOwner(ownerUuid, startDate, endDate));
    }

    @Override
    public void deleteTask(String uuid) {

        log.info("Adapter- Start method to delete a task with uuid {}", uuid);
        this.taskRepository.deleteById(UUID.fromString(uuid));
    }

    @Override
    public void changeOwner(String uuid, String ownerUuid) {

        log.info("Adapter- Start method to change a task owner {} ", uuid);
        TaskEntity taskEntityFounded = this.taskRepository.findById(UUID.fromString(uuid)).orElseThrow(NotFoundException::new);
        taskEntityFounded.setOwnerUuid(ownerUuid);
        this.taskRepository.save(taskEntityFounded);
        log.info("Adapter- Finish method to save a task owner {}",uuid);
    }

    @Override
    public void changeAssignee(String uuid, String assigneeUuid) {

        log.info("Adapter- Start method to change a task assignee {} ", uuid);
        TaskEntity taskEntityFounded = this.taskRepository.findById(UUID.fromString(uuid)).orElseThrow(NotFoundException::new);
        taskEntityFounded.setAssigneeUuid(assigneeUuid);
        this.taskRepository.save(taskEntityFounded);
        log.info("Adapter- Finish method to save a task assignee {}",uuid);
    }

    @Override
    public void changeStatus(String uuid, Status status) {

        log.info("Adapter- Start method to change a task status {} ", status);
        TaskEntity taskEntityFounded = this.taskRepository.findById(UUID.fromString(uuid)).orElseThrow(NotFoundException::new);
        taskEntityFounded.setStatus(status.name());
        this.taskRepository.save(taskEntityFounded);
        log.info("Adapter- Finish method to save a task status {}", status);
    }
}
