package Test;

//Checked !!!!!!!!!


import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class CamelTest {

    /**
     * This function tests if Camel correctly adjust valid and invalid moves
     **/
    @Test
    public void moveKnight(){

        Board board = new Board();

        Camel camel = new Camel(board, 3, 3, true);
        assertTrue(camel.CamelMove(2,0));
        assertTrue(camel.CamelMove(0,2));
        assertTrue(camel.CamelMove(4,6));
        assertTrue(camel.CamelMove(6,4));
        assertFalse(camel.CamelMove(3,4));
        assertFalse(camel.CamelMove(5,7));

    }

}
