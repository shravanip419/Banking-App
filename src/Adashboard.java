//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class Adashboard extends JFrame {
    Adashboard() {
        Font titleFont = new Font("Futura", 1, 40);
        Font tableFont = new Font("Calibri", 0, 18);
        Font buttonFont = new Font("Calibri", 1, 20);
        JLabel title = new JLabel("Admin Dashboard", 0);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] columnNames = new String[]{"Username", "Balance", "Phone", "Email", "Gender", "WLimit"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", 1, 18));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));
        JScrollPane scrollPane = new JScrollPane(table);
        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);
        JButton b1 = new JButton("Filter");
        b1.setFont(buttonFont);
        b1.setBackground(new Color(0, 153, 76));
        b1.setForeground(Color.WHITE);
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(224, 224, 224));
        filterPanel.add(new JLabel("Min Balance:"));
        filterPanel.add(t1);
        filterPanel.add(new JLabel("Max Balance:"));
        filterPanel.add(t2);
        filterPanel.add(b1);
        JButton b2 = new JButton("Back");
        b2.setFont(buttonFont);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(255, 51, 51));
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        b2.addActionListener((ex) -> {
            new Alogin();
            this.dispose();
        });
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.add(title, "Center");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(b2);
        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.add(filterPanel, "North");
        centerPanel.add(scrollPane, "Center");
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, "North");
        c.add(centerPanel, "Center");
        c.add(bottomPanel, "South");
        b1.addActionListener((a) -> {
            tableModel.setRowCount(0);
            String str1 = t1.getText();
            String str2 = t2.getText();
            double min;
            if (str1.isEmpty()) {
                min = (double)0.0F;
            } else {
                min = Double.parseDouble(str1);
            }

            double max;
            if (str2.isEmpty()) {
                max = Double.MAX_VALUE;
            } else {
                max = Double.parseDouble(str2);
            }

            String url = "jdbc:mysql://localhost:3306/3dec";

            try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                String sql = "select * from users where balance between " + min + " and " + max;

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    ResultSet rs = pst.executeQuery();

                    while(rs.next()) {
                        String s1 = rs.getString("username");
                        double d1 = rs.getDouble("balance");
                        String s2 = rs.getString("phone");
                        String s3 = rs.getString("email");
                        String s4 = rs.getString("gender");
                        double d2 = rs.getDouble("wlimit");
                        tableModel.addRow(new Object[]{s1, d1, s2, s3, s4, d2});
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog((Component)null, e.getMessage());
            }

        });
        String url = "jdbc:mysql://localhost:3306/3dec";

        try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
            String sql = "select * from users";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    String s1 = rs.getString("username");
                    double d1 = rs.getDouble("balance");
                    String s2 = rs.getString("phone");
                    String s3 = rs.getString("email");
                    String s4 = rs.getString("gender");
                    double d2 = rs.getDouble("wlimit");
                    tableModel.addRow(new Object[]{s1, d1, s2, s3, s4, d2});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component)null, e.getMessage());
        }

        this.setTitle("Admin Dashboard");
        this.setSize(900, 600);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Adashboard();
    }
}
