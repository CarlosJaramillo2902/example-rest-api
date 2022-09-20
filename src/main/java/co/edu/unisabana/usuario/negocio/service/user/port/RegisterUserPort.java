package co.edu.unisabana.usuario.negocio.service.user.port;

import co.edu.unisabana.usuario.negocio.service.model.User;

public interface RegisterUserPort {

  boolean addNewUser(User user);
}
