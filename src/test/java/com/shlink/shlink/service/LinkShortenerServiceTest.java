package com.shlink.shlink.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.shlink.shlink.model.ShortenedLink;
import com.shlink.shlink.repository.LinkShortenerRepository;

import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class LinkShortenerServiceTest {

    @Mock
    private LinkShortenerRepository linkShortenerRepositoryMock;

    @InjectMocks
    private LinkShortenerService linkShortenerService;

    @Test
    public void getAllShortenedLinksSuccess() {

        when(linkShortenerRepositoryMock.findAll()).thenReturn(Arrays.asList(
            new ShortenedLink(
                UUID.fromString("bc89eafb-4354-4538-b364-c8f2133856a0"),
                "https://www.youtube.com/watch?v=xlSxTvUiJgw",
                "http://127.0.0.1:8080/7DhUW4"
            ),
            new ShortenedLink(
                UUID.fromString("4546cb84-df65-4f37-a01c-81182fb70a3b"),
                "https://www.baeldung.com/java-validate-url",
                "http://127.0.0.1:8080/yzpajj"
            )
        ));
        assertEquals(2, linkShortenerService.listShortenedLinks().size());
    }

    @Test
    public void find_404() {
        ShortenedLink link = new ShortenedLink(
                UUID.fromString("bc89eafb-4354-4538-b364-c8f2133856a0"),
                "https://www.youtube.com/watch?v=xlSxTvUiJgw",
                "http://127.0.0.1:8080/7DhUW4"
        );
        when(linkShortenerRepositoryMock.findByUuid(link.getUuid())).thenReturn(Optional.empty());

        ShortenedLink shortenedLink = linkShortenerService.retrieveShortenedLink(link.getUuid());
        assertNull(shortenedLink);

    }

}