package com.dominicswaine.seg_agile_project.Logic;

import com.dominicswaine.seg_agile_project.Board.GameWindow;
import com.dominicswaine.seg_agile_project.Board.HoleUI;
import com.dominicswaine.seg_agile_project.Board.KazanUI;

import java.awt.event.*;

/**
 * @author Ayberk Demirkol, Dominic Swaine
 */
public class Game {
    private Side player_side;
    private Board game_board;
    private GameWindow gui;

    /**
     * Initialize the GUI, connect each logical hole to holeUI and add mouse listeners.
     * Connect each kazan to kazanUI
     */
    private void initialiseGame() {
        player_side = Side.WHITE;
        game_board = new Board();
        gui = new GameWindow();

        for (int holeNo = 0; holeNo < game_board.getHoles().length; holeNo++) {
            Hole logicHole = game_board.getHoleByIndex(holeNo);
            HoleUI guiHole = holeNo < 9 ? gui.getHolesTopRow().get(8 - holeNo) : gui.getHolesBottomRow().get(holeNo - 9);
            logicHole.setGui(guiHole);

            final int holeIndex = holeNo;
            if (logicHole.getOwner() == Side.WHITE) {
                guiHole.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        game_board.redistribute(holeIndex);
                    }
                });
            }
        }

        for(int i = 0; i < game_board.getKazans().length; i++){
            Kazan logicKazan = game_board.getKazanByIndex(i);
            KazanUI guiKazan = gui.getKazans().get(i);
            logicKazan.setGui(guiKazan);
        }
    }

    /**
     * Default game constructor. Adds 9 korgools for each hole
     */
    public Game() {
        initialiseGame();
        for(Hole hole : game_board.getHoles()){
            for(int i = 0; i<9; i++){
                hole.addKorgool(new Korgool());
            }
        }
    }

    /**
     * Custom game constructor
     * @param playerTuz String storing players tuz. 0 if no tuz
     * @param opponentTuz String storing opponents tuz. 0 if no tuz
     * @param playerHoles Array of int storing number of korgools in players kazan at index 0 and holes at indexes 1-9
     * @param opponentHoles Array of int storing number of korgools in opponents kazan at index 0 and holes at indexes 1-9
     */
    public Game(String playerTuz, String opponentTuz, int[] playerHoles, int[] opponentHoles){
        initialiseGame();

        // Initializes to korgools per each hole. +1 for index because index 0 of playerHoles is for kazan.
        // -8 for opponentHoles because holeNo starts from 8 for opponent.
        for(Hole holeNo : game_board.getHoles()) {

            if(holeNo.getOwner() == player_side) {
                for (int korgoolNo = 0; korgoolNo < playerHoles[holeNo.getHoleIndex() + 1]; korgoolNo++) {
                    holeNo.addKorgool(new Korgool());
                }
            }
            else{
                for (int korgoolNo = 0; korgoolNo < opponentHoles[holeNo.getHoleIndex() - 8]; korgoolNo++) {
                    holeNo.addKorgool(new Korgool());
                }
            }
        }

        //Initialise Kazans.
        for(Kazan k : game_board.getKazans()){
            if(k.getOwner() == player_side) {
                for (int korgoolNo = 0; korgoolNo < playerHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }

            else{
                for (int korgoolNo = 0; korgoolNo < opponentHoles[0]; korgoolNo++) {
                    k.addKorgool(new Korgool());
                }
            }
        }

        // Sets tuz for each player.
        int playerTuzNo = Integer.parseInt(playerTuz);
        int opponentTuzNo = Integer.parseInt(opponentTuz);

        if(playerTuzNo != 0) {
            game_board.getHoleByIndex(playerTuzNo + 8).markAsTuz();
            player_side.makeTuz();
        }
        if(opponentTuzNo != 0) {
            game_board.getHoleByIndex(opponentTuzNo-1).markAsTuz();
            Side.BLACK.makeTuz();
        }
    }

    public static void main(String[] args){
        Game game1 = new Game();
        while(game1.game_board.getKazanByIndex(0).getKoorgools().size() <= 81 && game1.game_board.getKazanByIndex(1).getKoorgools().size() <= 81){
            Side nextToPlay = game1.game_board.getNextToPlay();
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException ie){
                System.out.println("Interrupted exception...");
            }
            //System.out.print("");
            if(nextToPlay == Side.BLACK){
                game1.game_board.challengeMove();
            }
        }
        //TODO: End game screen after while loop ends.
    }
}
