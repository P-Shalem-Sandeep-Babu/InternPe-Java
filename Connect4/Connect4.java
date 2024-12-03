package Connect4;
import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER = 'X';
    private static final char AI = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLS];
        initializeBoard(board);
        boolean gameWon = false;
        char currentPlayer = PLAYER;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon && !isBoardFull(board)) {
            printBoard(board);
            int col;

            if (currentPlayer == PLAYER) {
                System.out.println("Your turn! Choose a column (1-7): ");
                col = scanner.nextInt() - 1;

                if (col < 0 || col >= COLS || board[0][col] != EMPTY) {
                    System.out.println("Invalid column! Try again.");
                    continue;
                }
            } else {
                System.out.println("AI's turn...");
                col = getBestMove(board);
            }

            int row = dropPiece(board, col, currentPlayer);
            if (checkWin(board, row, col, currentPlayer)) {
                printBoard(board);
                if (currentPlayer == PLAYER) {
                    System.out.println("Congratulations! You win!");
                } else {
                    System.out.println("AI wins! Better luck next time.");
                }
                gameWon = true;
            } else {
                currentPlayer = (currentPlayer == PLAYER) ? AI : PLAYER;
            }
        }

        if (!gameWon) {
            printBoard(board);
            System.out.println("It's a draw!");
        }
        scanner.close();
    }

    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private static int dropPiece(char[][] board, int col, char piece) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = piece;
                return i;
            }
        }
        return -1;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char[][] board, int row, int col, char piece) {
        return checkDirection(board, row, col, piece, 1, 0)
                || checkDirection(board, row, col, piece, 0, 1)
                || checkDirection(board, row, col, piece, 1, 1)
                || checkDirection(board, row, col, piece, 1, -1);
    }

    private static boolean checkDirection(char[][] board, int row, int col, char piece, int dRow, int dCol) {
        int count = 1;
        count += countPieces(board, row, col, piece, dRow, dCol);
        count += countPieces(board, row, col, piece, -dRow, -dCol);
        return count >= 4;
    }

    private static int countPieces(char[][] board, int row, int col, char piece, int dRow, int dCol) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == piece) {
            count++;
            r += dRow;
            c += dCol;
        }

        return count;
    }

    private static int getBestMove(char[][] board) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                int row = simulateDrop(board, col, AI);
                if (checkWin(board, row, col, AI)) {
                    undoDrop(board, col);
                    return col;
                }
                undoDrop(board, col);
            }
        }

        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                int row = simulateDrop(board, col, PLAYER);
                if (checkWin(board, row, col, PLAYER)) {
                    undoDrop(board, col);
                    return col;
                }
                undoDrop(board, col);
            }
        }

        if (board[0][COLS / 2] == EMPTY) {
            return COLS / 2;
        }

        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return col;
            }
        }

        return -1;
    }

    private static int simulateDrop(char[][] board, int col, char piece) {
        return dropPiece(board, col, piece);
    }

    private static void undoDrop(char[][] board, int col) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col] != EMPTY) {
                board[i][col] = EMPTY;
                return;
            }
        }
    }
}
