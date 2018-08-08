import java.util.ArrayList;
import java.util.HashMap;

public class Graaf {

    private ArrayList<Verdiep> verdiepen;
    private ArrayList<Pijl> pijlen;

    public Graaf(int aantalVerdiepen){
        verdiepen = new ArrayList<Verdiep>();

        for(int i=0 ; i<aantalVerdiepen; i++){
            verdiepen.add(new Verdiep(i));
        }

    }

    public void voegPijlToe(int radliftId, int van, int naar){
        pijlen.add(new Pijl(radliftId,verdiepen.get(van),verdiepen.get(naar) )  );
    }







}
