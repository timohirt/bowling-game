package github.com.timohirt.bowling_game;

import java.util.Optional;

public class Frame {

    private Optional<Integer> maybePinsHitFirstRoll = Optional.empty();

    public void addRoll(int pinsHit) {
        maybePinsHitFirstRoll = Optional.of(pinsHit);
    }

    public Score calculateScore() {
        return maybePinsHitFirstRoll.map(pinsHit -> Score.of(pinsHit)).orElse(Score.zero());
    }
}