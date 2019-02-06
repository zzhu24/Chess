package Main;

//Checked !!!!!!!!!

import java.lang.*;

public class Queen extends Piece {

    public Queen(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move Queen out of the board boundary
     * This function should be called in QueenMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Queen is moved out of boundary
     **/
    public boolean QueenInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Queen to a different location
     * This function should be called in QueenMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if Queen is moved to a different location
     **/
    public boolean QueenDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player only move Queen vertically,
     *              diagonally or horizontally
     * This function should be called in QueenMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if Queen is moved vertically,
     *              diagonally or horizontally
     **/
    public boolean QueenDirection(int x, int y){
        //Move diagonally and vertically and horizontally
        if ( Math.abs(x - this.getRow()) == Math.abs(y - this.getCol())){
            return true;
        }

        if ( Math.abs(x - this.getRow()) > 0 && (y - this.getCol()) == 0){
            return true;
        }
        if ( (x - this.getRow()) == 0 && Math.abs(y - this.getCol()) > 0){
            return true;
        }

        return false;
    }

    /**
     * This function will check if player only move Queen vertically or horizontally
     *              and there must be no other piece blocking this move
     * This function should be called in QueenMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Queen made valid move vertically or horizontally
     **/
    public boolean RookOne(int x_dest, int y_dest){

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

    /**
     * This function will check if player only move Queen diagonally
     *              and there must be no other piece blocking this move
     * This function should be called in QueenMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Queen made valid move diagonally
     **/
    public boolean BishopOne(int x_dest, int y_dest){

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

    /**
     * This function will check if player only move Queen diagonally,
     *               vertically or horizontally without leaping over other pieces
     *               and prevent self suicide
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Queen made valid move
     **/
    public boolean QueenMove(int x_dest, int y_dest){

        if ( !QueenInBoundary(x_dest, y_dest) || !QueenDidMove(x_dest, y_dest) || !QueenDirection(x_dest, y_dest)){
            return false;
        }

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        if (Math.abs(x_dest - this.getRow()) == 0 || Math.abs(y_dest - this.getCol()) == 0){
            if(RookOne(x_dest, y_dest)){
                return true;
            }
        }

        if (Math.abs(x_dest - this.getRow()) != 0 && Math.abs(y_dest - this.getCol()) != 0){
            if(BishopOne(x_dest, y_dest)){
                return true;
            }
        }

        return false;

    }

}
