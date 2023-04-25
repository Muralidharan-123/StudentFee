package FeeStructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fees 
{
	static Scanner sc = new Scanner(System.in);
	
	public static JsonNode readFile(String fileLocation) throws Exception
	{
		JsonNode node = null;
        String fileContent = "";
       
        File file = new File(fileLocation); 
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine = "";
        while (( currentLine = bufferedReader.readLine() ) != null)
        {
            fileContent = fileContent.concat(currentLine);
        }
        node = objectMapper.readTree(fileContent);
        bufferedReader.close();  
		return node;
	}
	
	public static void examFee(JsonNode json) throws Exception
	{
		JsonNode fee = json.get("Exam Fee");
		Map<String, String> nationList = new HashMap();
		ObjectMapper objectMapper = new ObjectMapper();
		nationList = objectMapper.readValue(fee.toString(), Map.class);
		System.out.println("nations are : " + nationList.keySet());
		System.out.println("Please select the nation : ");
		String nationInput = sc.next();
		if(nationList.containsKey(nationInput))
		{
			JsonNode nation = fee.get(nationInput);
			Map<String, String> courseList = new HashMap();
			courseList = objectMapper.readValue(nation.toString(), Map.class);
			System.out.println("courses are : " + courseList.keySet());
			System.out.println("please select the type Course");
			String courseInput = sc.next();
			if(courseList.containsKey(courseInput))
			{
				JsonNode course = nation.get(courseInput);
				Map<String, String> levelList = new HashMap();
				levelList = objectMapper.readValue(course.toString(), Map.class);
				System.out.println("Levels are : " + levelList.keySet());
				System.out.println("please select the type level");
				String levelInput = sc.next();
				if(levelList.containsKey(levelInput))
				{
					JsonNode level = course.get(levelInput);
					System.out.println("Your Fee amount will be: " + level.get("amount").toString());
				}
				else {
					System.out.println("invalid Level");
				}
			}else {
				System.out.println("invalid Course");
			}
		}
		else {
			System.out.println("invalid Nation");
		}
		
	}
	
	public static void applicationFee(JsonNode json) throws Exception
	{
		JsonNode fee = json.get("Application Fee");
		Map<String, String> nationList = new HashMap();
		ObjectMapper objectMapper = new ObjectMapper();
		nationList = objectMapper.readValue(fee.toString(), Map.class);
		System.out.println("nations are : " + nationList.keySet());
		System.out.println("Please select the nation : ");
		String nationInput = sc.next();
		if(nationList.containsKey(nationInput))
		{
			JsonNode nation = fee.get(nationInput);
			Map<String, String> courseList = new HashMap();
			courseList = objectMapper.readValue(nation.toString(), Map.class);
			System.out.println("courses are : " + courseList.keySet());
			System.out.println("please select the type Course");
			String courseInput = sc.next();
			if(courseList.containsKey(courseInput))
			{
				JsonNode course = nation.get(courseInput);
				Map<String, String> levelList = new HashMap();
				levelList = objectMapper.readValue(course.toString(), Map.class);
				System.out.println("Levels are : " + levelList.keySet());
				System.out.println("please select the type Level");
				String levelInput = sc.next();
				if(levelList.containsKey(levelInput))
				{
					JsonNode level = course.get(levelInput).get("amount");
					System.out.println("your fee is : " + level.toString());
				}
				else {
					System.out.println("Invalid Level");
				}
			}
			else {
				System.out.println("Invalid Courses");
			}
		}
		else {
			System.out.println("Invalid Nation");
		}
	}
	
	public static void main(String... args) throws Exception
	{
		File file = new File("src/main/resources/sampleJsonFile.json");
		String fileLocation = file.getAbsolutePath();  
		JsonNode json = readFile(fileLocation);
		System.out.println("please enter the fee number to check : 1. Exam Fee or 2. Application Fee");
		String feeType = sc.next();
		if(feeType.equals("1"))
		{
			examFee(json);
		}
		else if(feeType.equalsIgnoreCase("2"))
		{
			applicationFee(json);
		}else {
			System.out.println("Please select the valid Fee Type");
		}
	}
}
