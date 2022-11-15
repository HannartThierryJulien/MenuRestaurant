package Menu.V4.vue;

import Menu.V4.controller.GraphiquesController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class GraphiquesVue {

    public void afficherGraphique(String typeGraphique) {

        JButton jbtn24mois = new JButton("24 derniers mois");
        JButton jbtn31jours = new JButton("31 derniers jours");

        GraphiquesController objGraphiquesController = new GraphiquesController();
        ChartPanel panelGraphique24mois = objGraphiquesController.creerPanelDuGraphique(typeGraphique, jbtn24mois.getText());
        ChartPanel panelGraphique31jours = objGraphiquesController.creerPanelDuGraphique(typeGraphique, jbtn31jours.getText());

        JPanel panelGraphiques = new JPanel(new CardLayout());
        panelGraphiques.add(panelGraphique24mois, jbtn24mois.getText());
        panelGraphiques.add(panelGraphique31jours, jbtn31jours.getText());

        ActionListener jbtnEcouteur = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();
                CardLayout cardLayout = (CardLayout) (panelGraphiques.getLayout());
                cardLayout.show(panelGraphiques, aButton.getText());
            }
        };
        jbtn24mois.addActionListener(jbtnEcouteur);
        jbtn31jours.addActionListener(jbtnEcouteur);

        JPanel panelJbtn = new JPanel();
        panelJbtn.add(jbtn24mois);
        panelJbtn.add(jbtn31jours);

        JFrame frameGraphiques = new JFrame("Fenêtre de visualisation des graphiques");
        frameGraphiques.setExtendedState(JFrame.MAXIMIZED_BOTH);
        

        Container container = frameGraphiques.getContentPane();
        container.add(panelGraphiques, BorderLayout.CENTER);
        container.add(panelJbtn, BorderLayout.PAGE_END);

        frameGraphiques.setVisible(true);
    }

}
