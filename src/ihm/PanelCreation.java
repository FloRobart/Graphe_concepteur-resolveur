package ihm;

import controleur.Controleur;
import metier.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.StackWalker.Option;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
    private JComboBox<Node> cbNodeA;
    private JComboBox<Node> cbNodeB;
    private JButton          btnAddEdge;
    private JButton          btnFindShortPath;
    private JButton          btnFindAbsorbingCircuit;


    public PanelCreation(Controleur ctrl)
    {
        this.ctrl = ctrl;


        /* Création des composants */
        this.btnAddNode              = new JButton();
        this.cbNodeA                = new JComboBox<Node>();
        this.cbNodeB                = new JComboBox<Node>();
        this.btnAddEdge              = new JButton();
        this.btnFindShortPath        = new JButton();
        this.btnFindAbsorbingCircuit = new JButton();

        this.setPreferredSize(new Dimension(300, 400));

        /* Bouton ajouter noeud */
        this.btnAddNode.setText("Add node");
        this.btnAddNode.setMaximumSize  (new Dimension(100, 25));
        this.btnAddNode.setMinimumSize  (new Dimension(100, 25));
        this.btnAddNode.setPreferredSize(new Dimension(100, 25));

        /* ComboBox noeud A */
        this.cbNodeA.setMaximumSize  (new Dimension(50, 75));
        this.cbNodeA.setMinimumSize  (new Dimension(50, 75));
        this.cbNodeA.setPreferredSize(new Dimension(50, 75));

        /* ComboBox noeud B */
        this.cbNodeB.setMaximumSize  (new Dimension(50, 75));
        this.cbNodeB.setMinimumSize  (new Dimension(50, 75));
        this.cbNodeB.setPreferredSize(new Dimension(50, 75));

        /* Bouton ajouter arête */
        this.btnAddEdge.setText("this.btnAddEdge");
        this.btnAddEdge.setPreferredSize(new Dimension(100, 25));

        /* Bouton trouver plus court chemin */
        this.btnFindShortPath.setText("this.btnFindShortPath");
        this.btnFindShortPath.setPreferredSize(new Dimension(100, 25));

        /* Bouton trouver circuit absorbant */
        this.btnFindAbsorbingCircuit.setText("this.btnFindAbsorbingCircuit");
        this.btnFindAbsorbingCircuit.setPreferredSize(new Dimension(100, 25));


        /* Positionnement des composants */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        /* Horizontal */
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(this.cbNodeA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(this.cbNodeB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(this.cbNodeB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.btnAddNode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(this.cbNodeA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
            if (this.cbNodeA.getSelectedItem() != null && this.cbNodeB.getSelectedItem() != null)
            {
                String cout = "";
                do
                {
                    cout = JOptionPane.showInputDialog(this.ctrl.getFramePrincipale(), "cout de l'arête : ", "Ajout d'une arête", JOptionPane.QUESTION_MESSAGE);
                }while(!cout.matches("[0-9]+"));

                this.ctrl.addEdge((Node)(this.cbNodeA.getSelectedItem()), (Node)(this.cbNodeB.getSelectedItem()), Integer.parseInt(cout));
            }
        }
        else if(ae.getSource() == this.btnFindShortPath)
        {
            if (this.cbNodeA.getSelectedItem() != null && this.cbNodeB.getSelectedItem() != null)
                this.ctrl.findShortPath((Node)(this.cbNodeA.getSelectedItem()), (Node)(this.cbNodeB.getSelectedItem()));
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

        this.cbNodeA.setBackground(buttonsBackground);
        this.cbNodeA.setForeground(foreGeneralColor);

        this.cbNodeB.setBackground(buttonsBackground);
        this.cbNodeB.setForeground(foreGeneralColor);

        this.btnAddEdge.setBackground(buttonsBackground);
        this.btnAddEdge.setForeground(foreGeneralColor);

        this.btnFindShortPath.setBackground(buttonsBackground);
        this.btnFindShortPath.setForeground(foreGeneralColor);

        this.btnFindAbsorbingCircuit.setBackground(buttonsBackground);  
        this.btnFindAbsorbingCircuit.setForeground(foreGeneralColor);
    }
}
