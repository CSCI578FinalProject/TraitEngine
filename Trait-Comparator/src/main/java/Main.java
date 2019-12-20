import Pattern.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String sourceCodeDir = args[0];
        String originalACDC = args[1];
        String modifiedACDC = args[2];
        String version = args[3];
        String pattern = args[4];
        
        String patternClassName = "Pattern." + pattern + "Pattern";
        
        Pattern patternObject = new Pattern();
        try {
            patternObject = (Pattern) Class.forName(patternClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TraitComparator tc = new TraitComparator(patternObject, sourceCodeDir);
        tc.setVersion(version);
        tc.compare(originalACDC,modifiedACDC);


    }
}
