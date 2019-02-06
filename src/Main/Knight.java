package Main;

//Checked !!!!!!!!!

public class Knight extends Piece {

    public Knight(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move Knight out of the board boundary
     * This function should be called in KnightMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Knight is moved out of boundary
     **/
    public boolean KnightInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }


    /**
     * This function will check if player did move Knight to a different location
     * This function should be called in KnightMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Knight is moved to a different location
     **/
    public boolean KnightDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }


    /**
     * This function will check if player did move Knight with (1,2)-leaper
     * This function should be called in CamelMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Knight made a valid move
     **/
    public boolean KnightDirection(int x, int y){

        if ( Math.abs(x - this.getRow()) == 1 && Math.abs(y - this.getCol()) == 2 ){
            return true;
        }

        if ( Math.abs(x - this.getRow()) == 2 && Math.abs(y - this.getCol()) == 1 ){
            return true;
        }

        return false;
    }


    /**
     * This function will check if player make a valid Knight Move
     * This function also check that the piece on destination is not with same side
     *              to prevent self suicide
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if this Knight made a valid move
     **/
    public boolean KnightMove(int x_dest, int y_dest){

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        if ( !KnightInBoundary(x_dest, y_dest) || !KnightDidMove(x_dest, y_dest) || !KnightDirection(x_dest, y_dest)){
            return false;
        }

        return true;

    }

}
