/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Logic;

import bancocorvus.Database.UsuariosSql;
import bancocorvus.Models.CuentaBancaria;
import bancocorvus.Models.Usuario;

/**
 *
 * @author Jdany
 */
public class MovimientosLogic {
    private UsuarioLogic userLogic;

    public MovimientosLogic() {
        this.userLogic = new UsuarioLogic();
    }
    
    private boolean Movimiento(Usuario user, float movimiento) {
        user.addSaldo(movimiento);
        this.userLogic.ActualizarUsuario((CuentaBancaria)user);
        return true;
    }
    
    public boolean Deposito(Usuario user, float deposito) {
        return Movimiento(user, deposito);
    }
    
    public boolean Retiro(Usuario user, float retiro) {
        return Movimiento(user, -retiro);
    }
    
    public boolean Transferencia(Usuario origen, Usuario destino, float movimiento) {
        boolean retiroCompleto = this.Retiro(origen, movimiento);
        if (!retiroCompleto) {
            return false;
        }
        boolean depositoCompleto = this.Deposito(destino,  movimiento);
        return depositoCompleto;
    }
}
