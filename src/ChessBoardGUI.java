//Checked !!!!!!!!!

import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.io.File;

import java.util.List;
import java.util.ArrayList;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class ChessBoardGUI{

    private JFrame gameFrame;
    private BoardPanel boardPanel;
    private Game game;
    private Board board;

    private int white_score;
    private int black_score;
    private boolean white_turn = true;
    private boolean alreadyUndo = false;

    private TilePanel sourceTile;
    private TilePanel destinationTile;
    private Piece selectedMovePiece;

    public String white_user_name = null;
    public String black_user_name = null;


    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 800);
    private static Dimension BOARD_PANEL_DIMENSION = new Dimension(700, 700);
    private static Dimension TILE_PANEL_DIMENSION = new Dimension(75, 75);
    private Color lightBrown = new Color(255, 204, 51);
    private Color darkBrown = new Color(153,102, 0);
    private Color beigeAround = new Color(255, 255, 204);




    /**
     * This function create new chessboard with players' customized name kept same
     *              and the score accumulated until they exit the game
     * This function should be called in ChessBoardGUI Main().
     *
     * @param  isCustomized   if players want to use customized camel and zebra or traditional knight
     * @param  white_user_name   white pieces owner's customized name
     * @param  black_user_name   black pieces owner's customized name
     * @param  prev_white_score   white pieces owner's accumulated score
     * @param  prev_black_score   black pieces owner's accumulated score
     **/
    public ChessBoardGUI(boolean isCustomized, String white_user_name, String black_user_name, int prev_white_score, int prev_black_score){

        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.white_user_name = white_user_name;
        this.black_user_name = black_user_name;
        this.white_score = prev_white_score;
        this.black_score = prev_black_score;


        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenuItem forfeitButton = new JMenuItem("Forfeit Game");
        forfeitButton.addActionListener(new ActionListener() {
            @Override
            /**
             * This function will ask if opponent agree to forfeit and gain one point
             **/
            public void actionPerformed(ActionEvent e) {
                int dialogSet = JOptionPane.showConfirmDialog(null, "Do You Agree To Forfeit ?",
                        "", JOptionPane.YES_NO_OPTION);
                if (dialogSet == 0) {
                    if (white_turn) {
                        black_score += 1;
                    } else {
                        white_score += 1;
                    }
                    int dialogSet2 = JOptionPane.showConfirmDialog(null,
                            "Already Forfeited !~ \n" + "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                            "", JOptionPane.CLOSED_OPTION);

                    gameFrame.dispose();
                    boolean newCustomized = false;
                    int dialogSet3 = JOptionPane.showConfirmDialog(null, "Use Customized Pieces ?",
                            "", JOptionPane.YES_NO_OPTION);
                    if (dialogSet3 == 0) {
                        newCustomized = true;
                    }
                    ChessBoardGUI newGame = new ChessBoardGUI(newCustomized, white_user_name, black_user_name, white_score, black_score);
                    newGame.gameFrame.setVisible(true);
                }
            }
        });
        gameMenu.add(forfeitButton);
        JMenuItem resetButton = new JMenuItem("Restart Game");
        resetButton.addActionListener(new ActionListener() {
            @Override
            /**
             * This function will ask if players want to restart game
             * This function will also ask if players want to use customized Pieces or traditional pieces
             **/
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();
                boolean newCustomized = false;
                int dialogSet = JOptionPane.showConfirmDialog(null, "Use Customized Pieces ?",
                        "", JOptionPane.YES_NO_OPTION);
                if (dialogSet == 0)
                    newCustomized = true;
                ChessBoardGUI newGame = new ChessBoardGUI(newCustomized, white_user_name, black_user_name, white_score, black_score);
                newGame.gameFrame.setVisible(true);
            }
        });
        gameMenu.add(resetButton);

        JMenuItem undoGame = new JMenuItem("Undo Previous Step");
        undoGame.addActionListener(new ActionListener() {
            @Override
            /**
             * This function will undo one step and give back the turns
             **/
            public void actionPerformed(ActionEvent e) {
                if(!alreadyUndo){
                    board.realUndoMove();
                    boardPanel.setTileIcon((board.getOldRow1() + 1), (board.getOldCol1() + 1));
                    boardPanel.setTileIcon((board.getOldRemovedRow1() + 1), (board.getOldRemovedCol1() + 1));
                    white_turn = !white_turn;
                    alreadyUndo = true;
                }
                else{
                    int dialogSet = JOptionPane.showConfirmDialog(null, "Already Undo One Step !",
                            "", JOptionPane.CLOSED_OPTION);
                }
            }
        });
        gameMenu.add(undoGame);

        JMenuItem ScoresGame = new JMenuItem("Check Scores");
        ScoresGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogSet5 = JOptionPane.showConfirmDialog(null,
                        white_user_name + " Current Scores: \n"+ "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                        "", JOptionPane.CLOSED_OPTION);
            }
        });
        gameMenu.add(ScoresGame);

        JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        gameMenu.add(exitGame);
        menuBar.add(gameMenu);
        this.gameFrame.setJMenuBar(menuBar);



        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);

        this.game = new Game(isCustomized);
        this.board = game.getGameBoard();

        this.boardPanel = new BoardPanel(this.board);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);

    }


    /**
     * This function create the board panel class
     */
    private class BoardPanel extends JPanel{

        List<TilePanel> boardTiles;
        Board board;

        BoardPanel(Board board){
            super(new GridLayout(10, 10));
            this.board = board;
            this.boardTiles = new ArrayList<>();
            for(int i = 0; i < 100; i++){
                TilePanel tilePanel = new TilePanel(this, (i / 10), (i % 10));
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();

        }

        private TilePanel getTilePanel(int tileRow, int tileCol){
            return boardTiles.get(tileRow * 10 + tileCol);
        }


        /**
         * This function return the piece on specific piece of the panel
         **/
        private Piece getPieceTileboard(int tileRow, int tileCol){
            return this.board.getPiece(tileRow - 1, tileCol - 1);
        }

        /**
         * This function sets the icon on specific location of the panel
         **/
        private void setTileIcon(int tileRow, int tileCol){
            getTilePanel(tileRow, tileCol).setIcon(this.board);
            validate();
            repaint();
        }

    }

    public Game getGame() {
        return this.game;
    }

    public Board getBoard() {
        return this.board;
    }

    /**
     * This function will create the tile panel class
     */
    private class TilePanel extends JPanel{

        private int tileRow;
        private int tileCol;

        TilePanel(BoardPanel boardPanel, int tileRow, int tileCol){
            super(new GridBagLayout());
            this.tileRow = tileRow;
            this.tileCol = tileCol;
            setPreferredSize(TILE_PANEL_DIMENSION);
            setBackColor();
            setIcon(board);
            validate();




            addMouseListener(new MouseListener() {
                @Override
                /**
                 * This function take the mouse click and move the piece
                 * This function also check for checkmate and stalemate situation
                 *              and add one point to the winner
                 **/
                public void mouseClicked(MouseEvent e) {
                    if(SwingUtilities.isLeftMouseButton(e)){
                        if(sourceTile == null){
                            sourceTile = boardPanel.getTilePanel(getTileRow(),getTileCol());
                            selectedMovePiece = boardPanel.getPieceTileboard(getTileRow(),getTileCol());
                            if (selectedMovePiece == null){
                                int dialogSet = JOptionPane.showConfirmDialog(null, "You did not choose a piece ~",
                                        "", JOptionPane.CANCEL_OPTION);
                                sourceTile  = null;
                            }
                            else{
                                if(selectedMovePiece.getMySide() != white_turn){
                                    sourceTile  = null;
                                    int dialogSet = JOptionPane.showConfirmDialog(null, "It is not you ture ~",
                                            "", JOptionPane.CANCEL_OPTION);
                                }
                            }

                        }
                        // second click
                        else{
                            destinationTile = boardPanel.getTilePanel(getTileRow(),getTileCol());

                            boolean validMove = boardPanel.board.tryMovePiece(getTileRow()-1,getTileCol()-1, selectedMovePiece);
                            if (validMove){
                                boardPanel.board.recordMovePiece(getTileRow()-1,getTileCol()-1, selectedMovePiece);

                                boardPanel.board.movePiece(getTileRow()-1,getTileCol()-1, selectedMovePiece);
                                //boardPanel.board.printBoard();
                                alreadyUndo = false;
                                white_turn = !white_turn;
                            }
                            else{
                                int dialogSet = JOptionPane.showConfirmDialog(null, "It is not a valid move ~",
                                        "", JOptionPane.CANCEL_OPTION);
                            }


                            boardPanel.setTileIcon(sourceTile.getTileRow(), sourceTile.getTileCol());
                            boardPanel.setTileIcon(destinationTile.getTileRow(), destinationTile.getTileCol());
                            sourceTile = null;
                            destinationTile = null;
                            selectedMovePiece = null;



                            King king = (King)board.findKing(white_turn);
                            if(king == null){
                                if(white_turn){
                                    black_score += 1;
                                    int dialogSet = JOptionPane.showConfirmDialog(null,
                                            white_user_name + " Already Lost ! \n"+ "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                                            "", JOptionPane.CLOSED_OPTION);
                                }
                                else{
                                    white_score += 1;
                                    int dialogSet = JOptionPane.showConfirmDialog(null,
                                            black_user_name + " Already Lost ! \n"+ "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                                            "", JOptionPane.CLOSED_OPTION);
                                }
                                gameFrame.dispose();
                                boolean newCustomized = false;
                                int dialogSet = JOptionPane.showConfirmDialog(null, "Use Customized Pieces ?",
                                        "", JOptionPane.YES_NO_OPTION);
                                if (dialogSet == 0){
                                    newCustomized = true;
                                }
                                ChessBoardGUI newGame = new ChessBoardGUI(newCustomized, white_user_name, black_user_name, white_score, black_score);
                                newGame.gameFrame.setVisible(true);
                            }
                            else{
                                if(game.checkMate(king)){
                                    if(white_turn){
                                        black_score += 1;
                                    }
                                    else{
                                        white_score += 1;
                                    }
                                    int dialogSet = JOptionPane.showConfirmDialog(null,
                                            "Checkmate ~ \n" + "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                                            "", JOptionPane.CLOSED_OPTION);
                                    gameFrame.dispose();
                                    boolean newCustomized2 = false;
                                    int dialogSet2 = JOptionPane.showConfirmDialog(null, "Use Customized Pieces ?",
                                            "", JOptionPane.YES_NO_OPTION);
                                    if (dialogSet2 == 0){
                                        newCustomized2 = true;
                                    }
                                    ChessBoardGUI newGame = new ChessBoardGUI(newCustomized2, white_user_name, black_user_name, white_score, black_score);
                                    newGame.gameFrame.setVisible(true);
                                }
                                /*
                                if(game.staleMate(king)){
                                    black_score += 1;
                                    white_score += 1;
                                    int dialogSet = JOptionPane.showConfirmDialog(null,
                                            "Stalemate ~ \n" + "(" + white_user_name + " " + white_score + ") : (" + black_user_name + " " + black_score + ")",
                                            "", JOptionPane.CLOSED_OPTION);
                                    gameFrame.dispose();
                                    boolean newCustomized3 = false;
                                    int dialogSet3 = JOptionPane.showConfirmDialog(null, "Use Customized Pieces ?",
                                            "", JOptionPane.YES_NO_OPTION);
                                    if (dialogSet3 == 0){
                                        newCustomized3 = true;
                                    }
                                    ChessBoardGUI newGame = new ChessBoardGUI(newCustomized3, white_user_name, black_user_name, white_score, black_score);
                                    newGame.gameFrame.setVisible(true);
                                }
                                */

                            }



                        }
                    }
                }


                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });




        }









        public int getTileRow(){
            return this.tileRow;
        }

        public int getTileCol(){
            return this.tileCol;
        }


        /**
         * This function will draw alphabets and numbers on the surrounding
         *              to indicate rows and columns
         * @param  g    graph we want to paint and generate
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Font font = new Font("TimesRoman", Font.PLAIN, 50);
            g.setFont(font);

            int xCoordinate = getWidth() / 2;
            int yCoordinate = getHeight() / 2;

            if(this.tileRow == 1 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("8", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 2 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("7", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 3 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("6", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 4 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("5", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 5 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("4", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 6 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("3", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 7 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("2", xCoordinate - 20, yCoordinate + 25);
            }
            if(this.tileRow == 8 && (this.tileCol == 0 || this.tileCol == 9)){
                g.drawString("1", xCoordinate - 20, yCoordinate + 25);
            }

            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 1){
                g.drawString("A", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 2){
                g.drawString("B", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 3){
                g.drawString("C", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 4){
                g.drawString("D", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 5){
                g.drawString("E", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 6){
                g.drawString("F", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 7){
                g.drawString("G", xCoordinate - 25, yCoordinate + 20);
            }
            if((this.tileRow == 0|| this.tileRow == 9) && this.tileCol == 8){
                g.drawString("H", xCoordinate - 25, yCoordinate + 20);
            }
        }



        /**
         * This function will paint all tiles with two colors
         *              and light color is next to dark colors
         * This function will paint all surrounding tiles with light beige
         */
        private void setBackColor() {
            if(this.tileRow == 0 || this.tileCol == 0 || this.tileRow == 9 || this.tileCol == 9){
                setBackground(beigeAround);
            }
            else if(this.tileRow % 2 != 0){
                if(this.tileCol % 2 != 0){
                    setBackground(lightBrown);
                }
                else if(this.tileCol % 2 == 0){
                    setBackground(darkBrown);
                }
            }
            else if(this.tileRow % 2 == 0){
                if(this.tileCol % 2 == 0){
                    setBackground(lightBrown);
                }
                else if(this.tileCol % 2 != 0){
                    setBackground(darkBrown);
                }
            }
        }

        /**
         * This function will paint all tiles with two colors
         *              and light color is next to dark colors
         * This function will paint all surrounding tiles with light beige
         */
        public void setIcon(Board board) {
            this.removeAll();

            if(board.getPiece(this.tileRow - 1, this.tileCol - 1) != null){
                String tempClass = board.getPiece(this.tileRow - 1, this.tileCol - 1).getClass().getSimpleName().toString();

                String tempColor = null;
                if(board.getPiece(this.tileRow - 1, this.tileCol - 1).getMySide()){
                    tempColor = "_white.png";
                }
                else if(!board.getPiece(this.tileRow - 1, this.tileCol - 1).getMySide()){
                    tempColor = "_black.png";
                }
                try{
                    BufferedImage image = ImageIO.read(new File("src/PieceImage/" + tempClass + tempColor));
                    add(new JLabel(new ImageIcon(image)));

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * This function is the main function that runs the GUI and the game logic
     **/
    public static void main(String[] args){
        boolean customize = false;

        String white_user_name = JOptionPane.showInputDialog("User Name for White Side : ");
        String black_user_name = JOptionPane.showInputDialog("User Name for Black Side : ");

        if(white_user_name == null || white_user_name.length() < 1){
            white_user_name = "White Player";
        }
        if(black_user_name == null || black_user_name.length() < 1 ){
            black_user_name = "Black Player";
        }


        int dialogSet = JOptionPane.showConfirmDialog(null, "Use Customized Pieces?",
                                                        "", JOptionPane.YES_NO_OPTION);
        if (dialogSet == 0)
            customize = true;

        ChessBoardGUI game = new ChessBoardGUI(customize, white_user_name, black_user_name, 0, 0);

    }

}