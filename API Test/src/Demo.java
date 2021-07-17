import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//given : input
		//when : submit the API
		//then : Validate the response
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
		//Parsing Json
		
		JsonPath js= new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		//addplace -> update place with new address-> verify if new address is updated 
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\"70 winter walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated")); 
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.header("Content-Type", "application/json").when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, USA"));
		System.out.println("----------------Executed Successfully----------------");
		Assert.assertEquals("YES", "Yes");

	}

}
