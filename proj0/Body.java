public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;
	public Body(double xP, double yP, double xV, double yV, 
				double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body another){
		return Math.sqrt(Math.pow((this.xxPos - another.xxPos), 2) + Math.pow((this.yyPos - another.yyPos), 2));
	}
	public double calcForceExertedBy(Body another){
		if (this.equals(another)){
			return 0;
		}else {
			return G * this.mass * another.mass / Math.pow(calcDistance(another), 2);
		}
	}
	public double calcForceExertedByX(Body another){
		if (this.equals(another)){
			return 0;
		}else {
			return this.calcForceExertedBy(another) * (another.xxPos - this.xxPos) / this.calcDistance(another);
		}
	}
	public double calcForceExertedByY(Body another){
		if (this.equals(another)) {
			return 0;
		}else {
			return this.calcForceExertedBy(another) * (another.yyPos - this.yyPos) / this.calcDistance(another);
		}
	}
	public double calcNetForceExertedByX(Body[] lst){
		double total = 0;
		for(int i = 0; i < lst.length; i++){
			if(this.equals(lst[i])) {
				;
			}else {
				total += this.calcForceExertedByX(lst[i]);
			}
		}
		return total;
	}
	public double calcNetForceExertedByY(Body[] lst){
		double total = 0;
		for(int i = 0; i < lst.length; i++){
			if(this.equals(lst[i])){
				;
			}else {
				total += this.calcForceExertedByY(lst[i]);
			}
		}
		return total;
	}
	public void update(double dt, double FX, double FY){
			double a_x = FX/this.mass;
			double a_y = FY/this.mass;
			xxVel = xxVel + a_x*dt;
			yyVel = yyVel + a_y*dt;
			xxPos = xxPos + xxVel*dt;
			yyPos = yyPos + yyVel*dt;
	}
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}