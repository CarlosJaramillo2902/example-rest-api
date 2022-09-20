package co.edu.unisabana.usuario.negocio.service.model;

import lombok.Data;

@Data
public class User {

  private String name;
  private String lastName;
  private int age;
  private boolean active;
}
