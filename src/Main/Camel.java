package Main;

//Checked !!!!!!!!!

public class Camel extends Piece {

    public Camel(Board board, int row, int col, boolean mySide){
        super(board, row, col, mySide);
    }


    /**
     * This function will check if player want to move Camel out of the board boundary
     * This function should be called in CamelMove()
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Camel is moved out of boundary
     **/
    public boolean CamelInBoundary(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Camel to a different location
     * This function should be called in CamelMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Camel is moved to a different location
     **/
    public boolean CamelDidMove(int x, int y){
        if (x == this.getRow() && y == this.getCol()){
            return false;
        }
        return true;
    }

    /**
     * This function will check if player did move Camel with (1,3)-leaper
     * This function should be called in CamelMove().
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @return     (true/false) boolean if this Camel is moved with (1,3)-leaper
     **/
    public boolean CamelDirection(int x, int y){

        if ( Math.abs(x - this.getRow()) == 1 && Math.abs(y - this.getCol()) == 3 ){
            return true;
        }

        if ( Math.abs(x - this.getRow()) == 3 && Math.abs(y - this.getCol()) == 1 ){
            return true;
        }

        return false;
    }

    /**
     * This function will check if player make a valid Camel Move
     * This function also check that the piece on destination is not with same side
     *              to prevent self suicide
     * @param  x_dest   destination x coordinate
     * @param  y_dest   destination y coordinate
     * @return     (true/false) boolean if player made a valid Camel move
     */
    public boolean CamelMove(int x_dest, int y_dest){

        if(this.getBoard().getPiece(x_dest, y_dest) != null){
            if(this.getBoard().getPiece(x_dest, y_dest).getMySide() == this.getMySide()) {
                return false;
            }
        }

        if ( !CamelInBoundary(x_dest, y_dest) || !CamelDidMove(x_dest, y_dest) || !CamelDirection(x_dest, y_dest)){
            return false;
        }

        return true;

    }
}
