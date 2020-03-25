package neuralnet.math;

import java.util.Random;

public class RandomNumberGenerator { //random double precision numbers according to a seed
    public static long seed=0; //used for a random number generation
    public static Random r;

    public static double GenerateNext(){
        if(r==null){
            r=new Random(seed);
        }
        return r.nextDouble(); //returns a newly random number
    }

    public static void setSeed(long seed){
        seed=seed;
        r.setSeed(seed);
    }
}
