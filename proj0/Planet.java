public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Planet(Planet b)
    {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b)
    {
        double raw_distance = (b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos);
        return Math.sqrt(raw_distance);
    }
    public double calcForceExertedBy(Planet b)
    {
        double distance = this.calcDistance(b);
        return  G * this.mass * b.mass / (distance*distance);
    }

    public double calcForceExertedByX(Planet b)
    {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Planet b)
    {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] planets)
    {
        double forceX = 0;
        for(Planet planet:planets)
        {
            if(planet.equals(this))continue;
            forceX += this.calcForceExertedByX(planet);
        }
        return forceX;
    }

    public double calcNetForceExertedByY(Planet[] planets)
    {
        double forceY = 0;
        for(Planet planet: planets)
        {
            if(planet.equals(this))continue;
            forceY += this.calcForceExertedByY(planet);
        }
        return forceY;
    }

    public void update(double dt,double fX,double fY)
    {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = xxVel + dt * aX;
        this.yyVel = yyVel + dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw()
    {
        String imag = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos,imag);
    }
}
