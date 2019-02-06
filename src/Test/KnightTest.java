package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class KnightTest {

    /**
     * This function tests if Knight piece is initialized with correct x coordinate,
     *              y coordinate and mySide boolean
     **/
    @Test
    public void initKnight() {

        Board board = new Board();

        Knight k1 = new Knight(board, 7, 1, true);
        assertTrue(k1.getRow() == 7);
        assertTrue(k1.getCol() == 1);
        assertTrue(k1.getMySide());

        Rook k2 = new Rook(board, 0 , 6, false);
        assertTrue(k2.getRow() == 0);
        assertTrue(k2.getCol() == 6);
        assertFalse(k2.getMySide());
    }

    /**
     * This function tests if Knight correctly adjust valid and invalid moves
     **/
    @Test
    public void moveKnight(){

        Board board = new Board();

        Knight k = new Knight(board, 4, 3, true);
        assertTrue(k.KnightMove(5,1));
        assertTrue(k.KnightMove(5,5));
        assertTrue(k.KnightMove(3,1));
        assertTrue(k.KnightMove(3,5));
        assertFalse(k.KnightMove(3,4));
        assertFalse(k.KnightMove(5,7));

    }


}
