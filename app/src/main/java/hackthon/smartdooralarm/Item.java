package hackthon.smartdooralarm;
import android.graphics.Bitmap;


public class Item {

    /* Instance fields */

    private final String uid;
    private Bitmap pic;
    private String des;

    /* Ctors */

    public Item(String uid, Bitmap pic, String des) {

        this.uid = uid;
        this.pic = pic;
        this.des = des;

    }

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
    public String getUid() { return uid; }

    public Bitmap getPic() {
        return pic;
    }

    public String getDes() {
        return des;
    }

}
