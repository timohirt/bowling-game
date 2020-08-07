package github.com.timohirt.bowling_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    
    private static final int MAX_FRAMES = 10;

    private Frame currentFrame;
    private List<Frame> allFrames;
    private LastFrame lastFrame;

    public Game() {
        currentFrame = new Frame();
        allFrames = new ArrayList<>();
        allFrames.add(currentFrame);
        lastFrame = new LastFrame();
    }

    public void addRoll(int pinsHit) {
        if (!currentFrame.canTakeAnotherRoll()) {
            if (isLastFrame()) {
                throw new IllegalStateException("A game only allows ten frames.");  
            }
            currentFrame = nextFrame();
        }

        currentFrame.addRoll(pinsHit);
    }

    private Frame nextFrame() {
        if (isNextFrameLast()) {
            allFrames.add(lastFrame);
            return lastFrame;
        } else {
            var newFrame = new Frame();
            allFrames.add(newFrame);
            return newFrame;
        }
    }

    private boolean isNextFrameLast() {
        return allFrames.size() == MAX_FRAMES - 1;
    }

    private boolean isLastFrame() {
        return allFrames.size() >= MAX_FRAMES;
    }

    public Score currentScore() {
        return allFrames
            .stream()
            .map(frame -> {
                var maybeNextFrame = findNextFrame(frame);
                return frame.calculateScore(maybeNextFrame);
            })
            .reduce(Score.zero(), (subtotal, current) -> Score.of(subtotal.getValue() + current.getValue()));
    }

    private Optional<Frame> findNextFrame(Frame frame) {
        var it = allFrames.iterator();
        while (it.hasNext()) {
            var current = it.next();
            if (current == frame && it.hasNext()) {
                return Optional.of(it.next());
            }
        }
        return Optional.empty();
    }
}