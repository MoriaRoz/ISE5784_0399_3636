package primitives;

public class Point {
    protected final Double3 xyz;

    //Constructors:
    public Point(double d1, double d2, double d3) {
        this.xyz = new Double3(d1,d2,d3);
    }
    public Point(Double3 d){
        this.xyz=d;
    }

    public Vector subtract(Point p){
        return new Vector(this.xyz.subtract(p.xyz));
    }

    public Point add(Vector v){
        return new Point(this.xyz.add(v.xyz));
    }

    public double distanceSquared(Point p){
        return ((this.xyz.d1-p.xyz.d1)*(this.xyz.d1-p.xyz.d1)+
                (this.xyz.d2-p.xyz.d2)*(this.xyz.d2-p.xyz.d2)+
                (this.xyz.d3-p.xyz.d3)*(this.xyz.d3-p.xyz.d3));
    }

    public double distance(Point p){
        return Math.sqrt(this.distanceSquared(p));
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }
}
