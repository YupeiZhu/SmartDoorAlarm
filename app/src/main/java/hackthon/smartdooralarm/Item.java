package hackthon.smartdooralarm;
import android.graphics.Bitmap;


public class Item {

    /* Instance fields */

    private final String uid;
    private Bitmap pic;
    private String des;

/*    private ArrayList<LoadListener> listeners;

    private ValueItemListener updater;
    private DatabaseReference database;*/

    /* Constants */

/*    private static final byte TRUE = 1;
    private static final byte FALSE = 0;

    static final String UID = "uid";
    private static final String NAME = "pic";
    private static final String DES = "des";
*/

    /* Ctors */

    public Item(String uid, Bitmap pic, String des) {

        this.uid = uid;
        this.pic = pic;
        this.des = des;

    }

    /**
     * Checks if this is equal to a given object. It will be equal if the object is another
     * Item with the same UID.
     *
     * @param obj Object to be compared.
     * @return true if this and obj represent the same object.
     *         false otherwise.
     */
    @Override
    public boolean equals( Object obj ) {

        if ( obj == null ) {
            return false;
        }

        if ( obj.getClass() != Item.class ) {
            return false;
        }

        Item ev = (Item) obj;

        return uid.equals( ev.uid );

    }

    /* Getters */
    public String getUid() {

        return uid;

    }

    public Bitmap getPic() {
        return pic;
    }

    public String getDes() {
        return des;
    }

}
