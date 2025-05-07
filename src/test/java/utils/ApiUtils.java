package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class ApiUtils {

    static RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (requestSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(getProperties("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
    }


    public ResponseSpecification responseSpecification() {
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
        return responseSpec;
    }

    public String getProperties(String property) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/environments/default.properties");
        properties.load(fileInputStream);
        return properties.getProperty(property);
    }

    public String getJsonValue(Response response, String key) {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString(key);
    }

}
