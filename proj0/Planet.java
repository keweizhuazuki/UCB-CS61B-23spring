public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,double yV, double m, String img){
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
    }
    public Planet(Planet p){
            xxPos = p.xxPos;
            yyPos = p.yyPos;
            xxVel = p.xxVel;
            yyVel = p.yyVel;
            mass = p.mass;
            imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
            return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)+(yyPos - p.yyPos)*(yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
            double r = calcDistance(p);
            return (G * mass * p.mass)/(r*r);
    }

    public double calcForceExertedByX(Planet p){
            double f = calcForceExertedBy(p);
            double r = calcDistance(p);
            return f*(p.xxPos-xxPos)/r;
    }
    
    public double calcForceExertedByY(Planet p){
            double f = calcForceExertedBy(p);
            double r = calcDistance(p);
            return f*(p.yyPos-yyPos)/r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
            double total_force = 0;
            for (Planet planet : allPlanets){
                if (this.equals(planet)){
                    continue;
                }
                total_force += calcForceExertedByX(planet);
            }
            return total_force;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
            double total_force = 0;
            for (Planet planet : allPlanets){
                if (this.equals(planet)){
                    continue;
                }
                total_force += calcForceExertedByY(planet);
            }
            return total_force;
    }

    public void update(double dt, double fX, double fY){
            double ax = fX/ mass;
            double ay = fY/ mass;
            xxVel = xxVel + dt * ax;
            yyVel = yyVel + dt * ay;
            xxPos = xxPos + dt * xxVel;
            yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);

    }
}
