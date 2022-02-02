package com.mahanty.service;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MazeService {
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

    public MazeService() {
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
                    deque.add(neighbour);
                }
            }

            Point lastPoint = deque.pop();

            if (lastPoint != null) {
                maze[lastPoint.x][lastPoint.y] = '*';
                visited.put(lastPoint, true);
                bfs(deque.peekFirst());
            }
        }
    }

    public void start() {
        deque.add(new Point(1, 0));
        bfs(new Point(1, 0));
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
}
