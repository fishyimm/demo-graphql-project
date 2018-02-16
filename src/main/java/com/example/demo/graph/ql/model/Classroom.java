package com.example.demo.graph.ql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classroom {

	@Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Classroom(String title) {
		this.title = title;
	}

	public Classroom() {
		super();
	}

	public Classroom(Long id) {
        this.id = id;
    }
	
	@Override
	public String toString() {
		return "Classroom [id=" + id + ", title=" + title + "]";
	}
	
}
