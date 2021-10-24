package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ToDoRepository extends CrudRepository<ToDo,String> {

	public long countByCompleted(boolean b);
	
}