package neuralnet;

import neuralnet.data.NeuralDataSet;
import neuralnet.math.IActivationFunction;

import java.util.ArrayList;

public class NeuralNet {

    private InputLayer inputLayer; //input layer
    private ArrayList<HiddenLayer> hiddenLayer; //hidden layer, might be 0 or many
    private OutputLayer outputLayer; //output layer
    private int numberOfHiddenLayers;
    private int numberOfInputs;
    private int numberOfOutputs;
    private ArrayList<Double> input;
    private ArrayList<Double> output;
    private boolean activeBias=true;

    public NeuralNet(int numberofinputs,int numberofoutputs,
            int [] numberofhiddenneurons,IActivationFunction[] hiddenAcFnc,
            IActivationFunction outputAcFnc){
        numberOfHiddenLayers=numberofhiddenneurons.length;
        numberOfInputs=numberofinputs;
        numberOfOutputs=numberofoutputs;
        if(numberOfHiddenLayers==hiddenAcFnc.length){
            input=new ArrayList<>(numberofinputs);
            inputLayer=new InputLayer(numberofinputs);
            if(numberOfHiddenLayers>0){
                hiddenLayer=new ArrayList<>(numberOfHiddenLayers);
            }
            for(int i=0;i<numberOfHiddenLayers;i++){
                if(i==0){ //if hidden layer is right after the input layer
                    try{
                        hiddenLayer.set(i,new HiddenLayer(numberofhiddenneurons[i],
                            hiddenAcFnc[i],
                            inputLayer.getNumberOfNeuronsInLayer()));
                    }
                    catch(IndexOutOfBoundsException iobe){
                        hiddenLayer.add(new HiddenLayer(numberofhiddenneurons[i],
                            hiddenAcFnc[i],
                            inputLayer.getNumberOfNeuronsInLayer()));
                    }
                    inputLayer.setNextLayer(hiddenLayer.get(i));
                }
                else{ //else get the reference from the previous layer
                    try{
                        hiddenLayer.set(i, new HiddenLayer(numberofhiddenneurons[i],
                             hiddenAcFnc[i],hiddenLayer.get(i-1)
                            .getNumberOfNeuronsInLayer()
                            ));
                    }
                    catch(IndexOutOfBoundsException iobe){
                        hiddenLayer.add(new HiddenLayer(numberofhiddenneurons[i],
                             hiddenAcFnc[i],hiddenLayer.get(i-1)
                            .getNumberOfNeuronsInLayer()
                            ));
                    }
                    hiddenLayer.get(i-1).setNextLayer(hiddenLayer.get(i));
                }
            }
            if(numberOfHiddenLayers>0){ //if there are any hidden layers
                outputLayer=new OutputLayer(numberofoutputs,outputAcFnc,
                        hiddenLayer.get(numberOfHiddenLayers-1)
                        .getNumberOfNeuronsInLayer() 
                        );
                hiddenLayer.get(numberOfHiddenLayers-1).setNextLayer(outputLayer);
            }
            else{ //none hidden layers
                outputLayer=new OutputLayer(numberofinputs, outputAcFnc,
                        numberofoutputs);
                inputLayer.setNextLayer(outputLayer);
            }
        }
    }

    public NeuralNet(int numberofinputs,int numberofoutputs,
                     IActivationFunction outputAcFnc){
        this(numberofinputs,numberofoutputs,new int[0],new IActivationFunction[0],outputAcFnc);
    }

    public void setInputs(ArrayList<Double> inputs){
        if(inputs.size()==numberOfInputs){
            this.input=inputs;
        }
    }

    public void setInputs(double[] inputs){
        if(inputs.length==numberOfInputs){
            for(int i=0;i<numberOfInputs;i++){
                try{
                    input.set(i, inputs[i]);
                }
                catch(IndexOutOfBoundsException iobe){
                    input.add(inputs[i]);
                }
            }
        }
    }

    public ArrayList<Double> getArrayInputs(){
        return input;
    }

    public Double getInput(int i){
        return input.get(i);
    }

    public Double[] getInputs(){
        Double[] result=new Double[numberOfInputs];
        for(int i=0;i<numberOfInputs;i++){
            result[i]=input.get(i);
        }
        return result;
    }

    public void calc(){  //calculate output for each layer and forwards values to next layer
        inputLayer.setInputs(input);
        inputLayer.calc();
        if(numberOfHiddenLayers>0){
            for(int i=0;i<numberOfHiddenLayers;i++){
                HiddenLayer hl = hiddenLayer.get(i);
                hl.setInputs(hl.getPreviousLayer().getOutputs());
                hl.calc();
            }
        }
        outputLayer.setInputs(outputLayer.getPreviousLayer().getOutputs());
        outputLayer.calc();
        this.output=outputLayer.getOutputs();
    }

    public ArrayList<Double> getArrayOutputs(){
        return output;
    }

    public double[] getOutputs(){
        double[] _outputs = new double[numberOfOutputs];
        for(int i=0;i<numberOfOutputs;i++){
            _outputs[i]=output.get(i);
        }
        return _outputs;
    }

    public double getOutput(int i){
        return output.get(i);
    }

    public void print(){
        System.out.println("Neural Network: "+this.toString());
        System.out.println("\tInputs:"+String.valueOf(this.numberOfInputs));
        System.out.println("\tOutputs:"+String.valueOf(this.numberOfOutputs));
        System.out.println("\tHidden Layers: "+String.valueOf(numberOfHiddenLayers));
        for(int i=0;i<numberOfHiddenLayers;i++){
            System.out.println("\t\tHidden Layer "+ String.valueOf(i)+": "+ String.valueOf(this.hiddenLayer.get(i).numberOfNeuronsInLayer)+" Neurons");
        }
    }

    public void setNeuralDataSet(NeuralDataSet _neuralDataSet){
        _neuralDataSet.neuralNet=this;
    }

    public int getNumberOfHiddenLayers(){
        return numberOfHiddenLayers;
    }

    public int getNumberOfInputs(){
        return numberOfInputs;
    }

    public int getNumberOfOutputs(){
        return numberOfOutputs;
    }

    public InputLayer getInputLayer(){
        return inputLayer;
    }

    public HiddenLayer getHiddenLayer(int i){
        return hiddenLayer.get(i);
    }

    public ArrayList<HiddenLayer> getHiddenLayers(){
        return hiddenLayer;
    }

    public OutputLayer getOutputLayer(){
        return outputLayer;
    }

    public void deactivateBias(){
        if(numberOfHiddenLayers>0){
            for(HiddenLayer hl:hiddenLayer){
                for(Neuron n:hl.getListOfNeurons()){
                    n.deactivateBias();
                }
            }
        }
        for(Neuron n:outputLayer.getListOfNeurons()){
            n.deactivateBias();
        }
    }

    public void activateBias(){
        for(HiddenLayer hl:hiddenLayer){
            for(Neuron n:hl.getListOfNeurons()){
                n.activateBias();
            }
        }
        for(Neuron n:outputLayer.getListOfNeurons()){
            n.activateBias();
        }
    }

    public boolean isBiasActive(){
        return activeBias;
    }
}
