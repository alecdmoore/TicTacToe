import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class TicTacToeController {

  private TicTacToeView view;
  private TicTacToeModel model;
  
  public TicTacToeController(TicTacToeView v, TicTacToeModel m) {
    view = v;
    model = m;
    
    this.view.addBoardSquareListener(new BoardSquareListener());
    this.view.addUndoListener(new UndoListener());
    this.view.addNewGameListener(new NewGameListener());

  }
  
  private class BoardSquareListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      int x,y;
      
      JButton temp = (JButton) e.getSource();
      x = (int) temp.getClientProperty('x');  
      y = (int) temp.getClientProperty('y');  
      
      if(model.getGameState()) return;
      
      if(model.boxFull(x, y)) return;
      
      model.addMove(x, y);
    }
    
  }
  
  private class UndoListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      model.undo();
    }
    
  }
  
  private class NewGameListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      model.startNewGame();
    }
  }
}
