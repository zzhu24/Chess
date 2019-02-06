package Main;

//Checked !!!!!!!!!

public class Board {

    /*Create the board with size 8*8 containing all the pieces*/

    private Piece[][] wholeBoard;

    private int oldRow;
    private int oldCol;
    private Piece oldPiece;
    private Piece oldRemovedPiece;
    private int oldRemovedRow;
    private int oldRemovedCol;


    private int oldRow1;
    private int oldCol1;
    private Piece oldPiece1;
    private Piece oldRemovedPiece1;
    private int oldRemovedRow1;
    private int oldRemovedCol1;


    //Initialize a 8 * 8 size board
    public Board(){

        wholeBoard = new Piece[8][8];

        for (int i = 0 ; i <= 7; i ++){
            for (int j = 0; j <= 7; j++){
                wholeBoard[i][j] = null;
            }
        }

    }

    public Piece[][] getWholeBoard() {
        return wholeBoard;
    }



    public int getOldRow(){
        return oldRow;
    }

    public int getOldCol() {
        return oldCol;
    }

    public Piece getOldPiece() {
        return oldPiece;
    }

    public Piece getOldRemovedPiece() {
        return oldRemovedPiece;
    }

    public int getOldRemovedCol() {
        return oldRemovedCol;
    }

    public int getOldRemovedRow() {
        return oldRemovedRow;
    }

    public void setOldPosition(int oldRow, int oldCol) {
        this.oldRow = oldRow;
        this.oldCol = oldCol;
    }

    public void setOldRemovedPosition(int oldRemovedRow, int oldRemovedCol) {
        this.oldRemovedRow = oldRemovedRow;
        this.oldRemovedCol = oldRemovedCol;
    }

    public void setOldPiece(Piece oldPiece) {
        this.oldPiece = oldPiece;
    }

    public void setOldRemovedPiece(Piece oldRemovedPiece) {
        this.oldRemovedPiece = oldRemovedPiece;
    }


    public int getOldRow1(){
        return oldRow1;
    }

    public int getOldCol1() {
        return oldCol1;
    }

    public Piece getOldPiece1() {
        return oldPiece1;
    }

    public Piece getOldRemovedPiece1() {
        return oldRemovedPiece1;
    }

    public int getOldRemovedCol1() {
        return oldRemovedCol1;
    }

    public int getOldRemovedRow1() {
        return oldRemovedRow1;
    }

    public void setOldPosition1(int oldRow1, int oldCol1) {
        this.oldRow1 = oldRow1;
        this.oldCol1 = oldCol1;
    }

    public void setOldRemovedPosition1(int oldRemovedRow1, int oldRemovedCol1) {
        this.oldRemovedRow1 = oldRemovedRow1;
        this.oldRemovedCol1 = oldRemovedCol1;
    }


    public void setOldPiece1(Piece oldPiece1) {
        this.oldPiece1 = oldPiece1;
    }

    public void setOldRemovedPiece1(Piece oldRemovedPiece1) {
        this.oldRemovedPiece1 = oldRemovedPiece1;
    }

    //Return a piece on the board
    public Piece getPiece(int row, int col){

        if (row < 0 || row > 7 || col < 0 || col > 7){
            return null;
        }

        return wholeBoard[row][col];
    }

    //Initialize Main.Piece
    public void initPiece(Piece piece, int row, int col){
        wholeBoard[piece.getRow()][piece.getCol()] = piece;
    }

    //Remove a Main.Piece on the board
    public void removePiece(Piece piece){
        if (piece != null){
            wholeBoard[piece.getRow()][piece.getCol()] = null;
        }
    }

    /**
     * This function will return if the intended move is a valid move for any piece
     * @param currentSide : currentSide playing
     * @return : the king that the current player holding
     */

