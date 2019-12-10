/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Models;

/**
 *
 * @author Jdany
 */
public class LoginModel {
    public String Usuario; 
    public String Contraseña;

    public LoginModel() {
        this.Contraseña = "";
        this.Usuario = "";
    }

    public LoginModel(String Usuario, String Password) {
        this.Contraseña = Password;
        this.Usuario = Usuario;
    }

    public void setUsuario(String value) {
        this.Usuario = value;
    }

    public String getUsuario() {
        return this.Usuario;
    }

    public void setContraseña(String value) {
        this.Contraseña = value;
    }

    public String getContraseña() {
        return this.Contraseña;
    }
}
