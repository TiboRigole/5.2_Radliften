import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int aantalTestGevallen = Integer.parseInt(sc.nextLine());

        for(int testGevalId=0 ; testGevalId<aantalTestGevallen; testGevalId++){

            String[] eersteLijn = sc.nextLine().split(" ");

            int aantalVerdiepingen = Integer.parseInt(eersteLijn[0]);
            Graaf graaf =new Graaf(aantalVerdiepingen);


            int aantalRadliften = Integer.parseInt(eersteLijn[1]);

            for(int radliftId =1 ; radliftId<aantalRadliften+1; radliftId++){

                String[] radliftLijn = sc.nextLine().split(" ");
                //verwerking van de radliftlijn

                int onderVerdiep = Integer.parseInt(radliftLijn[0]);
                int bovenVerdiep = Integer.parseInt(radliftLijn[1]);
                int stap = Integer.parseInt(radliftLijn[2]);

                //hiermee de juiste pijlen genereren
                ArrayList<FakePijl> fakePijlen = FakePijl.genereerPijlen(onderVerdiep, bovenVerdiep, stap);

                for(FakePijl fp : fakePijlen){
                    graaf.voegPijlToe(radliftId,fp.getVan(), fp.getNaar());
                }

            }

            String[] laatsteLijn = sc.nextLine().split(" ");
            int bronVerdiepId = Integer.parseInt(laatsteLijn[0]);
            int doelVerdiepId = Integer.parseInt(laatsteLijn[1]);

            //einde van de input


            //nu nog questie van dijkstra toe te passen
            DijkstraAlgoritme dijkstra = new DijkstraAlgoritme(graaf);

            dijkstra.execute(graaf.getVerdiepen().get(bronVerdiepId));

            LinkedList<Verdiep> pad = dijkstra.getPath(graaf.getVerdiepen().get(doelVerdiepId));
            ArrayList<Pijl> gebruiktePijlen = dijkstra.getPijlen(pad);
            System.out.println("klaarder dan klaar");









        }//einde verwerking van 1 testgeval


        //ArrayList<FakePijl> fp = FakePijl.genereerPijlen(1,3,1);

        System.out.println("klaar");

    }
}
