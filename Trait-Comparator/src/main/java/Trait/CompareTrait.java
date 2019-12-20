package Trait;

import java.util.Arrays;

public class CompareTrait implements Trait {

    private final int weight = 1;


    @Override
    public int matchScore(String sourceCode) {

        String[] keywordList = {"compare", "match"};

        sourceCode = sourceCode.toLowerCase();

        return Arrays.stream(keywordList).parallel().anyMatch(sourceCode::contains) ? weight : 0;
    }
}
