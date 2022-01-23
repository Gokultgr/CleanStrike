package com.tgr.cleanstrike;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GameImplementationTest {
    private final String TEST_CASE_FILE_PATH = "src\\test\\java\\com\\tgr\\cleanstrike\\testcases.json";

    @Test
    public void Test() {
        GameImplementation gameImplementation = new GameImplementation();
        HashMap<String, List<Integer>> playerInputs = new HashMap<>();
        playerInputs.put("Gokul", Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1));
        playerInputs.put("Rajam", Arrays.asList(1, 2, 1, 3, 1, 1, 1, 1, 1));
        String winner = gameImplementation.gameSimulator(playerInputs);
        assertEquals("Rajam", winner);
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser
                    .parse(new FileReader(TEST_CASE_FILE_PATH));
            JSONArray testCases = (JSONArray) json.get("TestCases");
            Iterator<?> itr = testCases.iterator();
            while (itr.hasNext()) {
                JSONObject jsonObject = (JSONObject) itr.next();
                JSONObject inputObj = (JSONObject) jsonObject.get("input");
                String output = (String) jsonObject.get("output");
                Iterator<?> inputIterator = inputObj.keySet().iterator();
                HashMap<String, List<Integer>> inputs = new HashMap<>();
                while (inputIterator.hasNext()) {
                    String key = (String) inputIterator.next();
                    JSONArray array = (JSONArray) inputObj.get(key);
                    List<Integer> list = new ArrayList<>();
                    Iterator<?> arrItr = array.iterator();
                    while (arrItr.hasNext()) {
                        Long in = (Long) arrItr.next();
                        list.add(in.intValue());
                    }
                    inputs.put(key, list);
                }
                System.out.println(inputs);
                String actString = gameImplementation.gameSimulator(inputs);
                System.out.println(actString);
                assertEquals(output, actString);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
