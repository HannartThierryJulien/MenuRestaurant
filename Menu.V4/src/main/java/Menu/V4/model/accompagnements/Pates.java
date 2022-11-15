package Menu.V4.model.accompagnements;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Pates extends PlatDecorator {

    double prixPates;
    String descriptionPates;

    public Pates(IPlat platDecore) {
        super(platDecore);
        this.prixPates = platDecore.getPrix();
        this.descriptionPates = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixPates += prix;
    }

    @Override
    public double getPrix() {
        return this.prixPates;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionPates += "\nAccompagné " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionPates;
    }
    
}
