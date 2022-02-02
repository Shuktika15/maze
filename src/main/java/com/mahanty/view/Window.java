package com.mahanty.view;


import com.mahanty.service.MazeService;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final ColorSomething color;
    private final JTextArea textArea = new JTextArea();
    private final JButton startButton = new JButton();
    private final MazeService mazeService = new MazeService();

    public Window() {
        super("Maze");
        color = new ColorSomething();
        createLayout();
        createFrame();
    }

    private void createLayout() {
        setLayout(new BorderLayout());
        add(color, BorderLayout.CENTER);
    }

    private void createFrame() {
        setBounds(25, 25, 800, 500);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
