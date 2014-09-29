public class Query {
    
    char type;
    int resultCount;
    Location loc;
    
    public Query(){}
    
    public Query(char t, int rCount ,Location l){
        type=t;
        resultCount = rCount;
        loc = l;
    }
    
    public char getType() {
        return type;
    }
    
    public void setType(char type) {
        this.type = type;
    }
    
    public int getResultCount() {
        return resultCount;
    }
    
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
    
}
