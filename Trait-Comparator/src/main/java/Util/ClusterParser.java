package Util;

import java.io.*;
import java.util.*;

public class ClusterParser {

    public static HashMap<String, String> parseCluster(String fileName) throws IOException {
        HashMap<String, String> clusterMap = new HashMap<>();
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null){
            String[] lineSplited = line.split(" ");
            if(lineSplited.length != 3) continue;
            String clusterName = lineSplited[1];
            String className = lineSplited[2];
            clusterMap.put(className, clusterName);
        }

        return clusterMap;
    }

    public static HashMap<String, List<String>> parseFunctionCluster(String fileName) throws IOException {
        // cluster -> list of functions
        HashMap<String, List<String>> functionCluster = new HashMap<>();
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null){
            String[] lineSplited = line.split(" ");
            if(lineSplited.length != 3) continue;
            String clusterName = lineSplited[1];
            String methodName = lineSplited[2];
            List<String> functionList = functionCluster.get(clusterName);
            if(functionList == null){
                functionList = new ArrayList<>();
                functionCluster.put(clusterName,functionList);
            }
            functionList.add(methodName);

        }

        return functionCluster;
    }

}
