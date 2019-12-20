package Pattern;

import Trait.*;

import java.util.List;


public class Pattern {

    List<Trait> traits = null;


    public int matchScore(String sourceCode){
        int score = 0;

        for(Trait trait : traits){

            score += trait.matchScore(sourceCode);

        }

        return score;
    }
}
