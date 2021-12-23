package com.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
