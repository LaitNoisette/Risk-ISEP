package Graphique;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import Jeu.Bataille;
import Jeu.Partie;
import Jeu.Territoire;
import Jeu.Unite;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {
	@FXML
	private BorderPane Body;
	@FXML
	private BorderPane header;
	@FXML
	private MenuItem CloseButton;
	@FXML
	private Slider sliderVolumeOST;
	@FXML
	private StackPane Info_Territory;
	@FXML
	private StackPane Info_Territory_Attack;
	@FXML
	private StackPane Info_TerritoryPlayer;
	@FXML
	private Pane Info_Territory_Picture;
	@FXML
	private AnchorPane TourSuivant;
	@FXML
	private Pane BodyMap;
	@FXML
	private AnchorPane ContainerSVG, PaneTourSuivantNomJoueur;
	@FXML
	private ScrollPane ListTerritoryAttack, ScrollPaneAddBackups;
	@FXML
	private GridPane Grid_Info_Territory, Info_TerritoryPlayer_GridPane, Info_TerritoryAttack_GridPane;
	@FXML
	private Button InfoTerritoryPlayer_CloseButton, InfoTerritory_CloseButton, InfoTerritoryAttack_CloseButton, ButtonAttack;
	@FXML
	private GridPane IconPlayers;
	@FXML
	private GridPane GridPaneAddBackups,GridPaneAddAttack;
	@FXML
	private AnchorPane video;
	@FXML
	private AnchorPane GridMenuIndex, LancerPartie;
	@FXML
	private GridPane GridPaneMenuIndex;
	@FXML
	private Text PseudoTextIndex__1, PseudoTextIndex__2, PseudoTextIndex__3, PseudoTextIndex__4, PseudoTextIndex__5, PseudoTextIndex__6;
	@FXML
	private Circle CircleIconPlayerIndex__1, CircleIconPlayerIndex__2, CircleIconPlayerIndex__3, CircleIconPlayerIndex__4, CircleIconPlayerIndex__5, CircleIconPlayerIndex__6;
	@FXML
	private SplitMenuButton SplitMenuButtonIndex__1, SplitMenuButtonIndex__2, SplitMenuButtonIndex__3, SplitMenuButtonIndex__4, SplitMenuButtonIndex__5, SplitMenuButtonIndex__6;
	@FXML
	private ImageView IconPlayerIndex__1, IconPlayerIndex__2, IconPlayerIndex__3, IconPlayerIndex__4, IconPlayerIndex__5, IconPlayerIndex__6;
	@FXML 
	private Text TitleHome, TextPlayerFinTour;
	
	//Infos Territoire
	@FXML
	private Text Info_Territory_NameTerritory;
	@FXML
	private Text Info_Territory_NameRegion;
	
	@FXML
	private Text Info_Territory_NbUnity__1;
	@FXML
	private Text Info_Territory_NameUnity__1;
	
	@FXML
	private Text Info_Territory_NbUnity__2;
	@FXML
	private Text Info_Territory_NameUnity__2;
	
	@FXML
	private Text Info_Territory_NbUnity__3;
	@FXML
	private Text Info_Territory_NameUnity__3;
	
	// Info Joueur
	@FXML
	private Text Info_TerritoryPlayer_Pseudo;
	@FXML
	private Text Info_TerritoryPlayer_NbTerritory;
	@FXML
	private Text Info_TerritoryPlayer_NbRegion;
	@FXML
	private Text Info_TerritoryPlayer_NbUnity__1;
	@FXML
	private Text Info_TerritoryPlayer_NameUnity__1;
	@FXML
	private Text Info_TerritoryPlayer_NbUnity__2;
	@FXML
	private Text Info_TerritoryPlayer_NameUnity__2;
	@FXML
	private Text Info_TerritoryPlayer_NbUnity__3;
	@FXML
	private Text Info_TerritoryPlayer_NameUnity__3;	
	
	//Menu attaque
	@FXML
	private Text Info_TerritoryAttack_NameTerritory;
	@FXML
	private ScrollPane ScrollPaneAddAttack;
	@FXML
	private GridPane GridPaneMoveUnity;

	private boolean isAnimationEnded = false;
	private boolean isPanelOpened = false;
	private boolean isTerritorySelectedBackup = false;
	private boolean InitPane = false;

	private boolean renfort = true;
	
	// Permet de changer le temps des animations pour le déboggage 
	private int time1 = 8000; //8000

	private Partie partie;
	public static Partie partieController;
	// Permet de stocker le territoire sur lequel on a a clique
	public static Territoire territoireSelectionne;
	public static Bataille batailleJeu;
	
	// Id utilisé pour gérer le hover
	String idMouseEnteredTerritory;
	
	// Définition du MediaPlayer pour l'accueil
	MediaPlayer playerIndex = new MediaPlayer(
			new Media(getClass().getResource("../resources/video/backgroundLaunch.mp4").toExternalForm()));
    MediaView mediaViewIndex = new MediaView(playerIndex);
    
    /********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/

	/**
	 * Initialisation de l'interface du controller
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Initialisation des vues des panels 
		setVisibleInit();			

		// Spécificaion du Media View de la vidéo à l'accueil
		mediaViewIndex.setFitHeight(1080);
		mediaViewIndex.setFitWidth(1920);
		mediaViewIndex.setPreserveRatio(true);
        video.getChildren().add(mediaViewIndex);
        
        // Transition de fondu
        FadeTransition ft = new FadeTransition(Duration.millis(time1), TitleHome);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();	        

        // Transition de translation
		TranslateTransition tt = new TranslateTransition(Duration.millis(time1), TitleHome);
        tt.setByY(-275);
        tt.play();
        
        // Quand la transition est terminée, on affiche le boutton "LancerPartie"
        tt.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LancerPartie.setVisible(true);
			}
        });
        
        // On définit le slider pour gérer le son, ainsi que sa valeur de base
        setVolumeInGame(playerIndex);
        
        // Lancement de la vidéo en fond
        playerIndex.play();	     
        
        // Pour chaque icônes, on l'ajoute dans SplitMenuButtonIndex_n
        int numberOfFiles=countFilesInDirectory(new File("src/resources/img/player/"));     		
		for (int i = 0; i<numberOfFiles; i++) {
			setMenuItemIndex(SplitMenuButtonIndex__1, i);
			setMenuItemIndex(SplitMenuButtonIndex__2, i);
			setMenuItemIndex(SplitMenuButtonIndex__3, i);
			setMenuItemIndex(SplitMenuButtonIndex__4, i);
			setMenuItemIndex(SplitMenuButtonIndex__5, i);
			setMenuItemIndex(SplitMenuButtonIndex__6, i);
		}
				
	}
	
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	
	/**
	 * Event appelé lors d'un clic souris sur un territoire
	 */
	@FXML
	private void handleMouseAction(MouseEvent event) {
		// On récupère l'id du territoire
		String idFromClick;
		// https://stackoverflow.com/questions/24302636/better-way-for-getting-id-of-the-clicked-object-in-javafx-controller
		idFromClick = event.getPickResult().getIntersectedNode().getId();
		// On split la chaîne de caractère pour ne garder que le prefix
		String[] tokens = idFromClick.split("__");
		String idFromClickPrefix = tokens[0];

		setInfoTerritory(idFromClickPrefix);
		
		// On split la chaîne de caractère à nouveau pour supprimer tous les "_" par des
		// " "
		String[] tokens2 = idFromClickPrefix.split("_");
		String idFromClickPrefixFinal = "";
		// On vérifie que la chaîne n'est pas nulle (pas de "_" dans la chaîne avant de split)
		if (tokens2.length >= 1) {
			int i = 1;
			for (String t : tokens2) {
				if (i >= tokens2.length) {
					idFromClickPrefixFinal += t;
				} else {
					idFromClickPrefixFinal += t + " ";
				}
				i++;
			}
		}
		
		if (FXMLDocumentController.territoireSelectionne.getProprietaire()
				.equals(FXMLDocumentController.partieController.getJoueurEnCours())) {			
			ButtonAttack.setText("Déplacement");
			Info_TerritoryAttack_NameTerritory.setText("Déplacer mes unités");
		}	
		else {
			ButtonAttack.setText("Attaquer");
			Info_TerritoryAttack_NameTerritory.setText("Attaquer l'ennemi");
		}
		
		Info_Territory_Attack.setVisible(false);

		// On active le panel
		if (!Info_Territory.isVisible()) {
			Info_Territory.setVisible(true);
			Info_Territory.setLayoutX(0);
			event.getPickResult().getIntersectedNode().getStyleClass().add("effectHover");
		}

		// Si le territoire cliqué appartient au joueur, que le panel des territoires du
		// joueur actuel est ouvert
		// et que l'on est en renforts, alors on positionne le Scroll Pane
		if (Info_TerritoryPlayer.isVisible() && renfort) {
			// Position du territoire dans le Scroll Pane
			int positionTerritorySelected = 4;
			
			// Nombre total de territoires dans le Scroll Pane
			int nbTerritoryOfPlayer = FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire().size();
			this.partieController.getCarte().recupererTerritoireNOM(idFromClickPrefixFinal);
			
			// Pour chaque territoire du joueur, on les compare pour trouver le territoire cliqué
			String t1 = this.partieController.getCarte().recupererTerritoireNOM(idFromClickPrefixFinal).getNom();			
			int i=0;
			for (Territoire t : FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire()) {
				if (t != null) {
					if (t.getNom().replaceAll(" ", "_").equals(t1.replaceAll(" ", "_"))) {
						positionTerritorySelected=i;					
					}
					i++;
				}			
			}
			ScrollPaneAddBackups.setVvalue((double)positionTerritorySelected / (double)nbTerritoryOfPlayer);
		} else {
			ScrollPaneAddBackups.setVvalue(0);
		}
	}


	/**
	 * Event appelé en cas de Scroll dans le panel contenant les SVG afin de zoomer les éléments du panel
	 */
	@FXML
	private void onScrollEventHandler(ScrollEvent event) {			
		AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
		double zoomFactor = 2;

		// Si molette vers le bas, on a un dezoom
		if (event.getDeltaY() < 0) {
			// On prend l'inverse pour avoir un facteur < 1
			zoomFactor = 1 / zoomFactor;
		}
		// Si molette vers le haut, on zoom
		else if (event.getDeltaY() > 0) {
			zoomOperator.zoom(ContainerSVG, zoomFactor, event.getSceneX(), event.getSceneY());
		}

		// Permet de dézoomer en cas de deux zooms trop rapides
		if (ContainerSVG.getScaleX() > 1)
			zoomOperator.zoom(ContainerSVG, zoomFactor, event.getSceneX(), event.getSceneY());
	}

	/**
	 * Event appelé pour afficher le menu d'attaque
	 */
	@FXML
	private void handleButtonAttackAction(ActionEvent event) {
		//AfficherMenuRenforts.deleteRow(Info_TerritoryAttack_GridPane, 4);
		AfficherMenuRenforts.deleteRow(Info_TerritoryAttack_GridPane, 4);
			
		Info_Territory_Attack.setVisible(true);
		
		if (ButtonAttack.getText().equals("Attaquer")) {
			FXMLDocumentController.batailleJeu=new Bataille(FXMLDocumentController.territoireSelectionne);
			AfficherMenuRenforts afficherMenuAttack = new AfficherMenuRenforts(AfficherMenuRenforts.controller = this,
					Info_Territory_Attack, ScrollPaneAddAttack, Info_TerritoryAttack_GridPane, Body.getPrefHeight()-header.getPrefHeight(), false, true, false);
			
			afficherMenuAttack.DisplayInformations();
		}
		else {
			AfficherMenuRenforts afficherMenuAttack = new AfficherMenuRenforts(AfficherMenuRenforts.controller = this,
					Info_Territory_Attack, ScrollPaneAddAttack, Info_TerritoryAttack_GridPane, Body.getPrefHeight()-header.getPrefHeight(), false, false, true);
			afficherMenuAttack.DisplayInformations();
		}		
	
		//Gerer cas proprietaire ou non du territoire
		/*if (FXMLDocumentController.territoireSelectionne.getProprietaire()
				.equals(FXMLDocumentController.partieController.getJoueurEnCours())) {			
			ButtonAttack.setText("Déplacement");
		}	
		else {
			ButtonAttack.setText("Attaquer");
		}*/
		
	}

	/**
	 * Event qui permet de centrer la carte si l'utilsateur a perdu la carte, car les développeurs sont incompétents
	 */
	@FXML
	private void onActionCenterMapButtonHandler(ActionEvent event) {
		ContainerSVG.setTranslateX(0);
		ContainerSVG.setTranslateY(0);
	}

	/**
	 * Event qui permet de fermer le panel en cas de clic sur la croix (haut à gauche)
	 */
	@FXML
	private void onActionPaneCloseHandler(ActionEvent event) {
		Node node = (Node) event.getSource();

		// Relativité au grand-parent, l'UI ne changera pas
		Node newNode = node.getParent().getParent();
		newNode.setVisible(false);

		// Si c'est le panel d'informations du joueur, on réaffiche l'icône pour voir
		// les informations du joueur
		if (Objects.equals((String) node.getId(), new String("InfoTerritoryPlayer_CloseButton"))) {
			TourSuivant.setVisible(true);
			IconPlayers.setVisible(true);
		}		
		else if (Objects.equals((String) node.getId(), new String("InfoTerritory_CloseButton")))
			Info_Territory_Attack.setVisible(false);
	}

	/**
	 * Event qui permet de gérer l'affichage du panel du joueur
	 */
	@FXML
	private void onMouseClickedPaneOpenInfoPlayerHandler(MouseEvent event) {
		// Position dans le Grid Pane du Scroll Pane
		final int row = 10;
		Node node = (Node) event.getSource();
		Info_TerritoryPlayer.setVisible(true);
		// On rend invisible l'Anchor Pane contenant les icônes des joueurs
		node.getParent().setVisible(false);
		//TourSuivant.setVisible(false);
		TourSuivant.setVisible(true);
		// Informations du joueurs modifiées dans l'interface
		Info_TerritoryPlayer_NbTerritory.setText("Territoires contrôlés : "
				+ FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire().size());
		Info_TerritoryPlayer_NbRegion.setText("Régions contrôlées : "
				+ FXMLDocumentController.partieController.getJoueurEnCours().getListeRegion().size());

		Info_TerritoryPlayer_NameUnity__1.setText(Unite.nouveauSoldat().getNom());
		Info_TerritoryPlayer_NbUnity__1
				.setText("" + FXMLDocumentController.partieController.getJoueurEnCours().getNbrUniteSoldatTotal());

		Info_TerritoryPlayer_NameUnity__2.setText(Unite.nouveauCavalier().getNom());
		Info_TerritoryPlayer_NbUnity__2.setText(
				"" + FXMLDocumentController.partieController.getJoueurEnCours().getNbrUniteCavalierTotal());

		Info_TerritoryPlayer_NameUnity__3.setText(Unite.nouveauCanon().getNom());
		Info_TerritoryPlayer_NbUnity__3
				.setText("" + FXMLDocumentController.partieController.getJoueurEnCours().getNbrUniteCanonTotal());
		AfficherMenuRenforts afficherMenuRenforts = new AfficherMenuRenforts(AfficherMenuRenforts.controller = this,
				Info_TerritoryPlayer, ScrollPaneAddBackups, Info_TerritoryPlayer_GridPane, Body.getPrefHeight()-header.getPrefHeight(), true, false, false);

		// Si le joueur actuel clique sur son icône et que c'est le moment des renforts, il peut en ajouter
		// et voir un résumé de toutes ses unités ainsi que sa mission secrète
		//if (!ScrollPaneAddBackups.isVisible() && renfort) { 
		/*if (!ScrollPaneAddBackups.isVisible()) { 
			afficherMenuRenforts.DisplayInformations();
		} 
		// Sinon, on supprime le tableau et on réduit sa taille
		else {
			ScrollPaneAddBackups.setVisible(false);
			AfficherMenuRenforts.deleteRow(Info_TerritoryPlayer_GridPane, row);
			Info_TerritoryPlayer.setPrefHeight(Info_Territory.getPrefHeight());			
		}*/
		AfficherMenuRenforts.deleteRow(Info_TerritoryPlayer_GridPane, row);
		afficherMenuRenforts.DisplayInformations();
	}

	/**
	 * Event permettant d'appliquer un effet de hover sur un ou plusieurs
	 * territoires ayant le même prefix. 
	 * NB : Appliquer l'effet directement dans le CSS était impossible sur 
	 * plusieurs territoires
	 */
	@FXML
	private void onMouseEnteredTerritoryHandler(MouseEvent event) {
		Node node = (Node) event.getSource();

		// On récupère l'id du territoire, qui est sauvegardé pour la fermeture (event
		// onMouseExitedTerritoryHandler)
		idMouseEnteredTerritory = node.getId();

		// Récupération une liste de tous les SVG (territoires) contenus dans
		// ContainerSVG
		ObservableList<Node> ListNodes = ContainerSVG.getChildren();

		// Split du nom du territoire cliqué pour ne récupérer que le prefix
		String[] tokens = node.getId().split("__");
		String prefix = tokens[0];

		// On retire l'effet CSS par sécurité
		for (Node n : ListNodes) {
			n.getStyleClass().removeIf(style -> style.equals("effectHover"));
		}

		// Pour chaque territoire, si l'id n'est pas nul (décor) et que le prefix du
		// territoire testé est égal au prefix du territoire cliqué, on applique l'effet CSS de hover
		for (Node n : ListNodes) {
			if (n.getId() != null) {
				String[] tokensn = n.getId().split("__");
				String prefixn = tokensn[0];
				if (prefixn.equals(prefix)) {
					n.getStyleClass().add("effectHover");
				}
			}
		}
	}

	/**
	 * Event permettant d'annuler l'effet CSS de hover, en le laissant si la fenêtre
	 * correspondant à ce territoire est ouverte
	 */
	@FXML
	private void onMouseExitedTerritoryHandler(MouseEvent event) {
		ObservableList<Node> ListNodes = ContainerSVG.getChildren();
		Node node = (Node) event.getSource();
		
		if (idMouseEnteredTerritory != null) {
			// Split du nom du territoire cliqué pour ne récupérer que le prefix
			String[] tokens = node.getId().split("__");
			String prefix = tokens[0];

			if (Info_Territory.isVisible()) {
				for (Node n : ListNodes) {
					if (n.getId() != null) {
						String[] tokensn = n.getId().split("__");
						String prefixn = tokensn[0];
						if (prefixn.equals(Info_Territory_NameTerritory.getText().replace(" ", "_"))) {
							n.getStyleClass().add("effectHover");
						} else {
							n.getStyleClass().removeIf(style -> style.equals("effectHover"));
						}
					}
				}
			} else {
				for (Node n : ListNodes)
					n.getStyleClass().removeIf(style -> style.equals("effectHover"));
			}
		}
	}
	
	/**
	 * Event appelé en cas d'appui sur le boutton "Tour suivant"
	 */
	@FXML
	private void onActionTourSuivantHandler() {
		FXMLDocumentController.partieController.gererFinTour();	
		
		// Changement du nom du joueur dans les menus
		Info_TerritoryPlayer_Pseudo.setText(FXMLDocumentController.partieController.getJoueurEnCours().getNom());
		PaneTourSuivantNomJoueur.setTranslateY(1080);
		System.out.println(PaneTourSuivantNomJoueur.getLayoutY());
		PaneTourSuivantNomJoueur.setVisible(true);
		TextPlayerFinTour.setText(FXMLDocumentController.partieController.getJoueurEnCours().getNom()+", à toi de jouer !");
		// Transition en fondu des menus
		TranslateTransition tt = new TranslateTransition(Duration.millis(time1/6), PaneTourSuivantNomJoueur);
        tt.setByY(-1000);
        tt.play();
        
        // Quand la transition est terminée, on affiche le boutton "LancerPartie"
        tt.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PaneTourSuivantNomJoueur.setVisible(false);
			}
        });
	}
	
	/**
	 * Event appelé en cas de changement de pseudo à l'accueil
	 */
	@FXML
	private void onActionPseudoTextFieldHandler(ActionEvent event) {
		// Taille limite du Text Field
		final int LIMIT = 10;
		TextField textField = (TextField) event.getSource();
		String idSelectedNode = textField.getId().replaceAll("PseudoTextFieldIndex__", "");
		
		// On associe Text et Text Field
		switch(idSelectedNode) {
			case "1":
				PseudoTextIndex__1.setText(textField.getText());
				break;
			case "2":
				PseudoTextIndex__2.setText(textField.getText());
				break;
			case "3":
				PseudoTextIndex__3.setText(textField.getText());
				break;
			case "4":
				PseudoTextIndex__4.setText(textField.getText());
				break;
			case "5":
				PseudoTextIndex__5.setText(textField.getText());
				break;
			case "6":
				PseudoTextIndex__6.setText(textField.getText());
				break;
		}
		
		// On ajoute un listener pour corriger quand l'utilisateur rentre une chaîne trop grande
		textField.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (newValue.intValue() > oldValue.intValue()) {
            		// Vérification si la nouvelle chaîne est plus grande que la limite
                    if (textField.getText().length() >= LIMIT) {
                    	// Report JDK-8081700
                        Platform.runLater(() -> { 
                        	//textField.setText(textField.getText().substring(0, LIMIT));
                        	textField.clear();
                        }); 

                    }
                }
            }
        });

	}
	
	/**
	 * Event appelé en cas de changement de couleur à l'accueil
	 */
	@FXML
	private void onActionColorPickerIndexHandler(ActionEvent event) {
		ColorPicker colorPicker = (ColorPicker) event.getSource();
		String idSelectedNode = colorPicker.getId().replaceAll("ColorPickerIndex__", "");
		
		String selectedColor = toRgbString(colorPicker.getValue());
		
		switch(idSelectedNode) {
			case "1":
				CircleIconPlayerIndex__1.setStyle("-fx-stroke : "+selectedColor);
				break;		
			case "2":
				CircleIconPlayerIndex__2.setStyle("-fx-stroke : "+selectedColor);
				break;	
			case "3":
				CircleIconPlayerIndex__3.setStyle("-fx-stroke : "+selectedColor);
				break;	
			case "4":
				CircleIconPlayerIndex__4.setStyle("-fx-stroke : "+selectedColor);
				break;	
			case "5":
				CircleIconPlayerIndex__5.setStyle("-fx-stroke : "+selectedColor);
				break;	
			case "6":
				CircleIconPlayerIndex__6.setStyle("-fx-stroke : "+selectedColor);
				break;	
		}
	}
	
	/**
	 * Event appelé en cas de changement d'icône de joueur
	 */
	@FXML
	private void onActionLancerPartieHandler() {
		System.out.println(CircleIconPlayerIndex__1.getStroke());
		// .setStroke() pour changer couleur
		System.out.println(PseudoTextIndex__1.getText());
		System.out.println(IconPlayerIndex__1.getViewport());
	
		setVisibleInit();
	}	

	/**
	 * Event appelé en cas d'appui sur "Quitter le jeu" ou raccourci "Ctrl + A"
	 */
	@FXML
	private void closeGameAction() {
		Platform.exit();
	}
	
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	/********************************************************************************************/
	
	/**
	 * Méthode complémentaire de Initializable
	 */
	private void setVisibleInit() {
		// Définition de la visiblité de tous les panels
		PaneTourSuivantNomJoueur.setVisible(false);
		LancerPartie.setVisible(this.InitPane);	
		Info_Territory_Attack.setVisible(this.InitPane);
		Info_Territory.setVisible(this.InitPane);
		Info_TerritoryPlayer.setVisible(this.InitPane);
		IconPlayers.setVisible(this.InitPane);
		ContainerSVG.setVisible(this.InitPane);
		TourSuivant.setVisible(this.InitPane);	
		
		if (this.InitPane) {	
			// Transition en fondu des menus
			FadeTransition ft = new FadeTransition(Duration.millis(time1/4), GridMenuIndex);
	        ft.setFromValue(1);
	        ft.setToValue(0);
	        ft.play();
	        
	        // Quand la transition en fondu des menus est terminée
	        ft.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Transition en fondu de la vidéo
					FadeTransition ft = new FadeTransition(Duration.millis(time1/2), video);
			        ft.setFromValue(1);
			        ft.setToValue(0);
			        ft.play();
			        
			        // Fade out de la musique de la vidéo
			        Timeline timeline = new Timeline(
			        	    new KeyFrame(Duration.millis(time1/4),
			        	        new KeyValue(playerIndex.volumeProperty(), 0)));
			        	timeline.play();
			        	
			        // Quand la transition en fondu de la vidéo est terminée
			        ft.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							// Les anciens panels sont rendus invisibles et la vidéo est stopée
					        GridMenuIndex.setVisible(false);	
							video.setVisible(false);
							playerIndex.stop();
							
							TranslateNode translateNode = new TranslateNode(ContainerSVG);

							// Initialisation de la musique
							String musicFile = "src/resources/audio/ost.mp3";

							Media sound = new Media(new File(musicFile).toURI().toString());
							MediaPlayer mediaPlayer = new MediaPlayer(sound);

							setVolumeInGame(mediaPlayer);

							// Placement du menu d'informations du joueur actuel
							Info_TerritoryPlayer.setLayoutX((double) BodyMap.getPrefWidth() - Info_TerritoryPlayer.getPrefWidth());
							System.out.println(BodyMap.getPrefWidth()+ " "+ Info_TerritoryPlayer.getPrefWidth() + " " + Info_TerritoryPlayer.getLayoutX());

							// Initialisation de l'affichage des menus
							Info_Territory.setLayoutX(0);
							Info_Territory_Attack.setLayoutX(0);
							Info_Territory.setVisible(false);
							Info_Territory_Attack.setVisible(false);
							Info_TerritoryPlayer.setVisible(false);

							// Ajout des filtres pour espionner les mouvements pour bouger le panel Container SVG
							ContainerSVG.addEventFilter(MouseEvent.MOUSE_PRESSED, translateNode.getOnMousePressedEventHandler());
							ContainerSVG.addEventFilter(MouseEvent.MOUSE_DRAGGED, translateNode.getOnMouseDraggedEventHandler());

							// Initialisation premier joueur exemple le nom
							// this.partieController.getJoueurEnCours().getNom();

							// Changement du nom du joueur dans les menus
							Info_TerritoryPlayer_Pseudo.setText(FXMLDocumentController.partieController.getJoueurEnCours().getNom());
							
							// On définit la couleur des territoires appartenant au joueur actuel
							// A MODIFIER POUR TOUS LES JOUEURS COUCOU FRANCK
							ArrayList<Node> nodes = new ArrayList<Node>();			
							addAllDescendents(ContainerSVG, nodes);	
							for (Territoire territoire : FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire()) {
								for (Node n : nodes) {
									if (n.getId()!=null) {
										String[] tokens = n.getId().split("__");
										String prefix = tokens[0];
										if (prefix.equals(territoire.getNom().replaceAll(" ", "_"))) {
											n.setStyle("-fx-fill:red");
										}
											
									}		
								}
							}	
						}
			        });
				}
	        });
		}		
		this.InitPane=!this.InitPane;
	}
	
	/**
	 * Permet d'initialiser la musique et de la gérer via le Slider en haut à droite
	 */
	void setVolumeInGame(MediaPlayer mp) {
		double defaultValue = 0.2;
		
		mp.setOnEndOfMedia(new Runnable() {
			public void run() {
				mp.seek(Duration.ZERO);
			}
		});
		mp.setVolume(defaultValue);
		sliderVolumeOST.setValue(defaultValue);
		mp.play();

		sliderVolumeOST.valueProperty().addListener((observable, oldValue, newValue) -> {
			mp.setVolume((double) newValue);
		});
	}
	
	/**
	 * Permet de créer les MenuItem des SplitMenu pour choisir l'icône du joueur
	 * Méthode appelée autant de fois qu'il y a d'icônes de joueur
	 */
	private void setMenuItemIndex(SplitMenuButton splitMenuButton, int i) {
		// Les images commencent à 1 pas à 0
		int idImg = i+1;
		
		// Définition de l'image et de ses paramètres
		String pathImg = "./resources/img/player/Player__"+idImg+".png";
		Image image = new Image(pathImg);
		ImageView imageView = new ImageView(pathImg);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		imageView.setPreserveRatio(true);
		
		// Spécification du MenuItem
		MenuItem menuItem = new MenuItem("Icône "+idImg, imageView);
		splitMenuButton.getItems().add(menuItem);
		menuItem.setId("MenuItemIndex__"+idImg);
		
		// Ajout d'un évènement pour récupérer la valeur choisie par l'utilisateur et changer l'aperçu
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
            	MenuItem menuItemEvent = (MenuItem) event.getSource();
        		String numberImg = menuItemEvent.getId().replace("MenuItemIndex__", "");
        		
        		String idSelectedNode = splitMenuButton.getId().replace("SplitMenuButtonIndex__", "");       	    		
        		
        		Image imageSelected = new Image("./resources/img/player/Player__"+numberImg+".png");
        		
        		System.out.println(idSelectedNode);      				
        		
        		switch(idSelectedNode) {
	        		case "1":
	        			System.out.println(imageSelected);
	        			IconPlayerIndex__1.setImage(imageSelected);
	        			break;		
	        		case "2":
	        			IconPlayerIndex__2.setImage(imageSelected);
	        			break;	
	        		case "3":
	        			IconPlayerIndex__3.setImage(imageSelected);
	        			break;	
	        		case "4":
	        			IconPlayerIndex__4.setImage(imageSelected);
	        			break;	
	        		case "5":
	        			IconPlayerIndex__5.setImage(imageSelected);
	        			break;	
	        		case "6":
	        			IconPlayerIndex__6.setImage(imageSelected);
	        			break;	
        		}
            }
        });
	}
		
	/**
	 * Définit les informations du territoire à partir de son id
	 */
	public void setInfoTerritory(String idSelectedTerritory) {
		// On split la chaîne de caractère à nouveau pour supprimer tous les "_" par des
		// " "
		String[] tokens2 = idSelectedTerritory.split("_");
		String idFromClickPrefixFinal = "";
		// On vérifique la chaîne n'est pas nulle (pas de "_" dans la chaîne avant de
		// split)
		if (tokens2.length >= 1) {
			int i = 1;
			for (String t : tokens2) {
				if (i >= tokens2.length) {
					idFromClickPrefixFinal += t;
				} else {
					idFromClickPrefixFinal += t + " ";
				}
				i++;
			}
		}

		FXMLDocumentController.territoireSelectionne = this.partieController.getCarte().recupererTerritoireNOM(idFromClickPrefixFinal);

		// Changement de tous les labels
		if (FXMLDocumentController.territoireSelectionne != null) {
			System.out.println("Nom Territoire : " + this.territoireSelectionne.getNom() + " Nom Region : "
					+ this.territoireSelectionne.getRegion().getNom() + " Nom Proprietaire : "
					+ this.territoireSelectionne.getProprietaire().getNom() + " Nombre Unite Total : "
					+ this.territoireSelectionne.getNbrTotalUniteTerritoire());
			Info_Territory_NameTerritory.setText(FXMLDocumentController.territoireSelectionne.getNom());
			Info_Territory_NameRegion.setText(FXMLDocumentController.territoireSelectionne.getRegion().getNom());
			Info_Territory_NameUnity__1.setText(Unite.nouveauSoldat().getNom());
			Info_Territory_NbUnity__1.setText(""+FXMLDocumentController.territoireSelectionne.getListeUniteSoldat().size());
			
			Info_Territory_NameUnity__2.setText(Unite.nouveauCavalier().getNom());
			Info_Territory_NbUnity__2.setText(""+FXMLDocumentController.territoireSelectionne.getListeUniteCavalier().size());
			
			Info_Territory_NameUnity__3.setText(Unite.nouveauCanon().getNom());
			Info_Territory_NbUnity__3.setText(""+FXMLDocumentController.territoireSelectionne.getListeUniteCanon().size());
		}

		// On change le texte en fonction du territoire cliqué
		Info_Territory_NameTerritory.setText(idFromClickPrefixFinal);

		// Informations du joueur

		// On change l'image en fonction du territoire cliqué
		/*Info_Territory_Picture.setStyle("-fx-background-image: url(\"resources/img/country/" + idSelectedTerritory
				+ ".jpg\");-fx-background-size: 440 152;-fx-background-position: center;");	*/
		File f = new File("src/resources/img/territory/"+idSelectedTerritory+".jpg");
		if(f.exists() && !f.isDirectory()) { 
			Info_Territory_Picture.setStyle("-fx-background-image: url(\"resources/img/territory/" + idSelectedTerritory
					+ ".jpg\");-fx-background-size: 440 152;-fx-background-position: center;");	
		}
		else {
			Info_Territory_Picture.setStyle("-fx-background-image: url(resources/img/territory/default.jpg);-fx-background-size: 440 152;-fx-background-position: center;");	
		}
	}
	
	/*
	 * public void setPartie(Partie p) { this.partie = p; }
	 */

	/**
	 * Get l'AnchorPane Container SVG
	 */
	public AnchorPane getContainerSVG() {
		return this.ContainerSVG;
	}

	/**
	 * Get le Stackpane Info_Territory
	 */
	public StackPane getInfoTerritory() {
		return this.Info_Territory;
	}

	/**
	 * Set un boolean pour savoir si un territoire est sélectionné dans les renforts
	 */
	public void setTerritorySelectedBackup(boolean selected) {
		this.isTerritorySelectedBackup = selected;
	}
	
	/**
	 * Méthode permettant de récupérer tous les enfants d'un Parent
	 */
	public static ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}
	
	/**
	 * Méthode dépendante de la précédente, qui permet d'ajouter enfant par enfant à la liste
	 */
	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}
	
	/**
	 * Méthode qui convertit la couleur d'un ColorPick en couleur utilisable par le CSS
	 */
	private String toRgbString(Color c) {
        return "rgb("
                          + to255Int(c.getRed())
                    + "," + to255Int(c.getGreen())
                    + "," + to255Int(c.getBlue())
             + ")";
    }

	/**
	 * Méthode dépendante de toRgbString()
	 */
    private int to255Int(double d) {
        return (int) (d * 255);
    }
    
    /**
	 * Permet de compter le nombre de fichiers dans un territoire
	 */
	public static int countFilesInDirectory(File dir) {  
	    int totalFiles = 0;  
	  
	    File[] listFiles = dir.listFiles();  
	    if (listFiles != null && listFiles.length > 0) {  
	        for (File file : listFiles) {  
	            if (file.isFile()) {  
	                totalFiles++;  
	            } else {  
	                totalFiles += countFilesInDirectory(file);  
	            }  
	        }  
	    }  
  
	    return totalFiles;  
	} 
	
	
}
