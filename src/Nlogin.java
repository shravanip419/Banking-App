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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Nlogin extends JFrame {
    Nlogin() {
        new Font("Futura", 1, 30);
        Font f2 = new Font("Calibri", 0, 18);
        JLabel l1 = new JLabel("Set Username");
        JTextField t1 = new JTextField(10);
        JLabel l2 = new JLabel("Set Password");
        JTextField t2 = new JTextField(10);
        JLabel l3 = new JLabel("Confirm Password");
        JTextField t3 = new JTextField(10);
        JLabel l4 = new JLabel("Phone");
        JTextField t4 = new JTextField(15);
        JLabel l5 = new JLabel("Email");
        JTextField t5 = new JTextField(20);
        JLabel l6 = new JLabel("Gender");
        JComboBox<String> genderBox = new JComboBox(new String[]{"male", "female", "other"});
        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");
        JLabel title = new JLabel("Signup", 0);
        title.setFont(new Font("Arial", 1, 28));
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        l3.setFont(f2);
        t3.setFont(f2);
        l4.setFont(f2);
        t4.setFont(f2);
        l5.setFont(f2);
        t5.setFont(f2);
        l6.setFont(f2);
        genderBox.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        int labelX = 200;
        int fieldX = 400;
        int yStart = 80;
        int width = 150;
        int height = 30;
        int gap = 40;
        title.setBounds(300, 10, 200, 40);
        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);
        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);
        l3.setBounds(labelX, yStart + 2 * gap, width, height);
        t3.setBounds(fieldX, yStart + 2 * gap, width, height);
        l4.setBounds(labelX, yStart + 3 * gap, width, height);
        t4.setBounds(fieldX, yStart + 3 * gap, width, height);
        l5.setBounds(labelX, yStart + 4 * gap, width, height);
        t5.setBounds(fieldX, yStart + 4 * gap, width, height);
        l6.setBounds(labelX, yStart + 5 * gap, width, height);
        genderBox.setBounds(fieldX, yStart + 5 * gap, width, height);
        b1.setBounds(250, yStart + 6 * gap, 120, 40);
        b2.setBounds(400, yStart + 6 * gap, 120, 40);
        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l3);
        c.add(t3);
        c.add(l4);
        c.add(t4);
        c.add(l5);
        c.add(t5);
        c.add(l6);
        c.add(genderBox);
        c.add(b1);
        c.add(b2);
        b1.addActionListener((a) -> {
            if (t2.getText().equals(t3.getText())) {
                String url = "jdbc:mysql://localhost:3306/3dec";

                try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                    String sql = "INSERT INTO users(username,password,phone,email,gender) VALUES(?, ? , ?, ?, ?)";

                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, t1.getText());
                        pst.setString(2, t2.getText());
                        pst.setString(3, t4.getText());
                        pst.setString(4, t5.getText());
                        pst.setString(5, genderBox.getSelectedItem().toString());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog((Component)null, "Signup Successful");
                        new Home(t1.getText());
                        this.dispose();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog((Component)null, e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog((Component)null, "Passwords do not match");
            }

        });
        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Signup");
    }

    public static void main(String[] args) {
        new Nlogin();
    }
}
