package com.management.library.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.library.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("SELECT b FROM Book b WHERE CONCAT(b.bookTitle, ' ', b.bookAuthor, ' ', b.ISBN, ' ',  b.bookId) LIKE %?1%")
	public List<Book> searchByBookNameOrBookAuthor( String keyword);

}
