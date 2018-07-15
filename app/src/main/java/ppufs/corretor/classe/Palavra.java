package ppufs.corretor.classe;

/**
 * Created by Micael on 11/08/2017.
 */

public class Palavra {
    private String palavra;
    public static final int TAM = 100;

    public Palavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
