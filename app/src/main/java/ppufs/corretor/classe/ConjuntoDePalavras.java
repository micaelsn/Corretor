package ppufs.corretor.classe;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Micael on 11/08/2017.
 */

public class ConjuntoDePalavras {

    public ConjuntoDePalavras() {
    }
    //adicionar palavra no arquivo
    public static ByteBuffer add(Palavra palavra) {
        ByteBuffer buf = ByteBuffer.allocate(Palavra.TAM);

        if ( palavra.getPalavra().getBytes().length < Palavra.TAM){
            buf.put(palavra.getPalavra().getBytes());
            buf.flip();
            return buf;
        }
        return null;
    }
    //capturar palavra
    public boolean contains(List<Palavra> dicioList,
                                  String palavraTextView){

        for(int i = 0; i < dicioList.size(); i++){
            if (dicioList.get(i).getPalavra().equals(palavraTextView)){
                return true;
            }
        }
        return false;
    }
}
