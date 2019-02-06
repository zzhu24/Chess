package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class PawnTest {


    /**
     * This function test if Pawn is initialized with correct x coordinate,
     *              y coordinate and mySide boolean
     **/
    @Test
    public void initPawn(){

        Board board = new Board();

        Pawn p1 = new Pawn(board, 6 , 4, true);
        assertTrue(p1.getRow() == 6);
        assertTrue(p1.getCol() == 4);
        assertTrue(p1.getMySide() == true);

        Pawn p2 = new Pawn(board, 1 , 4, false);
        assertTrue(p2.getRow() == 1);
        assertTrue(p2.getCol() == 4);
        assertFalse(p2.getMySide());


    }

    /**
    * This function test if Pawn correctly adjust valid and invalid move
    **/
    @Test
    public void  movePawn(){

        Board board = new Board();

        Pawn p = new Pawn(board, 6, 4, true);
        p.setFirst(0);
        assertTrue(p.PawnMove(4,4));
        assertTrue(p.PawnMove(5,4));
        assertFalse(p.PawnMove(7, 4));
        assertFalse(p.PawnMove(5, 3));
        p.setFirst(1);
        assertFalse(p.PawnMove(4,4));
        assertTrue(p.PawnMove(5,4));

        Pawn p2 = new Pawn(board, 1, 4, false);
        p2.setFirst(0);
        assertTrue(p2.PawnMove(3,4));
        assertTrue(p2.PawnMove(2,4));
        assertFalse(p2.PawnMove(0, 4));
        assertFalse(p2.PawnMove(5, 3));
        p2.setFirst(1);
        assertFalse(p2.isFirstMove() == 0);
        assertTrue(p2.PawnMove(2,4));
        assertFalse(p2.PawnMove(3,4));
        assertFalse(p2.PawnMove(0,4));


    }

    /**
     * This function test if Pawn correctly adjust valid and invalid move
     *              if there is another piece blocking its move
     **/
    @Test
    public void blockPawn(){

        Board board = new Board();
        Pawn p1 = new Pawn(board, 6, 4, true);
        p1.setFirst(0);
        Queen q1 = new Queen(board, 4, 4, true);
        board.getWholeBoard()[4][4] = q1;
        board.getWholeBoard()[6][4] = p1;
        assertFalse(p1.PawnMove(4,4));
        assertTrue(p1.PawnMove(5,4));
        p1.setFirst(1);
        King k1 = new King(board, 5, 4, true);
        board.getWholeBoard()[5][4] = k1;
        assertFalse(p1.PawnMove(5,4));


        Pawn p2 = new Pawn(board, 1, 4, false);
        p2.setFirst(0);
        board.getWholeBoard()[3][4] = q1;
        assertFalse(p2.PawnMove(3,4));
        assertTrue(p2.PawnMove(2,4));
        p2.setFirst(1);
        board.getWholeBoard()[2][4] = k1;
        assertFalse(p2.PawnMove(2,4));


    }

    /**
     * This function test if Pawn correctly adjust valid and invalid move
     *              if it want to make a capture move
     **/
    @Test
    public void capturePawn(){

        Board board = new Board();
        Pawn p1 = new Pawn(board, 6, 4, true);
        p1.setFirst(0);
        Queen q1 = new Queen(board, 5, 3, false);
        board.getWholeBoard()[5][3] = q1;
        board.getWholeBoard()[6][4] = p1;
        assertFalse(p1.PawnMove(5,5));
        assertTrue(p1.PawnMove(5,3));
        p1.setFirst(1);
        assertFalse(p1.PawnMove(5,5));
        assertTrue(p1.PawnMove(5,3));
        q1.setMySide(true);
        assertFalse(p1.PawnMove(5,3));

        Pawn p2 = new Pawn(board, 1, 4, true);
        p2.setFirst(0);
        Queen q2 = new Queen(board, 0, 3, false);
        board.getWholeBoard()[0][3] = q2;
        board.getWholeBoard()[1][4] = p2;
        assertFalse(p2.PawnMove(2,5));
        assertTrue(p2.PawnMove(0,3));
        p2.setFirst(1);
        assertFalse(p2.PawnMove(2,5));
        assertTrue(p2.PawnMove(0,3));
        q2.setMySide(true);
        assertFalse(p2.PawnMove(0,3));

    }


}
