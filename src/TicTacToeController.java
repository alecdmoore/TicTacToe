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
      
      model.winCheck();
      
      view.updateBoard(model.getBoard(), model.getWinner(), model.getPlayerMove(), false);
    }
    
  }
  
  private class UndoListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      boolean undoFlag = model.undo();
      view.updateBoard(model.getBoard(), model.getWinner(), model.getPlayerMove(), undoFlag);
      
    }
    
  }
  
  private class NewGameListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      model.startNewGame();
      view.updateBoard(model.getBoard(), model.getWinner(), model.getPlayerMove(), false);
      
    }
  }
}
