package ihm;

import controleur.Controleur;
import metier.Noeud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class PanelCreation extends JPanel implements ActionListener
{
    private Controleur ctrl;
                 
    private JButton          btnAddNode;
    private JComboBox<Noeud> cbNoeudA;
    private JComboBox<Noeud> cbNoeudB;
    private JButton          btnAddEdge;
    private JButton          btnFindShortPath;
    private JButton          btnFindAbsorbingCircuit;


    public PanelCreation(Controleur ctrl)
    {
        this.ctrl = ctrl;


        /* Création des composants */
        this.btnAddNode              = new JButton();
        this.cbNoeudA             = new JComboBox<Noeud>();
        this.cbNoeudB             = new JComboBox<Noeud>();
        this.btnAddEdge             = new JButton();
        this.btnFindShortPath        = new JButton();
        this.btnFindAbsorbingCircuit = new JButton();

        setPreferredSize(new Dimension(300, 400));

        /* Bouton ajouter noeud */
        this.btnAddNode.setText("Add node");
        this.btnAddNode.setMaximumSize  (new Dimension(100, 25));
        this.btnAddNode.setMinimumSize  (new Dimension(100, 25));
        this.btnAddNode.setPreferredSize(new Dimension(100, 25));

        /* ComboBox noeud A */
        this.cbNoeudA.setMaximumSize  (new Dimension(50, 75));
        this.cbNoeudA.setMinimumSize  (new Dimension(50, 75));
        this.cbNoeudA.setPreferredSize(new Dimension(50, 75));

        /* ComboBox noeud B */
        this.cbNoeudB.setMaximumSize  (new Dimension(50, 75));
        this.cbNoeudB.setMinimumSize  (new Dimension(50, 75));
        this.cbNoeudB.setPreferredSize(new Dimension(50, 75));

        /* Bouton ajouter arête */
        this.btnAddEdge.setText("this.btnAddEdge");
        this.btnAddEdge.setPreferredSize(new java.awt.Dimension(100, 25));

        /* Bouton trouver plus court chemin */
        this.btnFindShortPath.setText("this.btnFindShortPath");
        this.btnFindShortPath.setPreferredSize(new java.awt.Dimension(100, 25));

        /* Bouton trouver circuit absorbant */
        this.btnFindAbsorbingCircuit.setText("this.btnFindAbsorbingCircuit");
        this.btnFindAbsorbingCircuit.setPreferredSize(new java.awt.Dimension(100, 25));


        /* Positionnement des composants */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        /* Horizontal */
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(this.cbNoeudA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(this.cbNoeudB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.btnFindAbsorbingCircuit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnAddNode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnAddEdge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnFindShortPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        /* Vertical */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(this.cbNoeudB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.btnAddNode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(this.cbNoeudA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(this.btnAddEdge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(this.btnFindShortPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(this.btnFindAbsorbingCircuit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );


        /* Ajout des listeners */
        this.btnAddNode             .addActionListener(this);
        this.btnAddEdge             .addActionListener(this);
        this.btnFindShortPath       .addActionListener(this);
        this.btnFindAbsorbingCircuit.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == this.btnAddNode)
        {
            this.ctrl.addNode();
        }
        else if(ae.getSource() == this.btnAddEdge)
        {
            // TODO : faire les vérifications de séléction des noeuds dans les combobox
            this.ctrl.addEdge((Noeud)(this.cbNoeudA.getSelectedItem()), (Noeud)(this.cbNoeudB.getSelectedItem()));
        }
        else if(ae.getSource() == this.btnFindShortPath)
        {
            // TODO : faire les vérifications de séléction des noeuds dans les combobox
            this.ctrl.findShortPath((Noeud)(this.cbNoeudA.getSelectedItem()), (Noeud)(this.cbNoeudB.getSelectedItem()));
        }
        else if(ae.getSource() == this.btnFindAbsorbingCircuit)
        {
            this.ctrl.findAbsorbingCircuit();
        }
    }


    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        Color backGeneralColor  = this.ctrl.getTheme().get("background");
		Color foreGeneralColor  = this.ctrl.getTheme().get("foreground");
        Color buttonsBackground = this.ctrl.getTheme().get("buttonsBackground");

        
        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        this.btnAddNode.setBackground(buttonsBackground);
        this.btnAddNode.setForeground(foreGeneralColor);

        this.cbNoeudA.setBackground(buttonsBackground);
        this.cbNoeudA.setForeground(foreGeneralColor);

        this.cbNoeudB.setBackground(buttonsBackground);
        this.cbNoeudB.setForeground(foreGeneralColor);

        this.btnAddEdge.setBackground(buttonsBackground);
        this.btnAddEdge.setForeground(foreGeneralColor);

        this.btnFindShortPath.setBackground(buttonsBackground);
        this.btnFindShortPath.setForeground(foreGeneralColor);

        this.btnFindAbsorbingCircuit.setBackground(buttonsBackground);  
        this.btnFindAbsorbingCircuit.setForeground(foreGeneralColor);
    }
}
