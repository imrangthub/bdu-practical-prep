package com.imranmabar.book;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imranmabar.report.ReportGenerationService;
import com.imranmabar.util.CommonFunctions;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController{

	@Autowired
	private BookService bookService;

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	private ReportGenerationService reportGenerationService;

	@GetMapping("/list")
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

	@PutMapping("/update/{id}")
	public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity bookDetails) {
		return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public  void prescription(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		respondReportOutput(reportGenerationService.prescription(request,response),false,response);
		
	}

}
