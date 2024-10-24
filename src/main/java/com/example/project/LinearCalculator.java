package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
        int commaIndex1 = coord1.indexOf(","); // These local variables are used to store the index of where the comma is, which makes the code easier to read.
        int commaIndex2 = coord2.indexOf(",");
        x1 = Integer.parseInt(coord1.substring(1, commaIndex1)); // The substring of coord1 and coord2 are intended to be the x values of both coordinates and the parseInt method turns the string into an int.
        x2 = Integer.parseInt(coord2.substring(1, commaIndex2));
        y1 = Integer.parseInt(coord1.substring(commaIndex1 + 1, coord1.length() - 1)); // The substring of coord1 and coord 2 are intended to be the y values of both coordinates and the parseInt method turns the string into an int.
        y2 = Integer.parseInt(coord2.substring(commaIndex2 + 1, coord2.length() - 1));
    }

    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
    public void setX1(int x1){
        this.x1 = x1;
    }
    public void setY1(int y1){
        this.y1 = y1;
    }
    public void setX2(int x2){
        this.x2 = x2;
    }
    public void setY2(int y2){
        this.y2 = y2;
    }

    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        double y2Minusy1Squared = Math.pow(y2 - y1, 2); // When following the pythagorean theorem, these values are equivalent to a^2 and b^2.
        double x2Minusx1Squared = Math.pow(x2 - x1, 2);
        return roundedToHundredth(Math.sqrt(y2Minusy1Squared + x2Minusx1Squared)); // Does the square root of a^2 + b^2 and rounds it to the nearest hundredth.
    }

    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        double slopeTimesX = slope() * x1;
        if (x1 - x2 == 0) { // This condition checks to see if the difference in x values is 0, which would tell us if the slope is undefined, since we would be dividing by 0.
            return -999.99;
        }
        return roundedToHundredth(y1 - slopeTimesX); // Solves for the y-intercept by using the equation for lines, y = mx + b. First it multiplies m and x together.
    }                                                // Then, it stores that in a double variabe and does y - mx to get b, the y intercept. Rounds the y-int to the nearest hundredth at the end.

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        if (x1 - x2 == 0) { // This condition checks to see if the difference in x values is 0, which would tell us if the slope is undefined, since we would be dividing by 0.
            return -999.99;
        }
        return roundedToHundredth((double) (y1 - y2) / (x1 - x2)); // Divides the difference of y values by the difference of x values to find the slope. Then rounds the answer to the nearest hundredth.
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        if (slope() == -999.99) { // Checks if slope is undefined. If it is, returns undefined.
            return "undefined";
        }
        if (slope() == 0) { // Checks if slope is 0. If it is, returns only y = y-intercept.
            return "y=" + yInt();
        }
        if (yInt() == 0) { // Checks if y-intercept is 0. If it is, returns only y = mx
            return "y=" + slope() + "x";
        }
        if (yInt() < 0) { // Checks if y-intercept is negative. If it is, returns y = mx + b but there isn't a + sign at the end of x.
            return "y=" + slope() + "x" + yInt(); // This makes it so that a negative y-intercept will return y = 2x - 2, instead of y = 2x + -2.
        }
        return "y=" + slope() + "x+" + yInt(); // Returns the equation y = mx + b, where m is the slope and b is the y-intercept.
    }

    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        if (x <= 0) {
            return ((int) ((x - 0.005) * 100)) / 100.0; // Rounding for negative numbers. The x gets subtracted by 0.005 instead.
        }
        return ((int) ((x + 0.005) * 100)) / 100.0; // Adds 0.005 which will make sure that the number is supposed to round up. Then it multiplies by 100 and turns it into an int, which truncates it.
    }                                              // Then it divides by 100.0 to make it a double again and turns it back into the original number but rounded to the nearest hundredth.

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        String str = "The two points are: (" + x1 + "," + y1 + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n" + findSymmetry();
        str += "\n" + Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        if (x1 == x2 && y1 == -1 * y2) {
            return "Symmetric about the x-axis";
        }
        if (x1 == -1 * x2 && y1 == y2) {
            return "Symmetric about the y-axis";
        }
        if (x1 == -1 * x2 && y1 == -1 * y2) {
            return "Symmetric about the origin";
        }
        return "No symmetry";
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){
        double midPointX = ((double) x1 + x2) / 2;
        double midPointY = ((double) y1 + y2) / 2;
        return "The midpoint of this line is: (" + midPointX + "," + midPointY + ")";
    }
}