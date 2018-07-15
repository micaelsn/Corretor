package ppufs.corretor.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ppufs.corretor.R;
import ppufs.corretor.classe.Dicionário;
import ppufs.corretor.classe.Palavra;

import static ppufs.corretor.R.raw.dicionario;

public class DicionarioAdapter extends BaseAdapter {
    private Context context;
    private List<Palavra> palavras;
    private Dicionário dicionário;

    public DicionarioAdapter(Context context, List<Palavra> palavras,
                             Dicionário dicionário){
        this.context = context;
        this.palavras = palavras;
        this.dicionário = dicionário;
    }

    @Override
    public int getCount() {
        return palavras.size();
    }

    @Override
    public Object getItem(int position) {
        return palavras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder myHolder;
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.act_palavra, viewGroup, false);
        myHolder = new ViewHolder(row);
        row.setTag(myHolder);

        myHolder.textView.setText(palavras.get(position).getPalavra());

        myHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(palavras.get(position).getPalavra(), position);
            }
        });

        myHolder.btn_ignorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ignorar(palavras.get(position).getPalavra(), position);
            }
        });
        return row;
    }

    public void add(String palavra, int position){
        if (dicionário.save(context, palavra, 1) == 1){
            dicionário.getDicioList().add(new Palavra(palavra));
                Toast.makeText(context, palavra + " foi Adicionada",
                        Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, palavra + " já está Adicionada",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ignorar(String palavra, int position){
        if (dicionário.save(context, palavra, 2) == 1){
            dicionário.remover(new Palavra(palavra));
            notifyDataSetChanged();
            Toast.makeText(context, palavra + " foi Ignorada",
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, palavra + " já está Ignorada",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