    public Piece findKing(boolean currentSide){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(this.getPiece(i, j) instanceof King){
                    if(this.getPiece(i, j).getMySide() == currentSide){
                        return this.getPiece(i, j);
                    }
                }
            }
        }
        return null;

    }


    /**
     * This function will return if the intended move is a valid move for any piece
     * @param x : row for destination
     * @param y : col for destination
     * @param piece : the piece intended to be move
     * @return : boolean if it is a valid move
     */
    public boolean tryMovePiece(int x, int y, Piece piece){

        if (piece != null){
            if(piece instanceof Bishop){
                Bishop bishop = (Bishop) piece;
                if(bishop.BishopMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Camel){
                Camel camel = (Camel) piece;
                if(camel.CamelMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof King){
                King king = (King) piece;
                if(king.kingMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Knight){
                Knight knight = (Knight) piece;
                if(knight.KnightMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Pawn){
                Pawn pawn = (Pawn) piece;
                if(pawn.PawnMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Queen){
                Queen queen = (Queen) piece;
                if(queen.QueenMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Rook){
                Rook rook = (Rook) piece;
                if(rook.RookMove(x, y)){
                    return true;
                }
            }
            if(piece instanceof Zebra){
                Zebra zebra = (Zebra) piece;
                if(zebra.ZebraMove(x, y)){
                    return true;
                }
            }

        }
        return false;

    }


    /**
     * This function will check if player did a valid move and then move the piece
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @param  piece   the piece want to move
     */
    public void movePiece(int x, int y, Piece piece){

        if(piece == null){
            return;
        }

        setOldPosition(piece.getRow(), piece.getCol());
        setOldPiece(piece);
        setOldRemovedPosition(x, y);
        setOldRemovedPiece(wholeBoard[x][y]);

        if (piece != null){
            if(piece instanceof Bishop){
                Bishop bishop = (Bishop) piece;
                if(bishop.BishopMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof Camel){
                Camel camel = (Camel) piece;
                if(camel.CamelMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof King){
                King king = (King) piece;
                if(king.kingMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof Knight){
                Knight knight = (Knight) piece;
                if(knight.KnightMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof Pawn){
                Pawn pawn = (Pawn) piece;
                if(pawn.PawnMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                    pawn.setFirst(pawn.isFirstMove() + 1);
                }
            }
            if(piece instanceof Queen){
                Queen queen = (Queen) piece;
                if(queen.QueenMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof Rook){
                Rook rook = (Rook) piece;
                if(rook.RookMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }
            if(piece instanceof Zebra){
                Zebra zebra = (Zebra) piece;
                if(zebra.ZebraMove(x, y)){
                    wholeBoard[x][y] = piece;
                    wholeBoard[piece.getRow()][piece.getCol()] = null;
                    piece.setLocation(x, y);
                }
            }

        }

    }


    /**
     * This function will undo the previous step
     * This function will be called in checkmate and stalemate
     */
    public void undoMove(){
        wholeBoard[getOldRow()][getOldCol()] = getOldPiece();
        getOldPiece().setLocation(getOldRow(), getOldCol());
        wholeBoard[getOldRemovedRow()][getOldRemovedCol()] = getOldRemovedPiece();

        if(getOldPiece() instanceof Pawn){
            Pawn pawn = (Pawn) getOldPiece();
            pawn.setFirst(pawn.isFirstMove() - 1);
        }

    }

    /**
     * This function will record the previous step
     * @param  x   destination x coordinate
     * @param  y   destination y coordinate
     * @param  piece   the piece took the last step
     */
    public void recordMovePiece(int x, int y, Piece piece) {

        if (piece == null) {
            return;
        }

        setOldPosition1(piece.getRow(), piece.getCol());
        setOldPiece1(piece);
        setOldRemovedPosition1(x, y);
        setOldRemovedPiece1(wholeBoard[x][y]);
    }

    /**
     * This function will undo the previous step
     * This function will be called in ChessBoardGUI
     */
    public void realUndoMove(){
        wholeBoard[getOldRow1()][getOldCol1()] = getOldPiece1();
        getOldPiece1().setLocation(getOldRow1(), getOldCol1());
        wholeBoard[getOldRemovedRow1()][getOldRemovedCol1()] = getOldRemovedPiece1();

        if(getOldPiece1() instanceof Pawn){
            Pawn pawn = (Pawn) getOldPiece1();
            pawn.setFirst(pawn.isFirstMove() - 1);
        }

    }



}
