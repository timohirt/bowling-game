package github.com.timohirt.bowling_game;

public class Game {
    
    private Frame currentFrame;

    public Game() {
        currentFrame = new Frame();
    }

    public void addRoll(int pinsHit) {
        currentFrame.addRoll(pinsHit);
    }

    public Score currentScore() {
        return currentFrame.calculateScore();
    }
}