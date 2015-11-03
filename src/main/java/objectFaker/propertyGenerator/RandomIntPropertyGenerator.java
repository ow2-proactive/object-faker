package objectFaker.propertyGenerator;

import java.util.Random;

import objectFaker.propertyGenerator.RandomNumberPropertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class RandomIntPropertyGenerator extends RandomNumberPropertyGenerator<Integer> {

    protected Random rand;
    
    public RandomIntPropertyGenerator(int first, int last){
        super(first, last);
        this.rand = new Random();
    }


    @Override
    public Integer generate() {
        return rand.nextInt(this.last) + this.first;
    }
}
