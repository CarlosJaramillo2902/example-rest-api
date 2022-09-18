package co.edu.unisabana.usuario.persistencia.dao.port;

import co.edu.unisabana.usuario.negocio.service.library.model.Book;

public interface RegisterBookPort {

    void registerBook(Book newBook);
}

