import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by kkolesnichenko on 10/29/2015.
 */
public class Elvis implements Serializable {
    private static final long serialVersionUID = -8870240565519414478l;
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }
    private String[] favoriteSongs =
            { "Hound Dog", "Heartbreak Hotel" };
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    public String toString(){
        return "Elvis favourite song:"+Arrays.toString(favoriteSongs);
    }
}
