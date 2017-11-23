/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import practico.dao.BancoDAO;
import practico.modelo.Banco;

/**
 *
 * @author Eli
 */
public class FormularioBanco extends javax.swing.JFrame {
    private BancoDAO dao;
    /**
     * Creates new form FormularioBanco
     */
    public FormularioBanco() {
        initComponents();
        
        dao = new BancoDAO();
        
        DefaultTableModel modelo = (DefaultTableModel)  tablaBanco.getModel(); 
        modelo.setRowCount(0);
        
        List<Banco> users = new ArrayList<Banco>();
        users = dao.listarBancos();
        
        for (int i = 0; i < users.size(); i = i+1) {   
            Banco b = new Banco();
            b = users.get(i);
            
            modelo.addRow( new Object[]{ b.getId_banco(), b.getNombre() } );
            
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBanco = new javax.swing.JTable();
        editar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        idBanco = new javax.swing.JTextField();
        limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingresar Banco");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        tablaBanco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(tablaBanco);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        jLabel2.setText("Código Banco");

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(editar)
                .addGap(27, 27, 27)
                .addComponent(eliminar)
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(idBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(limpiar))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar)
                            .addComponent(limpiar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar)
                    .addComponent(eliminar))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        
        Banco banco = new Banco();
       
        if(!idBanco.getText().isEmpty()) {
            banco.setId_banco(Integer.valueOf(idBanco.getText()));
        }
        
        banco.setNombre(nombre.getText());
       
        
        dao.verificarBanco(banco);
        
        DefaultTableModel modelo = (DefaultTableModel)  tablaBanco.getModel(); 
        modelo.setRowCount(0);
        
        List<Banco> users = new ArrayList<Banco>();
        users = dao.listarBancos();
        
        for (int i = 0; i < users.size(); i = i+1) {   
            Banco b = new Banco();
            b = users.get(i);
            
            modelo.addRow( new Object[]{ b.getId_banco(), b.getNombre() } );
            
       }
        
        idBanco.setText("");
        nombre.setText("");
  
       //  idCliente.setText("");
        
    }//GEN-LAST:event_agregarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        int fila = tablaBanco.getSelectedRow();
        String id_banco  = tablaBanco.getValueAt(fila, 0).toString();
        
        
        dao.eliminarBanco(id_banco);
       
       DefaultTableModel modelo = (DefaultTableModel)  tablaBanco.getModel(); 
        modelo.setRowCount(0);
        
        List<Banco> users = new ArrayList<Banco>();
        users = dao.listarBancos();
        
        for (int i = 0; i < users.size(); i = i+1) {   
            Banco b = new Banco();
            b = users.get(i);
            
            modelo.addRow( new Object[]{ b.getId_banco(), b.getNombre() } );
            
       }
    }//GEN-LAST:event_eliminarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        idBanco.setText("");
        nombre.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        
        int fila=tablaBanco.getSelectedRow();
       // int columna=tablaCliente.getSelectedColumn();
        
        String bancoId = tablaBanco.getValueAt(fila, 0).toString();
        String bancoNombre = tablaBanco.getValueAt(fila, 1).toString();
        
        
        idBanco.setText(bancoId);
        nombre.setText(bancoNombre);
        
    }//GEN-LAST:event_editarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioBanco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton editar;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField idBanco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tablaBanco;
    // End of variables declaration//GEN-END:variables
}