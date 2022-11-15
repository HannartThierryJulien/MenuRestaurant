package Menu.V4.model.desserts;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class PousseCafe extends PlatDecorator {

    double prixPousseCafe;
    String descriptionPousseCafe;
    
    public PousseCafe(IPlat platDecore) {
        super(platDecore);
        this.prixPousseCafe = platDecore.getPrix();
        this.descriptionPousseCafe = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixPousseCafe += prix;
    }

    @Override
    public double getPrix() {
        return this.prixPousseCafe;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionPousseCafe += "\nAvec " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionPousseCafe;
    }
    
}
