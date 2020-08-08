# Bowling Game Kata

This is an implementation of the [bowling game
kata](https://ccd-school.de/coding-dojo/class-katas/bowling/) in Java.

Please note the following known limitations of this implementation:

- No input validation implemented.
- Two strikes in a row are currently not supported. The program will crash in
this case. So, don't pass two rolls with 10 pins hit directly after each
other.

## Running the Code
	
Run the code using `gradle` from a command line. Pass individual pins hit in
each roll as command line arguments.

```
# ./gradlew run --args="1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6"    
> Task :run
	
Total score is: 133

BUILD SUCCESSFUL in 646ms
2 actionable tasks: 1 executed, 1 up-to-date
```
