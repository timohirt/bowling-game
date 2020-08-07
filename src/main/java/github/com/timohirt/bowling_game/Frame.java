package github.com.timohirt.bowling_game;

import java.util.Optional;

public class Frame {

    protected static final int MAX_PINS = 10; 

    private Optional<Integer> maybePinsHitFirstRoll = Optional.empty();
    private Optional<Integer> maybePinsHitSecondRoll = Optional.empty();

    public void addRoll(int pinsHit) {
        if (maybePinsHitFirstRoll.isEmpty()) {
            maybePinsHitFirstRoll = Optional.of(pinsHit);
        } else if (maybePinsHitSecondRoll.isEmpty()) {
            maybePinsHitSecondRoll = Optional.of(pinsHit);
        } else {
            throw new IllegalStateException("Only two rolls allowed.");
        }
    }

    public boolean canTakeAnotherRoll() {
        return maybePinsHitSecondRoll.isEmpty() && pinsHitTotal() < 10;
    }


    protected int pinsHitTotal() {
        return maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0);
    }

    public boolean isStrike() {
        return allPinsHit() && maybePinsHitSecondRoll.isEmpty();
    }

    public boolean isSpare() {
        return allPinsHit() && maybePinsHitSecondRoll.isPresent();
    }

    private boolean allPinsHit() {
        return pinsHitTotal() == MAX_PINS;
    }

    private boolean addBonusToScore() {
        return isSpare() || isStrike();
    }

    private int pinsOfRoll(Optional<Integer> maybePinsHit) {
        return maybePinsHit
            .orElseThrow(() -> new IllegalArgumentException("No pins hit with this roll."));
    }

    private int calculateBonus(Frame nextFrame) {
        int bonus = pinsOfRoll(nextFrame.maybePinsHitFirstRoll);
        if (isStrike()) {
            bonus = bonus + pinsOfRoll(nextFrame.maybePinsHitSecondRoll);
        } 
        return bonus;
    }

    public Score calculateScore(Optional<Frame> maybeNextFrame) {
        var pinsHitTotal = pinsHitTotal();
        if (addBonusToScore()) {
            int bonus = maybeNextFrame
                .map(nextFrame -> calculateBonus(nextFrame))
                .orElseThrow(() -> new IllegalArgumentException("Next frame required to calculate score, but not provided."));
            
            return Score.of(pinsHitTotal + bonus);
        }        
        return Score.of(pinsHitTotal);
    }
}