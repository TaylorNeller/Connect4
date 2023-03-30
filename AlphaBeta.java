public class AlphaBeta {
    
    public int negamax(int[] board, int depth, int alpha, int beta, int color) {
        if (depth == 0 || isGameOver(board)) {
          return color * evaluate(board);
        }
    
        int value = Integer.MIN_VALUE;
        for (int move : getValidMoves(board)) {
          int[] newBoard = makeMove(board, move);
          value = Math.max(value, -negamax(newBoard, depth - 1, -beta, -alpha, -color));
          alpha = Math.max(alpha, value);
          if (beta <= alpha) {
            break;
          }
        }
        return value;
      }
    
      // returns the best move using negamax with alpha-beta pruning
      public int getBestMove(int[] board, int depth) {
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int color = 1;
        for (int move : getValidMoves(board)) {
          int[] newBoard = makeMove(board, move);
          int value = -negamax(newBoard, depth - 1, -beta, -alpha, -color);
          if (value > bestValue) {
            bestValue = value;
            bestMove = move;
          }
          alpha = Math.max(alpha, value);
        }
        return bestMove;
      }

}
