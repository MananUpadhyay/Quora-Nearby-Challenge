/**
 * Solution to Quora's Nearby Programming Challenge
 * Class: Quora Holds the logic to Quora's Nearby Challenge
 * ****************************
 * Parameters Related to TOPICS
 * ****************************
 * @param TOPIC_COUNT Number of Topics
 * @param mTopics[] holds the Topics read from the input.
 * @param topicMap<Double,Integer> TreeMap that holds the mapping from Topic distance(from a particular query) to Topic ID.
 * *******************************
 * Parameters Related to QUESTIONS
 * *******************************
 * @param QUESTION_COUNT Number of Questions.
 * @param mQuestions[] holds the Questions read from the input.
 * @param questionMap<Double,Integer> TreeMap that holds the mapping from Question distance(from a particular query) to Question ID.
 * *****************************
 * Parameters Related to QUERIES
 * *****************************
 * @param QUERY_COUNT Number of Queries
 * @param mQuery[] holds the Queries read from the input.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class Quora {
    
    public static int TOPIC_COUNT;
    public static int QUESTION_COUNT;
    public static int QUERY_COUNT;
    
    static Topic [] mTopics;
    static Question [] mQuestions;
    static Query [] mQuery;

    public Quora(){}

    // public void readUserInput(){
           // Reads the input from STDIN.
    //     Scanner sc = new Scanner(System.in);
        
    //     System.out.println("Enter number of Topics");
    //     TOPIC_COUNT = sc.nextInt();
        
    //     System.out.println("Enter number of Questions");
    //     QUESTION_COUNT = sc.nextInt();
        
    //     System.out.println("Enter number of Queries");
    //     QUERY_COUNT = sc.nextInt();
        
    //     mTopics = new Topic[TOPIC_COUNT];
    //     mQuestions = new Question[QUESTION_COUNT];
    //     mQuery = new Query[QUERY_COUNT];
        
    //     System.out.println("Enter " + TOPIC_COUNT +" Topics");
    //     System.out.println();
    //     for (int i = 0; i < TOPIC_COUNT; i++) {
    //         System.out.println("Enter Topic " +(i+1)+" ID.");
    //         int id = sc.nextInt();
    //         System.out.println("Enter Topic " +(i+1)+" X Co-Ordinate");
    //         float xCord = sc.nextFloat();
    //         System.out.println("Enter Topic " +(i+1)+" Y Co-Ordinate");
    //         float yCord = sc.nextFloat();
            
    //         mTopics[i] = new Topic(id, new Location(xCord,yCord));
    //     }
        
    //     System.out.println("Enter " + QUESTION_COUNT +" Questions");
    //     System.out.println();
    //     for (int i = 0; i < QUESTION_COUNT; i++) {
    //         System.out.println("Enter Question " +(i+1)+" ID.");
    //         int qid = sc.nextInt();
    //         System.out.println("Enter number of related Topics ");
    //         int relatedTopicCount = sc.nextInt();
    //         int [] relatedTopicID = new int[relatedTopicCount];
    //         System.out.println();
    //         for (int j = 0; j < relatedTopicCount; j++) {
    //             System.out.println("Enter related Topic " +(j+1)+" ID.");
    //             relatedTopicID[j] = sc.nextInt();
    //         }
    //         Question que = new Question(qid, relatedTopicCount);
    //         que.setRelatedTopicIds(relatedTopicID);
            
    //         mQuestions[i] = que;
    //     }
        
    //     System.out.println("Enter " + QUERY_COUNT +" Queries");
    //     System.out.println();
    //     for (int i = 0; i < QUERY_COUNT; i++) {
    //         System.out.println("Enter Query " +(i+1)+" Type");
    //         char type = sc.next().charAt(0);
    //         System.out.println("Enter Query " +(i+1)+" Expected Results");
    //         int res = sc.nextInt();
    //         System.out.println("Enter Query " +(i+1)+" X Co-Ordinate");
    //         float xCord = sc.nextFloat();
    //         System.out.println("Enter Query " +(i+1)+" Y Co-Ordinate");
    //         float yCord = sc.nextFloat();
    //         mQuery[i] = new Query(type, res, new Location(xCord, yCord));
    //     }
    // }

    public void readInputFile(String file) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        // Get Counts of Topics, Questions, Queries.
        String counts = br.readLine();
        String [] count = counts.split(" ");
        
        TOPIC_COUNT = Integer.parseInt(count[0]);
        QUESTION_COUNT = Integer.parseInt(count[1]);
        QUERY_COUNT = Integer.parseInt(count[2]);
        
        mTopics = new Topic[TOPIC_COUNT];
        mQuestions = new Question[QUESTION_COUNT];
        mQuery = new Query[QUERY_COUNT];
        
        // Get Topics...
        for (int i = 0; i < TOPIC_COUNT; i++) {
            String s = br.readLine();
            String [] tmp = s.split(" ");
            mTopics[i] = new Topic(Integer.parseInt(tmp[0]),new Location(Float.parseFloat(tmp[1]),Float.parseFloat(tmp[2])));
        }
        // Get Questions...
        for (int i = 0; i < QUESTION_COUNT; i++) {
            String s = br.readLine();
            String [] tmp = s.split(" ");
            final int qid = Integer.parseInt(tmp[0]);
            final int tCount = Integer.parseInt(tmp[1]);
            Question q = new Question(qid,tCount);
            
            int k = 0;
            for (int j = 2; j < 2+tCount; j++) {
                q.mTopicIds[k++] = Integer.parseInt(tmp[j]);
            }
            mQuestions[i] = q;
        }
        // Get Query...
        for (int i = 0; i < QUERY_COUNT; i++) {
            String s = br.readLine();
            String [] tmp = s.split(" ");
            mQuery[i] = new Query(tmp[0].charAt(0), Integer.parseInt(tmp[1]) ,new Location(Float.parseFloat(tmp[2]),Float.parseFloat(tmp[3])));
        }
    }
    
    public double getDistance(Location a, Location b){
        // geodesic distance
        return Math.sqrt((((a.x-b.x) * (a.x-b.x)) + ((a.y-b.y)*(a.y-b.y))));
    }
    
    public void addDescending(List<Integer> l, int id) throws Exception{
        // insert into the list in descending order.
        if(l == null || id < 0)
        {
            throw new Exception("Illegal Arguments: may be null");
        }
        int i = 0;
        while( i < l.size()){
            if(l.get(i) < id )
                break;
            i++;
        }
        l.add(i,id);
    }
    
    public int findTopicIndex(int val){
        Topic [] tempTopics = mTopics;
        for (int i = 0; i < TOPIC_COUNT; i++) {
            if(tempTopics[i].getId() == val)
                return i;
        }
        return -1; // should never happen
    }
    
    public double roundOff(double val){
        BigDecimal bd = new BigDecimal(val);
        bd = bd.setScale(3, RoundingMode.HALF_UP); //rounding to 3 places
        return bd.doubleValue();
    }
    
    public String solveTopicQuery(Query qr) throws Exception{
        if(qr.resultCount == 0)
            return null;
        
        StringBuilder sb = new StringBuilder();
        TreeMap<Double,List<Integer>> topicMap = new TreeMap<Double,List<Integer>>();
        
        for (int i = 0; i < TOPIC_COUNT; i++) {
            double dst = roundOff(getDistance(qr.loc,mTopics[i].getLocation()));
            if(topicMap.containsKey(dst)){
                List<Integer> li = topicMap.get(dst);
                if(!li.contains(mTopics[i].getId()))
                    addDescending(li,mTopics[i].getId());
            }else{
                List<Integer> tmp = new ArrayList<Integer>();
                tmp.add(mTopics[i].getId());
                topicMap.put(dst,tmp);
            }
        }
        
        for (int i = 0; i < qr.resultCount; i++) {
            if(topicMap.size() == 0)
                break;
            Map.Entry<Double,List<Integer>> tp = topicMap.pollFirstEntry();
            if(tp == null)
                return null;
            List<Integer> tpList = tp.getValue();
            if(tpList.size() == 1){
                // no ties
                int topicIndex = tpList.get(0);
                sb.append(topicIndex).append(" ");
            }else{
                for (int j = 0; j < tpList.size() ; j++) {
                    if(i >= qr.resultCount)
                        break;
                    sb.append(tpList.get(j)).append(" ");
                    i++;
                }
                i-=1; // resetting i to its correct value
            }
        }
        return sb.toString();
    }
    
    public String solveQuestionQuery(Query qr) throws Exception{
        if(qr.resultCount == 0)
            return null;
        
        StringBuilder sb = new StringBuilder();
        TreeMap<Double,List<Integer>> questionMap = new TreeMap<Double,List<Integer>>();
        
        for (int i = 0; i < QUESTION_COUNT; i++) {
            double minDistance = Double.MAX_VALUE;
            Question que = mQuestions[i];
            if(que.topicCount != 0){
                for (int j = 0; j < que.topicCount; j++) {
                    int tindx = findTopicIndex(que.mTopicIds[j]);
                    Location tLoc = mTopics[tindx].getLocation();
                    double queDistance = getDistance(qr.loc,tLoc);
                    if(queDistance < minDistance){
                        minDistance = queDistance;
                    }
                }
                minDistance = roundOff(minDistance);
//                System.out.println("md: "+ minDistance);
                if(questionMap.containsKey(minDistance)){
                    List<Integer> qi = questionMap.get(minDistance);
                    if(!qi.contains(mQuestions[i].getId()))
                        addDescending(qi,mQuestions[i].getId());
                }else{
                    List<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(mQuestions[i].getId());
                    questionMap.put(minDistance,tmp);
                }
            }
        }
        
        
        for (int i = 0; i < qr.resultCount; i++) {
            if(questionMap.size() == 0)
                break;
            Map.Entry<Double,List<Integer>> queEntry = questionMap.pollFirstEntry();
            if(queEntry == null)
                return null;
            List<Integer> queList = queEntry.getValue();
            
            if(queList.size() == 1){
                // no ties
                int questionIndex = queList.get(0);
                sb.append(questionIndex).append(" ");
            }else{
                for (int j = 0; j < queList.size(); j++) {
                    if(i >= qr.resultCount)
                        break;
                    sb.append(queList.get(j)).append(" ");
                    i++;
                }
                i-=1; // resetting i to its correct value
            }
        }
        return sb.toString();
    }
    
    public void solveQuery(Query qr) throws Exception{
        switch(qr.type){
            case 't' :
                String topicResults = solveTopicQuery(qr);
                System.out.println(topicResults);
                break;
                
            case 'q' :
                String questionResults = solveQuestionQuery(qr);
                System.out.println(questionResults);
                break;
                
            default : throw new Exception("Illegal Query Type");
        }
    }
}