package Main;

//Checked !!!!!!!!!

import java.lang.*;

public class King extends Piece {

    public King(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move King out of the board boundary
     * This function should be called in KingMove().
     * This function will take care of piece location update.
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this King is moved out of boundary
     **/
    public boolean kingInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move King to a different location
     * This function should be called in KingMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this King is moved to a different location
     **/
    public boolean kingDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move King one square in any direction
     * This function will check if player make a valid King Move
     * This function also check that the piece on destination is not with same side
     *              to prevent self suicide
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this King made a valid move
     **/
    public boolean kingMove(int x, int y){
        if ( !kingInBoundary(x, y) || !kingDidMove(x, y)){
            return false;
        }

        if(this.getBoard().getPiece(x, y) != null){
            if(this.getBoard().getPiece(x, y).getMySide() == this.getMySide()) {
                return false;
            }
        }

        int x_move = Math.abs(this.getRow() - x);
        int y_move = Math.abs(this.getCol() - y);

        if(x_move > 1 || y_move >1){
            return false;
        }
        return true;

    }

}
