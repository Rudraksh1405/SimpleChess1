import java.util.Scanner;

public class SimpleChess {
    static char[][] board = new char[8][8];

    public static void main(String[] args) {
        initBoard();
        printBoard();

        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        while (true) {
            System.out.println((whiteTurn ? "White" : "Black") + "'s move (e.g. 1 0 2 0): ");
            int r1 = scanner.nextInt();
            int c1 = scanner.nextInt();
            int r2 = scanner.nextInt();
            int c2 = scanner.nextInt();

            if (isValidMove(r1, c1, r2, c2, whiteTurn)) {
                makeMove(r1, c1, r2, c2);
                printBoard();
                whiteTurn = !whiteTurn;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    static void initBoard() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = 'P'; // White pawns
            board[6][i] = 'p'; // Black pawns
        }
        // Rooks
        board[0][0] = 'R'; board[0][7] = 'R';
        board[7][0] = 'r'; board[7][7] = 'r';

        // Empty spaces
        for (int i = 2; i <= 5; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = '.';
    }

    static void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isValidMove(int r1, int c1, int r2, int c2, boolean whiteTurn) {
        char piece = board[r1][c1];
        if (whiteTurn && Character.isLowerCase(piece)) return false;
        if (!whiteTurn && Character.isUpperCase(piece)) return false;
        
        // Allow only pawns & rooks for simplicity
        if (Character.toUpperCase(piece) == 'P') {
            int dir = (piece == 'P') ? 1 : -1;
            if (c1 == c2 && board[r2][c2] == '.' && r2 == r1 + dir) return true;
        }
        if (Character.toUpperCase(piece) == 'R') {
            if (r1 == r2 || c1 == c2) return true;
        }
        return false;
    }

    static void makeMove(int r1, int c1, int r2, int c2) {
        board[r2][c2] = board[r1][c1];
        board[r1][c1] = '.';
    }
}
