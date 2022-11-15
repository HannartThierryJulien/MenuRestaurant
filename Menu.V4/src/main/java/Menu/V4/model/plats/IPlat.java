package Menu.V4.model.plats;

/**
 *
 * @author Hannart Thierry-Julien
 */
public interface IPlat {
    
    public String getPreparation();
    
    public void setPrix(double prix);
    
    public double getPrix();
    
    public void setDescription(String description);
    
    public String getDescription();
}
