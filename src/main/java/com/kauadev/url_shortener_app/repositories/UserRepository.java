package com.kauadev.url_shortener_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kauadev.url_shortener_app.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
