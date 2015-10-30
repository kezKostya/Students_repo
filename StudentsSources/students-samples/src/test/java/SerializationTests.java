import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by kkolesnichenko on 10/29/2015.
 */
public class SerializationTests {
    // Byte stream could not have come from real Elvis instance!
    private static final byte[] serializedForm = new byte[] {
             -84,  -19,    0,    5,  115,   114,    0,  5,
              69,  108,  118,  105,  115,  -124,  -26,
            -109,   51,  -61,  -12, -117,    50,    2,
               0,    1,   76,    0,   13,   102,   97,
             118,  111,  114,  105,  116,   101,   83,
             111,  110,  103,  115,  116,     0,   18,
              76,  106,   97,  118,   97,    47,  108,
              97,  110,  103,   47,   79,    98,  106,
             101,   99,  116,   59,  120,   112,  115,
             114,    0,   12,   69,  108,   118,  105,
             115,   83,  116,  101,   97,   108,  101,
             114,    0,    0,    0,    0,     0,  0,
               0,    0,    2,    0,    1,    76,  0,
               7,  112,   97,  121,  108,   111,  97,
             100,  116,    0,    7,   76,    69,  108,
             118,  105,  115,   59,  120,   112,  113,
               0,  126,    0,    2

    };



    // Returns the object with the specified serialized form
    private static byte[] serialize(Object object) throws IOException {

        ObjectOutputStream  ois=null;
        try {

            ByteArrayOutputStream ous = new ByteArrayOutputStream();
            ois = new ObjectOutputStream (ous);
            ois.writeObject(object);
            return ous.toByteArray();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        finally {
            ois.close();
        }
    }

    private static Object deserialize(byte[] sf) {
        try {
            InputStream is = new ByteArrayInputStream(sf);
            ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }



    @Ignore//ignored since test doesb't check anything, just preforms output
    @Test
    public  void testSerialization() throws IOException {

        byte[] objectBytes=serialize(Elvis.INSTANCE);
        for(int i=0;i<objectBytes.length;i++){
            System.out.print(objectBytes[i]);
            System.out.print(", ");
            if(i>0 && i%7==0)System.out.println();
        }


        System.out.println();
        System.out.println();

        for(int i=0;i<serializedForm.length;i++){
            System.out.print(serializedForm[i]);
            System.out.print(",  ");
            if(i>0 && i%7==0)System.out.println();
        }
    }

    @Test
    public  void testDeserialization() throws IOException {
        Object elvis =  deserialize(serializedForm);

        Elvis impersonator = ElvisStealer.impersonator;
        System.out.println(elvis);
        System.out.println(impersonator);
        Assert.assertFalse(elvis.toString().equals( impersonator.toString()));
    }

    public static class SomeObject implements Serializable{
        private String serializableVale;
        private transient String transientValue;

        public String getSerializableVale() {
            return serializableVale;
        }

        public void setSerializableVale(String serializableVale) {
            this.serializableVale = serializableVale;
        }

        public String getTransientValue() {
            return transientValue;
        }

        public void setTransientValue(String transientValue) {
            this.transientValue = transientValue;
        }
    }

    @Test
    public void testTransient() throws IOException {
        SomeObject object=new SomeObject();
        object.setSerializableVale("value1");
        object.setTransientValue("value2");
        byte[] objectBytes=serialize(object);

        SomeObject object2 = (SomeObject) deserialize(objectBytes);
        Assert.assertTrue(object.getSerializableVale().equals(object2.getSerializableVale()));
        Assert.assertTrue(object2.getTransientValue()==null);//transient fields aren't serialaizable

    }
}
