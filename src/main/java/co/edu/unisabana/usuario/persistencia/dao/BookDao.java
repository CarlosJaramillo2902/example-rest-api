package co.edu.unisabana.usuario.persistencia.dao;

import co.edu.unisabana.usuario.persistencia.dao.entity.BookEntity;
import co.edu.unisabana.usuario.negocio.service.library.model.Book;
import co.edu.unisabana.usuario.persistencia.dao.port.AddBookPort;
import co.edu.unisabana.usuario.persistencia.dao.port.RegisterBookPort;
import co.edu.unisabana.usuario.persistencia.dao.port.SearchBookPort;
import co.edu.unisabana.usuario.persistencia.dao.port.DeleteBookPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class BookDao implements SearchBookPort, RegisterBookPort, AddBookPort, DeleteBookPort {

    static List<BookEntity> listBooks = new ArrayList<>();

    public List<BookEntity> getBooks(){
        return listBooks;
    }
    @Override
    public boolean validateExistsBook(String nameBook) {
        AtomicBoolean exists = new AtomicBoolean(false);
        listBooks.forEach(book -> {
            if (book.getName().equals(nameBook)) {
                exists.set(true);
            }
        });
        return exists.get();
    }


    @Override
    public void registerBook(Book newBook) {
        BookEntity bookEntity = BookEntity.fromModel(newBook);
        bookEntity.setId(listBooks.size() + 1);
        listBooks.add(bookEntity);
    }

    @Override
    public boolean addBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name)) {
                if (book.getQuantity() >= 9){
                    throw new IllegalArgumentException("Cantidad límite superada");
                } else {
                    book.setQuantity(book.getQuantity() + 1);
                    return true;
                }
            }
        }
        throw new IllegalArgumentException("No existe libro para actualizar");
    }
    public boolean removeBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name)) {
                book.setQuantity(book.getQuantity() - 1);
                return true;
            }
        }
        throw new IllegalArgumentException("No existe libro para remover");
    }
}
