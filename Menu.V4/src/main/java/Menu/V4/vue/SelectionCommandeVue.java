package Menu.V4.vue;

import Menu.V4.controller.SelectionCommandeController;
import Menu.V4.model.carte.Carte;
import Menu.V4.model.carte.Choix;
import Menu.V4.model.carte.Loader;
import Menu.V4.model.carte.Commande;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.Border;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class SelectionCommandeVue extends JFrame {

    SelectionCommandeController objController = new SelectionCommandeController();
    ParametrageVisuel objParametrageVisuel = new ParametrageVisuel();

    JTextArea txtConsole = new JTextArea();

    String numTableChoisi;
    String typePlatChoisi;
    String platChoisi;
    String typeAccompChoisi;
    String accompChoisi;
    String typeDessertChoisi;
    String dessertChoisi;

    Font fontSousTitres = new Font("Serif", Font.BOLD, 25);
    Color couleurSousPanels = objParametrageVisuel.getCouleur2();
    Color couleurTxt = objParametrageVisuel.getCouleur4();
    Border bordureSousPanels = BorderFactory.createEmptyBorder();

    private static final Loader loader = new Loader("Menu.XML");
    Choix objTest;

    public SelectionCommandeVue() {

        this.setTitle("Fenêtre de commande");
        this.setLayout(new GridLayout(1, 2));

        JPanel panelPrincipalGauche = new JPanel();
        panelPrincipalGauche.setLayout(new GridLayout(6, 1, 20, 20));
        panelPrincipalGauche.setBackground(objParametrageVisuel.getCouleur1());
        this.add(panelPrincipalGauche);

        JPanel panelPrincipalDroit = new JPanel();
        panelPrincipalDroit.setBackground(Color.LIGHT_GRAY);
        this.add(panelPrincipalDroit);

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : MenuBar
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCommandes = new JMenu("Commandes");
        menuBar.add(menuCommandes);

        ActionListener menuBarCommandesEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();
                ListingCommandesVue.getInstance().afficherListingCommandes(aButton.getText());

            }
        };
        JMenuItem menuItemListeJour = new JMenuItem("Liste du jour");
        menuItemListeJour.addActionListener(menuBarCommandesEcouteur);
        menuCommandes.add(menuItemListeJour);
        JMenuItem menuItemListe = new JMenuItem("Liste");
        menuItemListe.addActionListener(menuBarCommandesEcouteur);
        menuCommandes.add(menuItemListe);

        JMenu menuStatistiques = new JMenu("Statistiques");
        menuBar.add(menuStatistiques);

        ActionListener menuStatEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();
                GraphiquesVue objGraphiquesVue = new GraphiquesVue();
                objGraphiquesVue.afficherGraphique(aButton.getText());
            }
        };
        JMenuItem menuStatAnnee = new JMenuItem("Graphique gains/jours");
        menuStatAnnee.addActionListener(menuStatEcouteur);
        menuStatistiques.add(menuStatAnnee);
        JMenuItem menuStatMois = new JMenuItem("Graphique commandes/jours");
        menuStatMois.addActionListener(menuStatEcouteur);
        menuStatistiques.add(menuStatMois);

        this.setJMenuBar(menuBar);

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL DE DEBUT
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JPanel panelDebut = new JPanel();
        panelDebut.setBackground(couleurSousPanels);
        panelPrincipalGauche.add(panelDebut);
        panelDebut.setLayout(new GridBagLayout());

        JLabel titreDebut1 = new JLabel();
        panelDebut.add(titreDebut1);
        titreDebut1.setText("Commande table ");
        titreDebut1.setFont(new Font("Serif", Font.BOLD, 30));
        titreDebut1.setForeground(couleurTxt);

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL SELECTION DU NUMERO DE LA TABLE
         * **********************************************************************************************
         * *********************************************************************************************
         */
        String tabNumeroTable[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox comboBoxNumeroTable = new JComboBox(tabNumeroTable);
        JLabel tableChoisie = new JLabel();

        comboBoxNumeroTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comboBoxNumeroTable.getSelectedItem().equals("")) {
                    titreDebut1.setText("Commande table " + comboBoxNumeroTable.getSelectedItem().toString());
                    numTableChoisi = comboBoxNumeroTable.getSelectedItem().toString();
                }
            }
        });

        tableChoisie.setText("Sélection de la table");
        tableChoisie.setFont(fontSousTitres);
        tableChoisie.setForeground(couleurTxt);

        JPanel sousPanel2Bis = new JPanel();
        JPanel sousPanel2BisBis = new JPanel();

        JPanel panelNumTable = new JPanel();
        panelNumTable.setBackground(couleurSousPanels);
        panelPrincipalGauche.add(panelNumTable);
        panelNumTable.setLayout(new GridLayout(2, 1, 10, 10));
        panelNumTable.add(sousPanel2Bis);
        sousPanel2Bis.setBackground(couleurSousPanels);
        panelNumTable.add(sousPanel2BisBis);
        sousPanel2BisBis.setBackground(couleurSousPanels);

        sousPanel2Bis.add(tableChoisie);
        sousPanel2BisBis.add(comboBoxNumeroTable);

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL SELECTION DU PLAT
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JPanel panelPlat = new JPanel();
        panelPlat.setBackground(couleurSousPanels);
        panelPlat.setLayout(new GridLayout(3, 1, 10, 10));
        panelPrincipalGauche.add(panelPlat);

        JPanel sousPanelPlat1 = new JPanel();
        sousPanelPlat1.setBackground(couleurSousPanels);
        panelPlat.add(sousPanelPlat1);

        JLabel platTitre = new JLabel();
        platTitre.setText("Sélection du plat");
        platTitre.setFont(fontSousTitres);
        platTitre.setForeground(couleurTxt);
        sousPanelPlat1.add(platTitre);

        JPanel sousPanelPlat2 = new JPanel();
        sousPanelPlat2.setBackground(couleurSousPanels);
        panelPlat.add(sousPanelPlat2);

        //creation d'un JRadioButton par type de plat
        String[] tabNomJRBPlat = {"Viande", "Volaille", "Poisson"};
        JRadioButton tabJRBPlat[] = new JRadioButton[3];
        for (int i = 0; i < tabJRBPlat.length; i++) {
            tabJRBPlat[i] = new JRadioButton(tabNomJRBPlat[i]);
            tabJRBPlat[i].setForeground(couleurTxt);
        }

        //Chargement des plats contenu dans la carte XML
        loader.chargerListePlats();
        ArrayList<Choix> listePlats = Carte.getInstance().getListePlats();
        List<String> listViande = new ArrayList<>();
        List<String> listVolaille = new ArrayList<>();
        List<String> listPoisson = new ArrayList<>();
        //Ajout des plats dans une liste en fonction de leur type (voir @tabNomJRBPlat)
        for (Choix n : listePlats) {
            if (n.getType().equals(tabJRBPlat[0].getText())) {
                listViande.add(n.getId());
            } else if (n.getType().equals(tabJRBPlat[1].getText())) {
                listVolaille.add(n.getId());
            } else if (n.getType().equals(tabJRBPlat[2].getText())) {
                listPoisson.add(n.getId());
            }
        }

        JPanel sousPanelPlat3 = new JPanel();
        sousPanelPlat3.setBackground(couleurSousPanels);
        panelPlat.add(sousPanelPlat3);

        //Creation d'un ecouteur pour les JRadioButton. Il permet de remplir la @comboBoxPlat en fonction du JRadioButton sélectionné.
        JComboBox comboBoxPlat = new JComboBox();
        ActionListener platEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                comboBoxPlat.setEnabled(true);
                if (aButton.getText().equals(tabJRBPlat[0].getText())) {
                    comboBoxPlat.setModel(new DefaultComboBoxModel(listViande.toArray()));
                } else if (aButton.getText().equals(tabJRBPlat[1].getText())) {
                    comboBoxPlat.setModel(new DefaultComboBoxModel(listVolaille.toArray()));
                } else {
                    comboBoxPlat.setModel(new DefaultComboBoxModel(listPoisson.toArray()));
                }
                typePlatChoisi = aButton.getText();
            }
        };
        //Creation d'un ecouteur pour la @comboBoxPlat. Il permet d'obtenir le nom du plat sélectionné, ce dernier sera transmis au controller lorsque la commande sera envoyée.
        comboBoxPlat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                platChoisi = comboBoxPlat.getSelectedItem().toString();
            }
        });
        sousPanelPlat3.add(comboBoxPlat);

        //Parametrage des JRadioButton
        ButtonGroup bGPlat = new ButtonGroup();
        for (JRadioButton jrb : tabJRBPlat) {
            jrb.setBackground(couleurSousPanels);
            jrb.setForeground(couleurTxt);
            jrb.addActionListener(platEcouteur);
            bGPlat.add(jrb);
            sousPanelPlat2.add(jrb);
        }

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL SELECTION DE L'ACCOMPAGNEMENT
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JPanel panelAccompagnement = new JPanel();
        panelAccompagnement.setBackground(couleurSousPanels);
        panelAccompagnement.setLayout(new GridLayout(3, 1, 10, 10));
        panelPrincipalGauche.add(panelAccompagnement);

        JPanel sousPanelAccompagnement1 = new JPanel();
        sousPanelAccompagnement1.setBackground(couleurSousPanels);
        panelAccompagnement.add(sousPanelAccompagnement1);

        JLabel accompagnementTitre = new JLabel();
        accompagnementTitre.setText("Sélection de l'accompagnement");
        accompagnementTitre.setFont(fontSousTitres);
        accompagnementTitre.setForeground(couleurTxt);
        sousPanelAccompagnement1.add(accompagnementTitre);

        JPanel sousPanelAccompagnement2 = new JPanel();
        sousPanelAccompagnement2.setBackground(couleurSousPanels);
        panelAccompagnement.add(sousPanelAccompagnement2);

        //creation d'un JRadioButton par type d'accompagnement
        String[] tabNomJRBAccomp = {"Pommes de terre", "Pâtes", "Riz"};
        JRadioButton tabJRBAccomp[] = new JRadioButton[3];
        for (int i = 0; i < tabJRBAccomp.length; i++) {
            tabJRBAccomp[i] = new JRadioButton(tabNomJRBAccomp[i]);
            tabJRBAccomp[i].setForeground(couleurTxt);
        }

        //Chargement des accompagnements contenu dans la carte XML
        loader.chargerListeAccompagnements();
        ArrayList<Choix> listeAccomp = Carte.getInstance().getListeAccompagnements();
        List<String> listPommesDeTerre = new ArrayList<>();
        List<String> listPates = new ArrayList<>();
        List<String> listRiz = new ArrayList<>();
        //Ajout des accompagnements dans une liste en fonction de leur type (voir @tabNomJRBAccomp)
        for (Choix n : listeAccomp) {
            if (n.getType().equals(tabJRBAccomp[0].getText())) {
                listPommesDeTerre.add(n.getId());
            } else if (n.getType().equals(tabJRBAccomp[1].getText())) {
                listPates.add(n.getId());
            } else if (n.getType().equals(tabJRBAccomp[2].getText())) {
                listRiz.add(n.getId());
            }
        }

        JPanel sousPanelAccompagnement3 = new JPanel();
        sousPanelAccompagnement3.setBackground(couleurSousPanels);
        panelAccompagnement.add(sousPanelAccompagnement3);

        //Creation d'un ecouteur pour les JRadioButton. Il permet de remplir la @comboBoxAccompagnement en fonction du JRadioButton sélectionné.
        JComboBox comboBoxAccompagnement = new JComboBox();
        ActionListener AccompagnementEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                comboBoxAccompagnement.setEnabled(true);
                if (aButton.getText().equals(tabJRBAccomp[0].getText())) {
                    comboBoxAccompagnement.setModel(new DefaultComboBoxModel(listPommesDeTerre.toArray()));
                } else if (aButton.getText().equals(tabJRBAccomp[1].getText())) {
                    comboBoxAccompagnement.setModel(new DefaultComboBoxModel(listPates.toArray()));
                } else {
                    comboBoxAccompagnement.setModel(new DefaultComboBoxModel(listRiz.toArray()));
                }
                typeAccompChoisi = aButton.getText();
            }
        };
        //Creation d'un ecouteur pour la @comboBoxAccompagnement. Il permet d'obtenir le nom de l'accompagnement sélectionné, ce dernier sera transmis au controller lorsque la commande sera envoyée.
        comboBoxAccompagnement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accompChoisi = comboBoxAccompagnement.getSelectedItem().toString();
            }
        });
        sousPanelAccompagnement3.add(comboBoxAccompagnement);

        //Parametrage des JRadioButton
        ButtonGroup bGAccomp = new ButtonGroup();
        for (JRadioButton jrb : tabJRBAccomp) {
            jrb.setBackground(couleurSousPanels);
            jrb.setForeground(couleurTxt);
            jrb.addActionListener(AccompagnementEcouteur);
            bGAccomp.add(jrb);
            sousPanelAccompagnement2.add(jrb);
        }

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL SELECTION DU DESSERT
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JPanel panelDessert = new JPanel();
        panelDessert.setBackground(couleurSousPanels);
        panelDessert.setLayout(new GridLayout(3, 1, 10, 10));
        panelPrincipalGauche.add(panelDessert);

        JPanel sousPanelDessert1 = new JPanel();
        sousPanelDessert1.setBackground(couleurSousPanels);
        panelDessert.add(sousPanelDessert1);

        JLabel dessertTitre = new JLabel();
        dessertTitre.setText("Sélection du dessert");
        dessertTitre.setFont(fontSousTitres);
        dessertTitre.setForeground(couleurTxt);
        sousPanelDessert1.add(dessertTitre);

        JPanel sousPanelDessert2 = new JPanel();
        sousPanelDessert2.setBackground(couleurSousPanels);
        panelDessert.add(sousPanelDessert2);

        //creation d'un JRadioButton par type de dessert
        String[] tabNomJRBDessert = {"Fruits", "Patisseries", "Pousse-café", "Glaces"};
        JRadioButton tabJRBDessert[] = new JRadioButton[4];
        for (int i = 0; i < tabJRBDessert.length; i++) {
            tabJRBDessert[i] = new JRadioButton(tabNomJRBDessert[i]);
            tabJRBDessert[i].setForeground(couleurTxt);
        }

        //Chargement des desserts contenu dans la carte XML
        loader.chargerListeDesserts();
        ArrayList<Choix> listeDesserts = Carte.getInstance().getListeDesserts();
        List<String> listFruits = new ArrayList<>();
        List<String> listPatisseries = new ArrayList<>();
        List<String> listPousseCafe = new ArrayList<>();
        List<String> listGlaces = new ArrayList<>();
        //Ajout des desserts dans une liste en fonction de leur type (voir @tabNomJRBDessert)
        for (Choix n : listeDesserts) {
            if (n.getType().equals(tabJRBDessert[0].getText())) {
                listFruits.add(n.getId());
            } else if (n.getType().equals(tabJRBDessert[1].getText())) {
                listPatisseries.add(n.getId());
            } else if (n.getType().equals(tabJRBDessert[2].getText())) {
                listPousseCafe.add(n.getId());
            } else if (n.getType().equals(tabJRBDessert[3].getText())) {
                listGlaces.add(n.getId());
            }
        }

        JPanel sousPanelDessert3 = new JPanel();
        sousPanelDessert3.setBackground(couleurSousPanels);
        panelDessert.add(sousPanelDessert3);

        //Creation d'un ecouteur pour les JRadioButton. Il permet de remplir la @comboBoxDessert en fonction du JRadioButton sélectionné.
        JComboBox comboBoxDessert = new JComboBox();
        ActionListener dessertEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                comboBoxDessert.setEnabled(true);
                if (aButton.getText().equals(tabJRBDessert[0].getText())) {
                    comboBoxDessert.setModel(new DefaultComboBoxModel(listFruits.toArray()));
                } else if (aButton.getText().equals(tabJRBDessert[1].getText())) {
                    comboBoxDessert.setModel(new DefaultComboBoxModel(listPatisseries.toArray()));
                } else if (aButton.getText().equals(tabJRBDessert[2].getText())) {
                    comboBoxDessert.setModel(new DefaultComboBoxModel(listPousseCafe.toArray()));
                } else {
                    comboBoxDessert.setModel(new DefaultComboBoxModel(listGlaces.toArray()));
                }
                typeDessertChoisi = aButton.getText();
            }
        };
        //Creation d'un ecouteur pour la @comboBoxDessert. Il permet d'obtenir le nom du plat sélectionné, ce dernier sera transmis au controller lorsque la commande sera envoyée.
        comboBoxDessert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dessertChoisi = comboBoxDessert.getSelectedItem().toString();
            }
        });
        sousPanelDessert3.add(comboBoxDessert);

        //Parametrage des JRadioButton
        ButtonGroup bGDessert = new ButtonGroup();
        for (JRadioButton jrb : tabJRBDessert) {
            jrb.setBackground(couleurSousPanels);
            jrb.setForeground(couleurTxt);
            jrb.addActionListener(dessertEcouteur);
            bGDessert.add(jrb);
            sousPanelDessert2.add(jrb);
        }

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL DE FIN
         * **********************************************************************************************
         * *********************************************************************************************
         */
        JPanel panelFin = new JPanel();
        panelFin.setBackground(couleurSousPanels);
        panelFin.setLayout(new GridBagLayout());
        panelPrincipalGauche.add(panelFin);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JButton boutonAjouterCommande = new JButton("Ajouter une commande");
        boutonAjouterCommande.setBackground(objParametrageVisuel.getCouleur3());
        boutonAjouterCommande.setPreferredSize(new Dimension(180, 50));
        panelFin.add(boutonAjouterCommande, constraints);

        JButton boutonEnvoyerCommande = new JButton("Envoyer la commande");
        boutonEnvoyerCommande.setBackground(objParametrageVisuel.getCouleur3());
        boutonEnvoyerCommande.setPreferredSize(new Dimension(180, 50));
        panelFin.add(boutonEnvoyerCommande, constraints);

        JButton boutonDecocher = new JButton("Décocher tout");
        boutonDecocher.setBackground(objParametrageVisuel.getCouleur3());
        boutonDecocher.setPreferredSize(new Dimension(180, 50));
        panelFin.add(boutonDecocher, constraints);

        ActionListener jbtnAjouterCommandeEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (comboBoxNumeroTable.getSelectedItem().equals("")) {
                    txtConsole.append("\n###### Veuillez sélectionner un numéro de table ######\n");
                } else {
                    if (platChoisi == null) {
                        txtConsole.append("\n###### Veuillez sélectionner un plat ######\n");
                    } else {
                        objController.envoyerElementChoisi("NumeroTable", "", numTableChoisi);
                        objController.envoyerElementChoisi("Plat", typePlatChoisi, platChoisi);
                        objController.envoyerElementChoisi("Accompagnement", typeAccompChoisi, accompChoisi);
                        objController.envoyerElementChoisi("Dessert", typeDessertChoisi, dessertChoisi);

                        objController.ajouterCommande();

                        comboBoxNumeroTable.setEnabled(false);

                        bGPlat.clearSelection();
                        platChoisi = null;
                        comboBoxPlat.setEnabled(false);

                        bGAccomp.clearSelection();
                        accompChoisi = null;
                        comboBoxAccompagnement.setEnabled(false);

                        bGDessert.clearSelection();
                        dessertChoisi = null;
                        comboBoxDessert.setEnabled(false);
                    }
                }
            }
        };
        boutonAjouterCommande.addActionListener(jbtnAjouterCommandeEcouteur);

        ActionListener jbtnEnvoyerCommandeEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Commande commande = objController.envoyerCommandeValidee();
                if (commande != null) {
                    if (txtConsole != null) {
                        txtConsole.append("\n");
                    }
                    txtConsole.append(commande.toString());
                    objController.viderVariables();
                    boutonDecocher.doClick();

                    comboBoxNumeroTable.setEnabled(true);
                }
            }
        };
        boutonEnvoyerCommande.addActionListener(jbtnEnvoyerCommandeEcouteur);

        ActionListener boutonDecocherEcouteur = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                titreDebut1.setText("Commande table ");
                numTableChoisi = null;
                comboBoxNumeroTable.setSelectedIndex(0);

                bGPlat.clearSelection();
                platChoisi = null;
                comboBoxPlat.setEnabled(false);

                bGAccomp.clearSelection();
                accompChoisi = null;
                comboBoxAccompagnement.setEnabled(false);

                bGDessert.clearSelection();
                dessertChoisi = null;
                comboBoxDessert.setEnabled(false);
            }
        };
        boutonDecocher.addActionListener(boutonDecocherEcouteur);

        /**
         *
         *
         *
         *
         *
         * **********************************************************************************************
         * **********************************************************************************************
         * PARTIE : PANEL PRINCIPAL DROIT
         * **********************************************************************************************
         * *********************************************************************************************
         */
        txtConsole.setEditable(false);
        txtConsole.setFont(new Font("Serif", Font.BOLD, 15));
        txtConsole.setBackground(objParametrageVisuel.getCouleur1());
        txtConsole.setForeground(objParametrageVisuel.getCouleur4());
        txtConsole.setBorder(BorderFactory.createLineBorder(objParametrageVisuel.getCouleur1(), 20));

        //on place @txtConsole dans un scroll pane pour pouvoir une scrollbar si nécessaire
        JScrollPane scrollPaneTxtConsole = new JScrollPane(txtConsole);
        scrollPaneTxtConsole.setBorder(null);

        panelPrincipalDroit.setLayout(new BorderLayout());
        panelPrincipalDroit.add(scrollPaneTxtConsole);
        panelPrincipalDroit.setBorder(BorderFactory.createLineBorder(objParametrageVisuel.getCouleur1(), 20));
        panelPrincipalDroit.setBackground(objParametrageVisuel.getCouleur3());

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void dispose() {
        int a = JOptionPane.showConfirmDialog(this, "Êtes vous sûr de vouloir quitter ?", "Fenêtre de confirmation", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            System.exit(0);
            //Note: don't forget to call super.dispose() as this releases the screen resources!
            dispose();
        }
    }

}
