/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Models;

import static bancocorvus.Models.UtilsModels.ParseString;

 
public class CuentaBancaria extends Usuario {
    public String Calle;
    public String Colonia;
    public String Municipio;
    public String Pais;
    public String CodigoPostal;
    public String Telefono;
    public boolean TarjetaCredito;
    public boolean TarjetaDebito;
    public boolean Prestamos;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int Id, String Nombre) {
        super(Id, Nombre);
    }

    public CuentaBancaria(int Id, String Nombre, String ApellidoPaterno, String ApellidoMaterno, int NumeroCuenta, float Saldo) {
        super(Id, Nombre, ApellidoPaterno, ApellidoMaterno, NumeroCuenta, Saldo);
    }
    
    public CuentaBancaria(Usuario user) 
    {
        super(user.getId()
                , user.getNombre()
                , user.getApellidoPaterno()
                , user.getApellidoMaterno()
                , user.getNumeroCuenta()
                , user.getSaldo());
    }

    public String getCalle() {
        return ParseString(Calle);
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getColonia() {
        return ParseString(Colonia);
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public String getCodigoPostal() {
        return ParseString(CodigoPostal);
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public String getMunicipio() {
        return ParseString(Municipio);
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getPais() {
        return ParseString(Pais);
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getTelefono() {
        return ParseString(Telefono);
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Boolean getTarjetaCredito() {
        return TarjetaCredito;
    }

    public void setTarjetaCredito(boolean TarjetaCredito) {
        this.TarjetaCredito = TarjetaCredito;
    }

    public Boolean getTarjetaDebito() {
        return TarjetaDebito;
    }

    public void setTarjetaDebito(boolean TarjetaDebito) {
        this.TarjetaDebito = TarjetaDebito;
    }

    public Boolean getPrestamos() {
        return Prestamos;
    }

    public void setPrestamos(boolean Prestamos) {
        this.Prestamos = Prestamos;
    }
    
    public String getDomicilio() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCalle());
        sb.append(", ");
        sb.append(this.getMunicipio());
        sb.append(", ");
        sb.append(this.getColonia());
        sb.append(" ");
        sb.append(this.getCodigoPostal());
        sb.append(", ");
        sb.append(this.getPais());
        sb.append(".");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(this.getId());
        sb.append(" Numero de cuenta: ");
        sb.append(this.getNumeroCuenta());
        sb.append("\nNombre: ");
        sb.append(this.getNombreCompleto());
        sb.append("\nDomicilio: ");
        sb.append(this.getDomicilio());
        sb.append("\nTelefono: ");
        sb.append(this.getTelefono());
        sb.append("\nTarjeta de credito: ");
        sb.append(this.getTarjetaCredito());
        sb.append("\nTarjeta de debito: ");
        sb.append(this.getTarjetaDebito());
        sb.append("\nPrestamos: ");
        sb.append(this.getPrestamos());
        sb.append("\nSaldo: $");
        sb.append(this.getSaldo());
        sb.append("MXM");
        return sb.toString();
    }
}
