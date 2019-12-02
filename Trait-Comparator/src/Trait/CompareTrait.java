package Trait;

import java.util.Arrays;

public class CompareTrait implements Trait {

    private final int weight = 1;


    @Override
    public int matchScore(String sourceCode) {

        String[] keywordList = {"compare", "match"};

        sourceCode = sourceCode.toLowerCase();

//        for(String keyword :keywordList){
//            if(sourceCode.contains(keyword)) return true;
//        }
//        return false;

        return Arrays.stream(keywordList).parallel().anyMatch(sourceCode::contains) ? weight : 0;
    }
}
