package co.edu.unisabana.usuario.presentacion.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {

  private List<UserDTO> data;
}
