package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class CheckMateTest {

    /**
     * This function test if King is check by opponent's Bishops
     **/
    @Test
    public void BishopCheck(){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);
        Bishop b = new Bishop(board, 2, 3, false);
        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.checkByBishop(k));
        board.getWholeBoard()[2][3] = b;
        assertTrue(newGame.checkByBishop(k));
        b.setMySide(true);
        assertFalse(newGame.checkByBishop(k));

        b.setMySide(false);
        board.getWholeBoard()[3][6] = b;
        b.setLocation(3,6);
        board.getWholeBoard()[2][3] = null;
        assertTrue(newGame.checkByBishop(k));
        b.setMySide(true);
        assertFalse(newGame.checkByBishop(k));

        b.setMySide(false);
        board.getWholeBoard()[5][4] = b;
        b.setLocation(5,4);
        board.getWholeBoard()[3][6] = null;
        assertTrue(newGame.checkByBishop(k));
        b.setMySide(true);
        assertFalse(newGame.checkByBishop(k));


        b.setMySide(false);
        board.getWholeBoard()[5][6] = b;
        b.setLocation(5,6);
        board.getWholeBoard()[5][4] = null;
        assertTrue(newGame.checkByBishop(k));
        b.setMySide(true);
        assertFalse(newGame.checkByBishop(k));

    }

    /**
     * This function test if King is check by opponent's Rooks
     **/
    @Test
    public void RookCheck(){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);
        Rook r = new Rook(board, 3, 5, false);
        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.checkByRook(k));
        board.getWholeBoard()[3][5] = r;
        assertTrue(newGame.checkByRook(k));
        r.setMySide(true);
        assertFalse(newGame.checkByRook(k));

        r.setMySide(false);
        board.getWholeBoard()[5][5] = r;
        r.setLocation(5,5);
        board.getWholeBoard()[3][5] = null;
        assertTrue(newGame.checkByRook(k));
        r.setMySide(true);
        assertFalse(newGame.checkByRook(k));

        r.setMySide(false);
        board.getWholeBoard()[4][3] = r;
        r.setLocation(4,3);
        board.getWholeBoard()[5][5] = null;
        assertTrue(board.getPiece(4,3) instanceof Rook);
        assertTrue(newGame.checkByRook(k));
        r.setMySide(true);
        assertFalse(newGame.checkByRook(k));

        r.setMySide(false);
        board.getWholeBoard()[4][6] = r;
        r.setLocation(4,6);
        board.getWholeBoard()[4][3] = null;
        assertTrue(newGame.checkByRook(k));
        r.setMySide(true);
        assertFalse(newGame.checkByRook(k));

    }

    /**
     * This function test if King is check by opponent's Queen
     **/
    @Test
    public void QueenCheck(){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);

        Queen q = new Queen(board, 3, 5, false);

        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.checkByQueen(k));
        board.getWholeBoard()[3][5] = q;

        assertTrue(newGame.checkByQueen(k));


        board.getWholeBoard()[5][5] = q;
        q.setLocation(5,5);
        board.getWholeBoard()[3][5] = null;
        assertTrue(newGame.checkByQueen(k));


        board.getWholeBoard()[4][6] = q;
        q.setLocation(4,6);
        board.getWholeBoard()[5][5] = null;
        assertTrue(newGame.checkByQueen(k));

        board.getWholeBoard()[4][4] = q;
        q.setLocation(4,4);
        board.getWholeBoard()[4][6] = null;
        assertTrue(newGame.checkByQueen(k));

        board.getWholeBoard()[3][6] = q;
        q.setLocation(3,6);
        board.getWholeBoard()[4][4] = null;
        assertTrue(newGame.checkByQueen(k));

        board.getWholeBoard()[3][4] = q;
        q.setLocation(3,4);
        board.getWholeBoard()[3][6] = null;
        assertTrue(newGame.checkByQueen(k));

        board.getWholeBoard()[5][4] = q;
        q.setLocation(5,4);
        board.getWholeBoard()[3][4] = null;
        assertTrue(newGame.checkByQueen(k));

        board.getWholeBoard()[5][6] = q;
        q.setLocation(5,6);
        board.getWholeBoard()[5][4] = null;
        assertTrue(newGame.checkByQueen(k));

    }


    /**
     * This function test if King is check by opponent's Knight
     **/
    @Test
    public void KnightCheck(){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);

        Knight n = new Knight(board, 3, 3, false);

        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.checkByKnight(k));
        board.getWholeBoard()[3][3] = n;
        assertTrue(newGame.checkByKnight(k));

        board.getWholeBoard()[6][6] = n;
        n.setLocation(6,6);
        board.getWholeBoard()[3][3] = null;
        n.setMySide(true);
        assertFalse(newGame.checkByKnight(k));

    }


    /**
     * This function test if King is check by opponent's Pawns
     **/
    @Test
    public void PawnCheck(){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);

        Pawn p = new Pawn(board, 3, 4, false);

        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.checkByPawn(k));
        board.getWholeBoard()[3][4] = p;
        assertTrue(newGame.checkByPawn(k));

        board.getWholeBoard()[3][6] = p;
        p.setLocation(3,6);
        board.getWholeBoard()[3][4] = null;
        assertTrue(newGame.checkByPawn(k));
        board.getWholeBoard()[5][3] = p;
        p.setLocation(5,3);
        board.getWholeBoard()[3][6] = null;
        assertFalse(newGame.checkByPawn(k));

        p.setMySide(true);


        p.setMySide(true);
        k.setMySide(false);

        assertFalse(newGame.checkByPawn(k));
        board.getWholeBoard()[5][4] = p;
        p.setLocation(5,4);
        board.getWholeBoard()[5][3] = null;
        assertTrue(newGame.checkByPawn(k));

        board.getWholeBoard()[5][6] = p;
        p.setLocation(5,6);
        board.getWholeBoard()[5][4] = null;
        assertTrue(newGame.checkByPawn(k));
        board.getWholeBoard()[4][6] = p;
        p.setLocation(4,6);
        board.getWholeBoard()[5][6] = null;
        assertFalse(newGame.checkByPawn(k));

    }



    /**
     * This function test if King is checked and there is no valid move
     *              for king to escape
     **/
    @Test
    public void checkMateSimple() {

        Game newGame = new Game(false);

        Board board = newGame.getGameBoard();


        assertFalse(newGame.singleChecked((King) board.getPiece(7,4)));
        assertFalse(newGame.isChecked((King) board.getPiece(7,4)));

        assertFalse(newGame.singleChecked((King) board.getPiece(0,4)));
        assertFalse(newGame.isChecked((King) board.getPiece(0,4)));

        newGame.removeAllPiece();

        King k = new King(board, 4,5, true);

        Pawn p = new Pawn(board, 3, 4, false);

        board.getWholeBoard()[4][5] = k;
        assertFalse(newGame.singleChecked(k));
        board.getWholeBoard()[3][4] = p;
        assertTrue(newGame.singleChecked(k));
        assertFalse(newGame.isChecked(k));

    }

    /**
     * This function test if King is check by opponent's Rooks
     *              and there is no potential move to save the king
     **/
    @Test
    public void checkMateLarge() {

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();

        board.movePiece(5, 5, board.getPiece(6, 5));
        board.movePiece(3, 4, board.getPiece(1, 4));
        board.movePiece(4, 6, board.getPiece(6, 6));
        board.movePiece(4, 7, board.getPiece(0, 3));

        assertTrue(newGame.getGameBoard().getPiece(7, 4) instanceof King);
        assertTrue(newGame.singleChecked((King)newGame.getGameBoard().getPiece(7, 4)));
        assertTrue(newGame.isChecked((King)newGame.getGameBoard().getPiece(7, 4)));
        assertTrue(newGame.checkMate((King)newGame.getGameBoard().getPiece(7, 4)));

    }


}
