package com.shlink.shlink.service;

import com.shlink.shlink.model.ShortenedLink;

import com.shlink.shlink.utils.GenerateUniqueLinkIdentifier;
import com.shlink.shlink.repository.LinkShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkShortenerService {
    @Autowired
    private LinkShortenerRepository linkShortenerRepository;

    public List<ShortenedLink> listShortenedLinks() {
        return linkShortenerRepository.findAll();
    }

    public ShortenedLink shortenLink(String link) {
        ShortenedLink newLink = new ShortenedLink();
        newLink.setLink(link);
        newLink.setShortLink("http://127.0.0.1:8080/"+GenerateUniqueLinkIdentifier.generateUnquieIdentifier());
        linkShortenerRepository.save(newLink);
        return newLink;
    }

    public ShortenedLink retrieveShortenedLink(UUID id) {
        Optional<ShortenedLink> foundByUUID = linkShortenerRepository.findByUuid(id);

        return foundByUUID.orElse(null);
    }
}
