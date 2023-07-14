package com.Aniltasks.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Aniltasks.project1.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}