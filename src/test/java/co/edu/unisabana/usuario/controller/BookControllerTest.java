package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.AbstractTest;
import co.edu.unisabana.usuario.presentacion.controller.BookController;
import co.edu.unisabana.usuario.presentacion.dto.BookDto;
import co.edu.unisabana.usuario.presentacion.dto.BookReponse;
import co.edu.unisabana.usuario.persistencia.dao.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


public class BookControllerTest extends AbstractTest {

@Autowired
private TestRestTemplate restTemplate;


    private static final String PATH_REGISTER = "/book/register";
    private static final String PATH_SEARCH = "/book/search/";
    private static final String PATH_DELETE = "/book/remove/";

    @Test
    public void Given_registerNewBook_WhenRegisterBooks_Then_RegisterMessage (){
        BookDto dto = new BookDto("Pinocho", 1953, "El autor", "Comercial", "suave");
        ResponseEntity<BookReponse> bookReponseResponseEntity =
                restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        assertEquals("Nuevo libro registrado", bookReponseResponseEntity.getBody().getData());
    }
    @Test
    public void Given_register_previously_When_call_register_book_Then_updateBook(){
        BookDto dto = new BookDto("Cien años de soledad", 1967, "Gabriel Garcia Marquez", "Comercial", "duro");
        restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        ResponseEntity<BookReponse> otroRegistro =
                restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        assertEquals("Actualizada cantidad", otroRegistro.getBody().getData());
    }
    @Test
    public void Given_any_name_When_search_book_by_author_Then_show_book(){
        BookDto dto = new BookDto("Cien años de soledad", 1967, "Gabriel Garcia Marquez", "Comercial", "duro");
        restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        ResponseEntity<BookEntity> book = restTemplate.getForEntity(PATH_SEARCH+ "Gabriel Garcia Marquez", BookEntity.class);
        assertEquals("Cien años de soledad", book.getBody().getName());
    }
    @Test
    public void Given_null_name_When_search_book_by_author_Then_show_null(){
        ResponseEntity<BookEntity> book = restTemplate.getForEntity(PATH_SEARCH, BookEntity.class);
        assertEquals(null, book.getBody().getName());
    }
    @Test
    public void Given_a_name_When_deleteBook_Then_delete_message(){
        BookDto dto = new BookDto("Blanca como la nieve, roja como la sangre", 2010, "Alessandro D'Avenia", "Comercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        String name = "Blanca como la nieve, roja como la sangre";
        ResponseEntity<BookReponse> bookReponse =
                restTemplate.exchange("/book/remove/" + name, HttpMethod.DELETE, null, BookReponse.class);
        assertEquals("Libro eliminado exitosamente", bookReponse.getBody().getData());
    }
    @Test
    public void Given_a_name_When_deleteBook_Then_delete_error_message(){
        BookDto dto = new BookDto("Blanca como la nieve, roja como la sangre", 2010, "Alessandro D'Avenia", "Comercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER, dto, BookReponse.class);
        String name = "Odio Odiar";
        ResponseEntity<BookReponse> bookReponse =
                restTemplate.exchange("/book/remove/" + name, HttpMethod.DELETE, null, BookReponse.class);
        assertEquals("No existe un libro con ese nombre", bookReponse.getBody().getData());
    }
}
