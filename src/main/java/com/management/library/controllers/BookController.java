package com.management.library.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.library.entities.Book;
import com.management.library.entities.User;
import com.management.library.repositories.UserRepository;
import com.management.library.services.BookService;

import io.swagger.annotations.Api;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping(value = "/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/viewBooks")
	public String viewAllBooks(Model model) {
		List<Book> bookList = bookService.viewAllBooks();
		model.addAttribute("books", bookList);
		return "viewBooks";
	}
	
	@GetMapping("/viewBook/{id}")
	public String viewBook(@PathVariable int id,Model model) {
		Book book = bookService.viewBook(id);
		model.addAttribute("book", book);
		return "viewBook";
	}
	
	@GetMapping("/register")
	public String register() {
		return "registerUser";
	}
	
	@GetMapping("/admin/viewUsers")
	public String viewUsers(Model model) {
		List<User> userList = userRepository.findAll();
		model.addAttribute("users", userList);
		return "viewUsers";
	}
	
	
	@GetMapping("/admin/addBook")
	public String addBook(Model model) {
	    model.addAttribute("book", new Book());
		return "addBook";
	}
		
	@PostMapping("/addBook")
	public String saveBook(@ModelAttribute Book book) {
	    bookService.saveBook(book);	
		return "redirect:/home";
	}
	
	@GetMapping("/admin/edit/{id}")
	public String editBook(@PathVariable int id, Model model) {
		Book book = bookService.getBook(id);
		model.addAttribute("book", book);
		
		return "editBookDetails";
	}
	
	@PostMapping("/update")
	public String updateBook(@ModelAttribute Book book, HttpSession session) {
		
		bookService.saveBook(book);
		session.setAttribute("msg", "Book details updated successfully" );
		return "redirect:/";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		List<Book> bookList = bookService.search(keyword);
		model.addAttribute("books", bookList);
		model.addAttribute("keyword", keyword);
		return "viewBooks";
	}

	
	
}
