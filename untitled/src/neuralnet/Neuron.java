package neuralnet;

import neuralnet.math.IActivationFunction;
import neuralnet.math.RandomNumberGenerator;

import java.util.ArrayList;

public class Neuron {

    //declarare componente neuron

    protected ArrayList<Double> weight;
    private ArrayList<Double> input;
    private Double output;
    private Double outputBeforeActivation;
    private int numberOfInputs = 0; //if is 0, it means the neuron wasn't initialized yet
    protected Double bias = 1.0;
    private IActivationFunction activationFunction;

    //constructor Neuron

    public Neuron(){}

    public Neuron(int numberofinputs){
        numberOfInputs=numberofinputs;
        weight=new ArrayList<>(numberofinputs+1);
        input=new ArrayList<>(numberofinputs);
    }

    public Neuron(int numberofinputs,IActivationFunction iaf){
        numberOfInputs = numberofinputs;
        weight = new ArrayList<>(numberofinputs+1); // "+1" datorita bias-ului
        input = new ArrayList<>(numberofinputs);
        activationFunction=iaf;
    }

    //metoda de initializare a neuronului

    public void init(){
        for(int i=0;i<numberOfInputs;i++){
            double newWeight = RandomNumberGenerator.GenerateNext();
            try{
                this.weight.set(i,newWeight); //indexul si valoarea
            }catch(IndexOutOfBoundsException iobe){ //prevenim setarea unei valori in afara limitelor array-ului
                this.weight.add(newWeight);
            }
        }
    }

    public void setInputs(double [] values){
        if(values.length==numberOfInputs){
            for(int i=0;i<numberOfInputs;i++){
                try{
                    input.set(i, values[i]);
                }
                catch(IndexOutOfBoundsException iobe){
                    input.add(values[i]);
                }
            }
        }
    }

    public void setInputs(ArrayList<Double> values){
        if(values.size()==numberOfInputs){
            input=values;
        }
    }

    public ArrayList<Double> getArrayInputs(){
        return input;
    }

    public double[] getInputs(){
        double[] inputs = new double[numberOfInputs];
        for (int i=0;i<numberOfInputs;i++){
            inputs[i]=this.input.get(i);
        }
        return inputs;
    }

    public void setInput(int i,double value){
        if(i>=0 && i<numberOfInputs){
            try{
                input.set(i, value);
            }
            catch(IndexOutOfBoundsException iobe){
                input.add(value);
            }
        }
    }

    public double getInput(int i){
        return input.get(i);
    }

    public double[] getWeights(){
        double[] weights = new double[numberOfInputs+1];
        for(int i=0;i<=numberOfInputs;i++){
            weights[i]=weight.get(i);
        }
        return weights;
    }

    public ArrayList<Double> getArrayWeights(){
        return weight;
    }

    public void updateWeight(int i, double value){
        if(i>=0 && i<=numberOfInputs){
            weight.set(i, value);
        }
    }

    public int getNumberOfInputs(){
        return this.numberOfInputs;
    }

    public void setWeight(int i,double value) throws NeuralException{
        if(i>=0 && i<numberOfInputs){
            this.weight.set(i, value);
        }
        else{
            throw new NeuralException("Invalid weight index");
        }
    }

    public double getOutput(){
        return output;
    }

    //output-ul neuronului

    public void calc(){
        outputBeforeActivation=0.0;
        if(numberOfInputs>0){
            if(input!=null && weight!=null){
                for(int i=0;i<=numberOfInputs;i++){
                    outputBeforeActivation+=((i==numberOfInputs)?bias:input.get(i))*weight.get(i); //bias-ul se inmulteste doar la ultima iteratie
                }
            }
        }
    }

    public void setActivationFunction(IActivationFunction iaf){
        this.activationFunction=iaf;
    }


    public double getOutputBeforeActivation(){
        return outputBeforeActivation;
    }
}
