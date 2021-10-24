package com.apress.todo.domain;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity(name="TODO")
@Data
//@NoArgsConstructor
public class ToDo {
    @NotNull
    @Id
    	@GeneratedValue(generator = "system-uuid")
    	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotNull
    @NotBlank
    private String description;
    @Column(insertable = true, updatable = false)
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;
    private boolean completed;
    public ToDo(){
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modified = date;
    }
    public ToDo(String description){
        this.description = description;
    }
    
    @PrePersist
    void onCreate() {
    	this.setCreated(LocalDateTime.now());
    	this.setModified(LocalDateTime.now());
    }
    
    @PreUpdate
    	void onUpdate() {
    	this.setModified(LocalDateTime.now());
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}