package com.bridgelabz.BookStoreApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bridgelabz.BookStoreApp.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Long>{
    @Query(value = "select * from book_Details e where e.book_id = :id",nativeQuery = true)
    BookModel findBookById(@Param("id") long id);
}
