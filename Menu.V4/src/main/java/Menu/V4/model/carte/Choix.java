package Menu.V4.model.carte;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class Choix {
    
    private final String id;
    
    private final String categorie;
    
    private final String type;
    
    private final Double prix;
    
    private final String description;

    public Choix(String id, String categorie, String type, Double prix, String description) {
        this.id = id;
        this.categorie = categorie;
        this.type = type;
        this.prix = prix;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getType() {
        return type;
    }

    public Double getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }
    
    

}
