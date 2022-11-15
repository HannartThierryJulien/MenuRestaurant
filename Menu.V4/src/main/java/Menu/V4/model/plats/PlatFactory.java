package Menu.V4.model.plats;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class PlatFactory {
    public static IPlat choixPlat(String platChoisi) {
        switch (platChoisi) {
            case "Viande":
                return new Viande();
            case "Poisson":
                return new Poisson();
            case "Volaille":
                return new Volaille();
            default:
                return null;
        }
    }
}
