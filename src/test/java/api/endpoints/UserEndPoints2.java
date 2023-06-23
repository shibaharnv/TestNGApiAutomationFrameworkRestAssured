package api.endpoints;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

//Created to perform create,update,delete and read request
public class UserEndPoints2 {


            //Method to get  the urls from properties file
            static ResourceBundle getUrl()
            {
                ResourceBundle routes= ResourceBundle.getBundle("routes"); //Load the properties file

                return routes;
            }


   public static Response createUser (User payload)
    {

        String postUrl=getUrl().getString("post_url");

        Response response=given().contentType(ContentType.JSON).
                accept(ContentType.JSON).body(payload)
                .when().post(postUrl);
        return response;

    }

    public static Response readUser (String userName)
    {
        String getUrl=getUrl().getString("get_url");

        Response response=given().pathParam("username",userName)
                .when().get(getUrl);
        return response;

    }



    public static Response updateUser (User payload,String userName)
    {

        String updateUrl=getUrl().getString("update_url");

        Response response=given().contentType(ContentType.JSON).
                accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when().put(updateUrl);
        return response;
    }



    public static Response deleteUser (String userName)
    {
        String deleteUrl=getUrl().getString("delete_url");

        Response response=given().pathParam("username",userName)
                .when().delete(deleteUrl);
        return response;

    }

}
