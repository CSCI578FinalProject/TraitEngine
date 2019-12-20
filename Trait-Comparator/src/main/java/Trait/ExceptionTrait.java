package Trait;

import java.util.Arrays;

public class ExceptionTrait implements Trait {
    private final int weight = 1;

    @Override
    public int matchScore(String sourceCode) {

        String[] keywordList = {"IllegalArgumentException"};

        sourceCode = sourceCode.toLowerCase();

        return Arrays.stream(keywordList).parallel().anyMatch(sourceCode::contains) ? weight : 0;

    }
}
