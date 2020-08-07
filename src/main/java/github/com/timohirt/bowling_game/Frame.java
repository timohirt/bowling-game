package github.com.timohirt.bowling_game;

import java.util.Optional;

public class Frame {

    private Optional<Integer> maybePinsHitFirstRoll = Optional.empty();
    private Optional<Integer> maybePinsHitSecondRoll = Optional.empty();

    public void addRoll(int pinsHit) {
        if (maybePinsHitFirstRoll.isEmpty()) {
            maybePinsHitFirstRoll = Optional.of(pinsHit);
        } else {
            maybePinsHitSecondRoll = Optional.of(pinsHit);
        }
    }

    public Score calculateScore() {
        var pinsHitTotal = maybePinsHitFirstRoll.orElse(0) + maybePinsHitSecondRoll.orElse(0);

        return Score.of(pinsHitTotal);
    }
}