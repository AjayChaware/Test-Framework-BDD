package testdata;

import googlemaps.AddPlace;
import googlemaps.Location;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;

import java.util.List;
import java.util.Optional;

public class TestDataBuild {

    public AddPlace addPlacePayload() {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        addPlace.setAccuracy(50);

        location.setLat(-42.36);
        location.setLng(96.56);
        addPlace.setLocation(location);

        addPlace.setName("House 1");
        addPlace.setPhone_number("79654123");
        addPlace.setAddress("Pune");
        addPlace.setTypes(List.of("shop", "mall"));
        addPlace.setWebsite("rahulshettyacademy.com");
        addPlace.setLanguage("ENglish");
        return addPlace;
    }

    public AddPlace addPlacePayload(String name, String phoneNumber, String Address) {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        addPlace.setAccuracy(50);

        location.setLat(-42.36);
        location.setLng(96.56);
        addPlace.setLocation(location);

        addPlace.setName(name);
        addPlace.setPhone_number(phoneNumber);
        addPlace.setAddress(Address);
        addPlace.setTypes(List.of("shop", "mall"));
        addPlace.setWebsite("rahulshettyacademy.com");
        addPlace.setLanguage("ENglish");
        return addPlace;
    }

    public String deletePlacePayload(String place_id) {
        return "{\n" +
                "    \"place_id\": \"" + place_id + "\"\n" +
                "}";
    }
}
