package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class RookTest {

    /**
     * This function test if Rook is initialized with correct x coordinate,
     *              y coordinate and mySide boolean
     **/
    @Test
    public void initBishop() {

        Board board = new Board();

        Rook r1 = new Rook(board, 7, 0, true);
        assertTrue(r1.getRow() == 7);
        assertTrue(r1.getCol() == 0);
        assertTrue(r1.getMySide());

        Rook r2 = new Rook(board, 0 , 7, false);
        assertTrue(r2.getRow() == 0);
        assertTrue(r2.getCol() == 7);
        assertFalse(r2.getMySide());
    }

    /**
     * This function test if Rook correctly adjust valid and invalid move
     **/
    @Test
    public void moveRook(){

        Board board = new Board();

        Rook r1 = new Rook(board, 7, 0, true);
        assertTrue(r1.RookMove(6,0));
        assertTrue(r1.RookMove(7,1));
        assertTrue(r1.RookMove(7,3));
        assertFalse(r1.RookMove(5,2));
        assertFalse(r1.RookMove(4,1));
        assertFalse(r1.RookMove(10,1));

        Rook r2 = new Rook(board, 0 , 7, false);
        assertTrue(r2.RookMove(0,5));
        assertTrue(r2.RookMove(3,7));
        assertFalse(r2.RookMove(5,2));
        assertFalse(r2.RookMove(4,1));
        assertFalse(r2.RookMove(10,1));

    }

    /**
     * This function test if Rook correctly adjust valid and invalid move
     *              with blocking from other pieces
     **/
    @Test
    public void blockRook(){

        Board board = new Board();
        Rook r1 = new Rook(board, 0, 7, false);
        Pawn p1 = new Pawn(board, 3,7,true);
        King k1 = new King(board, 0, 4, false);
        board.getWholeBoard()[0][4] = k1;
        board.getWholeBoard()[3][7] = p1;
        board.getWholeBoard()[0][7] = r1;
        assertFalse(r1.RookMove(0,1));
        assertTrue(r1.RookMove(0,6));
        assertFalse(r1.RookMove(6,7));
        assertTrue(r1.RookMove(2,7));


        Rook r2 = new Rook(board, 7, 0, false);
        Pawn p2 = new Pawn(board, 5, 0, true);
        King k2 = new King(board, 7, 4, false);
        board.getWholeBoard()[7][0] = r2;
        board.getWholeBoard()[5][0] = p2;
        board.getWholeBoard()[7][4] = k2;
        assertFalse(r2.RookMove(3,0));
        assertTrue(r2.RookMove(6,0));
        assertFalse(r2.RookMove(7,6));
        assertTrue(r2.RookMove(7, 2));



    }



}
