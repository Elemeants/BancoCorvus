/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Models;

import static bancocorvus.Models.UtilsModels.ParseString;

/**
 *
 * @author Jdany
 */
public class Usuario extends LoginModel {
    public int Id;
    public String Nombre;
    public String ApellidoPaterno;
    public String ApellidoMaterno;
    public int NumeroCuenta;
    public float Saldo;
    
    public Usuario() {
    }

    public Usuario(int Id, String Nombre) {
        this.Id = Id;
        this.Nombre = Nombre;
    }

    public Usuario(int Id, String Nombre, String ApellidoPaterno, String ApellidoMaterno, int NumeroCuenta, float Saldo) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.NumeroCuenta = NumeroCuenta;
        this.Saldo = Saldo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return ParseString(this.Nombre);
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ParseString(ApellidoPaterno);
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ParseString(ApellidoMaterno);
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public int getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(int NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }
    
    public void addSaldo(float Saldo) {
        this.setSaldo(this.getSaldo() + Saldo);
    }
    
    public String getNombreCompleto() {
        return this.getNombre() + " " + this.getApellidoPaterno() + " " + this.getApellidoMaterno();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id:");
        sb.append(this.Id);
        sb.append(" Numero de cuenta:");
        sb.append(this.NumeroCuenta);
        sb.append("\nNombre:");
        sb.append(this.getNombreCompleto());
        return sb.toString();
    }
}
