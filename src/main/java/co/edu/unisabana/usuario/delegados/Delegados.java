package co.edu.unisabana.usuario.delegados;

import co.edu.unisabana.usuario.negocio.service.library.RegisterBookLibrary;
import org.springframework.stereotype.Service;

@Service
public class Delegados {
    public final RegisterBookLibrary registerBookLibrary;

    public Delegados(RegisterBookLibrary registerBookLibrary) {
        this.registerBookLibrary = registerBookLibrary;
    }


}
