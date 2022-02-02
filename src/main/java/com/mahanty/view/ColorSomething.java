package com.mahanty.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ColorSomething extends JPanel implements ActionListener {
    private static final String mazeString = "################\n" +
            "............#..#\n" +
            "####..#..####..#\n" +
            "#.....#.....#..#\n" +
            "####..#######..#\n" +
            "#.....#.....#..#\n" +
            "#..#..#..####..#\n" +
            "#..#..#........#\n" +
            "####..####..#..#\n" +
            "#...........#..*\n" +
            "################";
    private final int rows;
    private final int columns;
    private char[][] maze;
    private Deque<Point> deque = new ArrayDeque<>();
    private Map<Point, Boolean> visited = new HashMap<>();
    private final Timer timer;

    public ColorSomething() {
        String[] m = mazeString.split("\n");
        rows = m.length;
        columns = m[0].length();
        maze = new char[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                maze[i][j] = m[i].charAt(j);
                visited.put(new Point(i, j), false);
            }
        }

        deque.add(new Point(1, 0));
        bfs(new Point(1, 0));
        timer = new Timer(5, this);
        timer.start();
    }

    private void bfs(Point point) {
        int[][] directions = {{1, -1, 0, 0}, {0, 0, 1, -1}};

        if (!deque.isEmpty()) {
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; ++i) {
                int m = x + directions[0][i];
                int n = y + directions[1][i];
                Point neighbour = new Point(m, n);

                if (m >= 0 && m < rows && n >= 0 && n < columns && maze[m][n] != '#' && !visited.get(neighbour)) {
                    maze[neighbour.x][neighbour.y] = '*';
                    deque.add(neighbour);
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char[][] getMaze() {
        return maze;
    }

    @Override
    public void paintComponent(Graphics m) {
        super.paintComponent(m);
        int rows = getRows();
        int columns = getColumns();
        char[][] maze = getMaze();
        Color transMagenta = new Color(255, 0, 255, 200);
        Color transCyan = new Color(0, 255, 255, 200);
        Graphics2D gb1 = (Graphics2D) m;
        GradientPaint a1 = new GradientPaint(50, 10, transMagenta, 550, 1100, transCyan);
        gb1.setPaint(a1);
        gb1.fillRect(0, 0, 2000, 598);

        for (int i = 15, a = 0; i < columns * 35; i += 35, a++) {
            for (int j = 0, b = 0; j < rows * 35; j += 35, b++) {
                int red = (int) (Math.random() * 256);
                int blue = (int) (Math.random() * 256);
                int green = (int) (Math.random() * 256);
                Color c = new Color(red, green, blue);

                red = (int) (Math.random() * 256);
                blue = (int) (Math.random() * 256);
                green = (int) (Math.random() * 256);

                Color transWhite = new Color(255, 255, 255, 140);
                m.setColor(transWhite);
                m.fillRoundRect(i + 0 + 3, j + 0 + 8 + 10, 35, 35, 10, 10);
                m.setColor(Color.white);
                m.drawRoundRect(i + 0 + 3, j + 0 + 8 + 10, 35, 35, 10, 10);

                if (maze[b][a] == '#') {
                    m.setColor(Color.GRAY);
                    m.fillRoundRect(i + 0 + 3, j + 0 + 8 + 10, 35, 35, 10, 10);
                    m.setColor(Color.black);
                    m.drawRoundRect(i + 0 + 3, j + 0 + 8 + 10, 35, 35, 10, 10);
                } else if (maze[b][a] == '*') {
                    m.setColor(Color.GREEN);
                    m.fillOval(i + 0 + 5, j + 0 + 10 + 10, 30, 30);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Point destination = new Point(rows - 2, columns - 1);

        if (!deque.isEmpty()) {
            Point lastPoint = deque.pop();

            if (lastPoint.equals(destination)) {
                deque.clear();
                return;
            }

            if (lastPoint != null) {
                visited.put(lastPoint, true);
                bfs(deque.peekFirst());
                repaint();
            }
        }
    }
}
