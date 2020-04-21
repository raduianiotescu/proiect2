package neuralnet.math;

public interface IActivationFunction { //activation function is what fires the neuron's output, based on the sum of all incoming signals
    double calc(double x); //core method for calculating the activation function's value
    public enum ActivationFunctionENUM {
        STEP, LINEAR, SIGMOID, HYPERTAN
    }

    double derivative(double x);
    
}
