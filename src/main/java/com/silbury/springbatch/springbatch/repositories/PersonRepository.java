package com.silbury.springbatch.springbatch.repositories;

import com.silbury.springbatch.springbatch.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to add people
 * Created by trjena on 02.07.17.
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
