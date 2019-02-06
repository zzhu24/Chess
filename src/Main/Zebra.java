package Main;

//Checked !!!!!!!!!

public class Zebra extends Piece{

    public Zebra(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }

    /**
     * This function will check if player want to move Zebra out of the board boundary
     * This function should be called in ZebraMove()
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Zebra is moved out of boundary
     **/
    public boolean ZebraInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Zebra to a different location
     * This function should be called in ZebraMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Zebra is moved to a different location
     **/
    public boolean ZebraDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Zebra with (2,3)-leaper
     * This function should be called in ZebraMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Zebra is moved with (2,3)-leaper
     **/
    public boolean ZebraDirection(int x, int y){

        if ( Math.abs(x - this.getRow()) == 2 && Math.abs(y - this.getCol()) == 3 ){
            return true;
        }

        if ( Math.abs(x - this.getRow()) == 3 && Math.abs(y - this.getCol()) == 2 ){
            return true;
        }

        return false;
    }


    /**
     * This function will check if player make a valid Zebra Move
     * This function also check that the piece on destination is not with same side
     *              to prevent self suicide
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if player made a valid Zebra move
     */
    public boolean ZebraMove(int x_dest, int y_dest){

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        if ( !ZebraInBoundary(x_dest, y_dest) || !ZebraDidMove(x_dest, y_dest) || !ZebraDirection(x_dest, y_dest)){
            return false;
        }

        return true;

    }

}
