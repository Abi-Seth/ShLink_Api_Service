package com.shlink.shlink.repository;

import com.shlink.shlink.model.ShortenedLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkShortenerRepository extends JpaRepository<ShortenedLink, UUID> {
    Optional<ShortenedLink> findByUuid(UUID uuid);
}
