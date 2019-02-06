package Test;

import Main.*;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class StaleMateTest {

    @Test
    public void testStaleMateOne (){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        board.getWholeBoard()[0][5] = new King(board, 0, 5, false);
        board.getWholeBoard()[1][5] = new Pawn(board, 0, 5, true);
        board.getWholeBoard()[2][5] = new King(board, 1, 5, true);

        assertTrue(board.getPiece(0, 5) instanceof King);
        newGame.staleMate((King) board.getPiece(0, 5));

    }

    @Test
    public void testStaleMateTwo (){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        board.getWholeBoard()[0][0] = new King(board, 0, 0, false);
        board.getWholeBoard()[0][1] = new Bishop(board, 0, 1, false);
        board.getWholeBoard()[0][7] = new Rook(board, 0, 7, true);
        board.getWholeBoard()[2][1] = new King(board, 2, 1, true);

        assertTrue(board.getPiece(0, 0) instanceof King);
        newGame.staleMate((King) board.getPiece(0, 0));

    }

    @Test
    public void testStaleMateThree (){

        Game newGame = new Game(false);
        Board board = newGame.getGameBoard();
        newGame.removeAllPiece();

        board.getWholeBoard()[3][2] = new King(board, 3, 2, true);
        board.getWholeBoard()[5][1] = new Queen(board, 5, 1, true);
        board.getWholeBoard()[6][0] = new Pawn(board, 6, 0, false);
        board.getWholeBoard()[7][0] = new King(board, 7, 0, false);

        assertTrue(board.getPiece(7, 0) instanceof King);
        newGame.staleMate((King) board.getPiece(7, 0));

    }

}
