package com.BookCat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookCat.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

	Book findByISBN(Long isbn);

	void deleteByISBN(Long iSBN);

	List<Book> findByBookAuthor(String author);


	

}
