package Ungdung.java.demoF2.viewmodels;
import Ungdung.java.demoF2.entities.Book;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record BookGetVm(Long id, String title, String author, Double
        price, String category) {
    public static BookGetVm from(@NotNull Book book) {
        return BookGetVm.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory().getName())
                .build();
    }
}
