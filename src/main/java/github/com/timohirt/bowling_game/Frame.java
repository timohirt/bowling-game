package github.com.timohirt.bowling_game;

import java.util.Optional;

public class Frame {

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
        return maybePinsHitSecondRoll.isEmpty() && maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0) < 10;
    }

    public Score calculateScore() {
        var pinsHitTotal = maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0);

        return Score.of(pinsHitTotal);
    }
}