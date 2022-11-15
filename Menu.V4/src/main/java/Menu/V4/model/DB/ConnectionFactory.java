package Menu.V4.model.DB;

import Menu.V4.model.carte.Loader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    Loader loader = new Loader("ConnexionDB.xml");
    String driverClassName = loader.chargerDonneeConnexionDB("driverClassName");
    String connectionUrl = loader.chargerDonneeConnexionDB("connectionUrl");
    String username = loader.chargerDonneeConnexionDB("username");
    String password = loader.chargerDonneeConnexionDB("password");

    private static ConnectionFactory objConnectionFactory = null;

    private ConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erreur driver mySql : " + e);
        }
    }

    /**
     * Cette méthode permet d'obtenir une connection avec la base de données.
     * @return l'objet de connection.
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, username, password);
        return conn;
    }

    /**
     * Permet de mettre en place un singleton.
     * @return l'instance de la classe @ConnectionFactory.
     */
    public static ConnectionFactory getInstance() {
        if (objConnectionFactory == null) {
            objConnectionFactory = new ConnectionFactory();
        }
        return objConnectionFactory;
    }
    
}
