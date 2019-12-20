package Pattern;

import Trait.*;

import java.util.ArrayList;

public class CredentialPattern extends Pattern {

    public CredentialPattern (){
        super();

        traits = new ArrayList<>();
        traits.add(new CompareTrait());
        traits.add(new ReturnTrait());
        traits.add(new DigestTrait());
        traits.add(new CredentialTrait());
        traits.add(new AuthenticateTrait());
    }

}
