package github.com.timohirt.bowling_game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.Test;

public class LastFrameTest {

    @Test
    public void testCalculateLastFrameScore() {
        var lastFrame = new LastFrame();
        lastFrame.addRoll(2);
        lastFrame.addRoll(3);

        assertEquals(Score.of(5), lastFrame.calculateScore(Optional.empty()));
    }

    @Test
    public void testThrowExceptionIfNextFrameIsProvided() {
        var lastFrame = new LastFrame();
        lastFrame.addRoll(2);

        assertThrows(IllegalArgumentException.class, () -> lastFrame.calculateScore(Optional.of(new Frame())));
    }

    @Test
    public void testAllowThirdRollAndAddToScoreIfLastFrameIsStrike() {
        var lastFrame = new LastFrame();
        lastFrame.addRoll(10);
        lastFrame.addRoll(3);
        lastFrame.addRoll(5);

        assertEquals(Score.of(18), lastFrame.calculateScore(Optional.empty()));
    }

    @Test
    public void testAllowThirdRollAndAddToScoreIfLastFrameIsSplit() {
        var lastFrame = new LastFrame();
        lastFrame.addRoll(5);
        lastFrame.addRoll(5);
        lastFrame.addRoll(5);

        assertEquals(Score.of(15), lastFrame.calculateScore(Optional.empty()));
    }
}