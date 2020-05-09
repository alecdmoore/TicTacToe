import java.util.ArrayList;

import javax.swing.event.*;

public class TicTacToeModel {

  private char[][] board = new char[3][3];
  private char playerMove;
  private boolean gameFinished;
  private char winner;
  private ArrayList<MoveNode> moves;
  private ArrayList<ChangeListener> listeners;
  
  public TicTacToeModel() {
	  listeners = new ArrayList<>();
  }
  
  
  public class MoveNode {
    private int xPos;
    private int yPos;
    private char player;
    
    public MoveNode(int x, int y, char p) {
      xPos = x;
      yPos = y;
      player = p;
    }
    
    public int getX() { return xPos;}
    public int getY() { return yPos;}
    public char getPlayer() { return player; }
    
  }
  
  
  public void addChangeListener(ChangeListener listener) {
	  listeners.add(listener);
  }
  
  private void notifyChangeListeners() {
	  ChangeEvent event = new ChangeEvent(this);
	  for (ChangeListener listener: listeners) {
		  listener.stateChanged(event);
	  }
  }
  
  public void startNewGame() {
    
    gameFinished = false;
    winner = ' ';
    playerMove = 'x';
    moves = new ArrayList<MoveNode>();
    
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        board[i][j] = ' ';
      }
    }
    notifyChangeListeners();
  }
  
  public boolean winCheck() {
    
    for(int i = 0; i < board.length; i++) {
      if(checkRow(i)) {
        return true;
      } else if (checkCol(i)) {
        return true;
      }
    }
    
    return checkDiagonal();
  }
  
  private boolean checkRow(int row) {
    for(int col = 1; col < board.length; col++) {
      if(board[row][col] != board[row][col - 1]  || board[row][col] == ' ') {
        return false;
      }
    }
    return true;
  }
  
  private boolean checkCol(int col) {
    for(int row = 1; row < board.length; row++) {
      if(board[row][col] != board[row - 1][col] || board[row][col] == ' ') {
        return false;
      }
    }
    return true;
  }
  
  private boolean checkDiagonal() {
    if(board[0][0] == board[2][2] && board[0][0] == board[1][1] && board[0][0] != ' ') {
      return true;
    } else if (board[0][2] == board[2][0] && board[0][2] == board[1][1] && board[0][2] != ' ') {
      return true;
    }
    return false;
  }
  
  public boolean getGameState() { return gameFinished; }
  
  public boolean boxFull(int x, int y) { 
    if(board[x][y] == ' ')
      return false;
    return true;
  }
  
  public void addMove(int x, int y) {
    //TODO check bounds
    if(!boxFull(x,y)) {
      board[x][y] = playerMove;
      moves.add(new MoveNode(x,y,playerMove));
      changeTurn();
      
      if(winCheck()) {
        gameFinished = true;
        changeTurn();
        winner = playerMove;
        System.out.println("game won: winner " + winner);
      }
      
      print();
      System.out.println();
      notifyChangeListeners();

    }
  }
  
  public void changeTurn() {
    if(playerMove == 'x') {
      playerMove = 'o';
    } else {
      playerMove = 'x';
    }
  }
  
  public boolean ableToUndo() {
	   int endIndex = moves.size() - 1;
	  if(moves.size() >= 3) {
	      if(moves.get(endIndex).getPlayer() == moves.get(endIndex -1).getPlayer() && moves.get(endIndex -1).getPlayer()
	          == moves.get(endIndex - 2).getPlayer()) {
	        System.out.println("you cannot undo - 3 undos");
	        return false;
	      }
	   }  
	  return true;
  }
  
  public void undo() {
    int endIndex = moves.size() - 1;
    if (!ableToUndo())
    {}
    else {
    MoveNode temp = moves.get(endIndex);
    board[temp.getX()][temp.getY()] = ' ';
    changeTurn();
    }
    notifyChangeListeners();
  }
  
  public void print() {
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public char[][] getBoard() { return board; }
  
  public char getWinner() { return winner; }
  
  public char getPlayerMove() { return playerMove; }
  
}
