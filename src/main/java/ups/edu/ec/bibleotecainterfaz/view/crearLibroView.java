/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ups.edu.ec.bibleotecainterfaz.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import ups.edu.ec.bibleotecainterfaz.models.Autor;
/**
 *
 * @author stephancedillo
 */
public class CrearLibroView extends javax.swing.JInternalFrame {

    /**
     * Creates new form crearLibroView
     */
    public CrearLibroView() {
        initComponents();
        ((AbstractDocument) getTxtISBN().getDocument())
                .setDocumentFilter(new LimiteCaracteres(14));

        ((AbstractDocument) getTxtNombre().getDocument())
                .setDocumentFilter(new LimiteCaracteres(10));

        ((AbstractDocument) getTxtIdioma().getDocument())
                .setDocumentFilter(new LimiteCaracteres(12));

        ((AbstractDocument) getTxtNombreAutor().getDocument())
                .setDocumentFilter(new LimiteCaracteres(10));

        ((AbstractDocument) getTxtApellido().getDocument())
                .setDocumentFilter(new LimiteCaracteres(10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        lblPreguntaExistenciaAutor = new javax.swing.JLabel();
        lblNombre2 = new javax.swing.JLabel();
        txtNombreAutor = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JFormattedTextField();
        btnCrearAutor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblTituloCreacionLibro = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblISBN = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNumeroPagina = new javax.swing.JLabel();
        txtIdioma = new javax.swing.JTextField();
        txtNumeroPaginas = new javax.swing.JTextField();
        lblIdioma = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        radioButtonRestriccion = new javax.swing.JRadioButton();
        comboBoxAutores = new javax.swing.JComboBox<>();
        txtComboGenero = new javax.swing.JComboBox<>();

        jPanel7.setBackground(new java.awt.Color(249, 245, 245));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setForeground(new java.awt.Color(249, 245, 245));

        lblPreguntaExistenciaAutor.setBackground(new java.awt.Color(51, 51, 51));
        lblPreguntaExistenciaAutor.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblPreguntaExistenciaAutor.setForeground(new java.awt.Color(51, 51, 51));
        lblPreguntaExistenciaAutor.setText("Si el autor no está en el sistema, créalo aquí:");

        lblNombre2.setBackground(new java.awt.Color(51, 51, 51));
        lblNombre2.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblNombre2.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre2.setText("Nombre:");

        txtNombreAutor.addActionListener(this::txtNombreAutorActionPerformed);

        lblApellido.setBackground(new java.awt.Color(51, 51, 51));
        lblApellido.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(51, 51, 51));
        lblApellido.setText("Apellido:");

        txtApellido.addActionListener(this::txtApellidoActionPerformed);

        btnCrearAutor.setBackground(new java.awt.Color(51, 51, 51));
        btnCrearAutor.setText("Crear Autor");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPreguntaExistenciaAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreAutor))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCrearAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblPreguntaExistenciaAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearAutor)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(240, 237, 237));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Luminari", 3, 18)); // NOI18N
        jLabel2.setText("Andresito Books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(101, 101, 101))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(249, 245, 245));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTituloCreacionLibro.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        lblTituloCreacionLibro.setText("Creación de Libro");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lblTituloCreacionLibro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloCreacionLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblISBN.setBackground(new java.awt.Color(51, 51, 51));
        lblISBN.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblISBN.setForeground(new java.awt.Color(51, 51, 51));
        lblISBN.setText("ISBN:");

        lblNombre.setBackground(new java.awt.Color(51, 51, 51));
        lblNombre.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre.setText("Nombre:");

        lblGenero.setBackground(new java.awt.Color(51, 51, 51));
        lblGenero.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(51, 51, 51));
        lblGenero.setText("Genero:");

        lblNumeroPagina.setBackground(new java.awt.Color(51, 51, 51));
        lblNumeroPagina.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblNumeroPagina.setForeground(new java.awt.Color(51, 51, 51));
        lblNumeroPagina.setText("Numero de paginas:");

        lblIdioma.setBackground(new java.awt.Color(51, 51, 51));
        lblIdioma.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblIdioma.setForeground(new java.awt.Color(51, 51, 51));
        lblIdioma.setText("Idioma:");

        lblAutor.setBackground(new java.awt.Color(51, 51, 51));
        lblAutor.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        lblAutor.setForeground(new java.awt.Color(51, 51, 51));
        lblAutor.setText("Autor:");

        btnAceptar.setBackground(new java.awt.Color(51, 51, 51));
        btnAceptar.setText("Aceptar");

        radioButtonRestriccion.setFont(new java.awt.Font("ITF Devanagari", 0, 14)); // NOI18N
        radioButtonRestriccion.setForeground(new java.awt.Color(0, 0, 0));
        radioButtonRestriccion.setText("Marca si es solo para mayores");
        radioButtonRestriccion.addActionListener(this::radioButtonRestriccionActionPerformed);

        comboBoxAutores.setFont(new java.awt.Font("ITF Devanagari", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtComboGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtISBN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(lblISBN)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGenero)
                        .addGap(120, 120, 120))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(188, 188, 188))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(comboBoxAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioButtonRestriccion, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdioma)
                            .addComponent(txtIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumeroPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblNumeroPagina)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblISBN)
                .addGap(1, 1, 1)
                .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblGenero))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdioma)
                    .addComponent(lblNumeroPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(radioButtonRestriccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptar)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getLblTituloCreacionLibro() {
        return lblTituloCreacionLibro;
    }

    public JButton getBtnCrearAutor() {
        return btnCrearAutor;
    }
    

    
    
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JComboBox<Autor> getComboBoxAutores() {
        return comboBoxAutores;
    }

    public JLabel getLblApellido() {
        return lblApellido;
    }

    public JLabel getLblAutor() {
        return lblAutor;
    }

    public JLabel getLblGenero() {
        return lblGenero;
    }

    public JLabel getLblISBN() {
        return lblISBN;
    }

    public JLabel getLblIdioma() {
        return lblIdioma;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public JLabel getLblNombre2() {
        return lblNombre2;
    }

    public JLabel getLblNumeroPagina() {
        return lblNumeroPagina;
    }

    public JLabel getLblPreguntaExistenciaAutor() {
        return lblPreguntaExistenciaAutor;
    }

    public JRadioButton getRadioButtonRestriccion() {
        return radioButtonRestriccion;
    }

    public JFormattedTextField getTxtApellido() {
        return txtApellido;
    }

    public JComboBox<String> getTxtComboGenero() {
        return txtComboGenero;
    }

   

    public JTextField getTxtISBN() {
        return txtISBN;
    }

    public JTextField getTxtIdioma() {
        return txtIdioma;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtNombreAutor() {
        return txtNombreAutor;
    }

    public JTextField getTxtNumeroPaginas() {
        return txtNumeroPaginas;
    }
    
    
    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtNombreAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAutorActionPerformed

    private void radioButtonRestriccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonRestriccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonRestriccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCrearAutor;
    private javax.swing.JComboBox<Autor> comboBoxAutores;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNumeroPagina;
    private javax.swing.JLabel lblPreguntaExistenciaAutor;
    private javax.swing.JLabel lblTituloCreacionLibro;
    private javax.swing.JRadioButton radioButtonRestriccion;
    private javax.swing.JFormattedTextField txtApellido;
    private javax.swing.JComboBox<String> txtComboGenero;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtIdioma;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNumeroPaginas;
    // End of variables declaration//GEN-END:variables
}
