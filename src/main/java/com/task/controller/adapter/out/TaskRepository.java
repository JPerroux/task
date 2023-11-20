package com.task.controller.adapter.out;

import com.task.controller.adapter.out.TaskEntity;
import com.task.controller.common.UserType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends MongoRepository<TaskEntity, UUID> {

    @Query("{'ownerUuid': :#{#ownerUuid}, 'createdAt': {$gte: :#{#startDate}, $lte: :#{#endDate} }}")
    List<TaskEntity> findByOwner(String ownerUuid, LocalDateTime startDate, LocalDateTime endDate);

    @Query("{'assigneeUuid': {?0}, 'createdAt': {$gte: ?1, $lte:?2 }}")
    List<TaskEntity> findByAssignee(String assigneeUuid, LocalDateTime startDate, LocalDateTime endDate);
}