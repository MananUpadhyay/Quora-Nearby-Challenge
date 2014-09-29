public class Topic {
    private final int id;
    Location loc;
    
    public Topic(){
        id = Integer.MIN_VALUE;
    }
    
    public Topic(int i, Location l){
        id=i;
        loc=l;
    }
    
    public Location getLocation() {
        return loc;
    }
    
    public void setLocation(Location loc) {
        this.loc = loc;
    }
    
    public int getId() {
        return id;
    }
    
}
