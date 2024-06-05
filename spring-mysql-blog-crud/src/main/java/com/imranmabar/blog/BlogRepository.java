package com.imranmabar.blog;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

	Optional<BlogEntity> findById(long id);

}
