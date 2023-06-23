package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTestsWithExtentReport {

        Faker faker;
        User userPayload;


    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("extentreportresult.html");

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

            extent.createTest("testPostUser")
                    .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
            Response response=UserEndPoints.createUser(userPayload);
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(),200);

        }

         @Test(priority = 2)
        public void testGetUserByName()
        {
            extent.createTest("testGetUserByName")
                    .log(Status.PASS, "This is a logging event for SECOND, and it passed!");
            Response response=UserEndPoints.readUser(this.userPayload.getUsername());
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(),200);

         }

        @Test(priority = 3)
        public void testUpdateUser()
        {
            extent.createTest("testGetUserByName")
                    .log(Status.PASS, "This is a logging event for 3, and it passed!");
            userPayload.setUsername(faker.name().username());
        Response response=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking if the updated data is reflected
            Response response2=UserEndPoints.readUser(this.userPayload.getUsername());
            Assert.assertEquals(response2.getStatusCode(),200);


        }

    @Test(priority = 4)
    public void testDeleteUser()
    {

        extent.createTest("testGetUserByName")
                .log(Status.PASS, "This is a logging event for 4, and it passed!");
        Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @BeforeTest
    public  void extentReportMethod()
    {

        extent.attachReporter(spark);
    }


    @AfterTest
    public  void extentAfterMethod()
    {

        extent.flush();
    }



}
