package objectFaker.propertyGenerator;


/**
 * Created by Sandrine on 16/09/2015.
 */
public class RandomLongPropertyGenerator extends RandomNumberPropertyGenerator<Long> {

    public RandomLongPropertyGenerator(long first, long last){
        super(first, last);
    }


    @Override
    public Long generate() {
        double num = Math.random() * (this.last - this.first);
        long result = (long) num;
        return result + this.first;
    }
}
