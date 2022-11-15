package Menu.V4.model.desserts;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Fruit extends PlatDecorator {

    double prixFruit;
    String descriptionFruit;
    
    public Fruit(IPlat platDecore) {
        super(platDecore);
        this.prixFruit = platDecore.getPrix();
        this.descriptionFruit = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixFruit += prix;
    }

    @Override
    public double getPrix() {
        return this.prixFruit;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionFruit += "\nAvec " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionFruit;
    }
    
}