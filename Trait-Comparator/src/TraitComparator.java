import Pattern.Pattern;
import Util.SourceCodeParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TraitComparator {

    private Pattern pattern = null;

    // Default cluster resource code folder name
    private String clusterFolder = "src/clusterSourceCode";

    public TraitComparator (Pattern pattern){
        this.pattern = pattern;
    }

    public TraitComparator (Pattern pattern, String clusterFolder){
        this.pattern = pattern;
        this.clusterFolder = clusterFolder;
    }

    public void compare(){
        SourceCodeParser parser = new SourceCodeParser(pattern, clusterFolder);
        String[] clusters = parser.getClusterNames();

        HashMap<String, Double> clusterScores = new HashMap<>();

        PriorityQueue<String> clusterRank = new PriorityQueue<>(((o1, o2) -> clusterScores.get(o2) < clusterScores.get(o1) ? -1 : clusterScores.get(o2) > clusterScores.get(o1) ? 1 : 0));

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

        // Output cluster rank in descending order
        int rank = 1;
        while(!clusterRank.isEmpty()){
            String clusterName = clusterRank.poll();
            if(clusterScores.get(clusterName) == 0) break;
            System.out.println("No." + rank + "  " + clusterName+" : "+  clusterScores.get(clusterName));
            rank++;
        }

    }
}
