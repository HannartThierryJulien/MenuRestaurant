package Menu.V4.model.DB;

/**
 *
 * @author Hannart Thierry-Julien
 */
import Menu.V4.model.carte.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class JDBCDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public JDBCDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    /**
     * Cette méthode permet d'ajouter une commande dans la base de données.
     * @param objCommande est l'objet commande qui va être ajouté.
     */
    public void ajouter(Commande objCommande) {
        try {
            String query = "INSERT INTO Commande(Commande_Date, Commande_NumTable, Commande_type, Commande_prixTotal, Commande_facturee) VALUES(?,?,?,?,?)";
            connection = getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, objCommande.getDate());
            preparedStatement.setInt(2, objCommande.getNumTable());
            preparedStatement.setString(3, objCommande.getDescription());
            preparedStatement.setDouble(4, objCommande.getPrix());
            preparedStatement.setBoolean(5, objCommande.isFacturee());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur mySql : " + e);
        }
    }

    /**
     * Cette méthode permet de récupérer une liste d'objets commande.
     * @param choix défini quel type de SELECT va être exécuté sur la base de données.
     * @return une arraylist d'objets commande. Cette arraylist est le résultat de la query qui vient d'être faite.
     */
    public ArrayList<Commande> getListeCommandes(String choix) {
        ArrayList listeResultatQuery = new ArrayList();
        String query = null;
        switch (choix) {
            case "Liste du jour":
                query = "SELECT Commande_Date, Commande_NumTable, Commande_type, Commande_prixTotal, Commande_facturee FROM Commande WHERE DATE(Commande_Date) = CURDATE();";
                break;
            case "Liste":
                query = "SELECT Commande_Date, Commande_NumTable, Commande_type, Commande_prixTotal, Commande_facturee FROM Commande;";
                break;
            case "BarChart 24 derniers mois":
                query = "SELECT * FROM COMMANDE WHERE Commande_Date BETWEEN (CURRENT_DATE() - INTERVAL 2 YEAR) AND NOW();";
                break;
            case "BarChart 31 derniers jours":
                query = "SELECT * FROM COMMANDE WHERE Commande_Date BETWEEN (CURRENT_DATE() - INTERVAL 31 DAY) AND NOW();";
                break;
        }
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultatStatement = preparedStatement.executeQuery();
            while (resultatStatement.next()) {
                Commande objCommande = new Commande();
                objCommande.setDate(resultatStatement.getTimestamp("Commande_Date").toLocalDateTime());
                objCommande.setNumTable(resultatStatement.getInt("Commande_NumTable"));
                objCommande.setDescription(resultatStatement.getString("Commande_type"));
                objCommande.setPrix(resultatStatement.getDouble("Commande_prixTotal"));
                objCommande.setFacturee(resultatStatement.getBoolean("Commande_facturee"));
                listeResultatQuery.add(objCommande);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur mySql : " + ex);
        }
        return listeResultatQuery;
    }

    /**
     * Cette méthode permet de changer dans la base de données une commande non facturée en une commande facturée.
     * @param date est le paramètre qui va nous permettre de retrouver une commande précise dans la base de données.
     */
    public void facturerCommande(String date) {
        try {
            String query = "UPDATE Commande SET Commande_facturee = true where Commande_Date LIKE (?);";
            connection = getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date.substring(0, date.length() - 2));

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur mySql : " + e);
        }
    }

}
