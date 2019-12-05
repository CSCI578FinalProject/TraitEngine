package Trait;

public class AuthenticateTrait implements Trait {

    private final int weight = 1;

    @Override
    public int matchScore(String sourceCode) {

        return sourceCode.toLowerCase().contains("authenticate") ? weight : 0;

    }
}
