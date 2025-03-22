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

class Home extends JFrame {
    Home(String username) {
        double balance = (double)0.0F;
        Font f = new Font("Futura", 1, 40);
        Font f2 = new Font("Calibri", 0, 22);
        JLabel title = new JLabel("Welcome " + username, 0);
        JLabel balanceLabel = new JLabel("Balance: ₹0.00", 0);
        JButton b1 = new JButton("Deposit");
        JButton b2 = new JButton("Withdraw");
        JButton b3 = new JButton("Profile Settings");
        JButton b4 = new JButton("Transfer");
        JButton b5 = new JButton("Passbook");
        JButton b6 = new JButton("Logout");
        title.setFont(f);
        balanceLabel.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);
        b5.setFont(f2);
        b6.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        title.setBounds(100, 30, 600, 50);
        balanceLabel.setBounds(100, 100, 600, 30);
        b1.setBounds(100, 150, 200, 40);
        b2.setBounds(400, 150, 200, 40);
        b3.setBounds(100, 220, 200, 40);
        b4.setBounds(400, 220, 200, 40);
        b5.setBounds(100, 290, 200, 40);
        b6.setBounds(400, 290, 200, 40);
        c.add(title);
        c.add(balanceLabel);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
        c.add(b5);
        c.add(b6);
        b5.addActionListener((a) -> {
            new Passbook(username);
            this.dispose();
        });
        b4.addActionListener((a) -> new Transfer(username));
        b1.addActionListener((a) -> {
            new Deposit(username);
            this.dispose();
        });
        b6.addActionListener((a) -> {
            new Landing();
            this.dispose();
        });
        b2.addActionListener((a) -> {
            new Withdraw(username);
            this.dispose();
        });
        b3.addActionListener((a) -> new Profile("Bhakti"));
        String url = "jdbc:mysql://localhost:3306/3dec";

        try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
            String sql = "select balance from users where username=?";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }

                balanceLabel.setText("Balance: ₹" + balance);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component)null, e.getMessage());
        }

        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Home");
    }

    public static void main(String[] args) {
        new Home("Bhakti");
    }
}
