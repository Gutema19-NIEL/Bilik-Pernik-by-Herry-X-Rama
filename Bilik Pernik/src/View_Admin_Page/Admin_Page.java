/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Admin_Page;


import Bilik_Pernik_Connection.BK_Connection;
import View_Opening_Page.Opening_Page_Pt_2;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author asuna
 */
public class Admin_Page extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Page
     */
    public Admin_Page() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Close_Eye_Btn = new javax.swing.JLabel();
        Nama_Admin_Field = new javax.swing.JTextField();
        Kata_Sandi_Field = new javax.swing.JPasswordField();
        Nama_Admin_Label = new javax.swing.JLabel();
        Masuk_Btn = new javax.swing.JLabel();
        Kata_Sandi_Label = new javax.swing.JLabel();
        False2_Label = new javax.swing.JLabel();
        False1_Label = new javax.swing.JLabel();
        Nama_Admin_Flabel = new javax.swing.JLabel();
        Password_Flabel = new javax.swing.JLabel();
        Back_Btn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Close_Eye_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Mata Tutup.png"))); // NOI18N
        Close_Eye_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Close_Eye_BtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Close_Eye_BtnMousePressed(evt);
            }
        });
        jPanel1.add(Close_Eye_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1616, 540, -1, -1));

        Nama_Admin_Field.setFont(new java.awt.Font("SansSerif", 0, 22)); // NOI18N
        Nama_Admin_Field.setForeground(new java.awt.Color(253, 253, 252));
        Nama_Admin_Field.setBorder(null);
        Nama_Admin_Field.setCaretColor(new java.awt.Color(253, 253, 252));
        Nama_Admin_Field.setDisabledTextColor(new java.awt.Color(253, 253, 252));
        Nama_Admin_Field.setOpaque(false);
        Nama_Admin_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Nama_Admin_FieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Nama_Admin_FieldFocusLost(evt);
            }
        });
        Nama_Admin_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nama_Admin_FieldKeyPressed(evt);
            }
        });
        jPanel1.add(Nama_Admin_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 408, 330, -1));

        Kata_Sandi_Field.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Kata_Sandi_Field.setForeground(new java.awt.Color(253, 253, 252));
        Kata_Sandi_Field.setBorder(null);
        Kata_Sandi_Field.setCaretColor(new java.awt.Color(253, 253, 252));
        Kata_Sandi_Field.setDisabledTextColor(new java.awt.Color(253, 253, 252));
        Kata_Sandi_Field.setOpaque(false);
        Kata_Sandi_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Kata_Sandi_FieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Kata_Sandi_FieldFocusLost(evt);
            }
        });
        jPanel1.add(Kata_Sandi_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 543, 340, -1));

        Nama_Admin_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Nama Admin atau Email.png"))); // NOI18N
        jPanel1.add(Nama_Admin_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1312, 415, -1, -1));

        Masuk_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Tombol Masuk 1.png"))); // NOI18N
        Masuk_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Masuk_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Masuk_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Masuk_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Masuk_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Masuk_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Masuk_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 652, -1, -1));

        Kata_Sandi_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Kata Sandi.png"))); // NOI18N
        jPanel1.add(Kata_Sandi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1312, 545, -1, -1));
        jPanel1.add(False2_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1234, 604, -1, -1));
        jPanel1.add(False1_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1234, 478, -1, -1));

        Nama_Admin_Flabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Nama Pengguna Email Field.png"))); // NOI18N
        jPanel1.add(Nama_Admin_Flabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1234, 389, -1, -1));

        Password_Flabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Kata Sandi Field.png"))); // NOI18N
        jPanel1.add(Password_Flabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1234, 518, -1, -1));

        Back_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Back_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 100, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Login Page.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void Masuk_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Masuk_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Masuk;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Masuk = new ImageIcon("src/Picture/Effect/Tombol Masuk 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Masuk_Btn.setIcon(Btn_Masuk);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        
                    ImageIcon Admin_Name,Admin_pass,salah_1,salah_2;/*Deklarasi variabel yang digunakan
                    untuk memanggil ImageIcon*/
                    Admin_Name = new ImageIcon("src/Picture/Effect/Username False 1.png");/*Format pemanggilan file ImageIcon
                    yang ingin dipanggil*/
                    salah_1 = new ImageIcon("src/Picture/Effect/Tidak Boleh Kosong.png");/*Format pemanggilan file ImageIcon
                    yang ingin dipanggil*/
                    salah_2 = new ImageIcon("src/Picture/Effect/Notif False Keduanya Salah.png");/*Format pemanggilan file ImageIcon
                    yang ingin dipanggil*/
                    Admin_pass = new ImageIcon("src/Picture/Effect/Password False 1.png");/*Format pemanggilan file ImageIcon
                    yang ingin dipanggil*/
                    
        PreparedStatement cmd;
        ResultSet rs;
        String Username = Nama_Admin_Field.getText();// Variabel untuk mengambil data username
        String Email = Username;// Variabel untuk mengambil data username
        String Password = Arrays.toString(Kata_Sandi_Field.getPassword());// Variabel untuk mengambil data password
        
        /*Try dan Catch digunakan untuk menangani error pada java, 
        eksekusi yang menampilkan error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        String sql = "select*from tb_admin where (username_admin=?  or email_admin=?) and password_admin=?"; /*Syntax pada mysql yang digunakan untuk melakukan pemasukkan data username dan password*/    
       
        try {
        cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
        cmd.setString(1, Nama_Admin_Field.getText());
        cmd.setString(2, Nama_Admin_Field.getText());
        cmd.setString(3, Kata_Sandi_Field.getText());
        rs =  cmd.executeQuery();  
        if(rs.next()){// Kondisi apabila username dan password benar
                new Admin_DB_Page(Username).setVisible(true);
                setVisible(false);
           }
        else{
            if(Username.isEmpty() || Password.isEmpty()){
                if(Username.isEmpty()){
                  
                    Nama_Admin_Flabel.setIcon(Admin_Name);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    False1_Label.setIcon(salah_1);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    
                }else if(Password.isEmpty()){
                   
                    Password_Flabel.setIcon(Admin_pass);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    False2_Label.setIcon(salah_1);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                }else {
                   
                    Nama_Admin_Flabel.setIcon(Admin_Name);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    False1_Label.setIcon(salah_1);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    Password_Flabel.setIcon(Admin_pass);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    False2_Label.setIcon(salah_1);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                }
            }else{
                Nama_Admin_Field.setText("");
                Nama_Admin_Flabel.setIcon(Admin_Name);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    Password_Flabel.setIcon(Admin_pass);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
                    False2_Label.setIcon(salah_2);/*Pallete pada java (berupa JLabel)
                    yang digunakan untuk melakukan set pada ImageIcon*/
            }
        }
      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
        

          
    }//GEN-LAST:event_Masuk_BtnMouseClicked

    private void Masuk_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Masuk_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Masuk;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Masuk = new ImageIcon("src/Picture/Effect/Tombol Masuk 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Masuk_Btn.setIcon(Btn_Masuk);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Masuk_BtnMouseEntered

    private void Masuk_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Masuk_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Masuk;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Masuk = new ImageIcon("src/Picture/Effect/Tombol Masuk 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Masuk_Btn.setIcon(Btn_Masuk);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Masuk_BtnMouseExited

    private void Masuk_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Masuk_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Masuk;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Masuk = new ImageIcon("src/Picture/Effect/Tombol Masuk 4.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Masuk_Btn.setIcon(Btn_Masuk);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Masuk_BtnMousePressed

    private void Masuk_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Masuk_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Masuk;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Masuk = new ImageIcon("src/Picture/Effect/Tombol Masuk 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Masuk_Btn.setIcon(Btn_Masuk);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Masuk_BtnMouseReleased

    private void Close_Eye_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close_Eye_BtnMouseClicked
        // TODO add your handling code here:
        
        ImageIcon Btn_Close;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Close = new ImageIcon("src/Picture/Effect/Mata Tutup.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Close_Eye_Btn.setIcon(Btn_Close);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Kata_Sandi_Field.setEchoChar('\u25CF');// membuat karakter yang telah di set '*' akan memunculkan karakter yang dimasukkan
    }//GEN-LAST:event_Close_Eye_BtnMouseClicked

    private void Close_Eye_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close_Eye_BtnMousePressed
        // TODO add your handling code here:
         ImageIcon Btn_Open;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Open = new ImageIcon("src/Picture/Effect/Mata Buka.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Close_Eye_Btn.setIcon(Btn_Open);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Kata_Sandi_Field.setEchoChar((char)0);// membuat karakter yang telah di set '*' akan memunculkan karakter yang dimasukkan
    }//GEN-LAST:event_Close_Eye_BtnMousePressed

    private void Nama_Admin_FieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nama_Admin_FieldFocusGained
        // TODO add your handling code here:
            Nama_Admin_Label.setVisible(false);
    }//GEN-LAST:event_Nama_Admin_FieldFocusGained

    private void Nama_Admin_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nama_Admin_FieldFocusLost
        // TODO add your handling code here:
        if(Nama_Admin_Field.getText().length() > 0){
            Nama_Admin_Label.setVisible(false);
        }else
            Nama_Admin_Label.setVisible(true);
    }//GEN-LAST:event_Nama_Admin_FieldFocusLost

    private void Nama_Admin_FieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nama_Admin_FieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){ // Kondisi apabila menggunakan tombol down pada keyboard
            Kata_Sandi_Field.requestFocus(); // Menuju JTextField yang berada dibawahnya
       }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){ // Kondisi apabila menggunakan tombol enter pada keyboard
            Kata_Sandi_Field.requestFocus();// Menuju JTextField yang berada dibawahnya
        }
    }//GEN-LAST:event_Nama_Admin_FieldKeyPressed

    private void Kata_Sandi_FieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Kata_Sandi_FieldFocusGained
        // TODO add your handling code here:
        Kata_Sandi_Label.setVisible(false);
    }//GEN-LAST:event_Kata_Sandi_FieldFocusGained

    private void Kata_Sandi_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Kata_Sandi_FieldFocusLost
        // TODO add your handling code here:
         if(Kata_Sandi_Field.getText().length() > 0){
            Kata_Sandi_Label.setVisible(false);
        }else
            Kata_Sandi_Label.setVisible(true);
    }//GEN-LAST:event_Kata_Sandi_FieldFocusLost

    private void Back_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Opening_Page_Pt_2 a = new Opening_Page_Pt_2();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Back_BtnMouseClicked

    private void Back_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseEntered

    private void Back_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseExited

    private void Back_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMousePressed

    private void Back_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Back_Btn;
    private javax.swing.JLabel Close_Eye_Btn;
    private javax.swing.JLabel False1_Label;
    private javax.swing.JLabel False2_Label;
    private javax.swing.JPasswordField Kata_Sandi_Field;
    private javax.swing.JLabel Kata_Sandi_Label;
    private javax.swing.JLabel Masuk_Btn;
    private javax.swing.JTextField Nama_Admin_Field;
    private javax.swing.JLabel Nama_Admin_Flabel;
    private javax.swing.JLabel Nama_Admin_Label;
    private javax.swing.JLabel Password_Flabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
