package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class KingTest {

    /**
     * This function tests if King piece is initialized with correct x coordinate,
     *              y coordinate and mySide boolean
     **/
    @Test
    public void initKing() {

        Board board = new Board();

        King k1 = new King(board, 7 , 4, true);
        assertTrue(k1.getRow() == 7);
        assertTrue(k1.getCol() == 4);
        assertTrue(k1.getMySide() == true);

        King k2 = new King(board, 0 , 4, false);
        assertTrue(k2.getRow() == 0);
        assertTrue(k2.getCol() == 4);
        assertFalse(k2.getMySide());
    }

    /**
     * This function tests if King correctly adjust valid and invalid moves
     **/
    @Test
    public void moveKing() {

        Board board = new Board();

        King k = new King(board, 0, 4, true);

        assertFalse(k.kingMove(0, 4));
        assertFalse(k.kingMove(-1, -3));
        assertFalse(k.kingMove(10, 11));
        assertFalse(k.kingMove(1, 6));
        assertTrue(k.kingMove(0, 3));
        assertTrue(k.kingMove(0, 5));
        assertTrue(k.kingMove(1, 5));
    }




}
