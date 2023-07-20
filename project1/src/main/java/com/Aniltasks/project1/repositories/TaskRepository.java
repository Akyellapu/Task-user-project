package com.Aniltasks.project1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Aniltasks.project1.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	

	List<Task> findAllByUsersId(long userId);

}
