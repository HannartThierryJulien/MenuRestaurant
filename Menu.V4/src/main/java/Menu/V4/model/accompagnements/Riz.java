package Menu.V4.model.accompagnements;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Riz extends PlatDecorator {

    double prixRiz;
    String descriptionRiz;

    public Riz(IPlat platDecore) {
        super(platDecore);
        this.prixRiz = platDecore.getPrix();
        this.descriptionRiz = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixRiz += prix;
    }

    @Override
    public double getPrix() {
        return this.prixRiz;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionRiz += "\nAccompagné " + type;
    }   

    @Override
    public String getDescription() {
        return this.descriptionRiz;
    }
    
}
