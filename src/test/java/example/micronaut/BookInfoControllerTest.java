package example.micronaut;

import com.example.openapi.server.model.DetailedBookInfo;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@MicronautTest
class BookInfoControllerTest {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public BookInfoControllerTest(ObjectMapper objectMapper, @Client("/") HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    @Test
    void testPostModelWithDiscriminator() throws IOException {
        String bookInfoString = objectMapper.writeValueAsString(
                new DetailedBookInfo("Michael Ende", "1234567890123", "Never-ending Story")
        );
        System.out.println(bookInfoString);

        var request = HttpRequest.POST("/", bookInfoString);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);

        Assertions.assertEquals("OK", response);
    }
}
