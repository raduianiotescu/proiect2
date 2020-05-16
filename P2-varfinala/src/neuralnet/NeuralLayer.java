package neuralnet;

import neuralnet.math.IActivationFunction;
import java.util.ArrayList;

public abstract class NeuralLayer { //gruparea neuronilor cu ajutorul unei clase abstracte

    protected int numberOfNeuronsInLayer; //nr de neuroni din layer
    private ArrayList<Neuron> neuron; //Array de tip Neuron
    protected IActivationFunction activationFnc; //actv fnc
    protected NeuralLayer previousLayer;
    protected NeuralLayer nextLayer;
    protected ArrayList<Double> input;
    protected ArrayList<Double> output;
    protected int numberOfInputs;

    public NeuralLayer(int numberofneurons){
        this.numberOfNeuronsInLayer=numberofneurons;
        neuron = new ArrayList<>(numberofneurons);
        output = new ArrayList<>(numberofneurons);
    }

    public NeuralLayer(int numberofneurons,IActivationFunction iaf){
        this.numberOfNeuronsInLayer=numberofneurons;
        this.activationFnc=iaf;
        neuron = new ArrayList<>(numberofneurons);
        output = new ArrayList<>(numberofneurons);
    }

    public int getNumberOfNeuronsInLayer(){
        return numberOfNeuronsInLayer;
    }

    public ArrayList<Neuron> getListOfNeurons(){
        return neuron;
    }

    protected NeuralLayer getPreviousLayer(){
        return previousLayer;
    }

    protected NeuralLayer getNextLayer(){
        return nextLayer;
    }

    protected void setPreviousLayer(NeuralLayer layer){
        previousLayer=layer;
    }

    protected void setNextLayer(NeuralLayer layer){
        nextLayer=layer;
    }

    protected void init(){ //initializare cu ajutorul actv fnc
        if(numberOfNeuronsInLayer>=0){
            for(int i=0;i<numberOfNeuronsInLayer;i++){
                try{
                    neuron.get(i).setActivationFunction(activationFnc);
                    neuron.get(i).init();
                }
                catch(IndexOutOfBoundsException iobe){
                    neuron.add(new Neuron(numberOfInputs,activationFnc));
                    neuron.get(i).init();
                }
            }
        }
    }

    protected void setInputs(ArrayList<Double> inputs){
        this.numberOfInputs=inputs.size();
        this.input=inputs;
    }

    protected void calc(){ //calculeaza outputul
        if(input!=null && neuron!=null){
            for(int i=0;i<numberOfNeuronsInLayer;i++){
                neuron.get(i).setInputs(this.input);
                neuron.get(i).calc();
                try{
                    output.set(i,neuron.get(i).getOutput());
                }
                catch(IndexOutOfBoundsException iobe){
                    output.add(neuron.get(i).getOutput());
                }
            }
        }
    }

    protected ArrayList<Double> getOutputs(){
        return output;
    }

    public Neuron getNeuron(int i){
        return neuron.get(i);
    }

    protected void setNeuron(int i, Neuron _neuron){
        try{
            this.neuron.set(i, _neuron);
        }
        catch(IndexOutOfBoundsException iobe){
            this.neuron.add(_neuron);
        }
    }

    public Double getWeight(int i,int j){
        return this.neuron.get(j).getWeight(i);
    }
    
}
