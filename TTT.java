import java.util.*;
import java.io.*;
import java.math.*;

// Class qui représente un grille de morpion
class Grid {
    private Case[][] grid;
/*Constructeur*/
    public Grid(){
        grid = new Case[3][3];
        for (int i = 0;i < 3 ;i++ ) {
            for (int j = 0;j < 3 ;j++ ) {
                grid[i][j] = new Case(i,j);
            }
        }
    }
    public void displayGrid(){
        for (int i = 0; i < 3; i++ ) {
            for (int j = 0; j < 3; j++ ) {
                System.out.print("|"+grid[i][j]);
            }
            System.out.println("|");
        }
    }
    public void play(int i, int j, int player_sign){
        if(grid[i][j].getType() == 0){
            grid[i][j].setType(player_sign);
        }
        this.checkWinner();
    }
    private int checkWinner() {
        for (int i = 0; i < 3; i++) {
            // check rows
            if (grid[i][0].getType() > 0 && grid[i][0].getType() == grid[i][1].getType() && grid[i][0].getType() == grid[i][2].getType()) {
                System.exit(grid[i][0].getType());
            }

            // check cols
            if (grid[0][i].getType() > 0 && grid[0][i].getType() == grid[1][i].getType() && grid[0][i].getType() == grid[2][i].getType()) {
                System.exit(grid[0][i].getType());
            }
        }

        // check diags
        if (grid[0][0].getType() > 0 && grid[0][0].getType() == grid[1][1].getType() && grid[0][0].getType() == grid[2][2].getType()) {
            System.exit(grid[0][0].getType());
        }
        if (grid[2][0].getType() > 0 && grid[2][0].getType() == grid[1][1].getType() && grid[2][0].getType() == grid[0][2].getType()) {
           System.exit(grid[2][0].getType());
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
        int player_sign = 1;

        // game loop
        while (true) {
            int opponentRow = in.nextInt();
            System.out.println("Saisie: "+opponentRow);

            int opponentCol = in.nextInt();
            System.out.println("Saisie; "+opponentCol);
            g.play(opponentRow, opponentCol, player_sign);
            g.displayGrid();


            // int validActionCount = in.nextInt();

            // for (int i = 0; i < validActionCount; i++) {
            //     int row = in.nextInt();
            //     int col = in.nextInt();
            // }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("0 0");
        }

    }
}
