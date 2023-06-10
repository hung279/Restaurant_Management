/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

<<<<<<< HEAD
import file.GetData;
import file.ManageFileUser;
import java.util.List;
import javax.swing.JOptionPane;
import model.Bill;

=======
import javax.swing.JOptionPane;
>>>>>>> c0c7b4a92e097fdb6edbfa85eb182ca1cfead36b

/**
 *
 * @author Đoàn Hữu Minh
 */
public class Register extends javax.swing.JFrame {

    private static GetData getData = new GetData();    
    private static final ManageFileUser manageFileUser = new ManageFileUser();   
    private static List<model.Customer> listUser = getData.getDataUserFromFile();

    private static final String file_users = "E:\\HOC TAP\\LAP_TRINH_JAVA\\PROJECT JAVA\\Restaurant_Management\\src\\datas\\users.txt";

    public Register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        button_login_2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jUserIdRegister = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jFullnameRegister = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPhoneRegister = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jUsername_register = new javax.swing.JTextField();
        jradio_male_register = new javax.swing.JRadioButton();
        jFemale_register = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPassword_register = new javax.swing.JPasswordField();
        button_register1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Are you have account?");

        button_login_2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button_login_2.setText("Login ");
        button_login_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_login_2ActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Login to your account now?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(button_login_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(button_login_2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Register now");

        jLabel2.setText("Please provide your information to register ");

        jLabel3.setText("new account");

        jLabel4.setText("User id");

        jUserIdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUserIdRegisterActionPerformed(evt);
            }
        });

        jLabel5.setText("Fullname");

        jFullnameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFullnameRegisterActionPerformed(evt);
            }
        });

        jLabel6.setText("Phone");

        jPhoneRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPhoneRegisterActionPerformed(evt);
            }
        });

        jLabel7.setText("Gender");

        buttonGroup1.add(jradio_male_register);
        jradio_male_register.setText("Male");
        jradio_male_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradio_male_registerActionPerformed(evt);
            }
        });

        buttonGroup1.add(jFemale_register);
        jFemale_register.setText("Female");
        jFemale_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFemale_registerActionPerformed(evt);
            }
        });

        jLabel8.setText("Username");

        jLabel9.setText("Password");

        button_register1.setBackground(new java.awt.Color(18, 143, 93));
        button_register1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button_register1.setForeground(new java.awt.Color(255, 255, 255));
        button_register1.setText("Register");
        button_register1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_register1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jUserIdRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(jFullnameRegister)
                                    .addComponent(jPhoneRegister)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPassword_register, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jUsername_register, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jradio_male_register, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFemale_register, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(button_register1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUserIdRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFullnameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPhoneRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFemale_register)
                    .addComponent(jradio_male_register)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUsername_register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword_register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(button_register1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jUserIdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUserIdRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUserIdRegisterActionPerformed

    private void jFullnameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFullnameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFullnameRegisterActionPerformed

    private void jFemale_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFemale_registerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFemale_registerActionPerformed

    private void jradio_male_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradio_male_registerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jradio_male_registerActionPerformed

    private void button_login_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_login_2ActionPerformed
 
       //XSSFWorkbook 
        Login login = new Login();
           login.setVisible(true);
           
           Register register = new Register();
           register.dispose();
    }//GEN-LAST:event_button_login_2ActionPerformed
    public Boolean checkInputUser(String userId ,String username , String password){
 
       for(model.Customer customer : listUser){
           if(customer.getId().equals(userId)){
            JOptionPane.showMessageDialog(this, "UserID can not duplicate!");
            return false;    
           }
           if(customer.getUsername().equals(username)){
            JOptionPane.showMessageDialog(this, "Username can not duplicate!");
            return false;   
           }
       }
       if(password.equals("")){
           JOptionPane.showMessageDialog(this, "Password is not null");
           return false;
       }
        return true;
    }
    private void button_register1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_register1ActionPerformed
<<<<<<< HEAD

        
                Object[] user = new Object[6];
                user[0] = jUserIdRegister.getText();
                user[1] = !"".equals(jFullnameRegister.getText()) ? jFullnameRegister.getText() : "null";
                user[2] = !"".equals(jPhoneRegister.getText()) ? jPhoneRegister.getText() : "null";
                if(jradio_male_register.isSelected() == true){
                      user[3] = "Male";
                }else if(jFemale_register.isSelected() == true){
                    user[3] = "Female";
                }else{
                      user[3] = "Other";
                 }
                user[4] = jUsername_register.getText();
                char[] characterPass = jPassword_register.getPassword();
                String password = new  String(characterPass);
                user[5] = password; 
                if(checkInputUser(user[0].toString() , user[4].toString(), password)){
                     
                model.Customer newUser = new model.Customer(user[0].toString() , user[1].toString() , user[2].toString() , 
                                        user[3].toString() , user[4].toString() , user[5].toString(), "ROLE_USER");                
                manageFileUser.writeUserToFile(file_users, newUser);
                JOptionPane.showMessageDialog(this, "Add category successfull");
                
                this.dispose();
                Login login = new Login();
                login.setVisible(true);
           }else{
                    return;
             }
=======
        JOptionPane.showMessageDialog(this, "Register successful!");
        jFullname_register.setText("");
        jAddress_register.setText("");
        jPhone_register.setText("");
        jUsername_register.setText("");
        jPassword_register.setText("");
>>>>>>> c0c7b4a92e097fdb6edbfa85eb182ca1cfead36b
       
    }//GEN-LAST:event_button_register1ActionPerformed

    private void jPhoneRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPhoneRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPhoneRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Register().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_login_2;
    private javax.swing.JButton button_register1;
<<<<<<< HEAD
=======
    private javax.swing.JTextField jAddress_register;
>>>>>>> c0c7b4a92e097fdb6edbfa85eb182ca1cfead36b
    private javax.swing.JRadioButton jFemale_register;
    private javax.swing.JTextField jFullnameRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword_register;
<<<<<<< HEAD
    private javax.swing.JTextField jPhoneRegister;
    private javax.swing.JTextField jUserIdRegister;
=======
    private javax.swing.JTextField jPhone_register;
>>>>>>> c0c7b4a92e097fdb6edbfa85eb182ca1cfead36b
    private javax.swing.JTextField jUsername_register;
    private javax.swing.JRadioButton jradio_male_register;
    // End of variables declaration//GEN-END:variables
}
