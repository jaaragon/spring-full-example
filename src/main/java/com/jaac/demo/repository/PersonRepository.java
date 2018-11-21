package com.jaac.demo.repository;

import com.jaac.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * JpaRepository’s methods like save(), findOne(), findAll(), count(), delete()
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
