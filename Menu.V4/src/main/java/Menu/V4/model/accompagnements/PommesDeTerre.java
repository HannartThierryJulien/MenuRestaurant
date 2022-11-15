package Menu.V4.model.accompagnements;

import Menu.V4.model.plats.IPlat;
import Menu.V4.model.plats.PlatDecorator;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class PommesDeTerre extends PlatDecorator {

    double prixPommesDeTerre;
    String descriptionPommesDeTerre;

    public PommesDeTerre(IPlat platDecore) {
        super(platDecore);
        this.prixPommesDeTerre = platDecore.getPrix();
        this.descriptionPommesDeTerre = platDecore.getDescription();
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prixPommesDeTerre += prix;
    }

    @Override
    public double getPrix() {
        return this.prixPommesDeTerre;
    }

    @Override
    public void setDescription(String type) {
        this.descriptionPommesDeTerre += "\nAccompagné " + type;
    }

    @Override
    public String getDescription() {
        return this.descriptionPommesDeTerre;
    }

}
