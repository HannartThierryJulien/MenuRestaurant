package Menu.V4.model.plats;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Poisson implements IPlat {

    double prix;
    String description;

    public Poisson() {
    }

    public Poisson(String description) {
        this.description = description;
    }

    @Override
    public String getPreparation() {
        throw new UnsupportedOperationException("Aucune action n'est encore prévue pour cette méthode.");
    }

    @Override
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public void setDescription(String description) {
        this.description=description;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
