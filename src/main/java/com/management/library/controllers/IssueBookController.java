package com.management.library.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.library.entities.Book;
import com.management.library.entities.IssuedBook;
import com.management.library.entities.User;
import com.management.library.repositories.IssuedBookRepository;
import com.management.library.services.BookService;
import com.management.library.services.UserService;

import io.swagger.annotations.Api;


@Controller
public class IssueBookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssuedBookRepository repository;
	
	@Autowired
	private UserService userService;

	@GetMapping("/issueBook/{id}")
	public String issueBook(@PathVariable int id, Model model) {
		
		model.addAttribute("issueBook",  new IssuedBook());
		model.addAttribute("BookId", id);

		return "issueBookForm";
	}
	
	@PostMapping("/issue")
	public String bookIssue(@ModelAttribute IssuedBook issuedBook, HttpSession session) {
		User user = userService.findUserById(issuedBook.getUserId());
		int numBooks = user.getNumBooksIssued();
		if(numBooks > 5) {
			session.setAttribute("msg", "Max books issued already!");	
			return "redirect:/home";
		}
		
		
		else {
			numBooks++;
			user.setNumBooksIssued(numBooks);
			session.setAttribute("msg", "Book issued successfully");
		}
		
		List<IssuedBook> bookList = user.getBooksIssued();
		bookList.add(issuedBook);
		repository.save(issuedBook);
		Book book = bookService.getBook(issuedBook.getBookId());
		int numberOfCopies = book.getNumberCopies();
		numberOfCopies--;
		if(numberOfCopies <= 0) {
			book.setAvailable(false);
		}
		else
			
		book.setNumberCopies(numberOfCopies);		
		return "redirect:/home";
	}
	@GetMapping("/viewIssuedBooks")
	public String viewIssued(@PathVariable int id, Model model) {
		List<IssuedBook> issuedBooks = repository.findAll();
		model.addAttribute("issuedBooks", issuedBooks);
		
		return "viewIssuedBook";
	}
	
	@GetMapping("/viewIssuedByUser")
	public String viewIssuedBooksByUser(@PathVariable int id, Model model) {
		List<IssuedBook> issuedBooks = userService.findBooksIssued(id);
		model.addAttribute("issuedBooks", issuedBooks);		
		return "viewIssuedBook";
	}
	
	
	
	
}
