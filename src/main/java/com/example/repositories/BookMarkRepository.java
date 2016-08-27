package com.example.repositories;

import com.example.model.BookMark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends CrudRepository<BookMark, Long> {
}
