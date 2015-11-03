package objectFaker.propertyGenerator;

import java.util.Date;

public class RandomDatePropertyGenerator extends RandomLongPropertyGenerator{

    public RandomDatePropertyGenerator(long start, long end) {
        super(start, end);
    }
    
    public RandomDatePropertyGenerator(Date start, Date end){
        this(start.getTime(), end.getTime());
    }
   
}
