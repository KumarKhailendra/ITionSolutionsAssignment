package ITionSolutions;

import java.util.ArrayList;
import java.util.List;

public class OrchardSizes {
    public static void main(String[] args) {
        char[][] matrix = {
                {'O','T','O','O'},
                {'O','T','O','T'},
                {'T','T','O','T'},
                {'O','T','O','T'}
        };

        List<Integer> orchardSizes = computeOrchardSizes(matrix);
        for (int size : orchardSizes) {
            System.out.print(size + " ");
        }
    }

    public static List<Integer> computeOrchardSizes(char[][] matrix) {
        List<Integer> orchardSizes = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'T' && !visited[i][j]) {
                    int orchardSize = dfs(matrix, i, j, visited);
                    orchardSizes.add(orchardSize);
                }
            }
        }

        return orchardSizes;
    }

    private static int dfs(char[][] matrix, int row, int col, boolean[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[row][col] == 'O' || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;
        int size = 1;

        // Check all neighboring cells (horizontal, vertical, diagonal)
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            size += dfs(matrix, newRow, newCol, visited);
        }

        return size;
    }
}

