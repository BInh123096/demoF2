package Ungdung.java.demoF2.viewmodels;
import Ungdung.java.demoF2.entities.Book;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder

public record BootPostVm(String title, String author, Double price,
                         Long categoryId) {
    public static BootPostVm from(@NotNull Book book) {
        return new BootPostVm(book.getTitle(), book.getAuthor(),
                book.getPrice(), book.getCategory().getId());
    }
}
