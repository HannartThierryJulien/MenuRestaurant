package Menu.V4.vue;

import Menu.V4.model.DB.JDBCDAO;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Hannart Thierry-Julien
 */
//FacturationCommande
public class FacturationCommandeVue {

    ParametrageVisuel objParametrageVisuel = new ParametrageVisuel();

    public FacturationCommandeVue(String[] tabDonneesCommande, int numLigneSelectionnee) {

        JFrame frame = new JFrame("Fenêtre détail d'une commande");

        JDialog jdialog = new JDialog(frame, "Facturation d'une commande", true);
        jdialog.setSize(400, 400);
        jdialog.setLocationRelativeTo(null);
        jdialog.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
        

        JPanel panelDebut = new JPanel();
        panelDebut.setBackground(objParametrageVisuel.getCouleur2());
        jdialog.add(panelDebut, BorderLayout.PAGE_START);

        JLabel labelPanelDebut = new JLabel("Commande du " + tabDonneesCommande[0]);
        labelPanelDebut.setForeground(objParametrageVisuel.getCouleur4());
        panelDebut.add(labelPanelDebut);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(objParametrageVisuel.getCouleur1());
        jdialog.add(panelCentre, BorderLayout.CENTER);

        JTextArea jtxtarea = new JTextArea();
        jtxtarea.setText("************************************************************\n"
                + "TABLE n°" + tabDonneesCommande[1] + "  -  " + tabDonneesCommande[0]
                + "\n" + tabDonneesCommande[2]
                + "Prix : " + tabDonneesCommande[3]
                + "\nFacturée : " + tabDonneesCommande[4]
                + "\n************************************************************\n");
        jtxtarea.setBackground(objParametrageVisuel.getCouleur1());
        jtxtarea.setForeground(objParametrageVisuel.getCouleur4());
        jtxtarea.setEditable(false);
        panelCentre.add(jtxtarea);

        JPanel panelFin = new JPanel();
        panelFin.setBackground(objParametrageVisuel.getCouleur2());
        jdialog.add(panelFin, BorderLayout.PAGE_END);

        JButton jbtnFacturer = new JButton("Appuyez pour facturer");
        jbtnFacturer.setBackground(objParametrageVisuel.getCouleur3());
        panelFin.add(jbtnFacturer);
        if (tabDonneesCommande[4].equals("true")) {
            jbtnFacturer.setEnabled(false);
        }
        ActionListener jbuttonPanel3Ecouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDBCDAO studi = new JDBCDAO();
                studi.facturerCommande(tabDonneesCommande[0]);
                ListingCommandesVue.getInstance().updaterCelluleFacturation(numLigneSelectionnee);
                jdialog.dispose();
            }
        };
        jbtnFacturer.addActionListener(jbuttonPanel3Ecouteur);
        
        jdialog.setVisible(true);

    }

}
