package objectFaker.propertyGenerator;

import objectFaker.propertyGenerator.RandomNumberPropertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class RandomIntPropertyGenerator extends RandomNumberPropertyGenerator<Integer> {

    public RandomIntPropertyGenerator(int first, int last){
        super(first, last);
    }


    @Override
    public Integer generate() {
        return rand.nextInt(this.last) + this.first;
    }
}
