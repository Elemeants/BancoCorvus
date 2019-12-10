/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Layout;

import static bancocorvus.Layout.UtilsLayout.LeerInputSpinner;
import static bancocorvus.Layout.UtilsLayout.PopupError;
import static bancocorvus.Layout.UtilsLayout.PopupInfo;
import bancocorvus.Logic.MovimientosLogic;
import bancocorvus.Logic.UsuarioLogic;
import bancocorvus.Models.Usuario;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
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

public class TransferenciasLayout extends JPanel {
    private MovimientosLogic movLogic;
    private UsuarioLogic userLogic;
    ArrayList<Usuario> usuarios;

    public TransferenciasLayout() {
        this.movLogic = new MovimientosLogic();
        this.userLogic = new UsuarioLogic();
        this.userLogic.OnUsuariosChange(() -> {
            System.out.println("Lista actualizada");
            UpdateComboListUsuarios();
        });
        crearComponentes();
        UpdateComboListUsuarios();
    }

    private void UpdateComboListUsuarios() {
        ArrayList<String> userNames = new ArrayList<>();
        this.usuarios = this.userLogic.GetAllUsuarios();
        for (Usuario user : this.usuarios) {
            userNames.add(user.getNombreCompleto());
        }
        this.ComboBoxUsuarios.setModel(new DefaultComboBoxModel(userNames.toArray()));
        this.ComboBoxUsuarioOrigen.setModel(new DefaultComboBoxModel(userNames.toArray()));
        this.ComboBoxUsuarioDestino.setModel(new DefaultComboBoxModel(userNames.toArray()));
    }
    
    private void crearComponentes() {

        TipoMovimientoButtonGroup = new ButtonGroup();
        jSeparator1 = new JSeparator();
        jLabel1 = new JLabel();
        jTabbedPane1 = new JTabbedPane();
        MovimientosPanel = new JPanel();
        ComboBoxUsuarios = new JComboBox<>();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        InputMovimiento = new JSpinner();
        ButtonDeposito = new JRadioButton();
        ButtonRetiro = new JRadioButton();
        ButtonSave = new JButton();
        TransferenciaPanel = new JPanel();
        ComboBoxUsuarioOrigen = new JComboBox<>();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        InputTransferencia = new JSpinner();
        ButtonTransferencia = new JButton();
        jLabel6 = new JLabel();
        ComboBoxUsuarioDestino = new JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Transferencias");
        jLabel2.setText("Usuario");
        jLabel3.setText("Movimiento");
        InputMovimiento.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        ButtonDeposito.setText("Deposito");
        ButtonRetiro.setText("Retiro");
        ButtonSave.setText("Ejecutar movimiento");
        jTabbedPane1.addTab("Movimientos de usuario", MovimientosPanel);
        jLabel4.setText("Usuario Origen");
        jLabel5.setText("Movimiento");
        InputTransferencia.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        ButtonTransferencia.setText("Transferencia");
        jLabel6.setText("Usuario Destino");
        jTabbedPane1.addTab("Transferencia", TransferenciaPanel);

        ButtonDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDepositoActionPerformed(evt);
            }
        });

        ButtonRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRetiroActionPerformed(evt);
            }
        });

        ButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSaveActionPerformed(evt);
            }
        });

        ButtonTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonTransferenciaActionPerformed(evt);
            }
        });
        
        CrearLayout();
    }

    private void ButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        int userId = this.ComboBoxUsuarios.getSelectedIndex();
        float movimientoValue = (float)LeerInputSpinner(this.InputMovimiento);
        if (userId != -1) {
            Usuario user = this.usuarios.get(userId);
            boolean isDeposito = this.ButtonDeposito.isSelected();
            boolean isDone;
            isDone = isDeposito ? this.movLogic.Deposito(user, movimientoValue)
                    : this.movLogic.Retiro(user, movimientoValue);
            if (isDone) {
                PopupInfo("Movimiento exitoso", "Movimiento realizado");
            }
            else {
                PopupError("Movimiento fallido", "Hubo un fallo al realizar el movimiento");
            }
        }
    }

    private void ButtonDepositoActionPerformed(java.awt.event.ActionEvent evt) {
        this.ButtonRetiro.setSelected(false);
    }

    private void ButtonRetiroActionPerformed(java.awt.event.ActionEvent evt) {
        this.ButtonDeposito.setSelected(false);
    }

    private void ButtonTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {
        int destinoId = this.ComboBoxUsuarioDestino.getSelectedIndex();
        int origenId = this.ComboBoxUsuarioOrigen.getSelectedIndex();
        float movimientoValue = (float)LeerInputSpinner(this.InputTransferencia);
        if (destinoId != -1 && origenId != -1) {
            Usuario destino = this.usuarios.get(destinoId);
            Usuario origen = this.usuarios.get(origenId);
            boolean isDone = this.movLogic.Transferencia(origen, destino, movimientoValue);
            if (isDone) {
                PopupInfo("Transaccion exitosa", "Realizada");
            }
            else {
                PopupError("Transaccion fallido", "Hubo un fallo al realizar la transaccion");
            }
        }
    }

    private JRadioButton ButtonDeposito;
    private JRadioButton ButtonRetiro;
    private JButton ButtonSave;
    private JButton ButtonTransferencia;
    private JComboBox<String> ComboBoxUsuarioDestino;
    private JComboBox<String> ComboBoxUsuarioOrigen;
    private JComboBox<String> ComboBoxUsuarios;
    private JSpinner InputMovimiento;
    private JSpinner InputTransferencia;
    private JPanel MovimientosPanel;
    private ButtonGroup TipoMovimientoButtonGroup;
    private JPanel TransferenciaPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JSeparator jSeparator1;
    private JTabbedPane jTabbedPane1;

    private void CrearLayout() {
        javax.swing.GroupLayout MovimientosPanelLayout = new javax.swing.GroupLayout(MovimientosPanel);
        MovimientosPanel.setLayout(MovimientosPanelLayout);
        MovimientosPanelLayout.setHorizontalGroup(
            MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MovimientosPanelLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addGroup(MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MovimientosPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboBoxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MovimientosPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MovimientosPanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(ButtonDeposito)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonRetiro))
                            .addGroup(MovimientosPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InputMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(133, 133, 133))
        );
        MovimientosPanelLayout.setVerticalGroup(
            MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MovimientosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MovimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonDeposito)
                    .addComponent(ButtonRetiro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonSave)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout TransferenciaPanelLayout = new javax.swing.GroupLayout(TransferenciaPanel);
        TransferenciaPanel.setLayout(TransferenciaPanelLayout);
        TransferenciaPanelLayout.setHorizontalGroup(
            TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransferenciaPanelLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransferenciaPanelLayout.createSequentialGroup()
                        .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(TransferenciaPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InputTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ButtonTransferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransferenciaPanelLayout.createSequentialGroup()
                        .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransferenciaPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboBoxUsuarioOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBoxUsuarioDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124))))
        );
        TransferenciaPanelLayout.setVerticalGroup(
            TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransferenciaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxUsuarioOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxUsuarioDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TransferenciaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonTransferencia)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
    }
}
