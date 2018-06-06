package Graphique;

import java.io.File;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.event.EventQueue;
import com.sun.javafx.geom.Rectangle;

import Jeu.Partie;
import Jeu.Territoire;
import Jeu.Unite;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
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
	private Pane BodyMap;
	@FXML
	private AnchorPane ContainerSVG;
	@FXML
	private ScrollPane ListTerritoryAttack, ScrollPaneAddBackups;
	@FXML
	private GridPane Grid_Info_Territory, Info_TerritoryPlayer_GridPane;
	@FXML
	private Button InfoTerritoryPlayer_CloseButton, InfoTerritory_CloseButton, InfoTerritoryAttack_CloseButton;
	@FXML
	private GridPane IconPlayers;
	@FXML
	private GridPane GridPaneAddBackups;
	@FXML
	private AnchorPane video;
	@FXML 
	private Text TitleHome;
	
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
	private ScrollPane ScrollPaneAddAttack;
	@FXML
	private GridPane GridPaneMoveUnity;

	private boolean isAnimationEnded = false;
	private boolean isPanelOpened = false;
	private boolean isTerritorySelectedBackup = false;

	private boolean renfort = true;

	private Partie partie;
	public static Partie partieController;
	// Permet de stocker le territoire sur lequel on a a clique
	public static Territoire territoireSelectionne;
	String idMouseEnteredTerritory;
	public boolean debutPartie = true;

	/**
	 * Initialisation de l'interface du controller
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (!debutPartie) {
			Info_Territory_Attack.setVisible(false);
			Info_Territory.setVisible(false);
			Info_TerritoryPlayer.setVisible(false);
			IconPlayers.setVisible(false);
			ContainerSVG.setVisible(false);
			video.setVisible(true);

			MediaPlayer player = new MediaPlayer(
					new Media(getClass().getResource("backgroundLaunch.mp4").toExternalForm()));
			player.setVolume(0.1);
	        MediaView mediaView = new MediaView(player);
	        mediaView.setFitHeight(1080);
	        mediaView.setFitWidth(1920);
	        mediaView.setPreserveRatio(true);
	        video.getChildren().add(mediaView);
	        
	        TitleHome.setLayoutX(-500);
	        FadeTransition ft = new FadeTransition(Duration.millis(5000), TitleHome);
 	        ft.setFromValue(0);
 	        ft.setToValue(1);
 	        ft.play();	        

    		TranslateTransition tt = new TranslateTransition(Duration.millis(5000), TitleHome);
	        tt.setByY(-1000);
	        tt.play();
	        
	        player.setOnEndOfMedia(new Runnable() {
				public void run() {
					player.seek(Duration.ZERO);
				}
			});
	        
	        player.play();
	        
	        String img1 = "file:BackgroundGame.jpg";
	        String img2 = "file:background_ocean.jpg";
	        ObservableList<String> options = FXCollections.observableArrayList();
	        options.addAll(img1, img2);
	        //final ComboBox<String> comboBox = new ComboBox(options);
	        //ChoiceIconPlayer.setCellFactory(c -> new StatusListCell());
	        
	        /*Image icon;
	        String iconPath = "../resources.img.player.Player__1.png";
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
            ImageView iconImageView = new ImageView(icon);
            iconImageView.setFitHeight(30);
            iconImageView.setPreserveRatio(true);
            setGraphic(iconImageView);*/
	        /*String item = "Testfdsf";
	        ChoiceIconPlayer
            ChoiceIconPlayer.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> p) {
                    return new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            setText("ble");
                            Image icon = new Image(getClass().getResourceAsStream("./BackgroundGame.jpg"));
                            //Image icon = new Image(getClass().getClassLoader().getResourceAsStream("./BackgroundGame.jpg"));
                            System.out.println("test0");
                            if (item == null || empty) {
                                setGraphic(null);
                            } 
                            else {                           
                            	System.out.println("test");
                                int iconNumber = this.getIndex() + 1;
                                String iconPath = "./BackgroundGame.jpg";
                                //icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                icon = new Image(getClass().getResourceAsStream(iconPath));
                                ImageView iconImageView = new ImageView(icon);
                                iconImageView.setFitHeight(30);
                                iconImageView.setPreserveRatio(true);
                                setGraphic(iconImageView);
                            }
                        }
                    };
                }
            });*/
		}
		else {
			video.setVisible(false);
			TranslateNode translateNode = new TranslateNode(ContainerSVG);

			// Initialisation des musiques
			String musicFile = "src/ost/ost1.mp3";

			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mediaPlayer.seek(Duration.ZERO);
				}
			});
			mediaPlayer.setVolume(0);
			sliderVolumeOST.setValue(0);
			mediaPlayer.play();

			sliderVolumeOST.valueProperty().addListener((observable, oldValue, newValue) -> {
				mediaPlayer.setVolume((double) newValue);
			});

			// Placement du menu d'informations du joueur actuel
			Info_TerritoryPlayer.setLayoutX((double) BodyMap.getPrefWidth() - Info_TerritoryPlayer.getPrefWidth());

			// Initialisation de l'affichage des menus
			Info_Territory.setLayoutX(0);
			Info_Territory_Attack.setLayoutX(0);
			Info_Territory.setVisible(false);
			Info_Territory_Attack.setVisible(false);
			Info_TerritoryPlayer.setVisible(false);

			// Ajout des filtres pour espionner les mouvements pour bouger le panel
			// Container SVG
			ContainerSVG.addEventFilter(MouseEvent.MOUSE_PRESSED, translateNode.getOnMousePressedEventHandler());
			ContainerSVG.addEventFilter(MouseEvent.MOUSE_DRAGGED, translateNode.getOnMouseDraggedEventHandler());

			// Initialisation premier joueur exemple le nom
			// this.partieController.getJoueurEnCours().getNom();

			// Nom du joueur
			Info_TerritoryPlayer_Pseudo.setText(FXMLDocumentController.partieController.getJoueurEnCours().getNom());
			
			// On définit la couleur des territoires appartenant au joueur actuel
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
	}
	
	public class StatusListCell extends ListCell<String> {
	    protected void updateItem(String item, boolean empty){
	        super.updateItem(item, empty);
	        setGraphic(null);
	        setText(null);
	        if(item!=null){
	            ImageView imageView = new ImageView(new Image(item));
	            imageView.setFitWidth(40);
	            imageView.setFitHeight(40);
	            setGraphic(imageView);
	            setText("a");
	        }
	    }

	}

	@FXML
	private void handleMouseAction(MouseEvent event) {
		debutPartie=!debutPartie;
		// On récupère l'id du territoire
		String idFromClick;
		System.out.println(event.getPickResult().getIntersectedNode().getId()); // https://stackoverflow.com/questions/24302636/better-way-for-getting-id-of-the-clicked-object-in-javafx-controller
		idFromClick = event.getPickResult().getIntersectedNode().getId();
		// On split la chaîne de caractère pour ne garder que le prefix
		String[] tokens = idFromClick.split("__");
		String idFromClickPrefix = tokens[0];

		setInfoTerritory(idFromClickPrefix);

		// On active le panel avec transition
		if (!Info_Territory.isVisible()) {
			System.out.println(" Panel ouvert ");
			Info_Territory.setVisible(true);
			Info_Territory.setLayoutX(0);
			event.getPickResult().getIntersectedNode()
					.getStyleClass().add("effectHover");
		}

		// Si le territoire cliqué appartient au joueur, que le panel des territoires du
		// joueur actuel est ouvert
		// et que l'on est en renforts, alors on positionne le Scroll Pane
		if (Info_TerritoryPlayer.isVisible() && renfort) {
			int positionTerritorySelected = 4;
			int nbTerritoryOfPlayer = 15; // A COMPLETER DYNAMIQUEMENT
			nbTerritoryOfPlayer = FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire()
					.size();
			Territoire t1 = this.partieController.getCarte().recupererTerritoireNOM(idFromClickPrefix);
			
			int i=0;
			for (Territoire t : FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire()) {
				if (t != null) {
					//System.out.println(t.getNom() + " = " + t1.getNom().replaceAll(" ", "_"));
					/*if (t.getNom().replaceAll(" ", "_").equals(t1.getNom().replaceAll(" ", "_"))) {
						positionTerritorySelected=i;
					}*/
					i++;
				}			
			}
			ScrollPaneAddBackups.setVvalue((double) positionTerritorySelected / nbTerritoryOfPlayer - 0.01);
		} else {
			ScrollPaneAddBackups.setVvalue(0);
		}
	}

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
		
		
		// System.out.println(this.territoireSelectionne.getNom());
		System.out.println(idFromClickPrefixFinal);

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

		// Info joueur

		// On change l'image en fonction du territoire cliqué
		Info_Territory_Picture.setStyle("-fx-background-image: url(\"resources/img/country/" + idSelectedTerritory
				+ ".jpg\");-fx-background-size: 440 152;-fx-background-position: center;");	
	}

	/**
	 * Event appelé en cas de Scroll dans le pane contenant les SVG
	 */
	@FXML
	private void onScrollEventHandler(ScrollEvent event) {			
		AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
		double zoomFactor = 2;

		// Si molette vers le bas, on a un dezoom
		if (event.getDeltaY() < 0) {
			// if (ContainerSVG.getScaleX() >= 1)
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
		if (FXMLDocumentController.territoireSelectionne.getProprietaire()
				.equals(FXMLDocumentController.partieController.getJoueurEnCours())) {
			Info_Territory_Attack.setVisible(true);
		}
		
	}

	/**
	 * Permet de centrer la carte si l'utilsateur a perdu la carte, car les
	 * développeurs sont incompétents
	 */
	@FXML
	private void onActionCenterMapButtonHandler(ActionEvent event) {
		ContainerSVG.setTranslateX(0);
		ContainerSVG.setTranslateY(0);
	}

	/**
	 * Fermer le panel en cas de clic sur la croix (haut à gauche)
	 */
	@FXML
	private void onActionPaneCloseHandler(ActionEvent event) {
		Node node = (Node) event.getSource();

		// Taille fixe, l'UI ne changera pas
		Node newNode = node.getParent().getParent();
		newNode.setVisible(false);

		// Si c'est le panel d'informations du joueur, on réaffiche l'icône pour voir
		// les informations du joueur
		if (Objects.equals((String) node.getId(), new String("InfoTerritoryPlayer_CloseButton")))
			IconPlayers.setVisible(true);
		else if (Objects.equals((String) node.getId(), new String("InfoTerritory_CloseButton")))
			Info_Territory_Attack.setVisible(false);
	}

	@FXML
	private void onMouseClickedPaneOpenInfoPlayerHandler(MouseEvent event) {
		// Position dans le Grid Pane du Scroll Pane
		final int row = 10;
		Node node = (Node) event.getSource();
		Info_TerritoryPlayer.setVisible(true);
		System.out.println(event.getPickResult().getIntersectedNode().getId());
		// On rend invisible l'Anchor Pane contenant les icônes des joueurs
		node.getParent().setVisible(false);

		// Info joueur
		Info_TerritoryPlayer_NbTerritory.setText("Territoires contrôlés : "
				+ FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire().size());
		Info_TerritoryPlayer_NbRegion.setText("Régions contrôlées : "
				+ FXMLDocumentController.partieController.getJoueurEnCours().getListeRegion().size());

		Info_TerritoryPlayer_NameUnity__1.setText(Unite.nouveauSoldat().getNom());
		Info_TerritoryPlayer_NbUnity__1
				.setText("" + FXMLDocumentController.partieController.getJoueurEnCours().getListeUniteSoldat().size());

		Info_TerritoryPlayer_NameUnity__2.setText(Unite.nouveauCavalier().getNom());
		Info_TerritoryPlayer_NbUnity__2.setText(
				"" + FXMLDocumentController.partieController.getJoueurEnCours().getListeUniteCavalier().size());

		Info_TerritoryPlayer_NameUnity__3.setText(Unite.nouveauCanon().getNom());
		Info_TerritoryPlayer_NbUnity__3
				.setText("" + FXMLDocumentController.partieController.getJoueurEnCours().getListeUniteCanon().size());
		AfficherMenuRenforts afficherMenuRenforts = new AfficherMenuRenforts(AfficherMenuRenforts.controller = this,
				Info_TerritoryPlayer, ScrollPaneAddBackups, Info_TerritoryPlayer_GridPane, Body.getPrefHeight());

		// Si le joueur actuel clique sur son icône et que c'est le moment des renforts,
		// il peut en ajouter
		// et voir un résumé de toutes ses unités ainsi que sa mission secrète
		if (!ScrollPaneAddBackups.isVisible() && renfort) { // AJOUTER VERIF POUR MISSION SECRETE
			afficherMenuRenforts.DisplayInformations();
			// GridPaneAddBackupsManual.setStyle("-fx-grid-lines-visible: true;");
		} else {
			ScrollPaneAddBackups.setVisible(false);
			AfficherMenuRenforts.deleteRow(Info_TerritoryPlayer_GridPane, row);
			Info_TerritoryPlayer.setPrefHeight(Info_Territory.getPrefHeight());
			// System.out.println(Info_TerritoryPlayer_GridPane.getRowIndex(ScrollPaneAddBackups));
		}
	}

	/**
	 * Event permettant d'appliquer un effet de hover sur un ou plusieurs
	 * territoires ayant le même prefix NB : Appliquer l'effet directement dans le
	 * CSS était impossible sur plusieurs territoires
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

		/*
		 * for (String t : tokens) System.out.println(t);
		 */

		for (Node n : ListNodes) {
			//n.setStyle("-fx-effect:null");
			//n.getStyleClass().remove("effectHover");
			n.getStyleClass().removeIf(style -> style.equals("effectHover"));
		}

		// Pour chaque territoire, si l'id n'est pas nul (décor) et que le prefix du
		// territoire testé est égal
		// au prefix du territoire cliqué, on applique l'effet CSS de hover
		for (Node n : ListNodes) {
			if (n.getId() != null) {
				String[] tokensn = n.getId().split("__");
				String prefixn = tokensn[0];
				if (prefixn.equals(prefix)) {
					//n.setStyle("-fx-effect:innershadow(one-pass-box, #a3a3a3, 50, 0, 0, 0);");
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
						// On remplace les " " par des "_" pour la comparaison
						if (prefixn.equals(Info_Territory_NameTerritory.getText().replace(" ", "_"))) {
							// System.out.println(prefixn + "*" + Info_Territory_NameTerritory.getText());
							//n.styleProperty().set("effectHover");
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
	 * Event appelé en cas d'appui sur "Quitter le jeu" ou raccourci "Ctrl + A"
	 */
	@FXML
	private void closeGameAction() {
		Platform.exit();
	}
	/*
	 * public void setPartie(Partie p) { this.partie = p; }
	 */

	public AnchorPane getContainerSVG() {
		return this.ContainerSVG;
	}

	public StackPane getInfoTerritory() {
		return this.Info_Territory;
	}

	public void setTerritorySelectedBackup(boolean selected) {
		this.isTerritorySelectedBackup = selected;
	}

	public static ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}

	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}
}
