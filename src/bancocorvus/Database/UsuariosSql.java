/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Database;

import bancocorvus.Models.CuentaBancaria;
import bancocorvus.Models.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import bancocorvus.Models.LoginModel;

 
public class UsuariosSql extends SQLBase<CuentaBancaria> {

    public UsuariosSql() {
        super("Usuarios");
    }

    public CuentaBancaria ReadFromDb(ResultSet result) {
        CuentaBancaria model = new CuentaBancaria();
        try {
            model.setId(result.getInt("Id"));
            model.setNombre(result.getString("Nombre"));
            model.setApellidoPaterno(result.getString("ApellidoP"));
            model.setApellidoMaterno( result.getString("ApellidoM") );
            model.setNumeroCuenta( result.getInt("NoCuenta") );
            model.setSaldo( result.getFloat("Saldo") );
            model.setCalle( result.getString("Calle") );
            model.setCodigoPostal( result.getString("CP") );
            model.setColonia( result.getString("Colonia") );
            model.setMunicipio( result.getString("Municipio") );
            model.setPais( result.getString("Pais") );
            model.setTelefono( result.getString("Telefono") );
            model.setPrestamos( result.getBoolean("Prestamos") );
            model.setTarjetaDebito( result.getBoolean("TarjetaD") );
            model.setTarjetaCredito( result.getBoolean("TarjetaC") );
            model.setUsuario( result.getString("Usuario") );
            model.setContraseña( result.getString("Password") );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean Login(LoginModel model) {
        String loginQuery = "SELECT * FROM usuarios WHERE Usuario = \"" + model.getUsuario() + "\" AND Password = \"" + model.getContraseña() + "\"";
        System.out.println(loginQuery);
        int cuenta = this.ExecuteQuery(loginQuery, (ResultSet set) -> this.ReadFromDb(set)).size();
        return cuenta > 0;
    }

    @Override
    public boolean Insert(CuentaBancaria model) {
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("Id"); keys.add("" + model.getId());
        fields.add("Nombre"); keys.add("\"" + model.getNombre() + "\"");
        fields.add("ApellidoP"); keys.add("\"" + model.getApellidoPaterno()+ "\"");
        fields.add("ApellidoM"); keys.add("\"" + model.getApellidoMaterno() + "\"");
        fields.add("NoCuenta"); keys.add("" + model.getNumeroCuenta());
        fields.add("Saldo"); keys.add("" + model.getSaldo());
        fields.add("Calle"); keys.add("\"" + model.getCalle()+ "\"");
        fields.add("CP"); keys.add("\"" + model.getCodigoPostal()+ "\"");
        fields.add("Colonia"); keys.add("\"" + model.getColonia()+ "\"");
        fields.add("Municipio"); keys.add("\"" + model.getMunicipio()+ "\"");
        fields.add("Pais"); keys.add("\"" + model.getPais()+ "\"");
        fields.add("Telefono"); keys.add("\"" + model.getTelefono()+ "\"");
        fields.add("Prestamos"); keys.add("" + model.getPrestamos());
        fields.add("TarjetaD"); keys.add("" + model.getTarjetaDebito());
        fields.add("TarjetaC"); keys.add("" + model.getTarjetaCredito());
        fields.add("Usuario"); keys.add("\"" + model.getUsuario()+ "\"");
        fields.add("Password"); keys.add("\"" + model.getContraseña()+ "\"");
        String query = this.getInsertQuery(fields, keys);
        System.out.println(query);
        this.ExecuteUpdate(query);
        return true;
    }

    @Override
    public boolean Update(CuentaBancaria model) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("Id = " + model.getId());
        keys.add("Nombre = \"" + model.getNombre() + "\"");
        keys.add("ApellidoP = \"" + model.getApellidoPaterno()+ "\"");
        keys.add("ApellidoM = \"" + model.getApellidoMaterno() + "\"");
        keys.add("NoCuenta = " + model.getNumeroCuenta());
        keys.add("Saldo = " + model.getSaldo());
        keys.add("Calle = \"" + model.getCalle()+ "\"");
        keys.add("CP = \"" + model.getCodigoPostal()+ "\"");
        keys.add("Colonia = \"" + model.getColonia()+ "\"");
        keys.add("Municipio = \"" + model.getMunicipio()+ "\"");
        keys.add("Pais = \"" + model.getPais()+ "\"");
        keys.add("Telefono = \"" + model.getTelefono()+ "\"");
        keys.add("Prestamos = " + model.getPrestamos());
        keys.add("TarjetaD = " + model.getTarjetaDebito());
        keys.add("TarjetaC = " + model.getTarjetaCredito());
        String query = this.getUpdateQuery(model.getId(), keys);
        System.out.println(query);
        this.ExecuteUpdate(query);
        return true;
    }

    @Override
    public ArrayList<CuentaBancaria> GetAll() {
        String query = this.getGetAllQuery();
        System.out.println(query);
        return this.ExecuteQuery(query, (ResultSet set) -> this.ReadFromDb(set));
    }

    @Override
    public CuentaBancaria GetById(int id) {
        String query = this.getGetQuery(id);
        System.out.println(query);
        return this.ExecuteQuery(query, (ResultSet set) -> this.ReadFromDb(set)).get(0);
    }

    @Override
    public boolean Delete(int id) {
        String query = this.getDeleteQuery(id);
        System.out.println(query);
        this.ExecuteUpdate(query);
        return false;
    }
    
    
}
