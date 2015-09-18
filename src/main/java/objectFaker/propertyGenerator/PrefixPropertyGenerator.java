package objectFaker.propertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class PrefixPropertyGenerator implements PropertyGenerator<String> {

    protected String prefix;

    protected int index = 1;

    public PrefixPropertyGenerator(String prefix){
        this.prefix = prefix;
    }

    public PrefixPropertyGenerator(String prefix, int firstIndex){
        this.prefix = prefix;
        this.index = firstIndex;
    }

    @Override
    public String generate() {
        String result = this.prefix + Integer.toString(this.index);
        this.index++;
        return result;
    }
}
