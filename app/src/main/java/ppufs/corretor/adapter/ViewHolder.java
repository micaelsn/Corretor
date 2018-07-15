package ppufs.corretor.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import ppufs.corretor.R;

/**
 * Created by Micael on 18/08/2017.
 */

public class ViewHolder {
    public TextView textView;
    public Button btn_add;
    public Button btn_ignorar;
    public Button btn_substituir;

    public ViewHolder(View view){
        textView = (TextView)view.findViewById(R.id.textWord);
        btn_add = (Button)view.findViewById(R.id.btn_add);
        btn_ignorar = (Button)view.findViewById(R.id.btn_ignore);
    }
}
