import java.util.*;
import java.io.*;
import java.math.*;



// Class qui représente un grille de morpion
class Grid {
    private Case[][] grid;
    public static int mysign;
    private int player_sign;
    private int turn_counter;

    /*Constructeur*/
    public Grid(){
        grid = new Case[3][3];
        turn_counter = grid.length;
        player_sign = 1;
        this.mysign = mysign;
        for (int i = 0;i < 3 ;i++ ) {
            for (int j = 0;j < 3 ;j++ ) {
                grid[i][j] = new Case(i,j);
            }
        }
    }
    public boolean isTheEnd(){
        if(checkWinner() != 0 || this.turn_counter == 0 )
            return true;
        else
            return false;

    }
    public void setMySign(int sign){
        this.mysign = sign;
    }
    public Case getCase(GridIndex gi){
        return this.grid[gi.getI()][gi.getJ()];

    }
    public void displayGrid(){
        for (int i = 0; i < 3; i++ ) {
            for (int j = 0; j < 3; j++ ) {
                System.out.print("|"+grid[i][j]);
            }
            System.out.println("|");
        }
    }
    public int getPlayer_sign(){
        return this.player_sign;
    }
    public int getTurn(){
        return this.grid.length - this.turn_counter;
    }

    public void play(GridIndex gi, int player_sign){
        if(grid[gi.getI()][gi.getJ()].getType() == 0){
            grid[gi.getI()][gi.getJ()].setType(player_sign);
        }
        this.turn_counter--;
        this.checkWinner();
        this.changeTurn();
    }

    public void changeTurn(){
        if(this.turn_counter%2 == 0)
            this.player_sign = 2;
        else
            this.player_sign = 1;
    }
    public int checkWinner() {
        if(this.turn_counter <= 0)
            System.exit(0);
        for (int i = 0; i < 3; i++) {
            // check rows
            if (grid[i][0].getType() > 0 && grid[i][0].getType() == grid[i][1].getType() && grid[i][0].getType() == grid[i][2].getType()) {
                return grid[i][0].getType();
            }

            // check cols
            if (grid[0][i].getType() > 0 && grid[0][i].getType() == grid[1][i].getType() && grid[0][i].getType() == grid[2][i].getType()) {
                return grid[0][i].getType();
            }
        }

        // check diags
        if (grid[0][0].getType() > 0 && grid[0][0].getType() == grid[1][1].getType() && grid[0][0].getType() == grid[2][2].getType()) {
            return grid[0][0].getType();
        }
        if (grid[2][0].getType() > 0 && grid[2][0].getType() == grid[1][1].getType() && grid[2][0].getType() == grid[0][2].getType()) {
           return grid[2][0].getType();
        }

        return 0;
    }
    public int checkneighbor(){

        for (int i = 0; i < 3; i++) {
          if(grid[i][0].getType() > 0 && grid[i][1].getType() > 0){ // check up and middle of a row (wich row it is depends on i's value)
                if(grid[i][0].getType() == mysign && grid[i][1].getType() == mysign)
                    return 10;
                else if(grid[i][0].getType() != mysign && grid[i][1].getType() != mysign)
                    return -10;
            }

            if(grid[i][0].getType() > 0 && grid[i][2].getType() > 0){ // check up and down of a row (wich row it is depends on i's value)
                if(grid[i][0].getType() == mysign && grid[i][2].getType() == mysign)
                    return 10;
                else if(grid[i][0].getType() != mysign && grid[i][2].getType() != mysign)
                    return -10;

            }

            if(grid[i][1].getType() > 0 && grid[i][2].getType() > 0){ // check middle and down of a row (wich row it is depends on i's value)
                if(grid[i][1].getType() == mysign && grid[i][2].getType() == mysign)
                    return 10;
                else if(grid[i][1].getType() != mysign && grid[i][2].getType() != mysign)
                    return -10;

            }


            if(grid[0][i].getType() > 0 && grid[1][i].getType() > 0){ // check left and middle of a column (wich column it is depends on i's value)
                if(grid[0][i].getType() == mysign && grid[1][i].getType() == mysign)
                    return 10;
                else if(grid[0][i].getType() != mysign && grid[1][i].getType() != mysign)
                    return -10;

            }

            if(grid[0][i].getType() > 0 && grid[2][i].getType() > 0){ // check middle and right of a column (wich column it is depends on i's value)
                if(grid[0][i].getType() == mysign && grid[2][i].getType() == mysign)
                    return 10;
                else if(grid[0][i].getType() != mysign && grid[2][i].getType() != mysign)
                    return -10;
            }

            if(grid[1][i].getType() > 0 && grid[2][i].getType() > 0){ // check left and right of a column (wich column it is depends on i's value)
                if(grid[1][i].getType() == mysign && grid[2][i].getType() == mysign)
                    return 10;
                else if(grid[1][i].getType() != mysign && grid[2][i].getType() != mysign)
                    return -10;
            }
        }

        if(grid[0][0].getType() > 0 && grid[1][1].getType() > 0){ //up left and middle
            if (grid[0][0].getType() == mysign && grid[1][1].getType() == mysign)
                return 10;
            else if(grid[0][0].getType() != mysign && grid[1][1].getType() != mysign)
                return -10;
        }

        if(grid[0][0].getType() > 0 && grid[2][2].getType() > 0){//up left and down right
            if (grid[0][0].getType() == mysign && grid[2][2].getType() == mysign)
                return 10;
            else if(grid[0][0].getType() != mysign && grid[2][2].getType() != mysign)
                return -10;
        }

        if(grid[1][1].getType() > 0 && grid[2][2].getType() > 0){//middle and down right
            if (grid[1][1].getType() == mysign && grid[2][2].getType() == mysign)
                return 10;
            else if(grid[1][1].getType() != mysign && grid[2][2].getType() != mysign)
                return -10;
        }



        if(grid[2][0].getType() > 0 && grid[1][1].getType() > 0){ //down left and middle
            if (grid[2][0].getType() == mysign && grid[1][1].getType() == mysign)
                return 10;
            else if(grid[2][0].getType() != mysign && grid[1][1].getType() != mysign)
                return -10;
        }

        if(grid[2][0].getType() > 0 && grid[0][2].getType() > 0){ //down left and up right
            if (grid[2][0].getType() == mysign && grid[0][2].getType() == mysign)
                return 10;
            else if(grid[2][0].getType() != mysign && grid[0][2].getType() != mysign)
                return -10;
        }

        if(grid[1][1].getType() > 0 && grid[0][2].getType() > 0){ //middle and up right
            if (grid[1][1].getType() == mysign && grid[0][2].getType() == mysign)
                return 10;
            else if(grid[1][1].getType() != mysign && grid[0][2].getType() != mysign)
                return -10;
        }

        return 0;
    }
}

