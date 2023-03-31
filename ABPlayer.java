public class ABPlayer extends Player {
    public int determineMove(C4State game) throws Exception{

            //   returns the best move using negamax with alpha-beta pruning
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        for (int move : game.getValidMoves()) {
            C4State child = (C4State) game.clone();
            child.move(move);
            int value = -AlphaBeta.negamax(child, -beta, -alpha);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        alpha = Math.max(alpha, value);
        }
        System.out.println("ab eval: "+bestValue);

        return bestMove;

    }
}
