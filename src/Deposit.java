import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Deposit extends JFrame {
    Deposit(String username) {



        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        // Background image
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\Shravani\\Documents\\GitHub\\Banking-App1\\src\\bg1.jpeg"));
        background.setBounds(0, 0, 800, 550);
        setContentPane(background);
        background.setLayout(null);

        // Transparent overlay panel
        JPanel overlayPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.dispose();
            }
        };
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(null);
        overlayPanel.setBounds(100, 50, 600, 400);

        JLabel title = new JLabel("Deposit Money", JLabel.CENTER);
        JLabel label = new JLabel("Enter Amount:");
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Deposit");
        JButton b2 = new JButton("Back");

        // Fonts
        title.setFont(f);
        label.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        // Bounds
        title.setBounds(100, 20, 400, 50);
        label.setBounds(150, 100, 300, 30);
        t1.setBounds(150, 140, 300, 30);
        b1.setBounds(200, 200, 200, 40);
        b2.setBounds(200, 260, 200, 40);

        // Add to overlay
        overlayPanel.add(title);
        overlayPanel.add(label);
        overlayPanel.add(t1);
        overlayPanel.add(b1);
        overlayPanel.add(b2);

        background.add(overlayPanel);

        // Back button
        b2.addActionListener(a -> {
            new Home(username);
            dispose();
        });

        // Deposit button
        b1.addActionListener(a -> {
            double balance = 0.0;

            String url = "jdbc:mysql://localhost:3306/3dec";
            try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                String sql = "select balance from users where username=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, username);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        balance = rs.getDouble("balance");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            String s1 = t1.getText();
            if (s1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter value");
            } else {
                try {
                    double amount = Double.parseDouble(s1);
                    double total = amount + balance;
                    try (Connection con = DriverManager.getConnection(url, "root", "Bhakti@82")) {
                        String sql = "update users set balance=? where username=?";
                        try (PreparedStatement pst = con.prepareStatement(sql)) {
                            pst.setDouble(1, total);
                            pst.setString(2, username);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Deposit successful!");
                            t1.setText("");
                            updatePassbook(username, "deposit", amount, total);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid amount entered");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

        // Frame setup
        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Deposit Money");
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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Deposit("Shravani");
    }
}
