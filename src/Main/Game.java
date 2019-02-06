package Main;

//Checked !!!!!!!!!

public class Game {

    private Board board = new Board();

    /**
     * This function will initialize the board as start status
     */
    public  Game(boolean isCustomized){
        //Initialize Main.Rook
        this.board.getWholeBoard()[0][0] = new Rook(board, 0, 0, false);
        this.board.getWholeBoard()[0][7] = new Rook(board, 0, 7, false);
        this.board.getWholeBoard()[7][0] = new Rook(board, 7, 0, true);
        this.board.getWholeBoard()[7][7] = new Rook(board, 7, 7, true);

        //Initialize Main.Knight
        if(isCustomized){
            this.board.getWholeBoard()[0][1] = new Camel(board, 0, 1, false);
            this.board.getWholeBoard()[0][6] = new Zebra(board, 0, 6, false);
            this.board.getWholeBoard()[7][1] = new Camel(board, 7, 1, true);
            this.board.getWholeBoard()[7][6] = new Zebra(board, 7, 6, true);
        }
        else {
            this.board.getWholeBoard()[0][1] = new Knight(board, 0, 1, false);
            this.board.getWholeBoard()[0][6] = new Knight(board, 0, 6, false);
            this.board.getWholeBoard()[7][1] = new Knight(board, 7, 1, true);
            this.board.getWholeBoard()[7][6] = new Knight(board, 7, 6, true);
        }

        //Initialize Main.Bishop
        this.board.getWholeBoard()[0][2] = new Bishop(board, 0, 2, false);
        this.board.getWholeBoard()[0][5] = new Bishop(board, 0, 5, false);
        this.board.getWholeBoard()[7][2] = new Bishop(board, 7, 2, true);
        this.board.getWholeBoard()[7][5] = new Bishop(board, 7, 5, true);

        //Initialize Main.Queen
        this.board.getWholeBoard()[0][3] = new Queen(board, 0, 3, false);
        this.board.getWholeBoard()[7][3] = new Queen(board, 7, 3, true);

        //Initialize Main.King
        this.board.getWholeBoard()[0][4] = new King(board, 0, 4, false);
        this.board.getWholeBoard()[7][4] = new King(board, 7, 4, true);

        //Initialize Main.Pawn
        for(int i = 0; i <= 7; i++){
            this.board.getWholeBoard()[1][i] = new Pawn(board, 1, i, false);
            this.board.getWholeBoard()[6][i] = new Pawn(board, 6, i, true);
        }
    }


    public Board getGameBoard(){
        return this.board;
    }

    /**
     * This function will return the king owned by the current player
     * @param  currentSide   the side of king we want to find
     * @return  Piece   the piece want to move
     */


