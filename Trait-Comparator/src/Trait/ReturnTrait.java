package Trait;

import java.util.Arrays;

public class ReturnTrait implements Trait {

    private final int weight = 1;

    @Override
    public int matchScore(String sourceCode) {

        String[] keywordList = {"Principal"};

//        if(!sourceCode.trim().startsWith("public") && !sourceCode.trim().startsWith("private") && !sourceCode.trim().startsWith("protected")){
//
//            return 0;
//        }

//        for(String keyword :keywordList){
//            if(sourceCode.contains(keyword)) return true;
//        }
//
//        return false;
        return Arrays.stream(keywordList).parallel().anyMatch(sourceCode::contains) ? weight : 0;
    }
}
