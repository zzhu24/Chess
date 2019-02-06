package Main;

//Checked !!!!!!!!!

import java.lang.*;

public class Bishop extends Piece {

    public Bishop(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move Bishop out of the board boundary
     * This function should be called in BishopMove()
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Bishop is moved out of boundary
     **/
    public boolean BishopInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Bishop to a different location
     * This function should be called in BishopMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Bishop is moved out of boundary
     */
    public boolean BishopDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Bishop only diagonally
     * This function should be called in BishopMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Bishop is moved diagonally
     */
    public boolean BishopDirection(int x, int y){
        if ( Math.abs(x - this.getRow()) == Math.abs(y - this.getCol())){
            return true;
        }

        return false;
    }

    /**
     * This function will check if player make a valid Bishop Move
     * This function also check that the piece on destination is not with same side
     *              to prevent self suicide
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if player made a valid move
     */
    public boolean BishopMove(int x_dest, int y_dest){
        if ( !BishopInBoundary(x_dest, y_dest) || !BishopDidMove(x_dest, y_dest) || !BishopDirection(x_dest, y_dest)){
            return false;
        }

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        //Main.Bishop cannot leap over other pieces
        int curr_row = this.getRow();
        int curr_col = this.getCol();

        if (curr_row < x_dest && curr_col < y_dest){
            for (int i = 1; i < Math.abs(x_dest - curr_row); i ++){
                if(this.getBoard().getPiece(curr_row+i, curr_col+i) != null){
                    return false;
                }
            }
        }

        if (curr_row < x_dest && curr_col > y_dest){
            for (int i = 1; i < Math.abs(x_dest - curr_row); i ++){
                if(this.getBoard().getPiece(curr_row+i, curr_col-i) != null){
                    return false;
                }
            }
        }

        if (curr_row > x_dest && curr_col < y_dest){
            for (int i = 1; i < Math.abs(x_dest - curr_row); i ++){
                if(this.getBoard().getPiece(curr_row-i, curr_col+i) != null){
                    return false;
                }
            }
        }

        if (curr_row > x_dest && curr_col > y_dest){
            for (int i = 1; i < Math.abs(x_dest - curr_row); i ++){
                if(this.getBoard().getPiece(curr_row-i, curr_col-i) != null){
                    return false;
                }
            }
        }

        return true;

    }


}
