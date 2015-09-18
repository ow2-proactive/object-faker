package objectFaker.propertyGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Sandrine on 18/09/2015.
 */
public class ByteArrayPropertyGenerator implements PropertyGenerator<byte[]> {


    protected PropertyGenerator<? extends Serializable> contentGenerator;


    public ByteArrayPropertyGenerator(PropertyGenerator<? extends Serializable> contentGenerator){
        this.contentGenerator = contentGenerator;
    }

    @Override
    public byte[] generate() {
        Object obj = this.contentGenerator.generate();
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out)){
            objOut.writeObject(obj);
            return out.toByteArray();
        }
        catch(IOException e){
            return new byte[]{};
        }
    }
}
