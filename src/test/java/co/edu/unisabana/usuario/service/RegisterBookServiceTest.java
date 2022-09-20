package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.negocio.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.negocio.service.library.model.Book;
import co.edu.unisabana.usuario.negocio.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.negocio.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.negocio.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static co.edu.unisabana.usuario.negocio.service.library.model.CategoryBook.HARD_COVER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegisterBookServiceTest {

    @InjectMocks
    private RegisterBookLibrary service;

    @Mock
    private AddBookPort addBookPort;

    @Mock
    private RegisterBookPort registerBookPort;

    @Mock
    private SearchBookPort searchBookPort;


    @Test
    public void Given_book_exists_When_RegisterBook_Then_addBook() {
        Book book = new Book("Cien años de soledad", 1967, "Gabriel Garcia Marquez", true, HARD_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);

        int result = service.registerBook(book);

        Mockito.verify(addBookPort).addBook(book.getName());
        assertEquals(1, result);
    }

    @Test
    public void Given_book_no_exists_When_RegisterBook_Then_registerBook() {
        Book book = new Book("Cien años de soledad", 1967, "Gabriel Garcia Marquez", true, HARD_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(false);

        int result = service.registerBook(book);

        Mockito.verify(registerBookPort).registerBook(book);
        assertEquals(2, result);
    }
}
