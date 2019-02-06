package Main;

//Checked !!!!!!!!!

import java.lang.*;

public class Rook extends Piece{

    public Rook(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move Rook out of the board boundary
     * This function should be called in RookMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Rook is moved out of boundary
     **/
    public boolean RookInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Rook to a different location
     * This function should be called in RookMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if Rook move to different location
     **/
    public boolean RookDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player only move Rook vertically or horizontally
     * This function should be called in RookMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if Rook made valid move vertically or horizontally
     **/
    public boolean RookDirection(int x, int y){
        //Only move horizontally or vertically
        if ( Math.abs(x - this.getRow()) > 0 && (y - this.getCol()) == 0){
            return true;
        }
        if ( (x - this.getRow()) == 0 && Math.abs(y - this.getCol()) > 0){
            return true;
        }

        return false;
    }

    /**
     * This function will check if player only move Rook vertically or horizontally
     *              without leaping over other pieces
     *              and prevent self suicide
     * This function should be called in RookMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Rook made valid move vertically or horizontally
     **/
    public boolean RookMove(int x_dest, int y_dest){
        if ( !RookInBoundary(x_dest, y_dest) || !RookDidMove(x_dest, y_dest) || !RookDirection(x_dest, y_dest)){
            return false;
        }

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        //Main.Rook cannot leap over other pieces
        int curr_row = this.getRow();
        int curr_col = this.getCol();

        if (curr_row < x_dest){
            for (int i = curr_row+1; i < x_dest; i ++) {
                if (this.getBoard().getPiece(i, curr_col) != null) {
                    return false;
                }
            }
        }

        if (curr_row > x_dest){
            for (int i = curr_row-1; i > x_dest; i --){
                if(this.getBoard().getPiece(i, curr_col) != null){
                    return false;
                }
            }
        }

        if (curr_col < y_dest){
            for (int j = curr_col+1; j < y_dest; j ++){
                if(this.getBoard().getPiece(curr_row, j) != null){
                    return false;
                }
            }
        }

        if (curr_col > y_dest){
            for (int j = curr_col- 1; j > y_dest; j --){
                if(this.getBoard().getPiece(curr_row, j) != null){
                    return false;
                }
            }
        }

        return true;


    }

}
