package Ungdung.java.demoF2.services;
import Ungdung.java.demoF2.entities.Book;
import Ungdung.java.demoF2.repositories.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE,
        rollbackFor = {Exception.class, Throwable.class})
public class BookService {
    private final IBookRepository bookRepository;

    public List<Book> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return bookRepository.findAll(paging).getContent();
    }
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String uniqueCode = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/static/imgs/" + uniqueCode +"-" + file.getOriginalFilename());
                Files.write(path, bytes);
                book.setOriginalFilename(file.getOriginalFilename());
                book.setImage_url(uniqueCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bookRepository.save(book);
    }

    public void updateBook(@NotNull Book book, MultipartFile file) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElse(null);
        Objects.requireNonNull(existingBook).setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setCategory(book.getCategory());
        if (!file.isEmpty()) {
            try {
                String uniqueCode = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/static/imgs/" + uniqueCode +"-" + file.getOriginalFilename());
                Files.write(path, bytes);
                existingBook.setOriginalFilename(file.getOriginalFilename());
                existingBook.setImage_url(uniqueCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bookRepository.save(existingBook);
    }
    public List<Book> searchBook(String keyword) {
        return bookRepository.searchBook(keyword);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}