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

class Apage extends JFrame {
    Apage() {
        Font f = new Font("Futura", 1, 40);
        Font f2 = new Font("Calibri", 0, 22);
        JLabel l1 = new JLabel("Welcome Admin", 0);
        JLabel l2 = new JLabel();
        JButton b1 = new JButton("Logout");
        JButton b2 = new JButton("Show All Users");
        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        Container c = this.getContentPane();
        c.setLayout((LayoutManager)null);
        l1.setBounds(110, 30, 600, 50);
        b2.setBounds(250, 100, 300, 40);
        b1.setBounds(250, 160, 300, 40);
        l2.setBounds(250, 220, 300, 30);
        c.add(l1);
        c.add(b2);
        c.add(b1);
        c.add(l2);
        this.setVisible(true);
        this.setSize(800, 550);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Admin Page");
    }

    public static void main(String[] args) {
        new Apage();
    }
}
