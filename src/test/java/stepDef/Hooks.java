package stepDef;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //write a code that generates place id
        // only when place id is null
        if (MapsStepDefinitions.place_id == null) {
            MapsStepDefinitions m = new MapsStepDefinitions();
            m.payloadWithAddPlaceApiWithValues("HallRoom", "123456791", "Yavatmal");
            m.userSendsThePostRequestToAndMethodIs("AddPlaceAPI", "post");
            m.verifyUsingThePlace_idMapsToTheCreated("GetPlaceAPI", "HallRoom");
        }

    }
}
