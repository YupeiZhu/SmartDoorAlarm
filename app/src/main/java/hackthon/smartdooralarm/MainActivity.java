package hackthon.smartdooralarm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list;
    public static ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<Item>();
        for(int i=0; i< 9; i++){
            Item it = new Item(Integer.toString(i),
                    BitmapFactory.decodeResource(MainActivity.this.getResources(),
                            R.mipmap.ic_launcher), Integer.toString(i));
            itemList.add(it);
        }

        list = (RecyclerView)findViewById(R.id.picList);
        list.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter = new ItemAdapter(MainActivity.this, itemList);
        list.setAdapter(adapter);

        list.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, list ,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position){
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
}