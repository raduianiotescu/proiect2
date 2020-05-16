package neuralnet;

import neuralnet.math.IActivationFunction;

public class OutputLayer extends NeuralLayer {

    public OutputLayer(int numberofneurons,IActivationFunction iaf,int numberofinputs){
        super(numberofneurons,iaf);
        numberOfInputs=numberofinputs;
        nextLayer=null;
        init();
    }

    public OutputLayer(int numberofneurons,IActivationFunction iaf,NeuralLayer _previousLayer){
        super(numberofneurons,iaf);
        setPreviousLayer(_previousLayer);
        numberOfInputs=_previousLayer.getNumberOfNeuronsInLayer();
    }

    @Override
    public void setNextLayer(NeuralLayer layer){
        nextLayer=null;
    }

    @Override
    public void setPreviousLayer(NeuralLayer layer){
        previousLayer=layer;
        if(layer.nextLayer!=this)
            layer.setNextLayer(this);
    }
    
}
