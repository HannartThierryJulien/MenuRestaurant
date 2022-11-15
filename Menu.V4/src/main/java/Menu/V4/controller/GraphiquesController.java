package Menu.V4.controller;

import Menu.V4.model.DB.JDBCDAO;
import Menu.V4.model.carte.Commande;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class GraphiquesController {
    
    /**
     * Méthode permettant de créer un panel (ChartPanel) contenant un graphique (JFreeChart).
     * @param typeGraphique contient le nom du type de graphique qui est demandé (ex : "Graphique gains/jours").
     * @param dureeGraphique permet de savoir s'il faut les données des 31 derniers jours ou des 24 derniers mois.
     * @return le panel contenant le graphique demandé.
     */
    public ChartPanel creerPanelDuGraphique(String typeGraphique, String dureeGraphique) {
        DefaultCategoryDataset donneesGraph = new DefaultCategoryDataset();
        
        HashMap<String, String> hashmapCommandes = new HashMap<>();
        hashmapCommandes = preparerDonneesDuGraphique(typeGraphique, dureeGraphique);
        
        String nomAxeOrdonnee;
        if (typeGraphique.equals("Graphique gains/jours")) {
            nomAxeOrdonnee = "Montant";
        } else {
            nomAxeOrdonnee = "Nombre de commandes";
        }
        
        if (dureeGraphique.equals("24 derniers mois")) {
            for (Map.Entry mapentry : hashmapCommandes.entrySet()) {
                donneesGraph.addValue(Double.parseDouble(mapentry.getValue().toString()), mapentry.getKey().toString(), mapentry.getKey().toString());
            }
        } else if (dureeGraphique.equals("31 derniers jours")) {
            for (Map.Entry mapentry : hashmapCommandes.entrySet()) {
                donneesGraph.addValue(Double.parseDouble(mapentry.getValue().toString()), mapentry.getKey().toString(), mapentry.getKey().toString());
            }
        }
        
        JFreeChart graphique = ChartFactory.createBarChart(typeGraphique,
                "Jour/Mois", 
                nomAxeOrdonnee,
                donneesGraph,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        ChartPanel panel = new ChartPanel(graphique);
        return panel;
    }
    
    /**
     * Méthode qui importe les commandes contenues dans la DB, les trie et les retourne.
     * Elle est appelée dans la méthode @creerPanelDuGraphique.
     * @param typeGraphique (même chose que dans la méthode @creerPanelDuGraphique).
     * @param dureeGraphique (même chose que dans la méthode @creerPanelDuGraphique).
     * @return l'objet @hashmapCommandes qui contient les données nécessaires à la création d'un JFreeChart.
     */
    public HashMap preparerDonneesDuGraphique(String typeGraphique, String dureeGraphique) {

        //https://jenkov.com/tutorials/java-internationalization/simpledateformat.html
//        SimpleDateFormat hourformatter = new SimpleDateFormat("HH mm");
//        SimpleDateFormat dayformatter = new SimpleDateFormat("dd");
        SimpleDateFormat dayMonthFormatter = new SimpleDateFormat("dd/MM");
//        SimpleDateFormat monthformatter = new SimpleDateFormat("MM");
//        SimpleDateFormat monthLetterformatter = new SimpleDateFormat("MMMM");
//        SimpleDateFormat yearformatter = new SimpleDateFormat("YYYY");
//        SimpleDateFormat monthCCCformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
//        /*new SimpleDateFormat("MMMMM");*/
//        SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("dd/MM");

        JDBCDAO objJDBCDAO = new JDBCDAO();
        ArrayList<Commande> menus = new ArrayList();
        if (dureeGraphique.equals("24 derniers mois")) {
            menus = objJDBCDAO.getListeCommandes("BarChart 24 derniers mois");
        } else if (dureeGraphique.equals("31 derniers jours")) {
            menus = objJDBCDAO.getListeCommandes("BarChart 31 derniers jours");
        }

        HashMap<String, String> hashmapCommandes = new HashMap<>();

        if (typeGraphique.equals("Graphique gains/jours")) {
            for (Commande str : menus) {
                if (!hashmapCommandes.containsKey(dayMonthFormatter.format((java.util.Date) str.getDate()))) {
                    hashmapCommandes.put(dayMonthFormatter.format((java.util.Date) str.getDate()), "0");
                }
            }
            for (Commande str : menus) {
                for (Map.Entry mapentry : hashmapCommandes.entrySet()) {
                    if (dayMonthFormatter.format((java.util.Date) str.getDate()).equals(mapentry.getKey())) {
                        double additionPrixCommandes = Double.parseDouble(mapentry.getValue().toString()) + str.getPrix();
                        hashmapCommandes.replace(mapentry.getKey().toString(), String.valueOf(additionPrixCommandes));
                    }
                }
            }
        } else if (typeGraphique.equals("Graphique commandes/jours")) {
            for (Commande str : menus) {
                if (!hashmapCommandes.containsKey(dayMonthFormatter.format((java.util.Date) str.getDate()))) {
                    hashmapCommandes.put(dayMonthFormatter.format((java.util.Date) str.getDate()), "0");
                }
            }
            for (Commande str : menus) {
                for (Map.Entry mapentry : hashmapCommandes.entrySet()) {
                    if (dayMonthFormatter.format((java.util.Date) str.getDate()).equals(mapentry.getKey())) {
                        double comptageNbrCommandes = Double.parseDouble(mapentry.getValue().toString()) + 1;
                        hashmapCommandes.replace(mapentry.getKey().toString(), String.valueOf(comptageNbrCommandes));
                    }
                }
            }
        }

        return hashmapCommandes;
    }
    
}
