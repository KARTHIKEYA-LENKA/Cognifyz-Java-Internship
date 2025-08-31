import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;

        System.out.println("Welcome to Tic-Tac-Toe!");

        do {
            resetBoard();
            char currentPlayer = 'X';
            boolean gameEnded = false;

            while (!gameEnded) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1-3): ");

                int row = sc.nextInt() - 1;
                int col = sc.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid move! Try again.");
                } else if (board[row][col] != ' ') {
                    System.out.println("Cell already taken! Try again.");
                } else {
                    board[row][col] = currentPlayer;
                    if (checkWinner(currentPlayer)) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameEnded = true;
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        gameEnded = true;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = sc.next().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("Thanks for playing Tic-Tac-Toe!");
        sc.close();
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static boolean checkWinner(char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Diagonals
        return ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player));
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
