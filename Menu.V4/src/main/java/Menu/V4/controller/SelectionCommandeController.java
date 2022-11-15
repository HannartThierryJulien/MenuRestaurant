package Menu.V4.controller;

import Menu.V4.model.DB.JDBCDAO;
import Menu.V4.model.accompagnements.PommesDeTerre;
import Menu.V4.model.accompagnements.Riz;
import Menu.V4.model.carte.Choix;
import Menu.V4.model.carte.Loader;
import Menu.V4.model.carte.Commande;
import Menu.V4.model.desserts.Fruit;
import Menu.V4.model.desserts.Glace;
import Menu.V4.model.desserts.Patisserie;
import Menu.V4.model.desserts.PousseCafe;
import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class SelectionCommandeController {

    private static final Loader loader = new Loader("Menu.XML");
    String numTableChoisi;
    String typePlatChoisi;
    String platChoisi;
    String typeAccompChoisi;
    String accompChoisi;
    String typeDessertChoisi;
    String dessertChoisi;
    String commande;
    JDBCDAO objJDBCDAO = new JDBCDAO();
    String rafraichirOuPas;
    ArrayList<IPlat> listeMenus = new ArrayList();

    /**
     * Méthode servant à envoyer les données sélectionnées par l'utilisateur dans la classe @SelectionCommandeVue.
     * @param typeElement (Exemples : Numero de table, Plat, Accompagnement, Dessert).
     * @param typeElementChoisi correspond aux valeurs des JRadioButton de la classe @SelectionCommandeVue (Ex : Volaille, Riz, Fruits, etc).
     * @param ElementChoisi correspond aux valeurs des JComboBox de la classe @SelectionCommandeVue (Ex : carpe, Fusilly, Pommes, etc).
     * Les 3 paramètres ci-dessous vont permettre d'aller rechercher un plat précis dans le fichier XML lorsque la méthode @ajouterCommande sera exécutée.
     */
    public void envoyerElementChoisi(String typeElement, String typeElementChoisi, String ElementChoisi) {
        switch (typeElement) {
            case "NumeroTable":
                numTableChoisi = ElementChoisi;
            case "Plat":
                typePlatChoisi = typeElementChoisi;
                platChoisi = ElementChoisi;
            case "Accompagnement":
                typeAccompChoisi = typeElementChoisi;
                accompChoisi = ElementChoisi;
            case "Dessert":
                typeDessertChoisi = typeElementChoisi;
                dessertChoisi = ElementChoisi;
        }
    }

    /**
     * Méthode exécutée lorsque le JButton @boutonAjouterCommande de classe @SelectionCommandeVue est sélectionné.
     * En utilisant les classes @PlatFactory et @PlatDecorator, elle va créer un nouveau plat (@platCommande).
     * Celui-ci sera ensuite  ajouté à la commande existante (@listeMenus).
     */
    public void ajouterCommande() {

        IPlat platCommande = null;
        Choix objChoix;

        if (platChoisi != null) {
            platCommande = PlatFactory.choixPlat(typePlatChoisi);
            objChoix = loader.chargerElement("plats", platChoisi);
            platCommande.setDescription(objChoix.getDescription());
            platCommande.setPrix(objChoix.getPrix());
        }

        if (accompChoisi != null) {
            switch (typeAccompChoisi) {
                case "Frites":
                    platCommande = new Fruit(platCommande);
                case "Pâtes":
                    platCommande = new Glace(platCommande);
                case "Pommes de terre":
                    platCommande = new PommesDeTerre(platCommande);
                case "Riz":
                    platCommande = new Riz(platCommande);
            }
            objChoix = loader.chargerElement("accompagnements", accompChoisi);
            platCommande.setDescription(objChoix.getDescription());
            platCommande.setPrix(objChoix.getPrix());
        }

        if (dessertChoisi != null) {
            switch (typeDessertChoisi) {
                case "Fruits":
                    platCommande = new Fruit(platCommande);
                case "Glaces":
                    platCommande = new Glace(platCommande);
                case "Patisseries":
                    platCommande = new Patisserie(platCommande);
                case "Pousse-café":
                    platCommande = new PousseCafe(platCommande);
            }
            
            objChoix = loader.chargerElement("desserts", dessertChoisi);
            platCommande.setDescription(objChoix.getDescription());
            platCommande.setPrix(objChoix.getPrix());
        }

        listeMenus.add(platCommande);
    }

    /**
     * Méthode qui va créer un objet Commande (@objCommande) à partir des données récoltées précédemment.
     * Elle va ensuite stocker cet objet dans la base de données.
     * @return l'objet Commande (@objCommande) pour l'afficher dans le JTextArea de la classe @SelectionCommandeVue.
     */
    public Commande envoyerCommandeValidee() {
        if (!listeMenus.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            Commande objCommande = new Commande(now, Integer.parseInt(numTableChoisi), listeMenus);
            objJDBCDAO.ajouter(objCommande);
            return objCommande;
        } else {
            return null;
        }
    }

    /**
     * Méthode exécutée lorsque le JButton @boutonEnvoyerCommande de classe @SelectionCommandeVue est sélectionné.
     * Elle permet de remettre les données d'une commande à zero entre l'envoi des différentes commandes.
     * Elle permet ainsi d'éviter des "restes" de la commande précédente.
     */
    public void viderVariables() {
        typePlatChoisi = null;
        platChoisi = null;
        typeAccompChoisi = null;
        accompChoisi = null;
        typeDessertChoisi = null;
        dessertChoisi = null;
        commande = null;
        rafraichirOuPas = null;
        listeMenus.clear();
    }

}
