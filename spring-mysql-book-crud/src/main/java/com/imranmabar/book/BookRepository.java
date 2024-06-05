package com.imranmabar.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	Optional<BookEntity> findById(long id);

	List<BookEntity> findByName(String name);

}
