public class AlphaBeta {

  public static void main(String[] args) {
    C4State game = new C4State();
    try {
      int gameVal = negamax(game,Integer.MIN_VALUE,Integer.MAX_VALUE);
      System.out.println(gameVal);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
    
    public static int negamax(C4State state, int alpha, int beta) throws CloneNotSupportedException {
        if (state.isGameOver) {
          if (state.winner == 0)
            return 0;
          return -1;
        }
    
        int value = Integer.MIN_VALUE;
        for (int move : state.getValidMoves()) {
          C4State child = (C4State) state.clone();
          child.move(move);
          value = Math.max(value, -negamax(child, -beta, -alpha));
          alpha = Math.max(alpha, value);
          if (beta <= alpha) {
            break;
          }
        }
        return value;
      }
    


}
