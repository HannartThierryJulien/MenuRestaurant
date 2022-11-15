package Menu.V4.model.carte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Loader {

    public Document document;
    public Element racine;

    /**
     * Méthode servant à créer un flux
     * @param nomFichier permet de choisir quel fichier xml on souhaite lire (ex : Menu.xml, MenuTest.xml ou ConnexionDB.xml)
     */
    public Loader(String nomFichier) {
        SAXBuilder sxb = new SAXBuilder();
        
        try {
            document = sxb.build(new File(nomFichier));
        } catch (JDOMException | IOException e) {
            System.out.println("Erreur : " + e);
        }

        racine = document.getRootElement();
    }

    /**
     * Méthode permettant d'aller récupérer tout les plats situés entre les balises "plats" du fichier xml
     * Fonctionnement global :
     *      1) On trouve la balise "plats" du fichier xml et on se place au début (@elementPlats)
     *      2) On récupère les "enfants" de cette balise et on les met dans une liste (@listeTypes) (ex : Viande, Poisson, Volaille)
     *      3) On crée un iterateur sur cette liste (@iterateur1)
     *      4) On rentre dans une boucle while qui nous permettra de passer sur chaque enfants contenue dans la balise "plats"
     *      5) Pour chaque enfant, on va à nouveau récupérer les "enfants" de cette balise et on va les placer dans une
     *      nouvelle liste (@listeCategorie) (ex : steack250, cuisseDePoulet, truite, etc)
     *      6) Pour chacun de ses enfants, on va récupérer la catégorie, l'id, la description et le prix
     *      7) Ces données vont être utilisées pour créer un objet de la classe @Choix (@objChoix) qui sera ajouté à une arrayList de plats (@listePlats)
     *      8) Finalement, cette arrayList sera envoyées dans l'instance (unique) de la classe @Carte afin de pouvoir être aisément utilisée plus tard
     */
    public void chargerListePlats() {
        ArrayList<Choix> listePlats = new ArrayList();

        Element elementPlats = racine.getChildren("plats").get(0);

        List listeTypes = elementPlats.getChildren();

        Iterator iterateur1 = listeTypes.iterator();

        while (iterateur1.hasNext()) {
            Element elementCategorie = (Element) iterateur1.next();
            String type = elementCategorie.getAttributeValue("id");

            List listeCategorie = elementCategorie.getChildren();
            Iterator iterateur2 = listeCategorie.iterator();
            while (iterateur2.hasNext()) {
                Element elementPlat = (Element) iterateur2.next();
                String categorie = elementPlat.getName();
                String id = elementPlat.getAttributeValue("id");
                String description = elementPlat.getChildText("description");
                Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
                Choix objChoix = new Choix(id, categorie, type, prix, description);
                listePlats.add(objChoix);
            }
        }

        Carte carte = Carte.getInstance();
        carte.setListePlats(listePlats);
    }

    /**
     * Méthode (presque) identique à la méthode @chargerListePlats
     */
    public void chargerListeAccompagnements() {
        ArrayList<Choix> listeAccompagnements = new ArrayList();

        Element elementAccomp = racine.getChildren("accompagnements").get(0);

        List listeTypes = elementAccomp.getChildren();

        Iterator iterateur1 = listeTypes.iterator();

        while (iterateur1.hasNext()) {
            Element elementCategorie = (Element) iterateur1.next();
            String type = elementCategorie.getAttributeValue("id");

            List listeCategorie = elementCategorie.getChildren();
            Iterator iterateur2 = listeCategorie.iterator();
            while (iterateur2.hasNext()) {
                Element elementPlat = (Element) iterateur2.next();
                String categorie = elementPlat.getName();
                String id = elementPlat.getAttributeValue("id");
                String description = elementPlat.getChildText("description");
                Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
                Choix objChoix = new Choix(id, categorie, type, prix, description);
                listeAccompagnements.add(objChoix);
            }
        }

        Carte carte = Carte.getInstance();
        carte.setListeAccompagnements(listeAccompagnements);
    }

    /**
     * Méthode (presque) identique à la méthode @chargerListePlats
     */
    public void chargerListeDesserts() {
        ArrayList<Choix> listeDesserts = new ArrayList();

        Element elementDesserts = racine.getChildren("desserts").get(0);

        List listeTypes = elementDesserts.getChildren();

        Iterator iterateur1 = listeTypes.iterator();

        while (iterateur1.hasNext()) {
            Element elementCategorie = (Element) iterateur1.next();
            String type = elementCategorie.getAttributeValue("id");

            List listeCategorie = elementCategorie.getChildren();
            Iterator iterateur2 = listeCategorie.iterator();
            while (iterateur2.hasNext()) {
                Element elementPlat = (Element) iterateur2.next();
                String categorie = elementPlat.getName();
                String id = elementPlat.getAttributeValue("id");
                String description = elementPlat.getChildText("description");
                Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
                Choix objChoix = new Choix(id, categorie, type, prix, description);
                listeDesserts.add(objChoix);
            }
        }

        Carte carte = Carte.getInstance();
        carte.setListeDesserts(listeDesserts);
    }

    /**
     * Méthode basée sur les 3 méthodes précédentes, mais celle-ci permet de récupérer un élement précis du XML.
     * @param typeElement
     * @param nomElement
     * @return 
     */
    public Choix chargerElement(String typeElement, String nomElement) {
        ArrayList<Choix> listeDesserts = new ArrayList();

        Element elementPlatAccompDessert = racine.getChildren(typeElement).get(0);

        List listeTypes = elementPlatAccompDessert.getChildren();

        Iterator iterateur1 = listeTypes.iterator();

        while (iterateur1.hasNext()) {
            Element elementCategorie = (Element) iterateur1.next();
            String type = elementCategorie.getAttributeValue("id");

            List listeCategorie = elementCategorie.getChildren();
            Iterator iterateur2 = listeCategorie.iterator();
            while (iterateur2.hasNext()) {

                Element elementPlat = (Element) iterateur2.next();
                if (elementPlat.getAttributeValue("id").equals(nomElement)) {
                    String categorie = elementPlat.getName();
                    String id = elementPlat.getAttributeValue("id");
                    String description = elementPlat.getChildText("description");
                    Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
                    Choix objChoix = new Choix(id, categorie, type, prix, description);

                    return objChoix;
                }
            }
        }
        return null;
    }

    /**
     * Méthode permettant d'aller charger les données contenues dans le fichier ConnexionDB.xml.
     * @param nomDonneeACharger
     * @return 
     */
    public String chargerDonneeConnexionDB(String nomDonneeACharger) {
        String valeurDonnee = null;
        List<Element> listOfUsers = racine.getChildren("Donneess");

        for (Element userElement : listOfUsers) {
            valeurDonnee = userElement.getChildText(nomDonneeACharger);
        }
        return valeurDonnee;
    }

}
