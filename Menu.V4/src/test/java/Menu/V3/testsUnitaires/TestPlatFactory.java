package Menu.V3.testsUnitaires;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatFactory;
import Menu.V4.model.plats.Poisson;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Hannart Thierry-Julien
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPlatFactory {

    String[] tabTypePlat = {"Poisson", "Viande", "Volaille"};

    public TestPlatFactory() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testCreerPlatPoisson() {
        System.out.println("\n######################################################################");
        System.out.println("##           TEST CREATION d'UN PLAT AVEC LE PLATFACTORY            ##");
        System.out.println("##                                                                  ##");
        System.out.println("######################################################################");

        IPlat plat1 = PlatFactory.choixPlat("Poisson");
        plat1.setPrix(15);
        plat1.setDescription("Aile de raie");
        
        Poisson platPoisson = new Poisson();
        platPoisson.setPrix(15);
        platPoisson.setDescription("Aile de raie");
        
        assertThat(plat1.getPrix()).isEqualTo(platPoisson.getPrix());
        assertThat(plat1.getDescription()).isEqualTo(platPoisson.getDescription());
        System.out.println("Test de création d'un plat avec le platfactory : réussi\n");
    }
}
