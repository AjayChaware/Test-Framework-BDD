package utils.constants;

public enum ResourcePaths {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    UpdatePlaceAPI("/maps/api/place/update/json");

    private String path;

    ResourcePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
