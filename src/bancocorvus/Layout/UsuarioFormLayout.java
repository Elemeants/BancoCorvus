/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bancocorvus.Layout;

import static bancocorvus.Layout.UtilsLayout.LeerInputSpinner;
import bancocorvus.Logic.UsuarioLogic;
import bancocorvus.Models.CuentaBancaria;
import bancocorvus.Models.Usuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jdany
 */
public class UsuarioFormLayout extends javax.swing.JPanel {
    private final UsuarioLogic userLogic;
    private int userIdSearch;
    /** Creates new form UsuarioFormLayout */
    public UsuarioFormLayout() {
        this.userIdSearch = -1;
        this.userLogic = new UsuarioLogic();
        this.userLogic.OnUsuariosChange(() -> {
            System.out.println("Tabla actualizada");
            this.ActualizarTabla();
        });
        initComponents();
        ActualizarTabla();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollTable = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        LabelModuloUsuarios = new javax.swing.JLabel();
        BotonBuscarId = new javax.swing.JButton();
        InputUsuarioId = new javax.swing.JSpinner();

        setPreferredSize(new java.awt.Dimension(500, 500));

        TablaUsuarios.setModel(getTableUsuariosModel());
        TablaUsuarios.setEnabled(false);
        ScrollTable.setViewportView(TablaUsuarios);

        LabelModuloUsuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LabelModuloUsuarios.setText("Lista de usuarios");

        BotonBuscarId.setText("Buscar por Id");
        BotonBuscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarIdActionPerformed(evt);
            }
        });

        InputUsuarioId.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelModuloUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InputUsuarioId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonBuscarId))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelModuloUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonBuscarId)
                    .addComponent(InputUsuarioId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void ActualizarTabla() {
        DefaultTableModel tableData = (DefaultTableModel)getTableUsuariosModel();
        if (this.userIdSearch != -1 && this.userIdSearch != 0) {
            CuentaBancaria user = (CuentaBancaria)this.userLogic.GetUsuarioById(this.userIdSearch);
            if (user != null) {
                // tableData.addRow(UsuarioToRow(user));
                UtilsLayout.PopupInfo("Usuario", user.toString());
            } else {
                UtilsLayout.PopupError("Buscar usuario", "Usuario no encontrado");
            }
        } else {
            ArrayList<Usuario> usuarios = this.userLogic.GetAllUsuarios();
                
            for (int index = 0; index < usuarios.size(); index++) {
                Usuario user = usuarios.get(index);
                tableData.addRow(UsuarioToRow(user));
            }
        }
        
        
        this.TablaUsuarios.setModel(tableData);
    }
    
    private Object[] UsuarioToRow(Usuario user) {
        return new Object[] {
                user.getId(),
                user.getNombreCompleto(),
                user.getNumeroCuenta(),
                user.getSaldo()
            };
    }
    
    public TableModel getTableUsuariosModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Numero de Cuenta");
        model.addColumn("Saldo");
        return model;
    }
    
    private void BotonBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarIdActionPerformed
        int userId = (int)LeerInputSpinner(this.InputUsuarioId);
        this.userIdSearch = userId != 0 ? userId : -1;
        this.ActualizarTabla();
        this.userIdSearch = -1;
    }//GEN-LAST:event_BotonBuscarIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscarId;
    private javax.swing.JSpinner InputUsuarioId;
    private javax.swing.JLabel LabelModuloUsuarios;
    private javax.swing.JScrollPane ScrollTable;
    private javax.swing.JTable TablaUsuarios;
    // End of variables declaration//GEN-END:variables

}