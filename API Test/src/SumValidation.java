import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void sumOfCourses()
	{
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");
		int totalPurchaseAmount=0;
		for(int i=0;i<count;i++)
		{
				totalPurchaseAmount = totalPurchaseAmount + (int) (js.get("courses["+i+"].copies")) * (int) (js.get("courses["+i+"].price"));
				
		}
		System.out.println(totalPurchaseAmount);
		int purchase = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(totalPurchaseAmount, purchase);
	}

}
