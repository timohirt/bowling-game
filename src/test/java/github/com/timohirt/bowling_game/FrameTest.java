package github.com.timohirt.bowling_game;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Optional;

public class FrameTest {


    @Test
    public void testWhenNoRollsScoreMustBeZero() {
        var frame = new Frame();
        assertEquals(Score.zero(), frame.calculateScore(Optional.empty()));
    }

    @Test
    public void testWhenOnePinHitWithTheFirstRollScoreMustBeOne() {
        var frame = new Frame();
        frame.addRoll(1);
        assertEquals(Score.of(1), frame.calculateScore(Optional.empty()));
    }

    @Test
    public void testWhenTwoPinsHitInTwoRollsScoreMustBeTwo() {
        var frame = new Frame();
        frame.addRoll(1);
        frame.addRoll(1);
        assertEquals(Score.of(2), frame.calculateScore(Optional.empty()));
    }

    @Test
    public void testWhenThreeRollsAddedMustThrowAnException() {
        var frame = new Frame();
        frame.addRoll(1);
        frame.addRoll(1);
        assertThrows(IllegalStateException.class, () -> frame.addRoll(1));
    }

    @Test
    public void testCanTakeAnotherRollIfNoRollsAddedYet() {
        assertTrue(new Frame().canTakeAnotherRoll());
    }

    @Test
    public void testCanTakeAnotherRollIfLessThanTenPinsWereHitWithOneRoll() {
        var frame = new Frame();
        frame.addRoll(9);

        assertTrue(frame.canTakeAnotherRoll());
    }

    @Test
    public void testCannotTakeAnotherRollIfLessThanTenPinsWereHitWithTwoRolls() {
        var frame = new Frame();
        frame.addRoll(1);
        frame.addRoll(8);

        assertFalse(frame.canTakeAnotherRoll());
    }

    @Test
    public void testCannotTakeAnotherRollIfTenPinsWereHitWithTheFirstRoll() {
        var frame = new Frame();
        frame.addRoll(10);

        assertFalse(frame.canTakeAnotherRoll());
    }

    @Test
    public void testAddPinsOfFirstRollBonusToScoreIfCurrentFrameIsASplit() {
        var splitFrame = new Frame();
        splitFrame.addRoll(2);
        splitFrame.addRoll(8);

        var nextFrame = new Frame();
        nextFrame.addRoll(5);

        assertEquals(Score.of(15), splitFrame.calculateScore(Optional.of(nextFrame)));
    }

    @Test
    public void testIfFrameIsASplitThrowAnExceptionWhenNoRollsAddedToNextFrame() {
        var splitFrame = new Frame();
        splitFrame.addRoll(2);
        splitFrame.addRoll(8);

        var nextFrame = new Frame();

        assertThrows(IllegalArgumentException.class, () -> splitFrame.calculateScore(Optional.of(nextFrame)));
    }
}