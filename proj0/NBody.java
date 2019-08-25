public class NBody{
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body [] bodies = readBodies(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
		for (Body b: bodies){
			b.draw();
		}
		StdDraw.enableDoubleBuffering();
		for(double t = 0; t <= T; t += dt){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for (int i = 0; i < bodies.length; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < bodies.length; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
			for (Body b: bodies){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++){
			StdOut.printf("%11.4e %11.4e %11.2e %11.4e %12s\n",
					bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
					bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
	public static double readRadius(String file){
		In in = new In(file);
		in.readDouble();
		return in.readDouble();
	}
	public static Body[] readBodies(String file){
		In in = new In(file);
		double number = in.readDouble();
		in.readDouble();
		Body[] bodies = new Body[(int)number];
		for(int i = 0; i < number ; i++){
			 bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return bodies;
	}
}