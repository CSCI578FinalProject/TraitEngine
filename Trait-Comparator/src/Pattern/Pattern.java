package Pattern;

import Trait.*;

import java.util.List;


public abstract class Pattern {

    List<Trait> traits = null;


    public abstract int matchScore(String sourceCode);
}
