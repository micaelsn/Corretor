package ppufs.corretor.classe;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by Micael on 11/08/2017.
 */

public class Dicionário extends ConjuntoDePalavras{
    private FileChannel canal;
    private List<Palavra> dicioList = new ArrayList<>();;
    private List<Palavra> listPalIgnoradas = new ArrayList<>();
    private String dicionario = "myfile.txt";
    private String ignorada = "myfile.txt";
    private File file;
    private File file2;

    public Dicionário(){

    }

    public void load(Context ctx){
        //Carregar o dicionário com todas a palavras do arquivo
        file = new File(ctx.getFilesDir(), dicionario);
        file2 = new File(ctx.getFilesDir(), ignorada);
        String palavra;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader bufferedreader = new BufferedReader(inputreader);


            while (( palavra = bufferedreader.readLine()) != null)
            {
                dicioList.add(new Palavra(palavra));
            }
            inputStream.close();
            inputreader.close();
            bufferedreader.close();
        } catch (FileNotFoundException e) {
            Log.i("Arquivo", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Stack", e.getMessage());
            e.printStackTrace();
        }

        try {
            FileInputStream inputStream2 = new FileInputStream(file2);
            InputStreamReader inputreader2 = new InputStreamReader(inputStream2);
            BufferedReader bufferedreader2 = new BufferedReader(inputreader2);


            while (( palavra = bufferedreader2.readLine()) != null)
            {
                listPalIgnoradas.add(new Palavra(palavra));
            }
            inputStream2.close();
            inputreader2.close();
            bufferedreader2.close();
        } catch (FileNotFoundException e) {
            Log.i("Arquivo", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Stack", e.getMessage());
            e.printStackTrace();
        }
    }

    public int save(Context ctx, String palavraTextView, int tipo) {
        //Salvar a palavra no arquivo
        if (tipo == 1){
            //palavra add na lista
            if (super.contains(getDicioList(), palavraTextView) == false){

                FileOutputStream outputStream;
                try {
                    outputStream = new FileOutputStream(file, true);
                    dicioList.add(new Palavra(palavraTextView));
                    palavraTextView = palavraTextView + "\n";
                    outputStream.write(palavraTextView.getBytes());
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("try", e.getMessage());
                }

                return 1;
            }

        }else {
            if (tipo == 2){
                //palavra ignorada
                if (super.contains(getListPalIgnoradas(), palavraTextView) == false){
                    //add no arquivo de ignoradas
                    FileOutputStream outputStream;
                    try {
                        outputStream = new FileOutputStream(file2, true);
                        listPalIgnoradas.add(new Palavra(palavraTextView));
                        palavraTextView = palavraTextView + "\n";
                        outputStream.write(palavraTextView.getBytes());
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("try", e.getMessage());
                    }
                    return 1;
                }
            }
        }
        //retorna confirmacao
        return 0;
    }

    public List<Palavra> getDicioList(){
        return dicioList;
    }

    public List<Palavra> getListPalIgnoradas(){
        return listPalIgnoradas;
    }

    public void remover(Palavra palavra){
        listPalIgnoradas.remove(palavra);
    }

    public int verificarEntrada(String palavra) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader bufferedreader = new BufferedReader(inputreader);

            String linha;

            while (( linha = bufferedreader.readLine()) != null)
            {
                if (linha.equals(palavra)){
                    return 1;
                } else {
                    //substituir a palavra
                    return -1;
                }
            }
            inputStream.close();
            inputreader.close();
            bufferedreader.close();
        } catch (FileNotFoundException e) {
            Log.i("Arquivo", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Stack", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
