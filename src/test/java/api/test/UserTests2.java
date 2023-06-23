package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTests2 {

        Faker faker;
        User userPayload;

        @BeforeClass
        public void setUpData()
        {
            faker = new Faker();
            userPayload= new User();

            userPayload.setId(faker.idNumber().hashCode());
            userPayload.setUsername(faker.name().username());
            userPayload.setFirstName(faker.name().firstName());
            userPayload.setLastName(faker.name().lastName());
            userPayload.setEmail(faker.internet().safeEmailAddress());
            userPayload.setPassword(faker.internet().password(5,10));
            userPayload.setPhone(faker.phoneNumber().cellPhone());

        }


        @Test(priority = 1)
        public void testPostUser()
        {
            Response response=UserEndPoints2.createUser(userPayload);
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(),200);

        }

         @Test(priority = 2)
        public void testGetUserByName()
        {
            Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(),200);

         }

        @Test(priority = 3)
        public void testUpdateUser()
        {

            userPayload.setUsername(faker.name().username());
        Response response=UserEndPoints2.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking if the updated data is reflected
            Response response2=UserEndPoints2.readUser(this.userPayload.getUsername());
            Assert.assertEquals(response2.getStatusCode(),200);


        }

    @Test(priority = 4)
    public void testDeleteUser()
    {
        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @BeforeTest
    public  void extentReportMethod()
    {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("extentreportresult.html");
        extent.attachReporter(spark);
    }



}
