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
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UsuarioFormLayout extends JPanel {
    private final UsuarioLogic userLogic;
    private int userIdSearch;

    public UsuarioFormLayout() {
        this.userIdSearch = -1;
        this.userLogic = new UsuarioLogic();
        this.userLogic.OnUsuariosChange(() -> {
            System.out.println("Tabla actualizada");
            this.ActualizarTabla();
        });
        crearComponentes();
        ActualizarTabla();
    }

    private void crearComponentes() {

        ScrollTable = new JScrollPane();
        TablaUsuarios = new JTable();
        LabelModuloUsuarios = new JLabel();
        BotonBuscarId = new JButton();
        InputUsuarioId = new JSpinner();

        setPreferredSize(new java.awt.Dimension(500, 500));

        TablaUsuarios.setModel(getTableUsuariosModel());
        TablaUsuarios.setEnabled(false);
        ScrollTable.setViewportView(TablaUsuarios);

        LabelModuloUsuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        InputUsuarioId.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        LabelModuloUsuarios.setText("Lista de usuarios");
        BotonBuscarId.setText("Buscar por Id");

        BotonBuscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarIdActionPerformed(evt);
            }
        });


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
    }
    
    public void ActualizarTabla() {
        DefaultTableModel tableData = (DefaultTableModel)getTableUsuariosModel();
        if (this.userIdSearch != -1 && this.userIdSearch != 0) {
            CuentaBancaria user = (CuentaBancaria)this.userLogic.GetUsuarioById(this.userIdSearch);
            if (user != null) {
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
            this.TablaUsuarios.setModel(tableData);
        }
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
    
    private void BotonBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {
        int userId = (int)LeerInputSpinner(this.InputUsuarioId);
        this.userIdSearch = userId != 0 ? userId : -1;
        this.ActualizarTabla();
        this.userIdSearch = -1;
    }

    private JButton BotonBuscarId;
    private JSpinner InputUsuarioId;
    private JLabel LabelModuloUsuarios;
    private JScrollPane ScrollTable;
    private JTable TablaUsuarios;

}
