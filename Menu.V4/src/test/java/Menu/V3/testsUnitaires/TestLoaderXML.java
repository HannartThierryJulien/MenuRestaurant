package Menu.V3.testsUnitaires;

import Menu.V4.model.carte.Carte;
import Menu.V4.model.carte.Choix;
import Menu.V4.model.carte.Loader;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Hannart Thierry-Julien
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoaderXML {

    private static Loader loader;
    
    public TestLoaderXML() {
    }
    
    @BeforeAll
    public static void setUpClass(){
        loader = new Loader("MenuTest.XML");
    }
    
    @Test
    @Order(1)
    public void testChargerUnPlat(){
        System.out.println("\n######################################################################");
        System.out.println("##                    TEST CHARGEMENT D'UN PLAT                     ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");
        loader.chargerListePlats();
        ArrayList listePlats = Carte.getInstance().getListePlats();
        assertThat(listePlats.size()).isEqualTo(6);
        System.out.println("Chargement de la liste des plats contenus dans le XML : réussi");
        Choix objTest = (Choix) listePlats.get(0);
        assertThat(objTest.getCategorie()).isEqualTo("plat");
        assertThat(objTest.getType()).isEqualTo("Viande");
        assertThat(objTest.getId()).isEqualTo("steack250");
        assertThat(objTest.getDescription()).isEqualTo("Steack de 250 grammes");
        assertThat(objTest.getPrix()).isEqualTo(15.0);
        System.out.println("Test de chargement d'un plat : réussi");   
    }
    
    @Test
    @Order(2)
    public void testChargerUnAccompagnement(){
        System.out.println("\n######################################################################");
        System.out.println("##               TEST CHARGEMENT D'UN ACCOMPAGNEMENT                ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");
        loader.chargerListeAccompagnements();
        ArrayList listeAccompagnements = Carte.getInstance().getListeAccompagnements();
        assertThat(listeAccompagnements.size()).isEqualTo(7);
        System.out.println("Chargement de la liste d'accompagnements contenus dans le XML : réussi");
        Choix objTest = (Choix) listeAccompagnements.get(1);
        assertThat(objTest.getCategorie()).isEqualTo("accompagnement");
        assertThat(objTest.getType()).isEqualTo("Pommes de terre");
        assertThat(objTest.getId()).isEqualTo("Pommes de terre");
        assertThat(objTest.getDescription()).isEqualTo("de pommes de terre à la poêle");
        assertThat(objTest.getPrix()).isEqualTo(5.0);
        System.out.println("Test de chargement d'un accompagnement : réussi");   
    }
    
    @Test
    @Order(3)
    public void testChargerUnDessert(){
        System.out.println("\n######################################################################");
        System.out.println("##                    TEST CHARGEMENT D'UN DESSERT                  ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");
        loader.chargerListeDesserts();
        ArrayList listeDesserts = Carte.getInstance().getListeDesserts();
        assertThat(listeDesserts.size()).isEqualTo(8);
        System.out.println("Chargement de la liste de desserts contenus dans le XML : réussi");
        Choix objTest = (Choix) listeDesserts.get(0);
        assertThat(objTest.getCategorie()).isEqualTo("dessert");
        assertThat(objTest.getType()).isEqualTo("Fruits");
        assertThat(objTest.getId()).isEqualTo("Pommes");
        assertThat(objTest.getDescription()).isEqualTo("des pommes d'Ardennes");
        assertThat(objTest.getPrix()).isEqualTo(10.0);
        System.out.println("Test de chargement d'un dessert : réussi");   
    }
    
    @Test
    @Order(4)
    public void testChargerElementPrecis(){
        System.out.println("\n######################################################################");
        System.out.println("##                TEST CHARGEMENT D'UN ELEMENT PRECIS               ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");      
        Choix elementChoisi = loader.chargerElement("desserts", "Gosette pomme");
        System.out.println("Chargement d'un élement contenu dans le XML : réussi");
        assertThat(elementChoisi.getCategorie()).isEqualTo("dessert");
        assertThat(elementChoisi.getType()).isEqualTo("Patisseries");
        assertThat(elementChoisi.getId()).isEqualTo("Gosette pomme");
        assertThat(elementChoisi.getDescription()).isEqualTo("une gosette à la pomme");
        assertThat(elementChoisi.getPrix()).isEqualTo(10.0);
        System.out.println("Test de chargement d'un élement précis : réussi");  
    }
    
    @Test
    @Order(5)
    public void testChargerDonneesConnexionDB(){
        System.out.println("\n######################################################################");
        System.out.println("##          TEST CHARGEMENT DES DONNEES DE CONNEXION A LA DB        ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");
        loader = new Loader("ConnexionDB.xml");
        String driverClassName = loader.chargerDonneeConnexionDB("driverClassName");
        String connectionUrl = loader.chargerDonneeConnexionDB("connectionUrl");
        String username = loader.chargerDonneeConnexionDB("username");
        String password = loader.chargerDonneeConnexionDB("password");
        System.out.println("Chargement des données contenues dans le XML : réussi");
        assertThat(driverClassName).isEqualTo("com.mysql.cj.jdbc.Driver");
        assertThat(connectionUrl).isEqualTo("jdbc:mysql://localhost:3306/Hannart_MenuV4");
        assertThat(username).isEqualTo("root");
        assertThat(password).isEqualTo("root");
        System.out.println("Test de chargement des données de connexion à la DB : réussi\n");  
    }
    
}
