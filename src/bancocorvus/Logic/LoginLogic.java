/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Logic;

import bancocorvus.Database.UsuariosSql;
import bancocorvus.Models.LoginModel;

public class LoginLogic {
    private UsuariosSql dbContext;

    public LoginLogic() {
      this.dbContext = new UsuariosSql();
    }

    public boolean Login(String usuario, String password) {
      LoginModel model = new LoginModel();
      model.Contraseña = password;
      model.Usuario = usuario;
      boolean result = this.dbContext.Login(model);
      return result;
    }
}
