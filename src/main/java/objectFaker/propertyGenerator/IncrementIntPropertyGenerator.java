package objectFaker.propertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class IncrementIntPropertyGenerator implements PropertyGenerator<Integer> {


    protected int index = 0;


    public IncrementIntPropertyGenerator(){}

    public IncrementIntPropertyGenerator(int firstIndex){
        this.index = firstIndex;
    }


    @Override
    public Integer generate() {
        Integer result = this.index;
        this.index++;
        return result;
    }
}
