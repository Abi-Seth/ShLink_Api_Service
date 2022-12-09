package com.shlink.shlink.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shlink.shlink.model.ShortenedLink;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LinkShortenerRepositoryTest {
    @Autowired
    private LinkShortenerRepository linkShortenerRepository;

    @Test
    public void findAllShortenedLinksSuccess() {
        List<ShortenedLink> shortenedLinks = linkShortenerRepository.findAll();
        assertEquals(2, shortenedLinks.size());
    }

    @Test
    public void findOne_success() throws JSONException {
        Optional<ShortenedLink> linkOption = linkShortenerRepository.findByUuid(UUID.fromString("1d354731-f69c-4875-9caf-ee1b15f21f38"));
        assertTrue(linkOption.isPresent());
    }

}
