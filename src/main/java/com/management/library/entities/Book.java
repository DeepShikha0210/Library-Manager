package com.management.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer bookId;
	private String ISBN;
	private String bookTitle;
	private String bookAuthor;
	private int numberCopies;
	private Boolean available = true;
	

}
