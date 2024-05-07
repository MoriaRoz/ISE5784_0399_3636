package primitives;

public class Vector extends Point{
    //Constructors:
    public Vector(double d1,double d2,double d3){
        super(d1,d2,d3);
        Double3 d=new Double3(d1, d2, d3);
        if(d.equals(d.ZERO))
            throw new IllegalArgumentException("This is the zero vector");
    }
    public Vector(Double3 d){
        super(d);
        if(d.equals(d.ZERO))
            throw new IllegalArgumentException("This is the zero vector");
    }
    public Vector add(Vector v){
        return new Vector(this.xyz.add(v.xyz));
    }
    public Vector scale(double d){
        return new Vector(this.xyz.scale(d));
    }
    public double dotProduct(Vector v){
        return (this.xyz.d1*v.xyz.d1+this.xyz.d2*v.xyz.d2+this.xyz.d3*v.xyz.d3);
    }
    public Vector crossProduct(Vector v){
        double x = this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
        double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3;
        double z = this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1;

        return new Vector(x, y, z);
    }
    public double lengthSquared(){
        return this.dotProduct(this);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){
        double l=this.length();
        Vector v=this.scale(1/l);//Multiplication of a vector by 1 divided by its length
        return v;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
