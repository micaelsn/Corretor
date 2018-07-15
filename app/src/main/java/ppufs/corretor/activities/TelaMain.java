package ppufs.corretor.activities;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ppufs.corretor.R;
import ppufs.corretor.adapter.DicionarioAdapter;
import ppufs.corretor.classe.Dicionário;
import ppufs.corretor.classe.Palavra;

public class TelaMain extends AppCompatActivity implements View.OnClickListener{
    private EditText palavraEditText;
    private Button btn_load;
    private ListView listView;
    private Dicionário dicionário;
    private DicionarioAdapter adapter;
    private List<Palavra> palavrasContidasNoDicio = new ArrayList<>();
    private List<Palavra> palavrasDesconhecidas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dicionário = new Dicionário();

        dicionário.load(getApplicationContext());

        palavraEditText = (EditText)findViewById(R.id.edt_txt_word);
        btn_load = (Button) findViewById(R.id.btn_load);
        listView = (ListView)findViewById(R.id.listWords);

        adapter = new DicionarioAdapter(getApplicationContext(), palavrasDesconhecidas, dicionário);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                palavrasDesconhecidas.clear();
                String[] texto = palavraEditText.getText().toString().split(" ");
                if ( palavraEditText.getText().toString() != "" ||
                        palavraEditText.getText().toString() != " "){
                    for(int i = 0; i < texto.length; i++)
                    {
                        int resultado = comparar(texto[i]);

                        if (resultado == -1)
                        {
                            if (!dicionário.contains(dicionário.getListPalIgnoradas(),
                                    texto[i])){
                                palavrasDesconhecidas.add(new Palavra(texto[i]));
                            }
                        }
                        else
                        {
                            //if (resultado == )
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public int comparar(String palavraDigitada){
        for(int i = 0; i < dicionário.getDicioList().size(); i++ ){
            if (dicionário.getDicioList().get(i).getPalavra().equals(palavraDigitada)){
                return 1;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View view) {

    }
}
