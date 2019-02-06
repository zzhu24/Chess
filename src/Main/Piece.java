package Main;

//Checked !!!!!!!!!

public class Piece {

    /*Create Main.Piece that take one position on the Main.Board*/

    //Setting location and belonging to which side
    private int row;
    private int col;
    private boolean mySide;
    private Board board;

    public Piece(Board board, int row, int col, boolean mySide){

        this.board = board;
        this.row = row;
        this.col = col;
        this.mySide = mySide;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean getUser(){
        return mySide;
    }

    public boolean getMySide(){
        return mySide;
    }

    public void setMySide(boolean newSide){
        this.mySide = newSide;
    }

    public void setLocation(int x, int y){
        this.row = x;
        this.col = y;
    }

    public Board getBoard() {
        return board;
    }


}
