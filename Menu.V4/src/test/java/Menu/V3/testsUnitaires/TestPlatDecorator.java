package Menu.V3.testsUnitaires;

import Menu.V4.model.accompagnements.Frites;
import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.Viande;
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
public class TestPlatDecorator {

    @Test
    @Order(1)
    public void testDecorator() {
        System.out.println("\n######################################################################");
        System.out.println("##                     TEST CREATION PLAT DECORE                    ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");

        IPlat platViande = new Viande();
        platViande.setPrix(15);
        platViande.setDescription("Steack de 250g");

        IPlat platViandeFrites = new Frites(platViande);
        platViandeFrites.setPrix(5);
        platViandeFrites.setDescription("de frites");

        assertThat(platViandeFrites.getDescription()).isEqualTo("Steack de 250g\n" + "Accompagné de frites");
        assertThat(platViandeFrites.getPrix()).isEqualTo(20);
        System.out.println("Test de création d'un plat décoré : réussi\n");

    }
}
