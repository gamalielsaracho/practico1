/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import practico.dao.MovimientoDAO;
import practico.modelo.Movimiento;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FormularioMovimiento extends javax.swing.JFrame {
    private MovimientoDAO daoMovimiento;
    
    /**
     * Creates new form FormularioMovimiento
     */
    public FormularioMovimiento() {
        daoMovimiento = new MovimientoDAO();
        initComponents();
    }
    
    public  void  listarMovimientosPorIdCuentaEnTabla(int idCuenta) {
         DefaultTableModel modelo = (DefaultTableModel)  tablaMovimiento.getModel(); 
        modelo.setRowCount(0);
        
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        movimientos = daoMovimiento.listarMovimientosPorIdCuenta(idCuenta);
        
        for (int i = 0; i < movimientos.size(); i = i+1)
        {   
            Movimiento mov= new Movimiento();
            mov=movimientos.get(i);
            
            modelo.addRow( new Object[]{
                    mov.getId_movimiento(), mov.getMonto(),
                    mov.getTipo(), mov.getFechaMovimiento()});
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMovimiento = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        montoInput = new javax.swing.JTextField();
        tiposCombo = new javax.swing.JComboBox();
        editar = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        clienteIdInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        movimientoIdInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bancoIdInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cuentaIdInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaMovimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código movimiento", "Monto", "Tipo", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tablaMovimiento);

        jLabel1.setText("Monto");

        jLabel2.setText("Tipo");

        tiposCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Debe", "Haber" }));
        tiposCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiposComboActionPerformed(evt);
            }
        });

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        clienteIdInput.setEditable(false);

        jLabel3.setText("Código cliente");

        jLabel4.setText("Código movimiento");

        movimientoIdInput.setEditable(false);

        jLabel5.setText("Código banco");

        bancoIdInput.setEditable(false);

        jLabel6.setText("Código cuenta");

        cuentaIdInput.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(limpiar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(montoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(27, 27, 27))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cuentaIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(tiposCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(clienteIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bancoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))))))
                        .addComponent(movimientoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clienteIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bancoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cuentaIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movimientoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiposCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(limpiar))
                .addGap(13, 13, 13)
                .addComponent(editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
         Movimiento oMovi = new Movimiento();
      
         int cuentaId = Integer.valueOf(cuentaIdInput.getText());
         
         int clienteId = Integer.valueOf(clienteIdInput.getText());
         int bancoId = Integer.valueOf(bancoIdInput.getText());
         
        if(!movimientoIdInput.getText().isEmpty()) { // Si No está vacio.
            
            oMovi.setId_movimiento(Integer.valueOf(movimientoIdInput.getText()));
            // oMovi.setId_banco(Integer.valueOf(idBancoInput.getText()));
       }
        
        oMovi.setMonto(Integer.valueOf(montoInput.getText()));
        
        oMovi.setTipo(tiposCombo.getSelectedItem().toString());
        
        // Cargamos las claves foraneas.
        oMovi.setId_cuenta(cuentaId);
        oMovi.setId_cliente(clienteId);
        oMovi.setId_banco(bancoId);
        
        daoMovimiento.verificarMovimiento(oMovi);
        
        listarMovimientosPorIdCuentaEnTabla(cuentaId);
        
        
        // LIMPIANDO EL FORMULARIO.
        int fila=tablaMovimiento.getSelectedRow();
       
        movimientoIdInput.setText("");
        montoInput.setText("");
        tiposCombo.setSelectedItem("Debe");
    }//GEN-LAST:event_agregarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        int fila=tablaMovimiento.getSelectedRow();
        
         
         String movimientoId = tablaMovimiento.getValueAt(fila, 0).toString();
         String monto = tablaMovimiento.getValueAt(fila, 1).toString();
         String tipo = tablaMovimiento.getValueAt(fila, 2).toString();
       
        movimientoIdInput.setText(movimientoId);
        montoInput.setText(monto);
        tiposCombo.setSelectedItem(tipo);
    }//GEN-LAST:event_editarActionPerformed

    private void tiposComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiposComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiposComboActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        int fila=tablaMovimiento.getSelectedRow();
       
        movimientoIdInput.setText("");
        montoInput.setText("");
        tiposCombo.setSelectedItem("Debe");
    }//GEN-LAST:event_limpiarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioMovimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    public javax.swing.JTextField bancoIdInput;
    public javax.swing.JTextField clienteIdInput;
    public javax.swing.JTextField cuentaIdInput;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    public javax.swing.JTextField montoInput;
    private javax.swing.JTextField movimientoIdInput;
    public javax.swing.JTable tablaMovimiento;
    private javax.swing.JComboBox tiposCombo;
    // End of variables declaration//GEN-END:variables
}