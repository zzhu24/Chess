package Main;

//Checked !!!!!!!!!

public class Pawn extends Piece{

    private int firstMove = 0;
    public int isFirstMove(){
        return firstMove;
    }
    public void setFirst(int b){
        firstMove = b;
    }
    public Pawn(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
        setFirst(0);
    }

    /**
     * This function will check if player want to move Pawn out of the board boundary
     * This function should be called in PawnMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Pawn is moved out of boundary
     **/
    public boolean PawnInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Pawn to a different location
     * This function should be called in PawnMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if Pawn is moved to a different location
     **/
    public boolean PawnDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player move Pawn vertically
     *              and there is no piece blocking its way
     *              and there is no piece on the destination position
     * This function should be called in PawnMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Pawn is moved vertically without capture
     **/
    public boolean verticalMove(int x_dest, int y_dest){

        int curr_row = this.getRow();
        int curr_col = this.getCol();

        if(y_dest != curr_col){
            return false;
        }

        if(isFirstMove() == 0){
            if ( Math.abs(x_dest - curr_row) > 2){
                return false;
            }
            if(this.getMySide()){
                if((curr_row - x_dest) == 1 || (curr_row - x_dest) == 2){
                    for (int i = curr_row-1; i >= x_dest; i --){
                        if(this.getBoard().getPiece(i, curr_col) != null){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }

            }
            else{
                if((x_dest - curr_row) == 1 || (x_dest - curr_row) == 2){
                    for (int i = curr_row+1; i <= x_dest; i ++){
                        if(this.getBoard().getPiece(i, curr_col) != null){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }

            }

        }

        else{
            if ( Math.abs(x_dest - curr_row) > 1){
                return false;
            }
            if(this.getMySide()){
                if((curr_row - x_dest) != 1 || this.getBoard().getPiece(x_dest, y_dest) != null) {
                    return false;
                }
            }
            else{
                if((x_dest - curr_row) != 1 || this.getBoard().getPiece(x_dest, y_dest) != null) {
                    return false;
                }
            }


        }
        return true;
    }

    /**
     * This function will check if player move Pawn diagonally forward
     *              and there is a piece from opponent on destination to capture
     * This function should be called in PawnMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if Pawn made a capture move
     **/
    public boolean captureMove(int x_dest, int y_dest){

        int curr_row = this.getRow();
        int curr_col = this.getCol();

        if ( Math.abs(x_dest - curr_row) != 1 || Math.abs(y_dest - curr_col) != 1){
            return false;
        }

        if(this.getMySide()){
            if((curr_row - x_dest) == 1 && this.getBoard().getPiece(x_dest, y_dest) != null) {
                if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == false){
                    return true;
                }
            }
        }
        else{
            if((x_dest - curr_row) == 1 && this.getBoard().getPiece(x_dest, y_dest) != null) {
                if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == true){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This function will check if player move Pawn vertically forward without a capture
     *              or diagonally forward with a capture
     * This function should be called in PawnMove().
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if this Pawn made a valid move
     **/
    public boolean PawnMove(int x_dest, int y_dest){

        if(!PawnDidMove(x_dest, y_dest) || !PawnDidMove(x_dest, y_dest)){
            return false;
        }

        if(verticalMove(x_dest,y_dest) || captureMove(x_dest, y_dest)){
            return true;
        }

        return false;
    }


}
