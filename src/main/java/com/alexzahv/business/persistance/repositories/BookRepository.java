package com.alexzahv.business.persistance.repositories;

import com.alexzahv.business.persistance.domain.Book;
import com.alexzahv.business.persistance.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Set<Book> findAllByTitleContaining(String title);

    Set<Book> findAllByAuthorContaining(String author);

    Set<Book> findAllByDescriptionContaining(String description);

    Set<Book> findAllByUser(User user);

    Book findFirstByUserOrderByCreateDateDesc(User user);
}
