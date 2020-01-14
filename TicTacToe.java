import java.util.*;
import java.io.*;
import java.math.*;

class Grid{
  public final static int ROW = 3;
  public final static int COL = 3;

  private Map<String, Grid> sons = new HashMap<>();
  private boolean myturn;
  private int score;
  private int remaining_turn;
  private GridIndex grid_index;//coup actuel
  public Case[][] board;

  //constructor
  public Grid(int remaining_turn, boolean myturn, GridIndex gi){
    this.remaining_turn = remaining_turn;
    this.myturn = myturn;
    this.grid_index = gi;
    board = new Case[Grid.ROW][Grid.COL];
    makeBoard(gi);
  }
   public Grid(int remaining_turn, boolean myturn, GridIndex gi, Case[][] b){
    this.remaining_turn = remaining_turn;
    this.myturn = myturn;
    this.board = b.clone();

  }

  private void makeBoard(GridIndex gi){
    if(gi.getI() != -1 && gi.getJ() != -1)
      this.board[gi.getI()][gi.getJ()] = new Case(this.getSign(), gi);

    for (int i = 0;i < 3 ;i++ ) {
      for (int j = 0;j < 3 ;j++ ) {
        if(this.board[i][j] == null)
          this.board[i][j] = new Case(i,j);
      }
    }
  }


  public Grid makeBranch(int remaining_turn, boolean myturn, int depth, GridIndex gi){
    if (depth < 0){
      return null;
    }
    int n = 0 ;
    Grid g = new Grid(remaining_turn, myturn, gi);

    if(n < remaining_turn && depth >= 1 ){
      for (int i = 0; i < Grid.ROW; i++ ) {
        for (int j = 0; j < Grid.COL; j++ ) {
          g.board[i][j] = new Case(i,j);
          g.board[i][j] = this.board[i][j]; //je voulais copier ma case mais ...
          if(board[i][j].isPlayable()){
            g.board[i][j].play(getSign());
            g.sons.put(""+n+i+j, makeBranch(remaining_turn - 1, !myturn, depth - 1, new GridIndex(i,j) ));
            g.board[i][j].redo();
            n++;
          }
        }
      }
    }
    return g;
  }

  public int getSign(){
    if(this.remaining_turn%2 == 0)
      return 2;
    return 1;
  }

  public Case[][] getBoard(){
    return this.board;
  }

  public void displayBoard(){
    for (int i = 0; i < 3; i++ ) {
      for (int j = 0; j < 3; j++ ) {
        System.out.print("|"+this.board[i][j]);
      }
      System.out.println("|");
    }
  }
  @Override
  public String toString()
  {
    String res = "(";
    res += this.grid_index;
    displayBoard();
    for(Grid son : sons.values())
      res += ", " +this.grid_index+son.toString();
    res += ")";
    return res;
  }
}

class GridIndex{
  private int i, j;

  public GridIndex(int i, int j){
    this.i = i;
    this.j = j;
  }

  public int getI(){
    return this.i;
  }

  public int getJ(){
    return this.j;
  }
  public String toString(){
    return "{ "+this.i+" "+this.j+" }";
  }
}
class Case{
  public final static int NEUTRE  = 0;
  public final static int CROSS   = 1;
  public final static int ZERO    = 2;
  public final static GridIndex[] RATIO = {
      new GridIndex(0,0),new GridIndex(0,1),new GridIndex(0,2),
      new GridIndex(1,0),new GridIndex(1,1),new GridIndex(1,2),
      new GridIndex(2,0),new GridIndex(2,1),new GridIndex(2,2)
  };

  private int type;
  private GridIndex index;

  public Case(int i, int j){
    this.type = NEUTRE;
    this.index = new GridIndex(i,j);
  }
  public Case(GridIndex gi){
    this.type = NEUTRE;
    this.index = gi;
  }
  public Case(int type, int i, int j){
    this.type = type;
    this.index = new GridIndex(i,j);
  }

  public Case(int type, GridIndex gi){
    this.type = type;
    this.index = gi;
  }
  public int getType(){
    return this.type;
  }

  public GridIndex getIndex(){
    return this.index;
  }

  public boolean setType(int actual_sign){
    if(actual_sign == 1 || actual_sign == 2){
      this.type = actual_sign;
      return true;
    }else
    return false;
  }

  public void play(int actual_sign){
    if(this.type == NEUTRE && actual_sign == 1 || actual_sign == 2)
      this.type = actual_sign;
  }
  public void redo(){
    if(this.type == 1 || this.type == 2)
      this.type = NEUTRE;
  }
  public boolean isPlayable(){
    if(this.type == NEUTRE)
      return true;
    return false;
  }

  public String toString(){
    if(this.type == ZERO)
      return "O";
    else if(this.type == CROSS)
      return "X";
    else
      return " ";
  }
}
class Player {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    Grid g;
    GridIndex gi;
    int validActionCount = Grid.ROW * Grid.COL;
    int opponentRow = in.nextInt();
    int opponentCol = in.nextInt();
        // while (true) {
        //     int opponentRow = in.nextInt();
        //     int opponentCol = in.nextInt();
        //     int validActionCount = in.nextInt();
        //     for (int i = 0; i < validActionCount; i++) {
        //         int row = in.nextInt();
        //         int col = in.nextInt();
        //     }
            gi = new GridIndex(opponentRow,opponentCol);
            g = new Grid(validActionCount,true,gi);
            g = g.makeBranch(validActionCount,true,2,new GridIndex(0,0));


            System.out.println(g);
            System.out.println("Fin de transmision");
        //}
  }
}
