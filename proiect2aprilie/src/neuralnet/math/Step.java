package neuralnet.math;

public class Step implements IActivationFunction {

    @Override
    public double calc(double x){
        if(x<0) return 0.0;
        else return 1.0;
    }

    @Override
    public double derivative(double x){
        if(x==0)
            return Double.MAX_VALUE;
        else
            return 0.0;

    }
    
}
