package com.rsa.setool.logview;

import javax.swing.*;
import java.awt.*;

public class TabSample {
    static Color colors[] = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
            Color.MAGENTA };

    static void add(JTabbedPane tabbedPane, String label) {
        int count = tabbedPane.getTabCount();
        JButton button = new JButton(label);
        button.setBackground(colors[count]);
        tabbedPane.addTab(label, new ImageIcon("yourFile.gif"), button, label);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Tabbed Pane Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        String titles[] = { "A", "B", "C", "D", "E", "F" };
        for (int i = 0, n = titles.length; i < n; i++) {
            add(tabbedPane, titles[i]);
        }

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setSize(400, 150);
        frame.setVisible(true);
    }
}