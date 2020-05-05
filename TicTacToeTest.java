
public class TicTacToeTest {

  public static void main(String args[]) {
    
    TicTacToeModel model = new TicTacToeModel();
    TicTacToeView view = new TicTacToeView();
    TicTacToeController controller = new TicTacToeController(view,model);

  }
}
