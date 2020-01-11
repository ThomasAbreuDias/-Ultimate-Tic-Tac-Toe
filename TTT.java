/*Thomas Abreu Dias aka Boya*/
/*Dépot git: https://github.com/ThomasAbreuDias/-Ultimate-Tic-Tac-Toe */
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.HashMap;
 //revoir Tree
//la fonction de parcour de ttt


// Class qui représente un grille de morpion
class Grid {
    private Case[][] grid;
    public static int MYSIGN;
    private int actual_sign;
    private int turn_counter;
    public final static int ROW = 3;
    public final static int COL = 3;

    /*Constructeur*/
    public Grid(){
        grid = new Case[Grid.ROW][Grid.COL];
        turn_counter = Grid.ROW * Grid.COL;
        actual_sign = 1;
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

    public boolean isPlayable(GridIndex gi){
        if(this.grid[gi.getI()][gi.getJ()].getType() == 0)
            return true;

        return false;
    }

    public boolean isPlayable(int i, int j){
        if(this.grid[i][j].getType() == 0)
            return true;

        return false;
    }

    public void setMYSIGN(int sign){
        this.MYSIGN = sign;
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
    public int getActual_sign(){
        return this.actual_sign;
    }
    public int getTurn(){
        return this.grid.length - this.turn_counter;
    }

    public Grid play(GridIndex gi){

        if(this.isPlayable(gi)){
            grid[gi.getI()][gi.getJ()].setType(this.actual_sign);
        }
        this.turn_counter--;
        this.checkWinner();
        this.changeTurn();
        return this;
    }

    public void changeTurn(){
        if(this.turn_counter%2 == 0)
            this.actual_sign = 2;
        else
            this.actual_sign = 1;
    }
    public int checkWinner() {
        if(this.turn_counter <= 0)
            System.out.println(this.turn_counter);
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
                if(grid[i][0].getType() == grid[i][1].getType())
                    return (grid[i][1].getType() == Grid.MYSIGN) ? 10 : -10;

            }
            if(grid[i][1].getType() > 0 && grid[i][2].getType() > 0){ // check middle and down of a row (wich row it is depends on i's value)
                if(grid[i][1].getType() == Grid.MYSIGN && grid[i][2].getType() == Grid.MYSIGN)
                    return 10;
                else if(grid[i][1].getType() != Grid.MYSIGN && grid[i][2].getType() != Grid.MYSIGN)
                    return -10;

            }
            if(grid[i][0].getType() > 0 && grid[i][2].getType() > 0){ // Pas vraiment cote a cote
                if(grid[i][0].getType() == Grid.MYSIGN && grid[i][2].getType() == Grid.MYSIGN)
                    return 10;
                else if(grid[i][0].getType() != Grid.MYSIGN && grid[i][2].getType() != Grid.MYSIGN)
                    return -10;

            }

            if(grid[0][i].getType() > 0 && grid[1][i].getType() > 0){ // check left and middle of a column (wich column it is depends on i's value)
                if(grid[0][i].getType() == Grid.MYSIGN && grid[1][i].getType() == Grid.MYSIGN)
                    return 10;
                else if(grid[0][i].getType() != Grid.MYSIGN && grid[1][i].getType() != Grid.MYSIGN)
                    return -10;

            }

            if(grid[0][i].getType() > 0 && grid[2][i].getType() > 0){ // check middle and right of a column (wich column it is depends on i's value)
                if(grid[0][i].getType() == Grid.MYSIGN && grid[2][i].getType() == Grid.MYSIGN)
                    return 10;
                else if(grid[0][i].getType() != Grid.MYSIGN && grid[2][i].getType() != Grid.MYSIGN)
                    return -10;
            }

            if(grid[1][i].getType() > 0 && grid[2][i].getType() > 0){ // check left and right of a column (wich column it is depends on i's value)
                if(grid[1][i].getType() == Grid.MYSIGN && grid[2][i].getType() == Grid.MYSIGN)
                    return 10;
                else if(grid[1][i].getType() != Grid.MYSIGN && grid[2][i].getType() != Grid.MYSIGN)
                    return -10;
            }
        }

        if(grid[0][0].getType() > 0 && grid[1][1].getType() > 0){ //up left and middle
            if (grid[0][0].getType() == Grid.MYSIGN && grid[1][1].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[0][0].getType() != Grid.MYSIGN && grid[1][1].getType() != Grid.MYSIGN)
                return -10;
        }

        if(grid[0][0].getType() > 0 && grid[2][2].getType() > 0){//up left and down right
            if (grid[0][0].getType() == Grid.MYSIGN && grid[2][2].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[0][0].getType() != Grid.MYSIGN && grid[2][2].getType() != Grid.MYSIGN)
                return -10;
        }

        if(grid[1][1].getType() > 0 && grid[2][2].getType() > 0){//middle and down right
            if (grid[1][1].getType() == Grid.MYSIGN && grid[2][2].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[1][1].getType() != Grid.MYSIGN && grid[2][2].getType() != Grid.MYSIGN)
                return -10;
        }



        if(grid[2][0].getType() > 0 && grid[1][1].getType() > 0){ //down left and middle
            if (grid[2][0].getType() == Grid.MYSIGN && grid[1][1].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[2][0].getType() != Grid.MYSIGN && grid[1][1].getType() != Grid.MYSIGN)
                return -10;
        }

        if(grid[2][0].getType() > 0 && grid[0][2].getType() > 0){ //down left and up right
            if (grid[2][0].getType() == Grid.MYSIGN && grid[0][2].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[2][0].getType() != Grid.MYSIGN && grid[0][2].getType() != Grid.MYSIGN)
                return -10;
        }

        if(grid[1][1].getType() > 0 && grid[0][2].getType() > 0){ //middle and up right
            if (grid[1][1].getType() == Grid.MYSIGN && grid[0][2].getType() == Grid.MYSIGN)
                return 10;
            else if(grid[1][1].getType() != Grid.MYSIGN && grid[0][2].getType() != Grid.MYSIGN)
                return -10;
        }

        return 0;
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


    public boolean setType(int actual_sign){
        if(actual_sign == 1 || actual_sign == 2){
            this.type = actual_sign;
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
        return "{ "+this.i+" "+this.j+" }";
    }
}
class Tree{
    private Map<String, Tree> sons = new HashMap<>();
    private Grid grid;
    private int depth;

    public Tree(Grid g){
        this.grid = g;
    }
    public int getSize(){
        return sons.size();
    }
    public void put(String key, Tree t){
        sons.put(key, t);
    }
    public Tree makeBranch(int depth, Grid g){
        if(depth < 0)
            return null;
        Tree tree = new Tree(g);
        for (int i = 0 ; i < 3 && depth > 0; i++){
            for (int j = 0 ; j < 3 && depth > 0; j++){
                if(g.isPlayable(i,j) && !g.isTheEnd()){
                    GridIndex gi = new GridIndex(i, j);
                    tree.put(""+i+""+j,makeBranch(depth-1, g.play(gi)));
                }
            }
        }
        return tree;
    }
    public void showBranch(){
        for(Tree son : sons.values())
          this.grid.displayGrid();
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
        //while (true) {
            //System.out.println("Entrez vos coordoné");
            //int opponentRow = in.nextInt();
            //int opponentCol = in.nextInt();
            //int validActionCount = in.nextInt();
            // for (int i = 0; i < validActionCount; i++) {
            //     int row = in.nextInt();
            //     int col = in.nextInt();
            // }
            // if(opponentRow == -1 && opponentCol == -1 || Integer.parseInt(args[0]) == 1)// si je commence
            //     g.setMYSIGN(Case.CROSS);
            // else{
            //     gi.setIndex(opponentRow, opponentCol);
            //     g.setMYSIGN(Case.ZERO);
            // }
            //gi.setIndex(opponentRow, opponentCol);
            //g = testjeu(g,gi);
            Tree t = new Tree(g);
            t = t.makeBranch(3, g);
            System.out.println(t.getSize());
            t.showBranch();


        //}
    }
}

