package co.edu.unisabana.usuario.presentacion.controller;

import co.edu.unisabana.usuario.presentacion.dto.BookDto;
import co.edu.unisabana.usuario.presentacion.dto.BookReponse;
import co.edu.unisabana.usuario.persistencia.dao.entity.BookEntity;
import co.edu.unisabana.usuario.negocio.service.library.RegisterBookLibrary;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {

    private final RegisterBookLibrary registerBookLibrary;



    public BookController(RegisterBookLibrary registerBookLibrary) {
        this.registerBookLibrary = registerBookLibrary;
    }

    // Pendiente validar uso de exception handler
    // explicar camel case
    @PostMapping("/register")
    @ResponseBody
    public BookReponse registerBook(@RequestBody BookDto bookDto) {
        int result = registerBookLibrary.registerBook(bookDto.toModel());
        if (result == 1) {
            return new BookReponse("Actualizada cantidad");
        }
        return new BookReponse("Nuevo libro registrado");
    }
    /*@GetMapping("/search/{author}")
    public ArrayList<BookEntity> findbookbyauthor(@PathVariable String author){
        return registerBookLibrary.findbookbyauthor();
    }*/
    @GetMapping("/search/{author}")
    BookEntity bookEntity(@PathVariable String author){
        if (Objects.nonNull(registerBookLibrary.findbookbyauthor(author))) {
            return registerBookLibrary.findbookbyauthor(author);
        } else {
            return null;
        }
    }
    @DeleteMapping(value = "/remove/{name}")
    int bookEntitydelete(@PathVariable String name){
        if (Objects.isNull(registerBookLibrary.removebookbyname(name))) {
            throw new NullPointerException("Escriba un nombre valido");
        } else {
            return registerBookLibrary.removebookbyname(name);
        }
    }
}
