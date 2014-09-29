public class Question {
    private final int id;
    int topicCount;
    int [] mTopicIds;
    
    public Question(){
        id=Integer.MIN_VALUE;
    }
    
    public Question(int i, int cnt){
        topicCount=cnt;
        id=i;
        mTopicIds = new int[cnt];
    }
    
    public int getTCount() {
        return topicCount;
    }
    
    public void setTCount(int cnt) {
        topicCount = cnt;
    }

    public int getId() {
        return id;
    }

    public void setRelatedTopicIds(int[] t){
        this.mTopicIds = t;
    }
    
}
