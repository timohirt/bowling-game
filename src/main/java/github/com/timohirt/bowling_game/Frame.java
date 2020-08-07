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
        return maybePinsHitSecondRoll.isEmpty() && pinsHitTotal() < 10;
    }


    private int pinsHitTotal() {
        return maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0);
    }

    public Score calculateScore() {
        return Score.of(pinsHitTotal());
    }
}