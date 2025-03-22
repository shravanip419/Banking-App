//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
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
import javax.swing.JTextField;

class Withdraw extends JFrame {
    Withdraw(String username) {
        Font f = new Font("Futura", 1, 40);
        Font f2 = new Font("Calibri", 0, 22);
        JLabel title = new JLabel("Withdraw Money", 0);
        JLabel label = new JLabel("Enter Amount:");
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Withdraw");
        JButton b2 = new JButton("Back");
        title.setFont(f);
        label.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        c.setBackground(new Color(135, 206, 235));
        title.setBounds(200, 30, 400, 50);
        label.setBounds(250, 120, 300, 30);
        t1.setBounds(250, 160, 300, 30);
        b1.setBounds(300, 220, 200, 40);
        b2.setBounds(300, 280, 200, 40);
        c.add(title);
        c.add(label);
        c.add(t1);
        c.add(b1);
        c.add(b2);
        b2.addActionListener((a) -> {
            new Home(username);
            this.dispose();
        });
        b1.addActionListener((a) -> {
            double balance = (double)0.0F;
            double wlimit = (double)0.0F;
            String url = "jdbc:mysql://localhost:3306/3dec";

            try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                String sql = "select balance,wlimit from users where username=?";

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, username);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        balance = rs.getDouble("balance");
                        wlimit = rs.getDouble("wlimit");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog((Component)null, e.getMessage());
            }

            String s1 = t1.getText();
            if (s1.isEmpty()) {
                JOptionPane.showMessageDialog((Component)null, "Please enter value");
            } else {
                double amount = Double.parseDouble(s1);
                if (balance < amount) {
                    JOptionPane.showMessageDialog((Component)null, "amount exceed the balance");
                } else if (amount > wlimit) {
                    JOptionPane.showMessageDialog((Component)null, "withdraw limit exceeded");
                } else {
                    double total = balance - amount;

                    try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                        String sql = "update users set balance=? where username=?";

                        try (PreparedStatement pst = con.prepareStatement(sql)) {
                            pst.setDouble(1, total);
                            pst.setString(2, username);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog((Component)null, "success");
                            t1.setText("");
                            this.updatePassbook(username, "Withdrawn", -amount, balance + amount);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog((Component)null, e.getMessage());
                    }
                }
            }

        });
        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Withdraw Money");
    }

    void updatePassbook(String username, String desc, double amount, double balance) {
        String url = "jdbc:mysql://localhost:3306/3dec";

        try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
            String sql = "insert into transactions (username,description,amount,balance) values (?,?,?,?)";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, desc);
                pst.setDouble(3, amount);
                pst.setDouble(4, balance);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component)null, e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Withdraw("Bhakti");
    }
}
