package com.management.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.library.entities.IssuedBook;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Integer> {

}
