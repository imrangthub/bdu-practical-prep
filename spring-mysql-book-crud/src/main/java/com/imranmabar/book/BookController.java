package com.imranmabar.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/book")
	public List getBookList() {
		return bookRepository.findAll();
	}

	@GetMapping("/find-by-id")
	public Optional<BookEntity> getBookList(@RequestParam Long id) {
		return bookRepository.findById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book) {
		return ResponseEntity.ok(bookService.createBook(book));
	}

	@GetMapping
	public ResponseEntity<List<BookEntity>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.getBookById(id).orElseThrow(() -> new RuntimeException("Book not found")));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity bookDetails) {
		return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

}
