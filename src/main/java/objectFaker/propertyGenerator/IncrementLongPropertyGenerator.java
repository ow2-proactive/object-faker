package objectFaker.propertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class IncrementLongPropertyGenerator implements PropertyGenerator<Long> {

    protected long index = 0;


    public IncrementLongPropertyGenerator(){}

    public IncrementLongPropertyGenerator(long firstIndex){
        this.index = firstIndex;
    }

    @Override
    public Long generate() {
        long result = this.index;
        this.index++;
        return result;
    }
}
