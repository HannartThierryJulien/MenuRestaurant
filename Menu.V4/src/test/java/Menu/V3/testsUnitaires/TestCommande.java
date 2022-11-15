package Menu.V3.testsUnitaires;

import Menu.V4.model.carte.Commande;
import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.Poisson;
import Menu.V4.model.plats.Viande;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Hannart Thierry-Julien
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCommande {

    @Test
    @Order(1)
    public void testCreerCommande() {
        System.out.println("\n######################################################################");
        System.out.println("##                   TEST CREATION D'UNE COMMANDE                   ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");

        LocalDateTime datetime = LocalDateTime.of(2022, Month.JULY, 20, 19, 30, 40);
        ArrayList<IPlat> listeMenus = new ArrayList();
        Poisson plat1 = new Poisson();
        plat1.setPrix(15);
        plat1.setDescription("Aile de raie");
        Viande plat2 = new Viande();
        plat2.setPrix(22);
        plat2.setDescription("Steack de 250g");
        listeMenus.add(plat1);
        listeMenus.add(plat2);

        Commande objCommande = new Commande(datetime, 4, listeMenus);

        assertThat(objCommande.getDate()).isEqualTo(Timestamp.valueOf(datetime));
        assertThat(objCommande.getNumTable()).isEqualTo(4);
        assertThat(objCommande.getDescription()).isEqualTo("**********************\n" + "Aile de raie\n" + "**********************\n"
                + "Steack de 250g\n" + "**********************\n");
        assertThat(objCommande.getPrix()).isEqualTo(37);
        System.out.println("Test de création d'une commande : réussi\n");
    }

}
