package com.mahanty;

import com.mahanty.view.Window;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}
