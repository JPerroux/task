package com.task.controller.common;

import com.task.controller.adapter.out.TaskEntity;
import com.task.controller.domain.Task;
import com.task.controller.domain.TaskDto;
import com.task.controller.domain.TaskViewDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto fromDomainToDto(Task task);
    Task fromDtoToDomain(TaskDto taskDto);

    TaskEntity fromDomainToEntity(Task task);
    Task fromEntityToDomain(TaskEntity taskEntity);

    List<Task> fromEntityToDomainList(List<TaskEntity> taskEntities);
    List<TaskViewDto> fromDomainToDtoList(List<Task> tasks);
}