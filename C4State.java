import java.util.Scanner;

public class C4State {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        C4State game = new C4State();
        while (true) {
            System.out.println(game.toString());
            System.out.print("Player "+(game.player==1?1:2)+" move: ");
            int move = in.nextInt();
            if (game.move(move))
                break;
        }
        System.out.println(game.toString());
        if (game.winner == 0)
            System.out.println("Draw!");
        else if (game.winner == 1)
            System.out.println("Player 1 wins!");
        else
            System.out.println("Player 2 wins!");
        in.close();
    }

    int rows = 6;
    int cols = 7;
    int[][] board = new int[rows][cols];
    int player = 1; //1 and -1
    int[] nextOpen = new int[cols];
    int winner = 0;
    int turn = 0;

    C4State() {
        
    }

    public boolean move(int c) {
        turn++;
        int r = rows-nextOpen[c]-1;
        System.out.println(r);
        board[r][c] = player;
        nextOpen[c]++;
        player = player*-1;
        // return isGameOver(r,c); //added this to move for less function calls
        if (turn == rows*cols) {
            return true;
        }
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr != 0 || dc != 0) {
                    boolean line = true;
                    for (int i = 1; i <= 3; i++) {
                        int newR = r+dr*i;
                        int newC = c+dc*i;
                        if (newR >= rows || newR < 0 || newC >= cols || newC < 0) {
                            line = false;
                            break;
                        }
                        if (board[newR][newC] != board[r][c])
                            line = false;
                    }
                    if (line) {
                        winner = board[r][c];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (board[r][c]) {
                    case 1: 
                        sb.append("X");
                        break;
                    case -1: 
                        sb.append("O");
                        break;
                    case 0: 
                        sb.append("-");
                        break;
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // public boolean isGameOver(int r, int c) {
    //     if (turn == rows*cols) {
    //         return true;
    //     }
    //     for (int dr = -1; dr <= 1; dr++) {
    //         for (int dc = -1; dc <= 1; dc++) {
    //             if (dr != 0 || dc != 0) {
    //                 boolean line = true;
    //                 for (int i = 1; i <= 3; i++) {
    //                     if (board[r+dr*i][c+dc*i] != board[r][c])
    //                         line = false;
    //                 }
    //                 if (line) {
    //                     winner = board[r][c];
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }

}
