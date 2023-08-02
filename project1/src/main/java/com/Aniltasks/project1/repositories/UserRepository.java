package com.Aniltasks.project1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Aniltasks.project1.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
