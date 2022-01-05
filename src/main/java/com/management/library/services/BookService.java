package com.management.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.library.entities.Book;
import com.management.library.repositories.BookRepository;



@Service
public class BookService {
	
	@Autowired
    private BookRepository repository;
	
	public void saveBook(Book book) {
		repository.save(book);	
	}
	
	public List<Book> viewAllBooks(){
		return repository.findAll();
	}
	
	public Book getBook(int id) {
		Optional<Book> book = repository.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		return null;
	}
	
	public void deleteBook(int id) {
	 repository.deleteById(id);
	}


	public List<Book> search(String keyword) {
		if (keyword != null) {
	return repository.searchByBookNameOrBookAuthor(keyword);	
	}
	return repository.findAll();
	}

	public Book viewBook(int id) {	
		Optional<Book> book = repository.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		return null;
	}

}
