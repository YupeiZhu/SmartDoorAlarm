package hackthon.smartdooralarm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list;
    private String[] web = {"a"};
    private Integer[] imageId = {R.mipmap.ic_launcher};
    private static final int NONSELECTED_STATE = -1;
    private TextView tvSum;
    private LinearLayout linearLayout;
    private Button btCancel, btDelete;
    private boolean isSelecting = false;
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

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, itemList);
        list = (RecyclerView)findViewById(R.id.picList);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        ListFragment listFragment = new ListFragment();
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
    }
}