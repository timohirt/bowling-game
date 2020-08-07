package github.com.timohirt.bowling_game;

import java.util.Optional;

public class LastFrame extends Frame {

    private int bonus = 0;
    private int additionalRolls = 0;

    @Override
    public void addRoll(int pinsHit) {
        if (isAdditionalRollAllowed()) {
            bonus = bonus + pinsHit;
            additionalRolls = additionalRolls + 1;
        } else {
            super.addRoll(pinsHit);
        }
    }

    @Override
    public Score calculateScore(Optional<Frame> maybeNextFrame) {
        if (maybeNextFrame.isPresent()) {
            throw new IllegalArgumentException("Last frame doesn't allow next frame to calculate score.");
        }

        return Score.of(pinsHitTotal() + bonus);
    }

    private boolean isAdditionalRollAllowed() {
        return isSpare() && additionalRolls == 0 || isStrike() && additionalRolls < 2;
    }

    @Override
    public boolean canTakeAnotherRoll() {
        return super.canTakeAnotherRoll() || isAdditionalRollAllowed();
    }
    
    
}