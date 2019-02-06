package Test;

//Checked !!!!!!!!!

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class ZebraTest {

    /**
     * This function tests if Zebra correctly adjust valid and invalid moves
     **/
    @Test
    public void moveZebra(){

        Board board = new Board();

        Zebra z = new Zebra(board, 3, 3, true);
        assertTrue(z.ZebraMove(0,1));
        assertTrue(z.ZebraMove(1,0));
        assertTrue(z.ZebraMove(5,6));
        assertTrue(z.ZebraMove(6,5));
        assertFalse(z.ZebraMove(4,4));
        assertFalse(z.ZebraMove(4,6));

    }

}
