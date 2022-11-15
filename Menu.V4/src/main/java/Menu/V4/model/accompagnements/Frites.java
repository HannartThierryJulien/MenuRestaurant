package Menu.V4.model.accompagnements;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Frites extends PlatDecorator {

    double prixFrites;
    String descriptionFrites;
    
    public Frites(IPlat platDecore) {
        super(platDecore);
        this.prixFrites = platDecore.getPrix();
        this.descriptionFrites = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public final void setPrix(double prix) {
        this.prixFrites += prix;
    }

    @Override
    public double getPrix() {
        return this.prixFrites;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionFrites += "\nAccompagné " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionFrites;
    }
    
}
