public class Location {
    float x;
    float y;
    
    public Location(){}
    
    public Location (float ix, float iy){
        x = ix;
        y = iy;
    }
    
    public float getX() {
        return x;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "X: "+x +" "+"Y: "+y;
    }
    
}
