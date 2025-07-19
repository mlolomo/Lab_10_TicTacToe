import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            // Clear the board and set the player to X
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();
                System.out.println("Player " + player + "'s turn");

                int row = SafeInput.getRangedInt(in, "Please, enter row", 1, 3);
                int col = SafeInput.getRangedInt(in, "Please, enter column", 1, 3);

                row--;
                col--;

                // Loop until move is valid
                if (isValidMove(row, col)) {

                    // Record the move
                    board[row][col] = player;

                    // Increment
                    moveCount++;

                    // Check for win or tie
                    if (moveCount >= 5 && isWin(player)) {
                        display();
                        System.out.println("Player " + player + " WINS!");
                        gameOver = true;
                    } else if (moveCount == 9 && isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        gameOver = true;
                    } else {
                        player = player.equals("X") ? "O" : "X";
                    }
                } else {
                    // Invalid move
                    System.out.println("Invalid move. Try again; space already taken.");
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
        } while (playAgain);

        System.out.println("Thank you for playing!");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("\n  1   2   3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("  ---------");
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) &&
                    board[i][1].equals(player) &&
                    board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(player) &&
                    board[1][j].equals(player) &&
                    board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
