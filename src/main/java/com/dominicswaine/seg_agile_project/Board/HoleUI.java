package com.dominicswaine.seg_agile_project.Board;


import javax.swing.*;
import java.awt.*;

/**
 * A hole contains korgols and it's the main interactive
 * feature of the game. Clicking on a hole is how the player can
 * make a move.
 *
 * @author Danilo Del Busso
 * @version 30-11-2018
 */
public class HoleUI extends KorgoolsContainerUI {

    private Color tuzBorderColor;
    private int tuz;
    private int n;

    /**
     * The hole is the main korgol container of the game
     * @param n the number of the hole
     */
    public HoleUI(int n) {
        setOpaque(false);
        String backgroundPath = System.getProperty("user.dir") + "/src/main/resources/frames/frame" + (n) + ".png";

        setIcon(new ImageIcon(backgroundPath));
        setLayout(new GridLayout(8, 5));
        this.tuz = 0;
        tuzBorderColor = new Color(0f, 0f, 0f, .0f); //white but completely transparent color
    }

    /**
     * Displays a color rectangle around the hole showing that it has been assigned to a specific player
     * @param c the color of the rectangle to display
     */
    public void displayTuz(Color c) {
        this.tuzBorderColor = c;
        revalidate();
        repaint();
    }

    /**
     * Sets the color of the tuz rectangle to transparent
     */
    public void dontDisplayTuz() {
        this.tuzBorderColor = (new Color(0f, 0f, 0f, .0f));
        revalidate();
        repaint();
    }

    /**
     * Assigns this hole as tuz to the designed player
     * @param player which player the tuz has to b eassigned to (0 = not assigned to any player, 1 = assigned to 1st player, 2= assigned to 2nd player)
     * @return true if the tuz has been assigned successfully
     */
    public boolean assignTuz(int player) {
        if (player > -1 && player < 3) {
            tuz = player;
            return true;
        }

        return false;
    }
    
    /**
     * Paint window components
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D tuzBorder = (Graphics2D) g;
        tuzBorder.setColor(tuzBorderColor); //set color to red with 100% transparency (last parameter)
        tuzBorder.setStroke(new BasicStroke(6));
        tuzBorder.drawRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Function to return the maximum number of Korgools which can be contained within a Hole
     * return the maximum number of Korgools which can be contained within a Hole
     */
    @Override
    public int getMaxKorgolsPossible() {
        return 21;
    }

    /**
     * Return a Dimension object of the preferred window size
     * @return a Dimension object of the preferred window size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 240);
    }
    
    /**
     * Function to return the index of the Hole
     * @return the index of the Hole
     */
    public int getN(){return n;}

}
