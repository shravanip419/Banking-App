//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Landing extends JFrame {
    Landing() {
        Font f = new Font("futura", 1, 40);
        Font f2 = new Font("Calibri", 0, 22);
        JLabel l1 = new JLabel("Virtual Banking System", 0);
        JButton b1 = new JButton("Admin");
        JButton b2 = new JButton("Existing Customer");
        JButton b3 = new JButton("New Customer");
        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        l1.setBounds(150, 50, 500, 50);
        b1.setBounds(300, 150, 200, 50);
        b2.setBounds(300, 230, 200, 50);
        b3.setBounds(300, 310, 200, 50);
        c.add(l1);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        b1.addActionListener((a) -> {
            new Nlogin();
            this.dispose();
        });
        b2.addActionListener((a) -> {
            new Elogin();
            this.dispose();
        });
        b3.addActionListener((a) -> {
            new Nlogin();
            this.dispose();
        });
        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Landing Page");
    }

    public static void main(String[] args) {
        new Landing();
    }
}
