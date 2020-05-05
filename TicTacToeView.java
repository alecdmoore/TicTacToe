import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.geom.Line2D;


public class TicTacToeView extends JFrame{

  private JButton boardSquares[][];
  private JButton undo;
  
  public TicTacToeView() {
  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 800);
    this.setTitle("TicTacToe");
    
    JPanel thePanel = new JPanel();
    JPanel mPanel = new JPanel();
    
    mPanel.setLayout(new GridBagLayout());
    
    GridBagConstraints gridConstraints = new GridBagConstraints();
    
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 1;
    gridConstraints.gridwidth = 1;
    gridConstraints.gridheight = 1;
    gridConstraints.weightx = 50;
    gridConstraints.weighty = 50;
    gridConstraints.fill = GridBagConstraints.BOTH;
    
    boardSquares = new JButton[3][3];
    
    for(int i = 0; i < boardSquares.length; i++) {
      for(int j = 0; j < boardSquares[i].length; j++) {
        boardSquares[i][j] = new JButton();
        boardSquares[i][j].putClientProperty('x', i);
        boardSquares[i][j].putClientProperty('y', j);
        
        if(j ==2 && i ==2) {
          boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 0, 0, Color.GREEN ));
        }
        else if(j == 2) {
          boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 5, 0, Color.GREEN ));
        } else if(i == 2) {
          boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 0, 5, Color.GREEN ));
        } else {
          boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 5, 5, Color.GREEN ));
        }
        gridConstraints.gridx = j;
        gridConstraints.gridy = i;
        mPanel.add(boardSquares[i][j], gridConstraints);
      }
    }
    undo = new JButton("Undo");
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 4;
    gridConstraints.fill = GridBagConstraints.CENTER;
    
    mPanel.add(undo, gridConstraints);
    
    this.add(mPanel);
    
   
    this.setVisible(true);
  }
  
  public void updateBoard(char[][] board) {
    for(int i = 0; i < boardSquares.length; i++) {
      for(int j = 0; j < boardSquares[i].length; j++) {
        char temp = board[i][j];
        boardSquares[i][j].setText( Character.toString(temp) );
      }
    }
  }
  
  public void addBoardSquareListener(ActionListener clicked) {
    for(int i = 0; i < boardSquares.length; i++) {
      for(int j = 0; j < boardSquares[i].length; j++) {
        boardSquares[i][j].addActionListener(clicked);
      }
    }
  }
  
  public void addUndoListener(ActionListener clicked) {
    undo.addActionListener(clicked);
  }
}
