package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class QueenTest {

    /**
     * This function test if Queen is initialized with correct x coordinate,
     *              y coordinate and mySide boolean
     **/
    @Test
    public void initQueen() {

        Board board = new Board();

        Queen q1 = new Queen(board, 7 , 3, true);
        assertTrue(q1.getRow() == 7);
        assertTrue(q1.getCol() == 3);
        assertTrue(q1.getMySide() == true);

        Queen q2 = new Queen(board, 0 , 3, false);
        assertTrue(q2.getRow() == 0);
        assertTrue(q2.getCol() == 3);
        assertTrue(q2.getMySide() == false);

    }

    /**
     * This function test if Queen correctly adjust valid and invalid move
     **/
    @Test
    public void moveQueen() {

        Board board = new Board();

        //board.getWholeBoard()[3][1] = new King(board, 7 , 3, true);

        Queen q1 = new Queen(board, 7 , 3, true);
        assertTrue(q1.QueenMove(6,3));
        assertTrue(q1.QueenMove(7,2));
        assertTrue(q1.QueenMove(7,4));
        assertFalse(q1.QueenMove(5,2));
        assertFalse(q1.QueenMove(6,1));
        assertFalse(q1.QueenMove(5,2));

        Queen q2 = new Queen(board, 0 , 3, false);
        assertTrue(q2.QueenMove(1,3));
        assertTrue(q2.QueenMove(0,2));
        assertTrue(q2.QueenMove(0,4));
        assertTrue(q2.QueenMove(1,2));
        assertTrue(q2.QueenMove(1,4));
        assertFalse(q2.QueenMove(1,5));
        assertFalse(q2.QueenMove(2,4));
        assertFalse(q2.QueenMove(3,4));

    }

    /**
     * This function test if Queen correctly adjust valid and invalid move
     *              with blocking from other pieces
     **/
    @Test
    public void blockedQueen() {

        Board board = new Board();

        Queen q = new Queen(board, 4, 3, true);
        King k = new King(board, 3, 3, true);

        board.getWholeBoard()[3][3] = k;
        board.getWholeBoard()[4][3] = q;
        assertTrue(q.QueenMove(6, 3));
        assertFalse(q.QueenMove(2, 3));

        Pawn p1 = new Pawn(board, 2,1,true);
        board.getWholeBoard()[4][3] = q;
        board.getWholeBoard()[2][1] = p1;
        assertFalse(q.QueenMove(1,0));
        assertTrue(q.QueenMove(3,2));


        Pawn p2 = new Pawn(board, 2,5,true);
        board.getWholeBoard()[2][5] = p2;
        assertFalse(q.QueenMove(1,6));
        assertTrue(q.QueenMove(3,4));

        Pawn p3 = new Pawn(board, 6,1,true);
        board.getWholeBoard()[6][1] = p2;
        assertFalse(q.QueenMove(7,0));
        assertTrue(q.QueenMove(5,2));

        Pawn p4 = new Pawn(board, 6,5,true);
        board.getWholeBoard()[6][5] = p2;
        assertFalse(q.QueenMove(7,6));
        assertTrue(q.QueenMove(5,4));

        Queen q1 = new Queen(board, 0, 7, false);
        Pawn p5 = new Pawn(board, 3,7,true);
        King k1 = new King(board, 0, 4, false);
        board.getWholeBoard()[0][4] = k1;
        board.getWholeBoard()[3][7] = p5;
        board.getWholeBoard()[0][7] = q1;
        assertFalse(q1.QueenMove(0,1));
        assertTrue(q1.QueenMove(0,6));
        assertFalse(q1.QueenMove(6,7));
        assertTrue(q1.QueenMove(2,7));


        Queen q2 = new Queen(board, 7, 0, false);
        Pawn p6 = new Pawn(board, 5, 0, true);
        King k2 = new King(board, 7, 4, false);
        board.getWholeBoard()[7][0] = q2;
        board.getWholeBoard()[5][0] = p6;
        board.getWholeBoard()[7][4] = k2;
        assertFalse(q2.QueenMove(3,0));
        assertTrue(q2.QueenMove(6,0));
        assertFalse(q2.QueenMove(7,6));
        assertTrue(q2.QueenMove(7, 2));

    }
}
