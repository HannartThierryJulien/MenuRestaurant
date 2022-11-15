package Menu.V4.model.desserts;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Patisserie extends PlatDecorator {

    double prixPatisserie;
    String descriptionPatisserie;
    
    public Patisserie(IPlat platDecore) {
        super(platDecore);
        this.prixPatisserie = platDecore.getPrix();
        this.descriptionPatisserie = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixPatisserie += prix;
    }

    @Override
    public double getPrix() {
        return this.prixPatisserie;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionPatisserie += "\nAvec " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionPatisserie;
    }
    
}
