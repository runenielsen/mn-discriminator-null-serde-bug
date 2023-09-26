package example.micronaut;

import com.example.openapi.server.model.BookInfo;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Controller
public class BookInfoController {

    @Post
    String addBook(@NotNull @Valid @Body BookInfo bookInfo) {
        System.out.println("Controller: " + bookInfo);
        return "OK";
    }
}
