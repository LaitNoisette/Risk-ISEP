package Graphique;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MenuLancementPartie extends Parent {
	
	private int nbJoueur=0;
	private Button boutonAjouterJoueur;
	private MenuPrincipal menuPrincipal;
	
	public MenuLancementPartie() {
		
		BorderPane conteneurMenu=new BorderPane();
		Text textFenetre=new Text("Demarrage d'une nouvelle Partie");
		
		textFenetre.setTextAlignment(TextAlignment.CENTER);
		conteneurMenu.setTop(textFenetre);
		
		TilePane colonneCentre=new TilePane();
		colonneCentre.setPrefColumns(1);
		
		//Liste Joueur 
		HBox listeJoueur = new HBox();
		
		Button boutonAjouterJoueur=new Button("Ajouter Joueur");
		
		listeJoueur.getChildren().add(MenuLancementPartie.optionJoueur(Color.RED,MenuLancementPartie.this));
		listeJoueur.getChildren().add(MenuLancementPartie.optionJoueur(Color.BLUE,MenuLancementPartie.this));
		listeJoueur.getChildren().add(boutonAjouterJoueur);
		this.boutonAjouterJoueur=boutonAjouterJoueur;
		
		//Trigger bouton ajouter joueur
		boutonAjouterJoueur.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listeJoueur.getChildren().remove(boutonAjouterJoueur);
				listeJoueur.getChildren().add(MenuLancementPartie.optionJoueurSuppression(Color.BLACK,MenuLancementPartie.this,listeJoueur));
				
				if(nbJoueur<=5) {
					listeJoueur.getChildren().add(boutonAjouterJoueur);
				}
			}
		});
		listeJoueur.setAlignment(Pos.CENTER);
		colonneCentre.getChildren().add(listeJoueur);
		
		
		//Choix carte
		ToggleGroup choixCarte =new ToggleGroup();
		
		RadioButton carteDefaut=new RadioButton("Defaut");
		carteDefaut.setToggleGroup(choixCarte);
		carteDefaut.setSelected(true);
		
		HBox listeCarte = new HBox();
		listeCarte.getChildren().add(carteDefaut);
		listeCarte.setAlignment(Pos.CENTER);
		
		colonneCentre.getChildren().add(listeCarte);
		
		//Choix mission secrete 
		ToggleGroup choixMissionSecrete =new ToggleGroup();
		
		RadioButton ouiMissionSecrete=new RadioButton("OUI");
		ouiMissionSecrete.setToggleGroup(choixMissionSecrete);
		RadioButton nonMissionSecrete=new RadioButton("NON");
		nonMissionSecrete.setToggleGroup(choixMissionSecrete);
		nonMissionSecrete.setSelected(true);
		
		HBox listeChoixMissionSecrete = new HBox();
		listeChoixMissionSecrete.getChildren().add(ouiMissionSecrete);
		listeChoixMissionSecrete.getChildren().add(nonMissionSecrete);
		listeChoixMissionSecrete.setAlignment(Pos.CENTER);
		
		colonneCentre.getChildren().add(listeChoixMissionSecrete);
		
		//Bouton valider le formulaire
		
		Button validerChoixPartie=new Button("Valider");
		validerChoixPartie.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				listeJoueur.getChildren().toArray();
				listeJoueur.getChildren().iterator();
				//menuPrincipal.launch(null);
				
			}
		});
		
		colonneCentre.setAlignment(Pos.CENTER);
		conteneurMenu.setCenter(colonneCentre);
		
		conteneurMenu.autosize();
		conteneurMenu.setAlignment(conteneurMenu.getTop(), Pos.CENTER);
		
		this.getChildren().add(conteneurMenu);
	
	}

	 public void start(Stage primaryStage) {
	       
	    }
	 
	public int getNbJoueur() {
		return this.nbJoueur;
	}
	
	 public Button getBoutonAjouterJoueur() {
		 return this.boutonAjouterJoueur;
	 }
	 
	 public void ajouterJoueur() {
		 if(this.nbJoueur<=5) {
			 this.nbJoueur++;
		 }
	 }
	 public void retirerJoueur() {
		 if(this.nbJoueur>=3) {
			 this.nbJoueur--;
		 }
	 }
	
	 /**
	  * Fonction permettant l'affichage de case d'ajout de joueur
	  * @param color
	  * @param menu
	  * @return
	  */
	public static VBox optionJoueur(Color color,MenuLancementPartie menu) {
		VBox joueurOption = new VBox();
		
		ChoiceBox typeJoueur = new ChoiceBox();
		typeJoueur.setItems(FXCollections.observableArrayList(
		    "Humain", 
		    new Separator(),
		    "IA")
		);
		typeJoueur.setTooltip(new Tooltip("Choisir si l'adversaire sera controle par un humain ou une machine."));
		
		Rectangle couleurJoueur =new Rectangle(100, 100, color);
		
		TextField nomJoueur=new TextField();
		nomJoueur.setPromptText("Entrer votre nom");
		
		joueurOption.getChildren().add(typeJoueur);
		joueurOption.getChildren().add(couleurJoueur);
		joueurOption.getChildren().add(nomJoueur);
	
		joueurOption.setAlignment(Pos.CENTER);
		
		menu.ajouterJoueur();
		return joueurOption;
		
	}
	
	/**
	 * Fonction permettant l'affichage de case d'ajout de joueur avec le bouton de suppression
	 * @param color
	 * @param menu
	 * @param listeJoueurs
	 * @return
	 */
	public static VBox optionJoueurSuppression(Color color,MenuLancementPartie menu,HBox listeJoueurs) {
		VBox joueurOption = new VBox();
		Button boutonSupp=new Button("Supprimer");
		
		//Trigger bouton supprimer joueur
		boutonSupp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listeJoueurs.getChildren().remove(joueurOption);
				menu.retirerJoueur();
				if(!listeJoueurs.getChildren().contains(menu.getBoutonAjouterJoueur())) {
					if(menu.getNbJoueur()<=5) {
						listeJoueurs.getChildren().add(menu.getBoutonAjouterJoueur());
					}
				}
				
			}
		});
		
		ChoiceBox typeJoueur = new ChoiceBox();
		typeJoueur.setItems(FXCollections.observableArrayList(
		    "Humain", 
		    new Separator(),
		    "IA")
		);
		typeJoueur.setTooltip(new Tooltip("Choisir si l'adversaire sera controle par un humain ou une machine."));
		
		Rectangle couleurJoueur =new Rectangle(100, 100, color);
		
		TextField nomJoueur=new TextField();
		nomJoueur.setPromptText("Entrer votre nom");
		
		joueurOption.getChildren().add(boutonSupp);
		joueurOption.getChildren().add(typeJoueur);
		joueurOption.getChildren().add(couleurJoueur);
		joueurOption.getChildren().add(nomJoueur);
	
		joueurOption.setAlignment(Pos.CENTER);
		
		menu.ajouterJoueur();
		return joueurOption;
		
	}

}
