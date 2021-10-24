package com.apress.todo.actuator;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="todo-stats")
public class ToDoStatsEndpoint {
    private ToDoRepository toDoRepository;
    ToDoStatsEndpoint(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
    @ReadOperation
    public Stats stats() {
        return new Stats(this.toDoRepository.count(),this.toDoRepository.countByCompleted(true));
    }
    @ReadOperation
    public ToDo getToDo(@Selector String id) {
        return this.toDoRepository.findById(id).orElse(null);
    }
    @WriteOperation
    public Operation completeToDo(@Selector String id) {
        ToDo toDo = this.toDoRepository.findById(id).orElse(null);
        if(null != toDo){
            toDo.setCompleted(true);
            this.toDoRepository.save(toDo);
            return new Operation("COMPLETED",true);
        }
        return new Operation("COMPLETED",false);
    }
    @DeleteOperation
    public Operation removeToDo(@Selector String id) {
        try {
            this.toDoRepository.deleteById(id);
            return new Operation("DELETED",true);
        }catch(Exception ex){
            return new Operation("DELETED",false);
        }
    }
    @Data
    public class Stats {
        private long count;
        private long completed;
        
		public Stats(long count, long completed) {
			super();
			this.count = count;
			this.completed = completed;
		}
		public long getCount() {
			return count;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public long getCompleted() {
			return completed;
		}
		public void setCompleted(long completed) {
			this.completed = completed;
		}
        
    }
    @Data
    public class Operation{
        private String name;
        private boolean successful;
		public Operation(String name, boolean successful) {
			super();
			this.name = name;
			this.successful = successful;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isSuccessful() {
			return successful;
		}
		public void setSuccessful(boolean successful) {
			this.successful = successful;
		}
        
    }
}
