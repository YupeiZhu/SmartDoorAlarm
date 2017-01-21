package hackthon.smartdooralarm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Base64;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list;
    public static ArrayList<Item> itemList = new ArrayList<Item>();
    public static ArrayList<Item> deletedList;

    private GoogleApiClient client;
    private Firebase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread notification = new Thread() {
            public void run() {

                Intent notification = new Intent(MainActivity.this, MyService.class);

                startService(notification);
            }
        };
        notification.start();

        Toast.makeText(getApplicationContext(), "Please wait for loading", Toast.LENGTH_LONG).show();
        Firebase.setAndroidContext(getApplicationContext());
        fb = new Firebase("https://test-110.firebaseio.com/SB");
        /*itemList = new ArrayList<Item>();
        for(int i=0; i< 9; i++){
            Item it = new Item(Integer.toString(i),
                    BitmapFactory.decodeResource(MainActivity.this.getResources(),
                            R.mipmap.ic_launcher), Integer.toString(i));
            itemList.add(it);
        }*/
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList = new ArrayList<Item>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String base64 = data.child("image").getValue(String.class);
                    byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
                    Item newItem = new Item(data.getKey(),
                            BitmapFactory.decodeByteArray(bytes, 0, bytes.length),
                            data.child("str").getValue(String.class));
                    itemList.add(newItem);
                }


                list = (RecyclerView) findViewById(R.id.picList);
                list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ItemAdapter adapter = new ItemAdapter(MainActivity.this, itemList, fb);
                list.setAdapter(adapter);

                list.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, list,
                        new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                Dialog builder = new Dialog(MainActivity.this);
                                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                builder.getWindow().setBackgroundDrawable(
                                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        //nothing;
                                    }
                                });

                                ImageView imageView = new ImageView(MainActivity.super.getApplicationContext());
                                Bitmap img = itemList.get(position).getPic();
                                imageView.setImageBitmap(big(img));

                                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT));
                                builder.show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
                ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
                ItemTouchHelper helper = new ItemTouchHelper(callback);
                helper.attachToRecyclerView(list);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        fb.addValueEventListener(listener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    private Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = d.getWidth();
        float f = width/bitmap.getWidth();
        matrix.postScale(f,f);
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }

}