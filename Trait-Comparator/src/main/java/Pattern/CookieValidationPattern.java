package Pattern;

import Trait.*;

import java.util.ArrayList;

public class CookieValidationPattern extends Pattern {

    public CookieValidationPattern (){
        super();
        traits = new ArrayList<>();
        traits.add(new ExceptionTrait());
        traits.add(new CookieTrait());
        traits.add(new ValidationTrait());
        traits.add(new AuthenticateTrait());
    }

}
