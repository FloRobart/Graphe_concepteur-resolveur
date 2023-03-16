package ihm.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.InputEvent;

import controleur.Controleur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	/* Menus */
	private JMenu menuFichiers;
	private JMenu menuPreferences;
	private JMenu menuAide;

	/* Préférences */
	private JMenuItem menuiFichiersCharger;
	private JMenuItem menuiFichiersSauvegarder;

	private JMenu     menuiPreferencesThemes;
	private JMenuItem menuiPreferencesThemesClair;
	private JMenuItem menuiPreferencesThemesSombre;
	private JMenuItem menuiPreferencesThemesDark;
	private JMenuItem menuiPreferencesThemesPink;

	private JMenuItem menuiAideAPropos;


	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;


		/*=========================*/
		/* Création des composants */
		/*=========================*/
		/*----------*/
		/* Fichiers */
		/*----------*/
		this.menuFichiers = new JMenu("Fichiers");
		this.menuFichiers.setMnemonic('F');

		/* Charger */
		this.menuiFichiersCharger = new JMenuItem("Charger");
		this.menuiFichiersCharger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));	

		/* Sauvegarder */
		this.menuiFichiersSauvegarder = new JMenuItem("Sauvegarder");
		this.menuiFichiersSauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));


		/*-------------*/
		/* Préférences */
		/*-------------*/
		this.menuPreferences = new JMenu("Préférences");
		this.menuPreferences.setMnemonic('P');

		/* Thèmes */
		this.menuiPreferencesThemes       = new JMenu    ("Thèmes ");

		/* Clair, Sombre, Dark */
		this.menuiPreferencesThemesClair  = new JMenuItem("Clair" );
		this.menuiPreferencesThemesSombre = new JMenuItem("Sombre");
		this.menuiPreferencesThemesDark   = new JMenuItem("Dark"  );
		this.menuiPreferencesThemesPink   = new JMenuItem("Pink"  );


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide = new JMenu("Aide");
		this.menuAide.setMnemonic('A');
		this.menuiAideAPropos = new JMenuItem("Aide");


		/*=======================*/
		/* Ajouts des composants */
		/*=======================*/
		/*----------*/
		/* Fichiers */
		/*----------*/
		this.menuFichiers.add(this.menuiFichiersCharger);
		this.menuFichiers.add(this.menuiFichiersSauvegarder);


		/*------------*/
		/* Préférence */
		/*------------*/
		/* Thèmes prédéfinie */
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesClair);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesSombre);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesDark);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesPink);

		this.menuPreferences.add(this.menuiPreferencesThemes);


		/* Ajout de tout à la JMenuBar */
		this.add(menuFichiers);
		this.add(menuPreferences);


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.add(this.menuiAideAPropos);
		this.add(menuAide);


		/*============================*/
		/* Activations des composants */
		/*============================*/
		/*-------------*/
		/* Préférences */
		/*-------------*/
		/* Fichier */
		this.menuiFichiersCharger    .addActionListener(this);
		this.menuiFichiersSauvegarder.addActionListener(this);

		/* Thèmes */
		this.menuiPreferencesThemesClair .addActionListener(this);
		this.menuiPreferencesThemesSombre.addActionListener(this);
		this.menuiPreferencesThemesDark  .addActionListener(this);
		this.menuiPreferencesThemesPink  .addActionListener(this);

		/* Aide */
		this.menuiAideAPropos.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			/* Fichier */
			if (e.getSource() == this.menuiFichiersCharger)
			{
				JFileChooser fc = new JFileChooser("./bin/donnees/graphes/");
				fc.showOpenDialog(this.ctrl.getFramePrincipale());
				File file = fc.getSelectedFile();
				if (file != null)
				{
					this.ctrl.chargerGraphe(file);
					this.ctrl.majIhm();
				}
			}

			if (e.getSource() == this.menuiFichiersSauvegarder)
			{
				JFileChooser fc = new JFileChooser("./bin/donnees/graphes/");
				fc.showSaveDialog(this.ctrl.getFramePrincipale());
				File file = fc.getSelectedFile();
				if (file != null)
					this.ctrl.sauvegarderGraphe(file);
			}
			/* Préférences */
			if (e.getSource() == this.menuiPreferencesThemesClair)
				this.ctrl.changerTheme("clair");
			
			if (e.getSource() == this.menuiPreferencesThemesSombre)
				this.ctrl.changerTheme("sombre");

			if (e.getSource() == this.menuiPreferencesThemesDark)
				this.ctrl.changerTheme("dark");

			if (e.getSource() == this.menuiPreferencesThemesPink)
				this.ctrl.changerTheme("pink");

			/* Aide */
			if (e.getSource() == this.menuiAideAPropos)
			{
				// ouvre le fichier d'aide
				try { Desktop.getDesktop().open(new File("./A_LIRE.txt")); } catch(Exception ignored) {}
			}
		}
	}

	
	/**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
		Color backGeneralColor = this.ctrl.getTheme().get("background");
		Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

		/*-------------------------*/
		/* La Menu Barre elle même */
		/*-------------------------*/
		this.setBackground(backGeneralColor);
		this.setForeground(foreGeneralColor);


		/*----------*/
		/* Fichiers */
		/*----------*/
		/* Fichiers */
		this.menuFichiers.setBackground(backGeneralColor);
		this.menuFichiers.setForeground(foreGeneralColor);

		/* Charger */
		this.menuiFichiersCharger.setBackground(backGeneralColor);
		this.menuiFichiersCharger.setForeground(foreGeneralColor);

		/* Sauvegarder */
		this.menuiFichiersSauvegarder.setBackground(backGeneralColor);
		this.menuiFichiersSauvegarder.setForeground(foreGeneralColor);


		/*------------*/
		/* Préférence */
		/*------------*/
		/* Préférence */
		this.menuPreferences.setBackground(backGeneralColor);
		this.menuPreferences.setForeground(foreGeneralColor);

		/* Thèmes */
		this.menuiPreferencesThemes      .setOpaque(true);
		this.menuiPreferencesThemes      .setBackground(backGeneralColor);
		this.menuiPreferencesThemes      .setForeground(foreGeneralColor);

		/* Clair */
		this.menuiPreferencesThemesClair .setBackground(backGeneralColor);
		this.menuiPreferencesThemesClair .setForeground(foreGeneralColor);

		/* Sombre */
		this.menuiPreferencesThemesSombre.setBackground(backGeneralColor);
		this.menuiPreferencesThemesSombre.setForeground(foreGeneralColor);

		/* Dark */
		this.menuiPreferencesThemesDark  .setBackground(backGeneralColor);
		this.menuiPreferencesThemesDark  .setForeground(foreGeneralColor);

		/* Pink */
		this.menuiPreferencesThemesPink  .setBackground(backGeneralColor);
		this.menuiPreferencesThemesPink  .setForeground(foreGeneralColor);


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.setBackground(backGeneralColor);
		this.menuAide.setForeground(foreGeneralColor);
	}
}


