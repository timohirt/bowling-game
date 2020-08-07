package github.com.timohirt.bowling_game;

import org.junit.Test;
import static org.junit.Assert.*;

public class FrameTest {


    @Test
    public void testWhenNoRollsScoreMustBeZero() {
        var frame = new Frame();
        assertEquals(Score.zero(), frame.calculateScore());
    }

    @Test
    public void testWhenOnePinHitWithTheFirstRollScoreMustBeOne() {
        var frame = new Frame();
        frame.addRoll(1);
        assertEquals(Score.of(1), frame.calculateScore());
    }

    @Test
    public void testWhenTwoPinsHitInTwoRollsScoreMustBeTwo() {
        var frame = new Frame();
        frame.addRoll(1);
        frame.addRoll(1);
        assertEquals(Score.of(2), frame.calculateScore());
    }
}