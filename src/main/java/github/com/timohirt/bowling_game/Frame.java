package github.com.timohirt.bowling_game;

import java.util.Optional;

public class Frame {

    private static final int MAX_PINS = 10; 

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


    private int pinsHitTotal() {
        return maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0);
    }

    public Score calculateScore(Optional<Frame> maybeNextFrame) {
        var pinsHitTotal = pinsHitTotal();
        if (pinsHitTotal == MAX_PINS && maybePinsHitSecondRoll.isPresent()) {
            int bonus = maybeNextFrame
                .flatMap(f -> f.maybePinsHitFirstRoll)
                .orElseThrow(() -> new IllegalArgumentException("Next frame without first roll added not allowed."));
            return Score.of(pinsHitTotal + bonus);
        }        
        return Score.of(pinsHitTotal);
    }
}