package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.Node;


public class PanelCreation extends JPanel implements ActionListener
{
    private Controleur ctrl;
                 
    private JButton         btnAddNode;
    private JComboBox<Node> cbNodeA;
    private JComboBox<Node> cbNodeB;
    private JButton         btnAddEdge;
    private JButton         btnFindShortPath;
    private JButton         btnFindAbsorbingCircuit;


    public PanelCreation(Controleur ctrl)
    {
        this.ctrl = ctrl;


        /* Création des composants */
        this.btnAddNode              = new JButton();
        this.cbNodeA                 = new JComboBox<Node>();
        this.cbNodeB                 = new JComboBox<Node>();
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.btnFindAbsorbingCircuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnAddNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnAddEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnFindShortPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(this.cbNodeA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(this.cbNodeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        /* Vertical */
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(this.btnAddNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.cbNodeA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.cbNodeB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(this.btnAddEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(this.btnFindShortPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(this.btnFindAbsorbingCircuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        /* Ajout des listeners */
        this.btnAddNode             .addActionListener(this);
        this.btnAddEdge             .addActionListener(this);
        this.btnFindShortPath       .addActionListener(this);
        this.btnFindAbsorbingCircuit.addActionListener(this);
    }


    /**
     * Permet d'ajouter un noeud dans la JList de l'ihm
     * @param node : Node à ajouter
     */
    public void addNodeInJList(Node node)
    {
        // Ajout du noeud dans la JList
        this.cbNodeA.addItem(node);
        this.cbNodeB.addItem(node);
    }

    /**
     * Permet de supprimer un noeud de la JList de l'ihm
     * @param node : Node à supprimer
     */
    public void removeNodeInJList(Node node)
    {
        this.cbNodeA.removeItem(node);
        this.cbNodeB.removeItem(node);
    }


    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == this.btnAddNode)
        {
            this.ctrl.addNode();
            this.ctrl.majIhm();
        }
        else if(ae.getSource() == this.btnAddEdge)
        {
            if (this.cbNodeA.getSelectedItem() != null && this.cbNodeB.getSelectedItem() != null && !(((Node)this.cbNodeA.getSelectedItem()).isNeighbor((Node)this.cbNodeB.getSelectedItem())))
            {
                String cout = "";
                do
                {
                    cout = JOptionPane.showInputDialog(this.ctrl.getFramePrincipale(), "cout de l'arête : ", "Ajout d'une arête", JOptionPane.QUESTION_MESSAGE);
                    if (cout == null)
                        break;
                }while(!cout.matches("[0-9]+"));

                if (cout != null)
                {
                    this.ctrl.addEdge((Node)(this.cbNodeA.getSelectedItem()), (Node)(this.cbNodeB.getSelectedItem()), Integer.parseInt(cout));
                    this.ctrl.majIhm();
                }
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
