import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Home extends JFrame {
    Home(String username) {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Welcome " + username, JLabel.CENTER);
        JLabel balanceLabel = new JLabel("Balance: ₹0.00", JLabel.CENTER);
        JButton b1 = new JButton("Deposit");
        JButton b2 = new JButton("Withdraw");
        JButton b3 = new JButton("Profile Settings");
        JButton b4 = new JButton("Transfer");
        JButton b5 = new JButton("Passbook");
        JButton b6 = new JButton("Logout");

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\Shravani\\Documents\\GitHub\\Banking-App1\\src\\bg1.jpeg"));
        background.setBounds(0, 0, 800, 550);
        setContentPane(background);
        background.setLayout(null);

        // Fonts
        title.setFont(f);
        balanceLabel.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);
        b5.setFont(f2);
        b6.setFont(f2);

        // Transparent overlay panel
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                g2d.setColor(new Color(255, 255, 255, 180));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.dispose();
            }
        };
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(null);
        overlayPanel.setBounds(50, 20, 700, 480);

        // Title panel with background box
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        titlePanel.setLayout(new BorderLayout());
        title.setForeground(Color.BLACK);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setBounds(100, 30, 600, 60);

        // Balance panel with background box
        JPanel balancePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 180));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        balancePanel.setLayout(new BorderLayout());
        balanceLabel.setForeground(Color.BLACK);
        balanceLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        balancePanel.add(balanceLabel, BorderLayout.CENTER);
        balancePanel.setBounds(100, 100, 600, 40);

        // Button bounds
        b1.setBounds(100, 150, 200, 40);
        b2.setBounds(400, 150, 200, 40);
        b3.setBounds(100, 220, 200, 40);
        b4.setBounds(400, 220, 200, 40);
        b5.setBounds(100, 290, 200, 40);
        b6.setBounds(400, 290, 200, 40);

        // Add components to overlay panel
        overlayPanel.add(titlePanel);
        overlayPanel.add(balancePanel);
        overlayPanel.add(b1);
        overlayPanel.add(b2);
        overlayPanel.add(b3);
        overlayPanel.add(b4);
        overlayPanel.add(b5);
        overlayPanel.add(b6);

        background.add(overlayPanel);

        // Button actions
        b1.addActionListener(a -> {
            new Deposit(username);
            dispose();
        });

        b2.addActionListener(a -> {
            new Withdraw(username);
            dispose();
        });

        b3.addActionListener(a -> {
            new Profile("Bhakti");
        });

        b4.addActionListener(a -> {
            new Transfer(username);
        });

        b5.addActionListener(a -> {
            new Passbook(username);
            dispose();
        });

        b6.addActionListener(a -> {
            new Landing();
            dispose();
        });

        // Get balance from DB
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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Final JFrame setup
        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");
    }

    public static void main(String[] args) {
        new Home("Shravani");
    }
}
