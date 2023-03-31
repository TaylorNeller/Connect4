import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner in = new Scanner(System.in);
    public int determineMove(C4State game) {
        return in.nextInt();
    }
}
