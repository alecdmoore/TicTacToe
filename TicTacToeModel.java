import java.util.ArrayList;

public class TicTacToeModel {

  private char[][] board = new char[3][3];
  private char playerMove;
  private boolean gameFinished;
  private char winner;
  private WinPath winningPath;
  private ArrayList<MoveNode> moves;
  
  public TicTacToeModel() {
    this.startNewGame();
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
  
  public class WinPath {
    private int beginRow;
    private int beginCol;
    private int endRow;
    private int endCol;
    
    public int getBeginRow() {return beginRow;}
    public int getBeginCol() {return beginCol;}
    public int getEndRow() {return endRow;}
    public int getEndCol() {return endCol;}
    
    
    public void setWinPath(int br, int bc, int er, int ec) {
      beginRow = br;
      beginCol = bc;
      endRow = er;
      endCol = ec;
      
    }
  }
  
  
  private void startNewGame() {
    
    gameFinished = false;
    winner = ' ';
    playerMove = 'x';
    winningPath = new WinPath();
    moves = new ArrayList<MoveNode>();
    
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        board[i][j] = ' ';
      }
    }
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
    winningPath.setWinPath(row, 0, row, 2);
    return true;
  }
  
  private boolean checkCol(int col) {
    for(int row = 1; row < board.length; row++) {
      if(board[row][col] != board[row - 1][col] || board[row][col] == ' ') {
        return false;
      }
    }
    winningPath.setWinPath(0, col, 2, col);
    return true;
  }
  
  private boolean checkDiagonal() {
    if(board[0][0] == board[2][2] && board[0][0] == board[1][1] && board[0][0] != ' ') {
      winningPath.setWinPath(0, 0, 2, 2);
      return true;
    } else if (board[0][2] == board[2][0] && board[0][2] == board[1][1] && board[0][2] != ' ') {
      winningPath.setWinPath(0, 2, 2, 0);
      return true;
    }
    return false;
  }
  
  public char getSquare(int row, int col) { return board[row][col]; }
  
  public WinPath getWinPath() {return winningPath; }
  
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
    }
  }
  
  public void changeTurn() {
    if(playerMove == 'x') {
      playerMove = 'o';
    } else {
      playerMove = 'x';
    }
  }
  
  public void undo() {
    //TODO check if list is null
    int endIndex = moves.size() - 1;
    if(moves.size() >= 3) {
      if(moves.get(endIndex).getPlayer() == moves.get(endIndex -1).getPlayer() && moves.get(endIndex -1).getPlayer()
          == moves.get(endIndex - 2).getPlayer()) {
        System.out.println("you cannot undo - 3 undos");
        return;
      }
    }
    MoveNode temp = moves.get(endIndex);
    board[temp.getX()][temp.getY()] = ' ';
    changeTurn();
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
  
}
