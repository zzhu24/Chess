package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class BishopTest {

    /**
     * This function tests if the initialized Bishop got correct x coordinate,
     *              y coordinate and player side boolean
     **/
    @Test
    public void initBishop() {

        Board board = new Board();

        Bishop b1 = new Bishop(board, 7 , 2, true);
        assertTrue(b1.getRow() == 7);
        assertTrue(b1.getCol() == 2);
        assertTrue(b1.getMySide() == true);

        Bishop b2 = new Bishop(board, 0 , 5, false);
        assertTrue(b2.getRow() == 0);
        assertTrue(b2.getCol() == 5);
        assertFalse(b2.getMySide());
    }

    /**
     * This function tests if Bishop correctly adjust valid and invalid moves
     **/
    @Test
    public void moveBishop(){

        Board board = new Board();

        Bishop b1 = new Bishop(board, 7 , 2, true);
        assertTrue(b1.BishopMove(5,4));
        assertTrue(b1.BishopMove(5,0));
        assertFalse(b1.BishopMove(6,5));
        assertFalse(b1.BishopMove(3,1));

    }

    /**
     * This function tests if Bishop correctly adjust valid and invalid moves
     *              if there is another piece block the way to destination
     **/
    @Test
    public void blockBishop(){

        Board board = new Board();

        Bishop b = new Bishop(board, 4 , 3, true);

        Pawn p1 = new Pawn(board, 2,1,true);
        board.getWholeBoard()[4][3] = b;
        board.getWholeBoard()[2][1] = p1;
        assertFalse(b.BishopMove(1,0));
        assertTrue(b.BishopMove(3,2));


        Pawn p2 = new Pawn(board, 2,5,true);
        board.getWholeBoard()[2][5] = p2;
        assertFalse(b.BishopMove(1,6));
        assertTrue(b.BishopMove(3,4));

        Pawn p3 = new Pawn(board, 6,1,true);
        board.getWholeBoard()[6][1] = p2;
        assertFalse(b.BishopMove(7,0));
        assertTrue(b.BishopMove(5,2));

        Pawn p4 = new Pawn(board, 6,5,true);
        board.getWholeBoard()[6][5] = p2;
        assertFalse(b.BishopMove(7,6));
        assertTrue(b.BishopMove(5,4));

    }



}
