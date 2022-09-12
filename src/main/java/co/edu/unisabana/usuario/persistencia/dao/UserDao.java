package co.edu.unisabana.usuario.persistencia.dao;

import co.edu.unisabana.usuario.negocio.service.model.User;
import co.edu.unisabana.usuario.negocio.service.user.port.RegisterUserPort;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements RegisterUserPort {

  @Override
  public boolean addNewUser(User user) {

    return false;
  }
}
