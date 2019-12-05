package Util;

import Pattern.Pattern;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SourceCodeParser {

    private double matchScore = 0;

    private Pattern pattern;

    private String folderName;

    private final String functionDivsor = "-----CSCI578-----";

    public SourceCodeParser (Pattern pattern, String folderName){

        this.pattern = pattern;
        this.folderName = folderName;
    }

    public void parse(String clusterName) throws IOException {

        matchScore = 0;

        File file = new File(getDirectory(clusterName));

        BufferedReader reader = new BufferedReader(new FileReader(file));

        int methodNum = 1;
        try {
            methodNum = Integer.valueOf(reader.readLine());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        double dividend = Math.pow(methodNum,0.5);
        String line;
        StringBuilder methodString = new StringBuilder();
        while((line = reader.readLine()) != null){

            if(line.equals(functionDivsor)){
                matchScore += pattern.matchScore(methodString.toString());
                methodString = new StringBuilder();
            }
            methodString.append(line);
//            if(clusterName.equals("org.apache.catalina.realm.RealmBase.ss11")){
//                if(pattern.matchScore(line) != 0){
//                    System.out.println(line);
//                }
//            }
        }

        if(methodString.length() != 0) matchScore += pattern.matchScore(methodString.toString());

        matchScore = matchScore / dividend;

    }

    public String getDirectory(String clusterName){

        String fileDirectory = folderName + "/" + clusterName + ".txt";
        return fileDirectory;
    }

    public double getMatchScore(){

        return matchScore;

    }

    public String[] getClusterNames(){
        File folder  = new File(folderName);
        File[] clusters = folder.listFiles();

        ArrayList<String> clusterNames = new ArrayList<>();
        for(int i = 0; i < clusters.length; i++){
            if(!clusters[i].getName().endsWith(".txt")) continue;
//            System.out.println(clusters[i].getName());
            clusterNames.add(clusters[i].getName().replace(".txt",""));
        }
        return clusterNames.toArray(new String[clusterNames.size()]);
    }

}
