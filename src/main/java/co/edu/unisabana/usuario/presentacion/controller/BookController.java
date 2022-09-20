package co.edu.unisabana.usuario.presentacion.controller;

import co.edu.unisabana.usuario.delegados.Delegados;
import co.edu.unisabana.usuario.presentacion.dto.BookDto;
import co.edu.unisabana.usuario.presentacion.dto.BookReponse;
import co.edu.unisabana.usuario.persistencia.dao.entity.BookEntity;
import co.edu.unisabana.usuario.negocio.service.library.RegisterBookLibrary;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {

    private final Delegados delegados;


    public BookController(Delegados delegados) {
        this.delegados = delegados;
    }

    // Pendiente validar uso de exception handler
    // explicar camel case
    @PostMapping("/register")
    @ResponseBody
    public BookReponse registerBook(@RequestBody BookDto bookDto) {
        int result = delegados.registerBookLibrary.registerBook(bookDto.toModel());
        if (result == 1) {
            return new BookReponse("Actualizada cantidad");
        }
        return new BookReponse("Nuevo libro registrado");
    }
    @GetMapping("/search/{author}")
    BookEntity bookEntity(@PathVariable String author){
        if (Objects.nonNull(delegados.registerBookLibrary.findbookbyauthor(author))) {
            return delegados.registerBookLibrary.findbookbyauthor(author);
        } else {
            return null;
        }
    }
    @DeleteMapping("/remove/{name}")
    public BookReponse bookEntitydelete(@PathVariable String name){
        int result = delegados.registerBookLibrary.removebookbyname(name);
        if (result ==1)
        {
            return new BookReponse("Libro eliminado exitosamente");
        } else {
            return new BookReponse("No existe un libro con ese nombre");
        }
    }
}
