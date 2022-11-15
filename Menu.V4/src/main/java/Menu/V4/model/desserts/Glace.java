package Menu.V4.model.desserts;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Glace extends PlatDecorator {

    double prixGlace;
    String descriptionGlace;
    
    public Glace(IPlat platDecore) {
        super(platDecore);
        this.prixGlace = platDecore.getPrix();
        this.descriptionGlace = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixGlace = prix;
    }

    @Override
    public double getPrix() {
        return this.prixGlace;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionGlace += "\nAvec " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionGlace;
    }
    
}
