package github.com.timohirt.bowling_game;

import java.util.ArrayList;
import java.util.List;

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
        if (currentFrame.canTakeAnotherRoll()) {
            currentFrame.addRoll(pinsHit);
        } else if (isLastFrame()) {
            throw new IllegalStateException("A game only allows ten frames.");  
        } else {
            var newFrame = addNewFrame();
            newFrame.addRoll(pinsHit);
        }
    }

    private Frame addNewFrame() {
        var newFrame = new Frame();
        allFrames.add(newFrame);
        currentFrame = newFrame;
        return newFrame;
    }

    private boolean isLastFrame() {
        return allFrames.size() >= MAX_FRAMES;
    }

    public Score currentScore() {
        return allFrames
            .stream()
            .map(frame -> frame.calculateScore())
            .reduce(Score.zero(), (subtotal, current) -> Score.of(subtotal.getValue() + current.getValue()));
    }
}