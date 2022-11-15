package Menu.V4.model.carte;

import Menu.V4.model.plats.IPlat;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public final class Commande {

    LocalDateTime date;
    int numTable;
    ArrayList<IPlat> listeMenus = new ArrayList();
    String description;
    double prix;
    boolean facturee; //par defaut une commande n'est pas facturée

    public Commande() {
    }

    public Commande(LocalDateTime date, int numTable, ArrayList<IPlat> listeMenus) {
        this.date = date;
        this.numTable = numTable;
        this.listeMenus = listeMenus;
        this.description = creerDescriptionCommande();
        this.prix = creerPrixCommande();
        this.facturee = false;
    }

    public Timestamp getDate() {
        Timestamp sqlNow = Timestamp.valueOf(date);
        return sqlNow;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isFacturee() {
        return facturee;
    }

    public void setFacturee(boolean facturee) {
        this.facturee = facturee;
    }

    public ArrayList<IPlat> getListeMenus() {
        return listeMenus;
    }

    public void setListeMenus(ArrayList<IPlat> listeMenus) {
        this.listeMenus = listeMenus;
    }
    
    public String creerDescriptionCommande() {
        Iterator<IPlat> iterator = this.listeMenus.iterator();
        while (iterator.hasNext()) {
            IPlat plat = iterator.next();
            if (this.description == null) {
                this.description = "**********************\n" + plat.getDescription() + "\n**********************\n";
//            } else if (!iterator.hasNext()) {
//                this.description += plat.getType();
            } else {
                this.description += plat.getDescription() + "\n**********************\n";
            }
        }

        return this.description;
    }
    
    public double creerPrixCommande(){
        for (IPlat plat : this.listeMenus) {
            this.prix += plat.getPrix();
        }
        
        return this.prix;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat monthCCCformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        String facturee = null;
        if (this.isFacturee() == false) {
            facturee = "Non";
        } else {
            facturee = "Oui";
        }
        
        return ("************************************************************\n"
                + "TABLE n°" + this.getNumTable() + "  -  " + monthCCCformatter.format((java.util.Date) this.getDate())
                + "\n" + this.getDescription()
                + "Prix : " + this.getPrix() 
                + "\nFacturée : " + facturee
                + "\n************************************************************\n");
    }

}
