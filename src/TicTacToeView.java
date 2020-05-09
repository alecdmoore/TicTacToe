import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;



public class TicTacToeView{

  private JButton boardSquares[][];
  private JButton undo;
  private JButton newGame;
  private TicTacToeStyle style;
  private JLabel message;
  private JFrame boardView;
  private JLabel mPanel;
  private TicTacToeModel model;
  private JPanel buttonPanel;
  
  public TicTacToeView(TicTacToeModel m) {
	model = m;
	startMenu();
	createBoard();
	ChangeListener listenerx = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			updateBoard(model.getBoard(), model.getWinner(), model.getPlayerMove(), !model.ableToUndo());		}
	};
	model.addChangeListener(listenerx);

  }
  
  private void startMenu() {
	  JFrame startMenu = new JFrame();
	  startMenu.setSize(500, 500);
	  startMenu.setTitle("Start Menu");
	  startMenu.setLayout(new FlowLayout());
	  startMenu.add(new JLabel("Pick a style!"));
	  JButton spaceButton = new JButton("Space");
	  spaceButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			style = new SpaceStyle();
			updateStyle();
			startMenu.setVisible(false);
			boardView.setVisible(true);


		}
	  });
	  JButton otherButton = new JButton("Ocean");
	  otherButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			style = new OceanStyle();
			updateStyle();
			startMenu.setVisible(false);
			boardView.setVisible(true);
		}
	  });
	  startMenu.add(spaceButton);
	  startMenu.add(otherButton);

	  startMenu.setVisible(true);
  }
  
  private void createBoard() {
	    boardView = new JFrame();
		style = new SpaceStyle(); //default

		boardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    boardView.setSize(500, 500);
	    boardView.setTitle("TicTacToe");
	    
	    mPanel = new JLabel(style.getBackgroundIcon(800, 800));
	    
	    boardView.setLayout(new BorderLayout());
	    
	    mPanel.setLayout(new GridLayout(3,3));
	        
	    boardSquares = new JButton[3][3];
	    
	    for(int i = 0; i < boardSquares.length; i++) {
	      for(int j = 0; j < boardSquares[i].length; j++) {
	        boardSquares[i][j] = new JButton();
	        boardSquares[i][j].putClientProperty('x', i);
	        boardSquares[i][j].putClientProperty('y', j);
	        boardSquares[i][j].setOpaque(false);
	        boardSquares[i][j].setContentAreaFilled(false);
	        mPanel.add(boardSquares[i][j]);
	      }
	    }
	    setBorder();
	    undo = new JButton("Undo");
	    newGame = new JButton("Start New Game");
	    buttonPanel = new JPanel();
	    buttonPanel.add(undo);
	    buttonPanel.add(newGame);
	    message = new JLabel("Player 1's turn"); //wins, undo count
	    boardView.add(message, BorderLayout.NORTH);
	    boardView.add(buttonPanel, BorderLayout.SOUTH);
	    boardView.add(mPanel, BorderLayout.CENTER);
	    
	   
	    boardView.setVisible(false);
	    
  }
  
  private void updateStyle() {
	  setBorder();
	  mPanel.setIcon(style.getBackgroundIcon(800, 800));
	  
  }
  
  private void setBorder()
  {
    for(int i = 0; i < boardSquares.length; i++) {
    	for(int j = 0; j < boardSquares[i].length; j++) {
			  Color borderColor = style.getBorderColor();
		      if(j ==2 && i ==2) {
		        boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 0, 0, borderColor));
		      }
		      else if(j == 2) {
		        boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 5, 0, borderColor));
		      } else if(i == 2) {
		        boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 0, 5, borderColor));
		      } else {
		        boardSquares[i][j].setBorder( BorderFactory.createMatteBorder( 0, 0, 5, 5, borderColor));
		      }
    	}
    }
  }
  
  public void updateBoard(char[][] board, char winner, char playerMove, boolean undoFlag) {
    for(int i = 0; i < boardSquares.length; i++) {
      for(int j = 0; j < boardSquares[i].length; j++) {
    	JButton button =  boardSquares[i][j];        
    	char temp = board[i][j];
        if (temp == 'x') {
            button.setIcon(style.getXIcon(button.getWidth(), button.getHeight()));
            
        }
        else if (temp == 'o') {
        	button.setIcon(style.getOIcon(button.getWidth(), button.getHeight()));
        	
        }
        else
        	button.setIcon(new ImageIcon());
      }
    }
    if(playerMove == 'x') {
      message.setText("Player 1's turn");
    } else {
      message.setText("Player 2's turn");
    }
    
    if(undoFlag) {
      message.setText("Can not undo thrice. Next PLayer's turn");
    }
    
    if(winner != ' ') {
      if(winner == 'x') {
        message.setText("Player 1 Won");
      } else {
        message.setText("Player 2 Won");
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
  
  public void addNewGameListener(ActionListener clicked) {
    newGame.addActionListener(clicked);
  }
}
