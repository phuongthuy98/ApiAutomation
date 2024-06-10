package common;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {

    public boolean checkErrorMessage(String responseBody, String expectedMessage) {
        String[] splittedResponseBody = responseBody.split("\"");
        boolean isChecked = false;
        for (int i = 0; i < splittedResponseBody.length; i++) {
            if (splittedResponseBody[i].equals(expectedMessage)) {
                isChecked = true;
            }
        }
        return isChecked;
    }

    // Get value from Json body by key
    public String getValueByKey(String responseBody, String key) {
        JSONParser parser = new JSONParser();
        String value = "";
        try {
            JSONObject responseBodyObj = (JSONObject) parser.parse(responseBody);
            value = responseBodyObj.get(key).toString();
        } catch (Exception e) {
            System.out.println("Response body is null.");
            e.printStackTrace();
        }
        return value;
    }

    public ArrayList<String> getValueByArrayKey(String responBody, String keyArray, String key) {
        ArrayList<String> valueList = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        JSONObject jsonObj;
        try {
            jsonObj = (JSONObject) parser.parse(responBody);
            JSONArray jsonArrayObj = (JSONArray) jsonObj.get(keyArray);
            for (int i = 0; i < jsonArrayObj.size(); i++) {

                JSONObject jsonObject1 = (JSONObject) jsonArrayObj.get(i);
                String value = jsonObject1.get(key).toString();
                valueList.add(value);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return valueList;

    }

    public void copyJsonFile(File sourceFile, File destinationFile) {
        if (destinationFile.exists()) {
            destinationFile.delete();
        }
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
            System.out.println("Copy successfully");
        } catch (Exception e) {
            System.out.println("Json request body is not found");
        }

    }

    public String readJsonFile(String jsonFileName) {
        String resourceFolder = System.getProperty("user.dir") + "/src/main/resources";
        String requestBody = "";
        String filePath = resourceFolder + jsonFileName;
        File sourceFile = new File(filePath);
        File destinationFile = new File(resourceFolder + jsonFileName.replace(".json", "Copy.json"));
        if (sourceFile.exists()) {
            try {
                copyJsonFile(sourceFile, destinationFile);
                requestBody = new String(Files.readAllBytes(destinationFile.toPath()));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return requestBody;
    }


    public String readJsonFileWithChangedValue(String jsonFileName, String fieldName, String value) {
        String resourceFolder = System.getProperty("user.dir") + "/src/main/resources";
        String requestBody="";
        String filePath = resourceFolder + jsonFileName;
        File sourceFile = new File (filePath);

        File destinationFile = new File ( resourceFolder + jsonFileName.replace(".json","Copy.json"));
        if (sourceFile.exists()) {
            copyJsonFile(sourceFile, destinationFile);
            requestBody = changeValueByFieldName(destinationFile,fieldName,value);
        }

        return requestBody;
    }

    // Pass value by fieldName
    public String changeValueByFieldName(File file, String fieldName, String value) {
        String resultFile = null;
        String filePath = file.getAbsolutePath();
        try {
            String originalFile = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(originalFile);
            if (value.equals("missing")) {
                jsonObject.remove(fieldName);
            } else if (value.equals("null")) {
                jsonObject.put(fieldName, null);
            } else if (value.equals("true")) {
                jsonObject.put(fieldName, true);
            } else if (value.equals("\"\"")) {
                jsonObject.put(fieldName, "");
            } else {
                jsonObject.put(fieldName, value);
            }
            resultFile = jsonObject.toJSONString();

        } catch (Exception e) {
            System.out.println("File not found");
        }
        return resultFile;
    }


}