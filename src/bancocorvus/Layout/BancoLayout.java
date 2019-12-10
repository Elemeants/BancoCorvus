/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Layout;

import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BancoLayout extends JFrame {
    public BancoLayout() {
        crearComponentes();
    }
    
    private void crearComponentes() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Banco Corvus");
        setResizable(false);
        setSize(new java.awt.Dimension(600, 400));

        UsuariosTab = new UsuarioFormLayout();
        agregarUsuarioLayout1 = new AgregarUsuarioLayout();
        transferenciasLayout1 = new TransferenciasLayout();

        MainTabMenu = new JTabbedPane();
        MainTabMenu.setPreferredSize(new java.awt.Dimension(529, 367));
        MainTabMenu.addTab("Lista de Usuarios", UsuariosTab);
        MainTabMenu.addTab("Agregar usuario", agregarUsuarioLayout1);
        MainTabMenu.addTab("Transferencias", transferenciasLayout1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTabMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTabMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );
        pack();
    }
    
    private JTabbedPane MainTabMenu;
    private UsuarioFormLayout UsuariosTab;
    private AgregarUsuarioLayout agregarUsuarioLayout1;
    private TransferenciasLayout transferenciasLayout1;
}
