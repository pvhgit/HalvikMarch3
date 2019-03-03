package com.prashant.domainjpa.repository;

import com.prashant.domainjpa.data.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
