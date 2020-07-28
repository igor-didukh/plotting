package ploting;

import org.jfree.data.xy.XYSeries;

public abstract class MethodsOfCalc {
    String name;
    XYSeries series;
    abstract double f(double a, double b, FunctionsToPlot func);
    
    void setSeriesItem(double x, double f) {
        this.series.add(x,f);
    }
    
    String getName() {
        return this.name;
    }
    
    XYSeries getSeries() {
        return this.series;
    }
}


class Bisection extends MethodsOfCalc {
    public Bisection() {
        this.name = "Bisection method";
        this.series = new XYSeries(this.name);
    }

    @Override
    public double f(double a, double b, FunctionsToPlot func) {
        return a + (b - a) / 2;
    }
}

class FalsePosition extends MethodsOfCalc {
    public FalsePosition() {
        this.name = "False position method";
        this.series = new XYSeries(this.name);
    }
    
    @Override
    public double f(double a, double b, FunctionsToPlot func) {
        return (a * func.f(b) - b * func.f(a)) / (func.f(b) - func.f(a));
    }
}