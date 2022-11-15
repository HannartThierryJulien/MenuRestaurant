package Menu.V4.model.carte;

import java.util.ArrayList;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Carte {
    
    private ArrayList<Choix> listePlats;
    private ArrayList<Choix> listeAccompagnements;
    private ArrayList<Choix> listeDesserts;
    
    private Carte(){
    }
    
    public static Carte getInstance(){
        return CarteHolder.INSTANCE;
    }
    
    private static class CarteHolder {
        private final static Carte INSTANCE = new Carte();
    }

    public ArrayList<Choix> getListePlats() {
        return listePlats;
    }

    public void setListePlats(ArrayList<Choix> listePlats) {
        this.listePlats = listePlats;
    }

    public ArrayList<Choix> getListeAccompagnements() {
        return listeAccompagnements;
    }

    public void setListeAccompagnements(ArrayList<Choix> listeAccompagnements) {
        this.listeAccompagnements = listeAccompagnements;
    }

    public ArrayList<Choix> getListeDesserts() {
        return listeDesserts;
    }

    public void setListeDesserts(ArrayList<Choix> listeDesserts) {
        this.listeDesserts = listeDesserts;
    }
    
    
}
