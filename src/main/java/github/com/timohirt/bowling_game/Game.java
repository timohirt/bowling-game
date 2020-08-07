package github.com.timohirt.bowling_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    
    private static final int MAX_FRAMES = 10;

    private Frame currentFrame;
    private List<Frame> allFrames;

    public Game() {
        currentFrame = new Frame();
        allFrames = new ArrayList<>();
        allFrames.add(currentFrame);
    }

    public void addRoll(int pinsHit) {
        if (!currentFrame.canTakeAnotherRoll()) {
            if (isLastFrame()) {
                throw new IllegalStateException("A game only allows ten frames.");  
            }
            currentFrame = addNewFrame();
        }

        currentFrame.addRoll(pinsHit);
    }

    private Frame addNewFrame() {
        var newFrame = new Frame();
        allFrames.add(newFrame);
        return newFrame;
    }

    private boolean isLastFrame() {
        return allFrames.size() >= MAX_FRAMES;
    }

    public Score currentScore() {
        return allFrames
            .stream()
            .map(frame -> frame.calculateScore(Optional.empty()))
            .reduce(Score.zero(), (subtotal, current) -> Score.of(subtotal.getValue() + current.getValue()));
    }
}