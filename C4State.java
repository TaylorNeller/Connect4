import java.util.ArrayList;
import java.util.Scanner;

public class C4State implements Cloneable {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            
            C4State game = new C4State();
            HumanPlayer p1 = new HumanPlayer();
            ABPlayer p2 = new ABPlayer();
            Player[] players = {p2,p1};
            while (true) {
                System.out.println(game.toString());
                System.out.println("c4 eval: "+AlphaBeta.negamax(game, Integer.MIN_VALUE, Integer.MAX_VALUE));
                System.out.print("Player "+(game.player==1?1:2)+" move: ");
                int move = players[(game.player==1?0:1)].determineMove(game);
                if (!(players[(game.player==1?0:1)] instanceof HumanPlayer))
                    System.out.println(move);

                    
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int rows = 4;
    int cols = 5;
    int[][] board = new int[rows][cols];
    int player = 1; //1 and -1
    int[] nextOpen = new int[cols];
    int winner = 0;
    int turn = 0;
    boolean isGameOver = false;

    C4State() {
        
    }

    public ArrayList<Integer> getValidMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int c = 0; c < cols; c++) {
            if (nextOpen[c] != rows) {
                moves.add(c);
                // System.out.println(nextOpen[c] +" != "+(rows-1));
            }
        }
        return moves;
    }

    public boolean move(int c) {
        turn++;
        int r = nextOpen[c];
        board[r][c] = player;
        nextOpen[c]++;
        player = player*-1;
        boolean over = isGameOver(r,c); //added this to move for less function calls
        isGameOver = over;
        return over;
    }

    public boolean isGameOver(int r, int c) {
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
                switch (board[rows-r-1][c]) {
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


    @Override
    public Object clone() throws CloneNotSupportedException {
        C4State child = (C4State) super.clone();
        int[][] childArr = new int[rows][];
        for (int r = 0; r < rows; r++) {
            childArr[r] = board[r].clone();
        }
        child.board = childArr;
        child.nextOpen = nextOpen.clone();
        return child;
    }

}
