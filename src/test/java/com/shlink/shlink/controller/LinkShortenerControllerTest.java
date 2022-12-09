package com.shlink.shlink.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.shlink.shlink.service.LinkShortenerService;
import com.shlink.shlink.model.ShortenedLink;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(LinkShortenerController.class)
public class LinkShortenerControllerTest {
    @MockBean
    private LinkShortenerService linkShortenerServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllShortenedLinksSuccess() throws Exception {
        List<ShortenedLink> asList = Arrays.asList(
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
        );
        when(linkShortenerServiceMock.listShortenedLinks()).thenReturn(asList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get("/api/v1/shortenedLinks")
            .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
            .perform(request)
            .andExpect(status().isOk())
            .andExpect(content().json("[" +
                "{\"uuid\": \"bc89eafb-4354-4538-b364-c8f2133856a0\" , \"link\": \"https://www.youtube.com/watch?v=xlSxTvUiJgw\", \"shortLink\": \"http://127.0.0.1:8080/7DhUW4\"}," +
                "{\"uuid\": \"4546cb84-df65-4f37-a01c-81182fb70a3b\" , \"link\": \"https://www.baeldung.com/java-validate-url\", \"shortLink\": \"http://127.0.0.1:8080/yzpajj\"}]"))
            .andReturn();
    }

    @Test
    public void getByOneSuccess() throws Exception {
        ShortenedLink link = new ShortenedLink(
            UUID.fromString("bc89eafb-4354-4538-b364-c8f2133856a0"),
            "https://www.youtube.com/watch?v=xlSxTvUiJgw",
            "http://127.0.0.1:8080/7DhUW4"
        );

        when(linkShortenerServiceMock.retrieveShortenedLink(link.getUuid())).thenReturn(link);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get("/api/v1/shortenedLinks/bc89eafb-4354-4538-b364-c8f2133856a0")
            .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
            .perform(request)
            .andExpect(status().isOk())
            .andExpect(content().json("{\"uuid\": \"bc89eafb-4354-4538-b364-c8f2133856a0\" , \"link\": \"https://www.youtube.com/watch?v=xlSxTvUiJgw\", \"shortLink\": \"http://127.0.0.1:8080/7DhUW4\"}"))
            .andReturn();

    }

    @Test
    public void getByOne_404() throws Exception {
        ShortenedLink link = new ShortenedLink(
            UUID.fromString("bc89eafb-4354-4538-b364-c8f2133856a0"),
            "https://www.youtube.com/watch?v=xlSxTvUiJgw",
            "http://127.0.0.1:8080/7DhUW4"
        );

        when(linkShortenerServiceMock.retrieveShortenedLink(link.getUuid())).thenReturn(link);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get("/api/v1/shortenedLinks/bc89eafb-4354-4538-b364-c8f2133856a1")
            .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
            .perform(request)
            .andExpect(status().isNotFound())
            .andExpect(content().json("{\"status\":false,\"message\":\"Shortened link not found!\"}"))
            .andReturn();

    }

}
