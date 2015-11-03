package objectFaker.propertyGenerator;

public abstract class RandomRangePropertyGenerator<T> implements PropertyGenerator<T>{

    protected T first;

    protected T last;
    
    public RandomRangePropertyGenerator(T first, T last) {
        this.first = first;
        this.last = last;
    }
}
