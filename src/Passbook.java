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
import javax.swing.table.DefaultTableModel;

public class Passbook extends JFrame {
    Passbook(String username) {
        Font titleFont = new Font("Futura", 1, 40);
        Font tableFont = new Font("Calibri", 0, 18);
        Font buttonFont = new Font("Calibri", 1, 20);
        JLabel title = new JLabel("Passbook", 0);
        title.setFont(titleFont);
        title.setForeground(new Color(255, 255, 255));
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] columnNames = new String[]{"Date & Time", "Description", "Amount", "Balance"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", 1, 18));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));
        JScrollPane scrollPane = new JScrollPane(table);
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 51, 51));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.addActionListener((ex) -> {
            new Home(username);
            this.dispose();
        });
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.add(title, "Center");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(backButton);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, "North");
        c.add(scrollPane, "Center");
        c.add(bottomPanel, "South");
        String url = "jdbc:mysql://localhost:3306/3dec";

        try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
            String sql = "select * from transactions where username=? order by date desc";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    String s1 = rs.getString("date");
                    String s2 = rs.getString("description");
                    Double d1 = rs.getDouble("amount");
                    Double d2 = rs.getDouble("balance");
                    tableModel.addRow(new Object[]{s1, s2, d1, d2});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component)null, e.getMessage());
        }

        this.setTitle("Passbook");
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Passbook("Bhakti");
    }
}
