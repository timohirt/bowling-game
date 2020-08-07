package github.com.timohirt.bowling_game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.stream.IntStream;

import org.junit.Test;

public class GameTest {
    
    @Test
    public void testCalculateScoreOfGameWithOneFrame() {
        var game = new Game();
        game.addRoll(1);

        assertEquals(Score.of(1), game.currentScore());
    }

    @Test
    public void testCalculateScoreOfGameWithTwoFrames() {
        var game = new Game();
        addRolls(game, 3, 1);

        assertEquals(Score.of(3), game.currentScore());
    }

    @Test
    public void testCalculateScoreOfGameWithTenFrames() {
        var game = new Game();
        addRolls(game, 20, 1);
        
        assertEquals(Score.of(20), game.currentScore());
    }

    @Test
    public void testThrowExceptionIfMoreThanTenFramesAdded() {
        var game = new Game();
        addRolls(game, 20, 1);
        
        assertThrows(IllegalStateException.class, () -> game.addRoll(1));
    }

    private void addRolls(Game game, int numberOfRolls, int pinsHit) {
        IntStream
            .range(0, numberOfRolls)
            .forEach(irrelevant -> game.addRoll(pinsHit));
    }

    @Test
    public void testCalculateScoreOfGameOnTaskSheet() {
        var game = new Game();
        game.addRoll(1);
        game.addRoll(4);
        game.addRoll(4);
        game.addRoll(5);
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(5);
        game.addRoll(5);
        game.addRoll(10);
        game.addRoll(0);
        game.addRoll(1);
        game.addRoll(7);
        game.addRoll(3);
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(10);
        game.addRoll(2);
        game.addRoll(8);
        game.addRoll(6);

        assertEquals(Score.of(133), game.currentScore());
    }
}