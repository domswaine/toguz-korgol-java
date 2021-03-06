package com.dominicswaine.seg_agile_project.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * This class test the scoreboard class in the UI
 * @author Danilo Del Busso
 * @version 12-12-2018
 */
@RunWith(Parameterized.class)
public class ScoreboardTest {

    /**
     * Function to return a Collection of scores
     * return an Collection of scores
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {-1, 0, 82}, {100, 100, 0}, {1, 1, 81}, {0, 0, 82}, {-100, 0, 82}
        });
    }

    private int input, outputAdding, outputRemoving;

    /**
     * Construct as ScoreboardTest object
     */
    public ScoreboardTest(int input, int outputAdding, int outputRemoving){
        this.input = input;
        this.outputAdding = outputAdding;
        this.outputRemoving = outputRemoving;
    }

    /**
     * Test if adding korgools to a kazan is properly
     * shown to screen when the scoreboard is updated
     */
    @Test
    public void testIfAddingKorgoolsToKazanUpdatesScoreboard(){
        GameWindow gw1 = new GameWindow();
        gw1.getKazans().get(0).addKorgols(input);
        gw1.getScoreboard().update();
        int content = Integer.parseInt(gw1.getScoreboard().getWhiteScore().getText().replaceAll("\\s+",""));
        assertEquals(outputAdding , content);

        GameWindow gw2 = new GameWindow();
        gw2.getKazans().get(1).addKorgols(input);
        gw2.getScoreboard().update();
        assertEquals(outputAdding, Integer.parseInt(gw1.getScoreboard().getWhiteScore().getText().replaceAll("\\s+","")));
    }

    /**
     * Test if adding and then removign the same number of
     * korgools from a kazan is properly shown to screen when
     * the scoreboard is updated
     */
    @Test
    public void testIfAddingAndRemovingKorgoolsToKazanUpdatesScoreboard(){
        GameWindow gw1 = new GameWindow();
        gw1.getKazans().get(0).addKorgols(input);
        gw1.getKazans().get(0).removeKorgols(input);
        gw1.getScoreboard().update();
        int content = Integer.parseInt(gw1.getScoreboard().getWhiteScore().getText().replaceAll("\\s+",""));
        assertEquals(0, content);

        GameWindow gw2 = new GameWindow();
        gw1.getKazans().get(1).addKorgols(input);
        gw2.getKazans().get(1).removeKorgols(input);
        gw2.getScoreboard().update();
        assertEquals(0 , Integer.parseInt(gw1.getScoreboard().getBlackScore().getText().replaceAll("\\s+","")));
    }

    /**
     * Test if removing korgools from a kazan with 82 korgools
     * is properly shown to screen when the scoreboard is updated
     */
    @Test
    public void testIfRemovingKorgoolsToKazanUpdatesScoreboard(){
        GameWindow gw1 = new GameWindow();
        gw1.getKazans().get(0).addKorgols(82);
        gw1.getKazans().get(0).removeKorgols(input);
        gw1.getScoreboard().update();
        int content = Integer.parseInt(gw1.getScoreboard().getWhiteScore().getText().replaceAll("\\s+",""));
        assertEquals(outputRemoving, content);

        GameWindow gw2 = new GameWindow();
        gw2.getKazans().get(1).addKorgols(82);
        gw2.getKazans().get(1).removeKorgols(input);
        gw2.getScoreboard().update();
        content = Integer.parseInt(gw2.getScoreboard().getBlackScore().getText().replaceAll("\\s+",""));
        assertEquals(outputRemoving, content);
    }

}
