package testCases;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC01_GET {
	
	public static RestAssured restAssured;
	public static Response response;

	@BeforeClass
	public void init() {
		restAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void getPost() {
		response = restAssured.get("/posts");
		assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void getPostwithID() {
		int id = 1;
		response = restAssured.get("/posts/" + id);
		assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void getComments() {
		int id = 1;
		response = restAssured.get("/posts/" + id + "/comments");
		assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void getCommentsPost() {
		int id = 1;
		response = restAssured.get("/comments?postId=" + id);
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void createPost() {
		String requestBody = "{\r\n" + "  title: 'foo',\r\n" + "  body: 'bar',\r\n" + "  userId: 101,\r\n" + " }";
		response = restAssured.given().body("requestBody").when().post("/posts");
		assertEquals(response.getStatusCode(), 201);
		JsonPath jPath = response.jsonPath();
		String str = jPath.getString("id");
		//System.out.println(str);
		Assert.assertEquals(str, "101", requestBody);
	}

	@Test
	public void update() {
		String requestBody = "{\r\n" + "  id: 1,\r\n" + "  title: 'foo',\r\n" + "  body: 'bar',\r\n"
				+ "  userId: 1,\r\n" + " }";
		response = restAssured.given().body("requestBody").when().put("/posts/1");
		System.out.println("Response code- " + response.getStatusCode());
		JsonPath jPath = response.jsonPath();
		System.out.println(jPath.toString());
		String str = jPath.getString("id");
		Assert.assertEquals(str, "1", requestBody);
	}


}