class Tree {
    private ArrayList<Node> tree;

    public Tree(Grid g, int deep ){
        for(int i=0;i<3; i++){
            for(int j=0;j<3;j++){
                GridIndex gi = new GridIndex(i, j);
               if(g.getCase(gi).getType() == 0){
                    Node n = new Node(g, gi, deep);
                    tree.add(n);
                }
            }
        }
    }
    public  int eval(Grid grid){
        if(grid.isTheEnd() && grid.checkWinner() == Grid.mysign){//win
            return Node.MAXEVAL - grid.getTurn();
        }else if(grid.isTheEnd() && grid.checkWinner() != Grid.mysign){//loose
            return Node.MINEVAL + grid.getTurn();
        }else if(grid.isTheEnd() && grid.checkWinner() == 0 ){ //draw
            return 0;
        }if(!grid.isTheEnd()){
            return grid.checkneighbor();
        }
        return -1;
    }
}
class Node{
    public final static int MINEVAL = -100000;
    public final static int MAXEVAL = 100000;
    private Grid grid;
    private GridIndex grid_index;
    private int score;
    private int deep;

    public Node(Grid g, GridIndex gi, int deep){
        this.grid       = g;
        this.grid_index = gi;
        this.score      = 0;
        this.deep       = deep;
    }


    private int min(Grid grid, int deep){
        int min_val = Node.MAXEVAL;
        int tmp;

        // if(!grid.isTheEnd() || deep == 0)
        //     eval()

        // for(int i=0;i<3; i++){
        //     for(int j=0;j<3;j++){
        //         GridIndex gi = new GridIndex(i, j);
        //        if(this.grid.getCase(gi).getType() == 0){
                    this.grid.play(this.grid_index, this.grid.getPlayer_sign());
                    tmp = this.max(this.grid, deep--);
                    if(tmp < min_val)
                        min_val = tmp;
        //        }
        //     }
        // }
        this.score = min_val;
        return min_val;
    }
    private int max(Grid grid, int deep){
        int max_val = Node.MINEVAL;
        int tmp;

        // if(!grid.isTheEnd() || deep == 0)
        //     eval()

        // for(int i=0;i<3; i++){
        //     for(int j=0;j<3;j++){
        //         GridIndex gi = new GridIndex(i, j);
        //        if(grid.getCase(gi).getType() == 0){
                    grid.play(this.grid_index, grid.getPlayer_sign());
                    tmp = this.min(grid, deep--);
                    if(tmp > max_val)
                        max_val = tmp;
        //        }
        //     }
        // }
        this.score = max_val;
        return max_val;
    }
}
class Case{
    public final static int NEUTRE  = 0;
    public final static int CROSS   = 1;
    public final static int ZERO    = 2;

    private int type;
    private GridIndex index;

    public Case(int i, int j){
        this.type = NEUTRE;
        this.index = new GridIndex(i,j);
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


    public boolean setType(int player_sign){
        if(player_sign == 1 || player_sign == 2){
            this.type = player_sign;
            return true;
        }else
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

class GridIndex{
    private int i, j;

    public GridIndex(int i, int j){
        this.i = i;
        this.j = j;
    }
    public GridIndex(){
        this.i = 0;
        this.j = 0;
    }
    public void setIndex(int i, int j){
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
        return this.i+" "+this.j;
    }
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
         Scanner in = new Scanner(System.in);
         Grid g = new Grid();
         GridIndex gi = new GridIndex();

        // game loop
        while (true) {
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt();
                int col = in.nextInt();
            }
            if(opponentRow == -1 && opponentCol == -1) // si je commence
                g.setMySign(Case.CROSS);
            else{
                gi.setIndex(opponentRow, opponentCol);
                g.setMySign(Case.ZERO);
                g.play(gi, Case.CROSS);
            }
            Tree t = new Tree(g, 3);
        }
    }
}

