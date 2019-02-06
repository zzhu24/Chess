package Test;


//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class InitTest {

    /**
     * This function test if the board is initialized with every piece on correct location
     *              and correct x coordinate, y coordinate and mySide boolean
     **/
    @Test
    public void boardKing() {

        Game game = new Game(false);

        Board board = game.getGameBoard();

        assertTrue(board.getPiece(0,4) instanceof King);
        assertTrue(board.getPiece(7,4) instanceof King);

        assertTrue(board.getPiece(0,4).getRow() == 0);
        assertTrue(board.getPiece(0,4).getCol() == 4);
        assertTrue(board.getPiece(7,4).getRow() == 7);
        assertTrue(board.getPiece(7,4).getCol() == 4);


        assertFalse(board.getPiece(0,4).getMySide());
        assertTrue(board.getPiece(7,4).getMySide());

        assertTrue(board.getPiece(0,0) instanceof Rook);
        assertTrue(board.getPiece(0,7) instanceof Rook);
        assertTrue(board.getPiece(7,0) instanceof Rook);
        assertTrue(board.getPiece(7,7) instanceof Rook);


        assertTrue(board.getPiece(0,1) instanceof Knight);
        assertTrue(board.getPiece(0,6) instanceof Knight);
        assertTrue(board.getPiece(7,1) instanceof Knight);
        assertTrue(board.getPiece(7,6) instanceof Knight);

        //Initialize Main.Bishop
        assertTrue(board.getPiece(0,2) instanceof Bishop);
        assertTrue(board.getPiece(0,5) instanceof Bishop);
        assertTrue(board.getPiece(7,2) instanceof Bishop);
        assertTrue(board.getPiece(7,5) instanceof Bishop);

        //Initialize Main.Queen
        assertTrue(board.getPiece(0,3) instanceof Queen);
        assertTrue(board.getPiece(7,3) instanceof Queen);
        assertTrue(board.getPiece(0,4) instanceof King);
        assertTrue(board.getPiece(7,4) instanceof King);


        assertTrue(board.getPiece(1,3) instanceof Pawn);
        assertTrue(board.getPiece(1,4) instanceof Pawn);
        assertTrue(board.getPiece(6,3) instanceof Pawn);
        assertTrue(board.getPiece(6,4) instanceof Pawn);


    }

    /**
     * This function test if no piece can make self suicide move
     **/
    @Test
    public void invalidSuicide(){

        Board board = new Board();

        Queen q = new Queen(board, 7,3,true);
        King k = new King(board, 7,4,true);
        Pawn p = new Pawn(board, 6, 3, false);

        board.getWholeBoard()[7][3] = q;
        board.getWholeBoard()[7][4] = k;
        board.getWholeBoard()[6][3] = p;

        assertTrue(q.QueenMove(6,3));
        assertFalse(q.QueenMove(7,4));

    }







}
