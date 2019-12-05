import Pattern.Pattern;
import Util.ClusterParser;
import Util.SourceCodeParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TraitComparator {

    private Pattern pattern;

    // Default cluster resource code folder name
    private String clusterFolder;

    private String version = "";


    public TraitComparator (Pattern pattern, String clusterFolder){
        this.pattern = pattern;
        this.clusterFolder = clusterFolder;
    }

    public void compare(String originalAcdcFile, String functionAcdcFile) throws IOException {
        SourceCodeParser parser = new SourceCodeParser(pattern, clusterFolder);

        // class -> original ACDC cluster
        HashMap<String, String> classClusterMap = ClusterParser.parseCluster(originalAcdcFile);

        // function level cluster -> functions
        HashMap<String, List<String>> clusterFunctionsMap = ClusterParser.parseFunctionCluster(functionAcdcFile);

        // function -> affected original ACDC cluster
        HashMap<String, Set<String>> clusterAffectedMap = new HashMap<>();

//        System.out.println(clusterFunctionsMap.keySet().size());
        String[] clusters = parser.getClusterNames();

        // function cluster -> cluster score
        HashMap<String, Double> clusterScores = new HashMap<>();
        // originalACDC cluster -> cluster score
        HashMap<String, Double> classClusterScores = new HashMap<>();

        PriorityQueue<String> clusterRank = new PriorityQueue<>(((o1, o2) -> clusterScores.get(o2) < clusterScores.get(o1) ? -1 : clusterScores.get(o2) > clusterScores.get(o1) ? 1 : 0));
        PriorityQueue<String> classClusterRank = new PriorityQueue<>(((o1, o2) -> classClusterScores.get(o2) < classClusterScores.get(o1) ? -1 : classClusterScores.get(o2) > classClusterScores.get(o1) ? 1 : 0));
        // Calculate cluster matching score
        try {
            for(String clusterName : clusters){
                parser.parse(clusterName);
                clusterScores.put(clusterName, parser.getMatchScore());
                clusterRank.add(clusterName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(String functionClusterName : clusters){
            Set<String> functionSeen = new HashSet<>();
            Set<String> clustersAffected = new HashSet<>();
            for(String functionName : clusterFunctionsMap.get(functionClusterName)) {
//                System.out.println(functionName);
                if(functionSeen.contains(functionName)) continue;
                functionSeen.add(functionName);
                String className = getClassName(functionName);
                String classClusterName = classClusterMap.get(className);
                if(classClusterName == null) continue;
                double classScore = clusterScores.get(functionClusterName);
                classClusterScores.put(classClusterName, classClusterScores.getOrDefault(classClusterName,0.0) + classScore);

                // keep track of original ACDC clusters related to the function level clusters
                clustersAffected.add(classClusterName);
            }
            clusterAffectedMap.put(functionClusterName, clustersAffected);
        }


        for(String classCluster : classClusterScores.keySet()){
            classClusterRank.add(classCluster);
        }


        outputClassClusteRanking(classClusterRank,classClusterScores);

        outputClusterRelation(clusterAffectedMap,clusterRank,clusterScores);
    }

    private String getClassName(String functionName){

        int index = functionName.lastIndexOf('.');
        String className = functionName.substring(0,index);
        if(className.contains("[]")) className = className.replace("[]","");
        return className;
    }


    private void outputClusterRelation(Map<String, Set<String>> map,
                                       PriorityQueue<String> clusterRank,
                                       HashMap<String, Double> clusterScores) throws IOException {

        String fileName = "out/Cluster_Relation"+ version +".txt";;
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        while(!clusterRank.isEmpty()){
            String clusterName = clusterRank.poll();
            writer.println(clusterName+" : "+  clusterScores.get(clusterName) + " : " + Arrays.toString(map.get(clusterName).toArray()));

        }
        writer.close();
    }

    private void outputClassClusteRanking(PriorityQueue<String> classClusterRank,
                                       HashMap<String, Double> classClusterScores) throws IOException {

        String fileName = "out/Original_ACDC_Cluster_Ranking"+ version +".txt";
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");


        while(!classClusterRank.isEmpty()){
            String clusterName = classClusterRank.poll();
            writer.println(clusterName+" : "+  classClusterScores.get(clusterName));
        }
        writer.close();
    }

    public void setVersion(String version){
        this.version = "_" + version;
    }


}
