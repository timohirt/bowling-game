package github.com.timohirt.bowling_game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Assert.*;

public class GameTest {
    
    @Test
    public void testCalculateScoreOfGameWithOneFrame() {
        var game = new Game();
        game.addRoll(1);

        assertEquals(Score.of(1), game.currentScore());
    }
}