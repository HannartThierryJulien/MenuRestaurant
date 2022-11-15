package Menu.V4.vue;

import Menu.V4.model.DB.JDBCDAO;
import Menu.V4.controller.RendererPersonnalise;
import Menu.V4.model.carte.Commande;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class ListingCommandesVue implements PropertyChangeListener {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    DefaultTableModel listTableModel;
    JTable jtableCommandes;

    private ListingCommandesVue() {
    }

    public static ListingCommandesVue getInstance() {
        return ListingCommandesHolder.INSTANCE;
    }

    private static class ListingCommandesHolder {

        private final static ListingCommandesVue INSTANCE = new ListingCommandesVue();
    }

    public void afficherListingCommandes(String typeListingChoisi) {
        Object[][] tabDonneesLignes = {};
        Object[] tabNomsColonnes = {"Date", "Numéro table", "Type", "Prix", "Facturee"};

        listTableModel = new DefaultTableModel(tabDonneesLignes, tabNomsColonnes);

        JDBCDAO objJDBCDAO = new JDBCDAO();
        ArrayList<Commande> listeCommandes = new ArrayList();
        listeCommandes = objJDBCDAO.getListeCommandes(typeListingChoisi);

        for (Commande commande : listeCommandes) {
            listTableModel.addRow(new Object[]{
                commande.getDate(),
                commande.getNumTable(),
                commande.getDescription(),
                commande.getPrix(),
                commande.isFacturee()
            });
        }

        jtableCommandes = new JTable(listTableModel) {
            @Override
            public boolean editCellAt(int ligne, int colonne, java.util.EventObject e) {
                return false;
            }
        };
        for (int i = 0; i < jtableCommandes.getColumnCount(); i++) {
            TableColumn tcol = jtableCommandes.getColumnModel().getColumn(i);
            tcol.setCellRenderer(new RendererPersonnalise());
        }
        jtableCommandes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtableCommandes.setCellEditor(null);

        JFrame frame = new JFrame("Fenêtre de listing des commandes passées");
        frame.add(new JScrollPane(jtableCommandes));
        frame.setPreferredSize(new Dimension(750, 500));
        frame.setBackground(Color.decode("#FCF8E8"));
        frame.pack();

        jtableCommandes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = jtableCommandes.getSelectedRow();
                    String[] tabValue = new String[5];
                    for (int i = 0; i < 5; i++) {
                        tabValue[i] = jtableCommandes.getValueAt(row, i).toString();
                    }
                    FacturationCommandeVue objafficherCommandeSelectionne = new FacturationCommandeVue(tabValue, row);
                }
            }
        });

        frame.setVisible(true);

        this.pcs.addPropertyChangeListener("Facturation", (PropertyChangeListener) ListingCommandesVue.getInstance());
    }

    public void updaterCelluleFacturation(int numLigneSelectionnee) {
        this.pcs.firePropertyChange("Facturation", null, numLigneSelectionnee);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Facturation")) {
            int numLigneSelectionnee = (int) evt.getNewValue();
            listTableModel.setValueAt(true, numLigneSelectionnee, 4);
            jtableCommandes.repaint();
        }
    }

}
