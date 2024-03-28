public class NBody {

    /** get Radius of the planets from a file*/
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        int nonsense = in.readInt();
        return in.readDouble();
    }

    /** Read planets from a file*/
    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int nums = in.readInt();
        double radius = in.readDouble();
        Planet [] planets = new Planet[nums];
        //如果还能读到数据
        int i = 0;
        while(i < nums)
        {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
            i++;
        }
        return planets;
    }

    public static void main(String []args)
    {

        //获取所需要的数据
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        //画布全局参数设置
        StdDraw.setScale(-radius,radius);
        StdDraw.enableDoubleBuffering();

        //绘制背景图
        StdDraw.clear();
        String background = "images/starfield.jpg";
        StdDraw.picture(0,0,background);

        //绘制所有的行星
        for(Planet planet : planets)
        {
            planet.draw();
        }
        //展示行星
        StdDraw.show();
        double time = 0;
        int num_planets = planets.length;
        while(time != T) {
            double[] xForces = new double[num_planets];
            double[] yForces = new double[num_planets];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
