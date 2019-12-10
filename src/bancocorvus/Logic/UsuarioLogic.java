/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Logic;

import bancocorvus.Database.UsuariosSql;
import bancocorvus.Models.CuentaBancaria;
import bancocorvus.Models.Usuario;
import java.util.ArrayList;
import bancocorvus.Models.OnUsuariosChange;

/**
 *
 * @author Jdany
 */
public class UsuarioLogic {
    private UsuariosSql dbContext;
    private static ArrayList<OnUsuariosChange> liseners = new ArrayList<>();
    
    public UsuarioLogic() {
        this.dbContext = new UsuariosSql();
    }
    
    public ArrayList<Usuario> GetAllUsuarios() {
        ArrayList<CuentaBancaria> cuentas = this.dbContext.GetAll();
        ArrayList<Usuario> users = new ArrayList<>();
        for ( CuentaBancaria cuenta : cuentas ) {
            users.add(cuenta);
        }
        return users;
    }
    
    public Usuario GetUsuarioById(int id) {
        return this.dbContext.GetById(id);
    }
    
    public boolean RemoveUsuario(int id) {
        boolean result = this.dbContext.Delete(id);
        this.UpdateLiseners();
        return result;
    }
    
    public boolean AgregarUsuario(Usuario user) {
        boolean result = this.dbContext.Insert((CuentaBancaria)user);
        this.UpdateLiseners();
        return result;
    }
    
    public boolean ActualizarUsuario(Usuario user) {
        boolean result = this.dbContext.Update((CuentaBancaria)user);
        this.UpdateLiseners();
        return result;
    }
    
    private void UpdateLiseners() {
        for (int i = 0; i < liseners.size(); i++) {
            OnUsuariosChange callback = liseners.get(i);
            callback.OnUsuariosChange();
        }
    }
    
    public void OnUsuariosChange( OnUsuariosChange callback ) {
        liseners.add(callback);
    }
}
