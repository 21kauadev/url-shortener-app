package com.kauadev.url_shortener_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kauadev.url_shortener_app.domain.link.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}
