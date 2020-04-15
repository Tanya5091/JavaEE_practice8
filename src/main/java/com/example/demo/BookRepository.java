package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByAuthorLike(String contains);
    List<BookEntity> findAllByBooknameLike(String contains);
    List<BookEntity> findAllByIsbnLike(String contains);


    @Query("SELECT u FROM BookEntity u "
            + "WHERE u.bookname LIKE :param1 "
            + "OR u.isbn LIKE :param1")
    List<BookEntity> findAllByIsbnOrName(@Param("param1") String param1);

}