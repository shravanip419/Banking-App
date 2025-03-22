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

class Profile extends JFrame {
    String result = "";

    Profile(String username) {
        Font f = new Font("Futura", 1, 35);
        Font f2 = new Font("Calibri", 0, 20);
        JLabel title = new JLabel("Profile Settings", 0);
        title.setFont(f);
        JLabel l1 = new JLabel("Select Field to Update:");
        JComboBox<String> box = new JComboBox(new String[]{"Username", "Password", "Phone", "Email", "wlimit"});
        JLabel l2 = new JLabel("Enter New Value:");
        JTextField t1 = new JTextField(15);
        JButton b1 = new JButton("Update");
        JButton b2 = new JButton("Back");
        l1.setFont(f2);
        box.setFont(f2);
        l2.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        title.setBounds(250, 20, 300, 40);
        l1.setBounds(200, 100, 200, 30);
        box.setBounds(400, 100, 200, 30);
        l2.setBounds(200, 160, 200, 30);
        t1.setBounds(400, 160, 200, 30);
        b1.setBounds(250, 220, 120, 40);
        b2.setBounds(400, 220, 120, 40);
        c.add(title);
        c.add(l1);
        c.add(box);
        c.add(l2);
        c.add(t1);
        c.add(b1);
        c.add(b2);
        b2.addActionListener((a) -> {
            new Home(username);
            this.dispose();
        });
        b1.addActionListener((a) -> {
            String s1 = box.getSelectedItem().toString().toLowerCase();
            String s2 = t1.getText();
            if (s2.isEmpty()) {
                JOptionPane.showMessageDialog((Component)null, "Cannot be Empty");
            } else {
                if (s1.equals("username")) {
                    this.dispose();
                    new Profile(s2);
                }

                String url = "jdbc:mysql://localhost:3306/3dec";

                try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                    String sql = "update users set " + s1 + "=? where username=?";

                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, s2);
                        pst.setString(2, username);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog((Component)null, "Updated");
                        t1.setText("");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog((Component)null, e.getMessage());
                    return;
                }

                if (s1.equals("username")) {
                    this.dispose();
                    new Profile(s2);

                    try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                        String sql = "update transactions set " + s1 + "=? where username=?";

                        try (PreparedStatement pst = con.prepareStatement(sql)) {
                            pst.setString(1, s2);
                            pst.setString(2, username);
                            pst.executeUpdate();
                            t1.setText("");
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
        this.setTitle("Profile Settings");
    }

    void changeUsername(String old, String naya) {
        String s1 = "username";
        if (naya.isEmpty()) {
            JOptionPane.showMessageDialog((Component)null, "Enter ");
        } else {
            String url = "jdbc:mysql://localhost:3306/3dec";

            try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                String sql = "update users set username=? where username=?";

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, naya);
                    pst.setString(2, old);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog((Component)null, "Updated");
                    this.result = naya;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog((Component)null, e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new Profile("Bhakti");
    }
}
