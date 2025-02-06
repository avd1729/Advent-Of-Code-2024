package day_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Directions for searching: (row change, col change)
    private static final int[][] DIRECTIONS = {
            {0, 1},   // Right (→)
            {0, -1},  // Left (←)
            {1, 0},   // Down (↓)
            {-1, 0},  // Up (↑)
            {1, 1},   // Diagonal Down-Right (↘)
            {-1, -1}, // Diagonal Up-Left (↖)
            {1, -1},  // Diagonal Down-Left (↙)
            {-1, 1}   // Diagonal Up-Right (↗)
    };

    public static int wordCount(char[][] grid, String word) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate over each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If first letter matches, check all directions
                if (grid[i][j] == word.charAt(0)) {
                    for (int[] dir : DIRECTIONS) {
                        if (searchWord(grid, word, i, j, dir[0], dir[1])) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean searchWord(char[][] grid, String word, int row, int col, int rowDir, int colDir) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLen = word.length();

        // Check if the word can fit in this direction
        for (int k = 0; k < wordLen; k++) {
            int newRow = row + k * rowDir;
            int newCol = col + k * colDir;

            // If out of bounds or character doesn't match, return false
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(k)) {
                return false;
            }
        }
        return true; // Word is found in this direction
    }

    public static int findXMAS(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate over each possible center for 'A'
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 'A' && isValidXMAS(grid, i, j)) {
                    count++; // Found a valid X-MAS pattern
                }
            }
        }
        return count;
    }

    private static boolean isValidXMAS(char[][] grid, int row, int col) {

        if (row < 1 || row >= grid.length - 1 || col < 1 || col >= grid[0].length - 1) {
            return false;
        }

        // Check each diagonal for either "MAS" or "SAM"
        boolean topLeftToBottomRight =
                (grid[row-1][col-1] == 'M' && grid[row+1][col+1] == 'S') ||
                        (grid[row-1][col-1] == 'S' && grid[row+1][col+1] == 'M');

        boolean topRightToBottomLeft =
                (grid[row-1][col+1] == 'M' && grid[row+1][col-1] == 'S') ||
                        (grid[row-1][col+1] == 'S' && grid[row+1][col-1] == 'M');

        return topLeftToBottomRight && topRightToBottomLeft;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/day_04/data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Verify we have data and all lines are the same length
        if (lines.isEmpty()) {
            throw new IOException("File is empty");
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        // Validate that all rows have the same length
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).length() != cols) {
                throw new IOException("Inconsistent row length at line " + (i + 1));
            }
        }

        // Create and populate the 2D array
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        int ans = wordCount(grid, "XMAS");
        System.out.println(ans);
        int xmas = findXMAS(grid);
        System.out.println(xmas);

    }


}



