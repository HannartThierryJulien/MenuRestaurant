package Menu.V4.model.plats;

/**
 *
 * @author Hannart Thierry-Julien
 */
public abstract class PlatDecorator implements IPlat {

    IPlat platDecore;

    public PlatDecorator(IPlat platDecore) {
        this.platDecore = platDecore;
    }

}
