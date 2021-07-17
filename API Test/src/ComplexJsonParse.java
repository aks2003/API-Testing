import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import files.Payload;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		int purchanseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchanseamount);
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		for(int i=0;i<count;i++)
		{
			System.out.println((String)js.get("courses["+i+"].title"));
			System.out.println(js.get("courses["+i+"].price").toString());

		}
		//No of copies sold by RPA
		for(int i=0;i<count;i++)
		{
			if(((String)js.get("courses["+i+"].title")).equalsIgnoreCase("RPA"))
			{
				System.out.println(js.get("courses["+i+"].copies").toString());
			
			}

		}
		//Total purchase Amount
		int totalPurchaseAmount=0;
		for(int i=0;i<count;i++)
		{
				totalPurchaseAmount = totalPurchaseAmount + (int) (js.get("courses["+i+"].copies")) * (int) (js.get("courses["+i+"].price"));
				System.out.println(totalPurchaseAmount);
			

		}
		
	}



}
