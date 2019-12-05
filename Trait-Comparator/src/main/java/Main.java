import Pattern.CredentialPattern;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        String sourceCodeDir = args[0];
        String originalACDC = args[1];
        String modifiedACDC = args[2];
        String version = args[3];

        TraitComparator tc = new TraitComparator(new CredentialPattern(), sourceCodeDir);
        tc.setVersion(version);
        tc.compare(originalACDC,modifiedACDC);


    }
}
