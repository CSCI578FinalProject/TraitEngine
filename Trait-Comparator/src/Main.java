import Pattern.CredentialPattern;

public class Main {

    public static void main(String[] args) {

        TraitComparator tc = new TraitComparator(new CredentialPattern());
        tc.compare();

    }
}
