package com.task.controller.adapter.in;

import com.task.controller.application.port.in.TaskUseCase;
import com.task.controller.common.Status;
import com.task.controller.common.UserType;
import com.task.controller.domain.TaskDto;
import com.task.controller.domain.TaskViewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(path = "task")
public class TaskController {

    private final TaskUseCase taskUseCase;

    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @PostMapping
    ResponseEntity<HttpStatus> saveTask(@RequestBody TaskDto taskDto) {

        log.info("Controller- Start method to create a new task {}", taskDto);
        this.taskUseCase.saveTask(taskDto);
        log.info("Controller- Finish method to create a new task {}", taskDto);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping(path = "{uuid}")
    ResponseEntity<HttpStatus> updateTask(@PathVariable(name = "uuid") String uuid, @RequestBody TaskDto taskDto) {

        log.info("Controller- Start method to update a new task {} with id {}", taskDto, uuid);
        this.taskUseCase.updateTask(uuid, taskDto);
        log.info("Controller- Finish method to update a new task {} with id {}", taskDto, uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "{uuid}")
    ResponseEntity<TaskDto> getTask(@PathVariable(name = "uuid") String uuid) {

        log.info("Controller- Start method to retrieve a task with id {}", uuid);
        TaskDto taskDto =  this.taskUseCase.getTask(UUID.fromString(uuid));
        log.info("Controller- Start method to retrieve a task {} with id {}", taskDto, uuid);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping
    ResponseEntity<List<TaskViewDto>> getTasks() {

        log.info("Controller- Start method to retrieve a list of tasks ");
        List<TaskViewDto> tasks = this.taskUseCase.getTasks();
        log.info("Controller- Start method to retrieve a list of tasks");
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "/owner/{ownerUuid}")
    ResponseEntity<List<TaskViewDto>> getTasksByOwner(@PathVariable(name = "ownerUuid") String ownerUuid,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {

        log.info("Controller- Start method to retrieve a list of tasks with owner {}", ownerUuid);
        List<TaskViewDto> tasks = this.taskUseCase.getTasks(UserType.OWNER, ownerUuid, startDate, endDate);
        log.info("Controller- Start method to retrieve a list of tasks with owner {}", ownerUuid);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "/assignee/{assigneeUuid}")
    ResponseEntity<List<TaskViewDto>> getTasksByAssignee(@PathVariable(name = "assigneeUuid") String assigneeUuid,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:SS") String startDateString,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:SS") String endDateString) {

        log.info("Controller- Start method to retrieve a list of tasks with assignee {}",assigneeUuid);
        LocalDateTime startDate = LocalDateTime.parse(startDateString);
        LocalDateTime endDate = LocalDateTime.parse(endDateString);
        List<TaskViewDto> tasks = this.taskUseCase.getTasks(UserType.ASSIGNEE, assigneeUuid, startDate, endDate);
        log.info("Controller- Start method to retrieve a list of tasks with assignee {}", assigneeUuid);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping(path = "/assignee/{uuid}")
    ResponseEntity<HttpStatus> changeAssignee(@PathVariable(name = "uuid") String uuid,
                                              @RequestParam String assigneeUuid) {

        log.info("Controller- Start method to change an assignee {} to a task {}", assigneeUuid, uuid);
        this.taskUseCase.changeAssignee(uuid, assigneeUuid);
        log.info("Controller- Finish method to change an assignee {} to a task {}",assigneeUuid, uuid);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/owner/{uuid}")
    ResponseEntity<HttpStatus> changeOwner(@PathVariable(name = "uuid") String uuid,
                                              @RequestParam String ownerUuid) {

        log.info("Controller- Start method to change an owner {} to a task {}", ownerUuid, uuid);
        this.taskUseCase.changeOwner(uuid, ownerUuid);
        log.info("Controller- Finish method to change an owner {} to a task {}", ownerUuid, uuid);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/status/{uuid}")
    ResponseEntity<HttpStatus> changeStatus(@PathVariable(name = "uuid") String uuid,
                                           @RequestParam Status status) {

        log.info("Controller- Start method to change the status {} to a task with id {}", status, uuid);
        this.taskUseCase.changeStatus(uuid, status);
        log.info("Controller- Finish method to change the status {} to a task with id{}", status, uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{uuid}")
    ResponseEntity<HttpStatus> deleteTask(@PathVariable(name = "uuid") String uuid) {

        log.info("Controller- Start method to delete a task with id {}", uuid);
        this.taskUseCase.deleteTask(uuid);
        log.info("Controller- Finish method to delete a task with id {}", uuid);
        return ResponseEntity.ok().build();
    }
}