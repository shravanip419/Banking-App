//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Elogin extends JFrame {
    Elogin() {
        Font f = new Font("Futura", 1, 40);
        Font f2 = new Font("Calibri", 0, 22);
        JLabel title = new JLabel("Login", 0);
        JLabel l1 = new JLabel("Enter Username");
        JTextField t1 = new JTextField(10);
        JLabel l2 = new JLabel("Enter Password");
        JPasswordField t2 = new JPasswordField(10);
        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");
        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        title.setBounds(250, 30, 300, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        l2.setBounds(250, 200, 300, 30);
        t2.setBounds(250, 240, 300, 30);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);
        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(b1);
        c.add(b2);
        b1.addActionListener((a) -> {
            String url = "jdbc:mysql://localhost:3306/3dec";

            try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                String sql = "select * from users where username=? and password=?";

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, t1.getText());
                    pst.setString(2, new String(t2.getPassword()));
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog((Component)null, "Success");
                        new Home(t1.getText());
                        this.dispose();
                    } else if (rs.equals("")) {
                        JOptionPane.showMessageDialog((Component)null, "Enter something");
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "User doesn't exist");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog((Component)null, e.getMessage());
            }

        });
        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Login");
    }

    public static void main(String[] args) {
        new Elogin();
    }
}
