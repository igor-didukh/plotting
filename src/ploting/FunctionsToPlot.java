/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploting;

public abstract class FunctionsToPlot {
    abstract String getName();
    abstract double f(double x);
    abstract double getLeftBound();
    abstract double getRightBound();
}

class f1 extends FunctionsToPlot {
    @Override
    public String getName() {
        return "Sin(x)-0.5";
    }
    
    @Override
    public double f(double x) {
        return Math.sin(x)-0.5;
    }

    @Override
    public double getLeftBound() {
        return -1;
    }

    @Override
    public double getRightBound() {
        return 3.5;
    }
}

class f2 extends FunctionsToPlot {
    @Override
    public String getName() {
        return "Cos(x)";
    }
    
    @Override
    public double f(double x) {
        return Math.cos(x);
    }

    @Override
    public double getLeftBound() {
        return -2;
    }

    @Override
    public double getRightBound() {
        return 2;
    }
}

class f3 extends FunctionsToPlot {
    @Override
    public String getName() {
        return "Exp(x)-3";
    }
    
    @Override
    public double f(double x) {
        return Math.exp(x)-3;
    }

    @Override
    public double getLeftBound() {
        return -4.8;
    }

    @Override
    public double getRightBound() {
        return 3.9;
    }
}