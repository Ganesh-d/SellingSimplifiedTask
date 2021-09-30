package com.BookCat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookCat.model.Book;
import com.BookCat.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/book")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);	
	@Autowired
	BookRepo bookRepo;

	/**
	 * this method is used for adding the book using restfull services
	 * 
	 * @param book
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/")
	public Book addBook(@RequestBody Book book) throws Exception {
		Book isbnRecord;
				if(book.getISBN().toString().length()!=15) {
					logger.warn("ISBN should be 15");
					throw new Exception("ISBN should be 15");
				}else {
					
			isbnRecord = bookRepo.findByISBN(book.getISBN());
			
			if (isbnRecord != null) {
				System.out.println("Record is already exit with given ISBN!!");

				throw new Exception("Record is already present!!");
			} else {
				isbnRecord = bookRepo.save(book);
				logger.info(isbnRecord+" Book Added Successfully");
				
			}
				}
		
		return isbnRecord;
	}

	@DeleteMapping("/{ISBN}")
	public void deleteByISBN1(@PathVariable("ISBN")Long ISBN) throws Exception {
		System.out.println(ISBN);
		if(ISBN.toString().length()!=15) {
			logger.warn("ISBN should be 15");
			throw new Exception("ISBN should be 15");
		}else {
		Book findByISBN = bookRepo.findByISBN(ISBN);
		if(findByISBN != null) {
		bookRepo.delete(findByISBN);
			logger.info("Deleted successfully");
		}else {
			System.out.println("please enter valid ISBN");
		}
		}
	}
	/**
	 * here we update the book by using ISBN
	 * @param book
	 * @param ISBN
	 * @return
	 * @throws Exception 
	 */
	@PutMapping("/update/{ISBN}")
	public Book updateBook(@RequestBody Book book,@PathVariable("ISBN")Long ISBN) throws Exception {
		if(book.getISBN().toString().length()!=15 || ISBN.toString().length()!=15) {
			logger.warn("ISBN should be 15");
			throw new Exception("ISBN should be 15");
		}else {
		Book findByISBN = bookRepo.findByISBN(ISBN);
		findByISBN.setBookTitle(book.getBookTitle());
		findByISBN.setISBN(book.getISBN());
		findByISBN.setPrice(book.getPrice());
		findByISBN.setPublicationDate(book.getPublicationDate());
		findByISBN.setBookAuthor(book.getBookAuthor());
		findByISBN.setBookGenre(book.getBookGenre());
		Book save = bookRepo.save(findByISBN);
		logger.info(save+" Book Updated successfully");
		return save;
		}
		

	}
	/**
	 * here we find book by using author
	 * @param author
	 * @return
	 */
	@GetMapping("/{author}")
	public List<Book> getBookByAuthor(@PathVariable("author")String author){
		List<Book> findByAuthor = bookRepo.findByBookAuthor(author);
		return findByAuthor;
	}
	/**
	 * if you have ISBN no only then u can use below method to search book
	 * @param ISBN
	 * @return
	 */
	@GetMapping("/byISBN/{ISBN}")
	public Book getBookByISBN(@PathVariable("ISBN")Long ISBN){
		Book findByISBN = bookRepo.findByISBN(ISBN);
		return findByISBN;
	}
	
	/**
	 * If you have author and genre then u can use below method to find out the books
	 * @param author
	 * @param genre
	 * @return
	 */
	@GetMapping("/{author}/{genre}")
	public List<Book> getBookByCriteria(@PathVariable("author")String author,@PathVariable("genre")String genre){
		List<Book> findByCriteria = bookRepo.findByBookAuthor(author);
		List<Book> collect = findByCriteria.stream().filter(i->i.getBookGenre().equals(genre)).collect(Collectors.toList());
		return collect;
	}
}
