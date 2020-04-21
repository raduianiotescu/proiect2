// mediul de dezvoltare folosit: IntelliJ IDEA

package neuralnet;

import neuralnet.data.NeuralDataSet;
import neuralnet.learn.DeltaRule;
import neuralnet.learn.LearningAlgorithm;
import neuralnet.math.HyperTan;
import neuralnet.math.Linear;
import neuralnet.math.RandomNumberGenerator;

import java.io.File;

//Main class

public class Main {

    // metoda de parcurgere folder cu imagini

    public static void readImagesPath(String dir) {
        File folder = new File(dir);
        double counter=0; //counter folosit pentru a numara cate imagini avem in folder
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                counter++;
            }
        }
        System.out.println("Numar total de imagini: " + counter);
    }

    // main procesare imagini

    /*public static void main(String[] args) throws IOException {

        //variabile citire o anumita imagine dupa calea data

        // int width = 300; //latime
        // int height = 225; //lungime
        BufferedImage image = null;
        File InputImage = null;
        File OutputImage = null;

        //citire imagine


        try {
            InputImage = new File("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cane\\OIF-e2bexWrojgtQnAPPcUfOWQ.jpeg");
            // image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //argb inseamna "Alpha, Red, Green, Blue component"
            image = ImageIO.read(InputImage);
            System.out.println("Imaginea a fost citita.");
        }catch(IOException e){
            System.out.println("Eroare: "+e);
        }

        //conversion to grayscale

        *//*
        width = image.getWidth();
        height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                int avg = (r+g+b)/3;
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                image.setRGB(x, y, p);
            }
        }
        *//*

        *//*
        //conversion to negative

        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                p = (a<<24) | (r<<16) | (g<<8) | b;
                image.setRGB(x, y, p);
            }
        }
         *//*

        *//*
        //conversion to sepia

        int width = image.getWidth();
        int height = image.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int R = (p>>16)&0xff;
                int G = (p>>8)&0xff;
                int B = p&0xff;
                int newRed = (int)(0.393*R + 0.769*G + 0.189*B);
                int newGreen = (int)(0.349*R + 0.686*G + 0.168*B);
                int newBlue = (int)(0.272*R + 0.534*G + 0.131*B);
                if (newRed > 255)
                    R = 255;
                else
                    R = newRed;
                if (newGreen > 255)
                    G = 255;
                else
                    G = newGreen;
                if (newBlue > 255)
                    B = 255;
                else
                    B = newBlue;
                p = (a<<24) | (R<<16) | (G<<8) | B;
                image.setRGB(x, y, p);
            }
        }
         *//*

        *//*
        //using opencv for face recognition

        //opencv load library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("C:\\Users\\radui\\Desktop\\opencv-master\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        Mat img = Imgcodecs.imread("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cane\\20191007_132925.jpeg");
        //detecting face
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(img, faceDetections);
        //rectangular box showing faces detected
        for (Rect rect : faceDetections.toArray())
        {
            Imgproc.rectangle(img, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        //the output image
        String filename = "OuputDETECTIEIMAGINE.jpg";
        Imgcodecs.imwrite("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\OutputImages\\"+filename, img);
        *//*

        //scriere imagine

        try{
            OutputImage = new File("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\OutputImages\\outputWITHOUTbuff.jpeg");
            ImageIO.write(image,"jpeg",OutputImage);
            System.out.println("Imaginea a fost scrisa");
        }catch(IOException e){
            System.out.println("Eroare: " + e);
        }

        //afisare imagine

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        frame.add(label); //adauga imaginea pe frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //intrerupe procesul odata cu inchiderea frame-ului
        frame.pack(); //dimensioneaza frame-ul in conformitate cu lungimea/latimea respectiva
        frame.setVisible(true);

        //citire si afisare denumirea imaginilor (si numarul acestora din folderul respectiv)

        *//*
        System.out.println("Denumirile imaaginilor din folderul Cane");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cane");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Cavallo");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cavallo");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Elefante");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\elefante");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Farfalla");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\farfalla");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Gallina");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\gallina");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Gatto");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\gatto");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Mucca");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\mucca");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Pecora");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\pecora");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Ragno");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\ragno");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Scoiattolo");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\scoiattolo");
        System.out.println();
         *//*

    }*/

    //main console test for my neural network

    /*public static void main(String[] args) {
        RandomNumberGenerator.seed=0;

        int numberOfInputs=2;
        int numberOfOutputs=1;
        int[] numberOfHiddenNeurons= { 3 };
        IActivationFunction[] hiddenAcFnc = { new Sigmoid(1.0) } ;
        Linear outputAcFnc = new Linear(1.0);

        System.out.println("Creating Neural Netowrk...");
        NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs, numberOfHiddenNeurons,hiddenAcFnc,outputAcFnc);
        System.out.println("Neural Network created!");

        nn.print();

        double [] neuralInput = { 1.5 , 0.5 };
        double [] neuralOutput;

        System.out.println("Feeding the values ["+ String.valueOf(neuralInput[0])+" ; "+ String.valueOf(neuralInput[1])+"] to the neural network");

        nn.setInputs(neuralInput);
        nn.calc();
        neuralOutput=nn.getOutputs();

        System.out.println("Output generated:"+String.valueOf(neuralOutput[0]));

        neuralInput[0] = 1.0;
        neuralInput[1] = 2.1;

        System.out.println("Feeding the values ["+ String.valueOf(neuralInput[0])+" ; "+ String.valueOf(neuralInput[1])+"] to the neural network");

        nn.setInputs(neuralInput);
        nn.calc();
        neuralOutput=nn.getOutputs();

        System.out.println("Output generated:"+String.valueOf(neuralOutput[0]));
    }*/

    //main  DeltaRuleTest
    // example of a single Neuron Neural Network to learn how to fit a simple linear function
    public static void main(String[] args){

        RandomNumberGenerator.seed=0;

        int numberOfInputs=1;
        int numberOfOutputs=1;

        Linear outputAcFnc = new Linear(1.0);
        HyperTan htAcFnc = new HyperTan(0.85);
        System.out.println("Creating Neural Network...");
        NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs,
                //outputAcFnc);
                htAcFnc);
        System.out.println("Neural Network created!");
        nn.print();

        Double[][] _neuralDataSet = {
                {1.2 , fncTest(1.2)}
                ,   {0.3 , fncTest(0.3)}
                ,   {-0.5 , fncTest(-0.5)}
                ,   {-2.3 , fncTest(-2.3)}
                ,   {1.7 , fncTest(1.7)}
                ,   {-0.1 , fncTest(-0.1)}
                ,   {-2.7 , fncTest(-2.7)}
        };

        int[] inputColumns = {0};
        int[] outputColumns = {1};

        NeuralDataSet neuralDataSet = new NeuralDataSet(_neuralDataSet,inputColumns,outputColumns);

        System.out.println("Dataset created");
        neuralDataSet.printInput();
        neuralDataSet.printTargetOutput();

        System.out.println("Getting the first output of the neural network");

        DeltaRule deltaRule=new DeltaRule(nn,neuralDataSet
                , LearningAlgorithm.LearningMode.ONLINE);

        deltaRule.printTraining=true;
        deltaRule.setLearningRate(0.3);
        deltaRule.setMaxEpochs(1000);
        deltaRule.setGeneralErrorMeasurement(DeltaRule.ErrorMeasurement.SimpleError);
        deltaRule.setOverallErrorMeasurement(DeltaRule.ErrorMeasurement.MSE);
        deltaRule.setMinOverallError(0.00001);

        try{
            deltaRule.forward();
            neuralDataSet.printNeuralOutput();

            Double weight = nn.getOutputLayer().getWeight(0, 0);
            Double bias = nn.getOutputLayer().getWeight(1, 0);

            System.out.println("Initial weight:"+String.valueOf(weight));
            System.out.println("Initial bias:"+String.valueOf(bias));

            System.out.println("Beginning training");

            deltaRule.train();

            System.out.println("End of training");
            if(deltaRule.getMinOverallError()>=deltaRule.getOverallGeneralError()){
                System.out.println("Training succesful!");
            }
            else{
                System.out.println("Training was unsuccesful");
            }
            System.out.println("Overall Error:"
                    +String.valueOf(deltaRule.getOverallGeneralError()));
            System.out.println("Min Overall Error:"
                    +String.valueOf(deltaRule.getMinOverallError()));
            System.out.println("Epochs of training:"
                    +String.valueOf(deltaRule.getEpoch()));

            System.out.println("Target Outputs:");
            neuralDataSet.printTargetOutput();

            System.out.println("Neural Output after training:");
            deltaRule.forward();
            neuralDataSet.printNeuralOutput();

            weight = nn.getOutputLayer().getWeight(0, 0);
            bias = nn.getOutputLayer().getWeight(1, 0);

            System.out.println("Weight found:"+String.valueOf(weight));
            System.out.println("Bias found:"+String.valueOf(bias));

            Double[][] _testDataSet ={
                    {-1.7 , fncTest(-1.7) }
                    , {-1.0 , fncTest(-1.0) }
                    , {0.0 , fncTest(0.0) }
                    , {0.8 , fncTest(0.8) }
                    , {2.0 , fncTest(2.0) }
            };

            NeuralDataSet testDataSet = new NeuralDataSet(_testDataSet, inputColumns, outputColumns);

            deltaRule.setTestingDataSet(testDataSet);
            deltaRule.test();
            testDataSet.printNeuralOutput();
        }
        catch(NeuralException ne){

        }

    }

    public static double fncTest(double x){
        return 0.11*x;
    }
}