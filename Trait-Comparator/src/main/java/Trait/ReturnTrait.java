package Trait;

import java.util.Arrays;

public class ReturnTrait implements Trait {

    private final int weight = 1;

    @Override
    public int matchScore(String sourceCode) {

        String[] keywordList = {"Principal"};

        return Arrays.stream(keywordList).parallel().anyMatch(sourceCode::contains) ? weight : 0;
    }
}
