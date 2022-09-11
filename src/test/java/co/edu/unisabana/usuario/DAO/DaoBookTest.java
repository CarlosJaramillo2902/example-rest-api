package co.edu.unisabana.usuario.DAO;

import co.edu.unisabana.usuario.repository.dao.BookDao;
import co.edu.unisabana.usuario.service.library.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static co.edu.unisabana.usuario.service.library.model.CategoryBook.HARD_COVER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
public class DaoBookTest {
    @InjectMocks
    BookDao dao;

    @Test
    public void Given_list_books_When_validate_exists_Then_return_true(){
        Book book = new Book("Cien años de soledad", 1967, "Gabriel Garcia Marquez", true, HARD_COVER);
        dao.registerBook(book);

        boolean result = dao.validateExistsBook("Cien años de soledad");
        assertTrue(result);
    }

    @Test
    public void Given_list_books_When_add_book_Then_return_true(){
        Book book = new Book("Cien años de soledad", 1967, "Gabriel Garcia Marquez", true, HARD_COVER);
        dao.registerBook(book);

        boolean result = dao.addBook(book.getName());
        assertTrue(result);
    }

    @Test
    public void Given_list_books_When_add_book_Then_return_exception(){
        assertThrows(IllegalArgumentException.class, () -> dao.addBook("Cien años de soledad"));
    }

    @Test
    public void Given_list_books_When_remove_book_Then_return_true(){
        Book book = new Book("Cien años de soledad", 1967, "Gabriel Garcia Marquez", true, HARD_COVER);
        dao.registerBook(book);

        boolean result = dao.removeBook(book.getName());
        assertTrue(result);
    }
}