    /**
     * This function will remove all the piece on the board
     **/
    public void removeAllPiece(){
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <=7; j++){
                this.board.getWholeBoard()[i][j] = null;
            }
        }
    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's Bishops
     * @param  k    king owned by current player
     * @return  (true/false)   if king can be captured by opponent's Bishops
     */
    public boolean checkByBishop(King k){

        int kRow = k.getRow();
        int kCol = k.getCol();
        boolean currentSide = k.getMySide();

        for(int i = 1; i < kRow ; i ++){
            if(this.board.getPiece(kRow - i, kCol - i) instanceof Bishop){
                Bishop b = (Bishop) this.board.getPiece(kRow - i, kCol - i);
                if(b.BishopMove(kRow, kCol) && b.getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow - i, kCol + i) instanceof Bishop){
                Bishop b = (Bishop) this.board.getPiece(kRow - i, kCol + i);
                if(b.BishopMove(kRow, kCol) && b.getMySide() != currentSide){
                    return true;
                }
            }
        }


        for(int i = 1; i < (7 - kRow) ; i ++){
            if(this.board.getPiece(kRow + i, kCol - i) instanceof Bishop){
                Bishop b = (Bishop) this.board.getPiece(kRow + i, kCol - i);
                if(b.BishopMove(kRow, kCol) && b.getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow + i, kCol + i) instanceof Bishop){
                Bishop b = (Bishop) this.board.getPiece(kRow + i, kCol + i);
                if(b.BishopMove(kRow, kCol) && b.getMySide() != currentSide){
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's Rooks
     * @param  k    king owned by current player
     * @return  (true/false)   if king can be captured by opponent's Rooks
     */
    public boolean checkByRook(King k){

        int kRow = k.getRow();
        int kCol = k.getCol();
        boolean currentSide = k.getMySide();

        for(int i = 0; i <= 7; i ++) {
            if (this.board.getPiece(i, kCol) instanceof Rook) {
                Rook r = (Rook) this.board.getPiece(i, kCol);
                if (r.RookMove(kRow, kCol) && r.getMySide() != currentSide) {
                    return true;
                }
            }
        }

        for(int j = 0; j <= 7; j ++){
            if(this.board.getPiece(kRow, j) instanceof Rook){
                Rook r = (Rook) this.board.getPiece(kRow, j);
                if(r.RookMove(kRow, kCol) && r.getMySide() != currentSide){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkByQueen(King k){

        int kRow = k.getRow();
        int kCol = k.getCol();
        boolean currentSide = k.getMySide();

        for(int i = 1; i < kRow ; i ++){
            if(this.board.getPiece(kRow - i, kCol - i) instanceof Queen){
                Queen q = (Queen) this.board.getPiece(kRow - i, kCol - i);
                if(q.QueenMove(kRow, kCol) && q.getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow - i, kCol + i) instanceof Queen){
                Queen q = (Queen) this.board.getPiece(kRow - i, kCol + i);
                if(q.QueenMove(kRow, kCol) && q.getMySide() != currentSide){
                    return true;
                }
            }
        }

        for(int i = 1; i < (7 - kRow) ; i ++){
            if(this.board.getPiece(kRow + i, kCol - i) instanceof Queen){
                Queen q = (Queen) this.board.getPiece(kRow + i, kCol - i);
                if(q.QueenMove(kRow, kCol) && q.getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow + i, kCol + i) instanceof Queen){
                Queen b = (Queen) this.board.getPiece(kRow + i, kCol + i);
                if(b.QueenMove(kRow, kCol) && b.getMySide() != currentSide){
                    return true;
                }
            }
        }


        for(int i = 0; i <= 7; i ++) {
            if (this.board.getPiece(i, kCol) instanceof Queen) {
                Queen q = (Queen) this.board.getPiece(i, kCol);
                if (q.QueenMove(kRow, kCol) && q.getMySide() != currentSide) {
                    return true;
                }
            }
            if(this.board.getPiece(kRow, i) instanceof Queen){
                Queen q = (Queen) this.board.getPiece(kRow, i);
                if(q.QueenMove(kRow, kCol) && q.getMySide() != currentSide){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's Knights
     * @param  k    king owned by current player
     * @return  (true/false)   if king can be captured by opponent's Knights
     */
    public boolean checkByKnight(King k){

        int kRow = k.getRow();
        int kCol = k.getCol();
        boolean currentSide = k.getMySide();

        if(this.board.getPiece(kRow + 1, kCol + 2) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow + 1, kCol + 2);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow + 2, kCol + 1) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow + 2, kCol + 1);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow - 1, kCol + 2) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow - 1, kCol + 2);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow - 2, kCol + 1) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow - 2, kCol + 1);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow + 1, kCol - 2) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow + 1, kCol - 2);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow + 2, kCol - 1) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow + 2, kCol - 1);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow - 1, kCol - 2) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow - 1, kCol - 2);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        if(this.board.getPiece(kRow - 2, kCol - 1) instanceof Knight){
            Knight knight = (Knight) this.board.getPiece(kRow - 2, kCol - 1);
            if(knight.KnightMove(kRow, kCol) && knight.getMySide() != currentSide){
                return true;
            }
        }

        return false;

    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's Pawns
     * @param  k    king owned by current player
     * @return  (true/false)   if king can be captured by opponent's Pawns
     */
    public boolean checkByPawn(King k){

        int kRow = k.getRow();
        int kCol = k.getCol();
        boolean currentSide = k.getMySide();

        if(k.getMySide()){
            if(this.board.getPiece(kRow-1, kCol-1) instanceof Pawn){
                if(this.board.getPiece(kRow-1, kCol-1).getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow-1, kCol+1) instanceof Pawn){
                if(this.board.getPiece(kRow-1, kCol+1).getMySide() != currentSide){
                    return true;
                }
            }
        }
        if(k.getMySide() != true){
            if(this.board.getPiece(kRow+1, kCol-1) instanceof Pawn){
                if(this.board.getPiece(kRow+1, kCol-1).getMySide() != currentSide){
                    return true;
                }
            }
            if(this.board.getPiece(kRow+1, kCol+1) instanceof Pawn){
                if(this.board.getPiece(kRow+1, kCol+1).getMySide() != currentSide){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's any piece
     * @param  king    king owned by current player
     * @return  (true/false)   if king can be captured by opponent's Pawns
     */
    public boolean singleChecked(King king){

        int kRow = king.getRow();
        int kCol = king.getCol();

        if (checkByBishop(king) || checkByQueen(king) || checkByRook(king) || checkByPawn(king) || checkByKnight(king)){
            return true;
        }

        return false;

    }

    /**
     * This function will return if the current player's king can be captured
     *              by opponent's any piece
     *              and any valid possible move of player's king cannot save king
     * @param  king    king owned by current player
     * @return  (true/false)   king can be captured by opponent's Pawns and cannot be saved
     */
    public boolean isChecked(King king){

        int kRow = king.getRow();
        int kCol = king.getCol();

        if(!singleChecked(king)){
            return false;
        }

        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {

                if (king.kingMove(kRow - 1 + i, kCol - 1 + j)) {
                    board.movePiece(kRow - 1 + i, kCol - 1 + j, king);
                    if (singleChecked(king)) {
                        board.undoMove();
                    } else {
                        board.undoMove();
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * This function will return if the current player's potential valid Rook move
     *              or Queen move can save king from checkmate
     * @param  king    king owned by current player
     * @param  piece    the piece gonna make potential move to save king
     * @return  (true/false)   king can be saved by Rook or Queen
     */
    public boolean RookQueenSave(King king, Piece piece){

        int pRow = piece.getRow();
        int pCol = piece.getCol();

        Rook rook = null;
        Queen queen = null;

        for(int i = 0; i <= 7; i++){
            if(piece instanceof Rook){
                rook = (Rook) piece;
                if (rook.RookMove(i, pCol)){
                    board.movePiece(i, pCol, rook);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
                if (rook.RookMove(pRow, i)){
                    board.movePiece(pRow, i, rook);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
            }
            if(piece instanceof Queen){
                queen = (Queen) piece;
                if (queen.QueenMove(i, pCol)){
                    board.movePiece(i, pCol, queen);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
                if (queen.QueenMove(pRow, i)){
                    board.movePiece(pRow, i, queen);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
            }
        }

        return false;
    }

    /**
     * This function will return if the current player's potential valid Bishop move
     *              or Queen move can save king from checkmate
     * @param  king    king owned by current player
     * @param  piece    the piece gonna make potential move to save king
     * @return  (true/false)   king can be saved by Bishop or Queen
     */
    public boolean BishopQueenSave(King king, Piece piece){

        int pRow = piece.getRow();
        int pCol = piece.getCol();

        Bishop bishop = null;
        Queen queen = null;

        for(int i = 0; i <= 7; i++){
            if(piece instanceof Bishop){
                bishop = (Bishop) piece;
                if (bishop.BishopMove(i, i)){
                    board.movePiece(i, i, bishop);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
                if (bishop.BishopMove(7-i, i)){
                    board.movePiece(7-i, i, bishop);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
            }
            if(piece instanceof Queen){
                queen = (Queen) piece;
                if (queen.QueenMove(i, i)){
                    board.movePiece(i, i, queen);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
                if (queen.QueenMove(7-i, i)){
                    board.movePiece(7-i, i, queen);
                    if(!singleChecked(king)){
                        board.undoMove();
                        return true;
                    }
                    else{
                        board.undoMove();
                    }
                }
            }
        }


        return false;
    }

    /**
     * This function will return if the current player's potential valid Knight move
     *                  can save king from checkmate
     * @param  king    king owned by current player
     * @param  piece    the piece gonna make potential move to save king
     * @return  (true/false)   king can be saved by Knight
     */
    public boolean KnightSave(King king, Piece piece){

        int pRow = piece.getRow();
        int pCol = piece.getCol();
        Knight knight = (Knight) piece;

        if(knight.KnightMove(pRow - 1, pCol - 2)){
            board.movePiece(pRow - 1, pCol - 2, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow - 2, pCol - 1)){
            board.movePiece(pRow - 2, pCol - 1, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow + 2, pCol - 1)){
            board.movePiece(pRow + 2, pCol - 1, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow + 1, pCol - 2)){
            board.movePiece(pRow + 1, pCol - 2, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow - 2, pCol + 1)){
            board.movePiece(pRow - 2, pCol + 1, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow - 1, pCol + 2)){
            board.movePiece(pRow - 1, pCol + 2, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow + 2, pCol + 1)){
            board.movePiece(pRow + 2, pCol + 1, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        if(knight.KnightMove(pRow + 1, pCol + 2)){
            board.movePiece(pRow + 1, pCol + 2, knight);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }

        return false;
    }

    /**
     * This function will return if the current player's potential valid Pawn move
     *                  can save king from checkmate
     * @param  king    king owned by current player
     * @param  piece    the piece gonna make potential move to save king
     * @return  (true/false)   king can be saved by Pawn
     */
    public boolean PawnSave(King king, Piece piece){

        int pRow = piece.getRow();
        int pCol = piece.getCol();

        Pawn pawn = (Pawn) piece;

        if(pawn.PawnMove(pRow + 2, pCol)){
            board.movePiece(pRow + 2, pCol, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow + 1, pCol)){
            board.movePiece(pRow + 1, pCol, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow - 2, pCol)){
            board.movePiece(pRow - 2, pCol, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow - 1, pCol)){
            board.movePiece(pRow - 1, pCol, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow - 1, pCol - 1)){
            board.movePiece(pRow - 1, pCol - 1, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow + 1, pCol - 1)){
            board.movePiece(pRow + 1, pCol - 1, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow - 1, pCol + 1)){
            board.movePiece(pRow - 1, pCol + 1, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }
        if(pawn.PawnMove(pRow + 1, pCol + 1)){
            board.movePiece(pRow + 1, pCol + 1, pawn);
            if(!singleChecked(king)){
                board.undoMove();
                return true;
            }
            else{
                board.undoMove();
            }
        }



        return false;
    }

    /**
     * This function will return if the current player's King is in checkmate
     *                  and no potential move of any piece can save King
     * @param  king    king owned by current player
     * @return  (true/false)   king is checked and cannot be saved
     */
    public boolean checkMate(King king){

        boolean currentSide = king.getMySide();

        if(!isChecked(king)){
            return false;
        }

        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                Piece piece = board.getPiece(i, j);
                if(piece !=  null){
                    if(currentSide == piece.getMySide()){
                        if(piece instanceof Queen){
                            if(RookQueenSave(king, piece)){
                                return false;
                            }
                            if(BishopQueenSave(king, piece)){
                                return false;
                            }
                        }

                        if(piece instanceof Rook){
                            if(RookQueenSave(king, piece)){
                                return false;
                            }
                        }
                        if(piece instanceof Bishop){
                            if(BishopQueenSave(king, piece)){
                                return false;
                            }
                        }
                        if(piece instanceof Knight){
                            if(KnightSave(king, piece)){
                                return false;
                            }
                        }
                        if(piece instanceof Pawn){
                            if(PawnSave(king, piece)){
                                return false;
                            }
                        }
                    }

                }

            }
        }

        return true;
    }

    /**
     * This function will return if the current player's King is in stalemate
     * Stalemate means that he player whose turn it is to move
     *              has no legal move and is not in check
     * @param  king    king owned by current player
     * @return  (true/false)   king is not check but there is no legal move
     */
    public boolean staleMate(King king){

        boolean kSide = king.getMySide();
        int kRow = king.getRow();
        int kCol = king.getCol();

        if(checkMate(king)){
            return false;
        }

        //Check if there is a valid move for King
        for(int a = 0; a < 3; a ++){
            for(int b = 0; b < 3; b++){
                if(king.kingMove(kRow - 1 + a, kCol - 1 + b)){
                    board.movePiece(kRow - 1 + a, kCol - 1 + b, king);
                    if(!checkMate(king)){
                        board.undoMove();
                        return false;
                    }
                    else{
                        board.undoMove();
                    }
                }
            }
        }


        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){

                //Check if there is a valid move for Bishop
                if(board.getPiece(i, j) instanceof Bishop){
                    if(board.getPiece(i, j).getMySide() == kSide){
                        Bishop bishop = (Bishop) board.getPiece(i, j);
                        if(bishop.BishopMove(i - 1, j - 1)){
                            board.movePiece(i - 1, j - 1, bishop);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(bishop.BishopMove(i + 1, j - 1)){
                            board.movePiece(i + 1, j - 1, bishop);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }if(bishop.BishopMove(i - 1, j + 1)){
                            board.movePiece(i - 1, j + 1, bishop);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(bishop.BishopMove(i + 1, j + 1)){
                            board.movePiece(i + 1, j + 1, bishop);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                    }
                }

                //Check if there is a valid move for Queen
                if(board.getPiece(i, j) instanceof Queen){
                    if(board.getPiece(i, j).getMySide() == kSide){
                        Queen queen = (Queen) board.getPiece(i, j);
                        int qRow = queen.getRow();
                        int qCol = queen.getCol();
                        for(int a = 0; a < 3; a ++){
                            for(int b = 0; b < 3; b++){
                                if(queen.QueenMove(qRow - 1 + a, qCol - 1 + b)){
                                    board.movePiece(qRow - 1 + a, qCol - 1 + b, queen);
                                    if(!checkMate(king)){
                                        board.undoMove();
                                        return false;
                                    }
                                    else{
                                        board.undoMove();
                                    }
                                }
                            }
                        }
                    }
                }

                //Check if there is a valid move for Knight
                if(board.getPiece(i, j) instanceof Knight){
                    if(board.getPiece(i, j).getMySide() == kSide){
                        Knight knight = (Knight) board.getPiece(i, j);
                        if(knight.KnightMove(i - 1, j - 2)){
                            board.movePiece(i - 1, j - 2, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i - 2, j - 1)){
                            board.movePiece(i - 2, j - 1, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i + 1, j - 2)){
                            board.movePiece(i + 1, j - 2, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i + 2, j - 1)){
                            board.movePiece(i + 2, j - 1, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i - 1, j + 2)){
                            board.movePiece(i - 1, j + 2, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i - 2, j + 1)){
                            board.movePiece(i - 2, j + 1, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i + 1, j + 2)){
                            board.movePiece(i + 1, j + 2, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(knight.KnightMove(i + 2, j + 1)){
                            board.movePiece(i + 2, j + 1, knight);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                    }
                }

                //Check if there is a valid move for Pawn
                if(board.getPiece(i, j) instanceof Pawn){
                    if(board.getPiece(i, j).getMySide() == kSide){
                        Pawn pawn = (Pawn) board.getPiece(i, j);
                        if(pawn.PawnMove(i - 1, j)){
                            board.movePiece(i - 1, j, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }

                        if(pawn.PawnMove(i - 2, j)){
                            board.movePiece(i - 2, j, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }

                        if(pawn.PawnMove(i + 1, j)){
                            board.movePiece(i + 1, j, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(pawn.PawnMove(i + 2, j)){
                            board.movePiece(i + 2, j, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(pawn.PawnMove(i - 1, j - 1)){
                            board.movePiece(i - 1, j - 1, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(pawn.PawnMove(i - 1, j + 1)){
                            board.movePiece(i - 1, j + 1, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(pawn.PawnMove(i + 1, j - 1)){
                            board.movePiece(i + 1, j - 1, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }

                        if(pawn.PawnMove(i + 1, j + 1)){
                            board.movePiece(i + 1, j + 1, pawn);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }

                    }
                }




                //Check if there is a valid move for Rook
                if(board.getPiece(i, j) instanceof Rook){
                    if(board.getPiece(i, j).getMySide() == kSide){
                        Rook rook = (Rook) board.getPiece(i, j);
                        if(rook.RookMove(i, j - 1)){
                            board.movePiece(i, j - 1, rook);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }if(rook.RookMove(i + 1, j)){
                            board.movePiece(i + 1, j, rook);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(rook.RookMove(i, j + 1)){
                            board.movePiece(i, j + 1, rook);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                        if(rook.RookMove(i - 1, j)){
                            board.movePiece(i - 1, j, rook);
                            if(!checkMate(king)){
                                board.undoMove();
                                return false;
                            }
                            else{
                                board.undoMove();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
