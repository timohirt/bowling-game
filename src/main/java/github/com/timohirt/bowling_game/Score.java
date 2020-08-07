package github.com.timohirt.bowling_game;

import lombok.Value;

@Value
public class Score {
    int value;

    public static Score zero() { return new Score(0); }
    public static Score of(int value) { return new Score(value); }
}