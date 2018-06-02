package Graphique;

import java.io.File;

import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

import com.sun.javafx.event.EventQueue;

import Jeu.Partie;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {
	@FXML private BorderPane Body;
	@FXML private BorderPane header;
	@FXML private SVGPath Territory1;
	@FXML private SVGPath Territory2;
	@FXML private MenuItem CloseButton;
	@FXML private Slider sliderVolumeOST;
	@FXML private StackPane Info_Territory;
	@FXML private StackPane Info_Territory_Attack;
	@FXML private StackPane Info_TerritoryPlayer;
	@FXML private Pane Territory_Picture;
	@FXML private Pane BodyMap;
	@FXML private Text Territory_NameTerritory;
	@FXML private AnchorPane ContainerSVG;
	@FXML private ScrollPane ListTerritoryAttack, ScrollPaneAddBackups;
	@FXML private GridPane Grid_Info_Territory, Info_TerritoryPlayer_GridPane;
	@FXML private Button InfoTerritoryPlayer_CloseButton, InfoTerritory_CloseButton, InfoTerritoryAttack_CloseButton;
	@FXML private GridPane IconPlayers;
	
	private boolean isAnimationEnded = false;
	private boolean isPanelOpened = false;

	private boolean renfort=true;
	
	private Partie partie;
	public static Partie partieController;
	
	/**
     * Initialisation de l'interface du controller
     */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
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
			mediaPlayer.setVolume((double)newValue);
        });
		
		// Placement du menu d'informations du joueur actuel
		Info_TerritoryPlayer.setLayoutX((double)BodyMap.getPrefWidth()-Info_TerritoryPlayer.getPrefWidth());
		
		// Initialisation de l'affichage des menus
		Info_Territory.setLayoutX(0);
		Info_Territory_Attack.setLayoutX(0);
		Info_Territory.setVisible(false);
		Info_Territory_Attack.setVisible(false);
		Info_TerritoryPlayer.setVisible(false);
		
		// Ajout des filtres pour espionner les mouvements pour bouger le panel Container SVG
		ContainerSVG.addEventFilter(MouseEvent.MOUSE_PRESSED, translateNode.getOnMousePressedEventHandler());
		ContainerSVG.addEventFilter(MouseEvent.MOUSE_DRAGGED, translateNode.getOnMouseDraggedEventHandler());		
	}
	
	@FXML
	private void handleMouseAction(MouseEvent event) {			
		// On récupère l'id du territoire
		String idFromClick;
		System.out.println(event.getPickResult().getIntersectedNode().getId()); // https://stackoverflow.com/questions/24302636/better-way-for-getting-id-of-the-clicked-object-in-javafx-controller
		idFromClick = event.getPickResult().getIntersectedNode().getId();	
		// On split la chaîne de caractère pour ne garder que le prefix
		String[] tokens = idFromClick.split("__");
		String idFromClickPrefix = tokens[0];
		
		// On split la chaîne de caractère à nouveau pour supprimer tous les "_" par des " "
		String[] tokens2 = idFromClickPrefix.split("_");
		String idFromClickPrefixFinal =  "";
		// On vérifique la chaîne n'est pas nulle (pas de "_" dans la chaîne avant de split)
		if (tokens2.length >= 1) {
			for (String t : tokens2)
				idFromClickPrefixFinal += t + " ";
		}

		// On change le texte en fonction du territoire cliqué
		Territory_NameTerritory.setText(idFromClickPrefixFinal);
		
		// On change l'image en fonction du territoire cliqué
		Territory_Picture.setStyle("-fx-background-image: url(\"resources/img/country/"+idFromClickPrefix+".jpg\");-fx-background-size: 440 152;-fx-background-position: center;");	
		
		// On active le panel avec transition
		if (!Info_Territory.isVisible()) {
			System.out.println(" Panel ouvert ");
			
			Info_Territory.setVisible(true);
			Info_Territory.setLayoutX(0);
			isPanelOpened = true;
			//transitionDisplayMenu(Info_Territory, true);
			//isAnimationEnded = false;
		}	
		/*else if (Info_Territory.isVisible()) {
			System.out.println(" Panel fermé ");
			Info_Territory.setVisible(false);			
		}*/
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
			//if (ContainerSVG.getScaleX() >= 1)
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
		Info_Territory_Attack.setVisible(true);
	}
	
	private void transitionDisplayMenu(StackPane stackpane, boolean direction) {
		TranslateTransition translateTransition = new TranslateTransition(); 
		
		/*stackpane.setVisible(true);
		
		stackpane.setLayoutX(-500);
	      
	    //Setting the duration of the transition  
		translateTransition.setDuration(Duration.millis(250)); 
	      
	    //Setting the node for the transition 
		translateTransition.setNode(stackpane); 
	      
	    //Setting the value of the transition along the x axis. 
		translateTransition.setByX(500); 
	      
	    //Setting the cycle count for the transition Pas besoin de répéter
		//translateTransition_Info_Territory.setCycleCount(2); 
	      
	    //Setting auto reverse value to false 
		//translateTransition_Info_Territory.setAutoReverse(false);*/
		
		
		//stackpane.setVisible(true);
		translateTransition.setNode(stackpane); 
		translateTransition.setDuration(Duration.millis(250)); 	
		
		if (!direction) {		
			translateTransition.setByX(-500); 		
		}
		else {			
			stackpane.setLayoutX(-500);
			translateTransition.setByX(500);
		}
		
	    //Playing the animation 
		translateTransition.play(); 
		
		translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	isAnimationEnded = true;          	
            	if(!direction) {
            		stackpane.setVisible(false);
            	}
            }
		});		

	}
	
	private void transitionHideMenu(StackPane stackpane, TranslateTransition translatetransition) {
		/*translatetransition.setDuration(Duration.millis(250)); 	 
		translatetransition.setNode(stackpane); 
		translatetransition.setByX(-500);
		translatetransition.play();	*/
	}
	
	/**
     * Permet de centrer la carte si l'utilsateur a perdu la carte, car les développeurs sont incompétents
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

		// Si c'est le panel d'informations du joueur, on réaffiche l'icône pour voir les informations du joueur
		if (Objects.equals((String)node.getId(), new String("InfoTerritoryPlayer_CloseButton")))
			IconPlayers.setVisible(true);
	}
	
	@FXML
	private void onMouseClickedPaneOpenInfoPlayerHandler(MouseEvent event) {
		final int row = 9;
		Node node = (Node) event.getSource();  
		Info_TerritoryPlayer.setVisible(true);
		System.out.println(event.getPickResult().getIntersectedNode().getId());
		node.getParent().setVisible(false);
		if (renfort) 
			ScrollPaneAddBackups.setVisible(true);
		else {
			ScrollPaneAddBackups.setVisible(false);
			
			//Info_TerritoryPlayer.getChildren().clear();
			deleteRow(Info_TerritoryPlayer_GridPane,row);
			//Info_TerritoryPlayer.getChildren().lastIndexOf(Info_TerritoryPlayer);
			//Info_TerritoryPlayer.getChildren().remove(ScrollPaneAddBackups);
			Info_TerritoryPlayer.setPrefHeight(Info_Territory_Attack.getLayoutY());
		}
			
	}
	
	static void deleteRow(GridPane grid, final int row) {
	    Set<Node> deleteNodes = new HashSet<>();
	    for (Node child : grid.getChildren()) {
	        // get index from child
	        Integer rowIndex = GridPane.getRowIndex(child);

	        // handle null values for index=0
	        int r = rowIndex == null ? 0 : rowIndex;

	        if (r > row) {
	            // decrement rows for rows after the deleted row
	            GridPane.setRowIndex(child, r-1);
	        } else if (r == row) {
	            // collect matching rows for deletion
	            deleteNodes.add(child);
	        }
	    }

	    // remove nodes from row
	    grid.getChildren().removeAll(deleteNodes);
	}
	
	@FXML
	private void onMouseEnteredTerritoryHandler(MouseEvent event) {
		Node node = (Node) event.getSource();  
		ObservableList<Node> ListNodes = ContainerSVG.getChildren();
		
		String[] tokens = node.getId().split("__");
		String prefix = tokens[0];
		
		/*for (String t : tokens)
			  System.out.println(t);*/
		
		for (Node n:ListNodes) {
			if (n.getId()!=null) {
				String[] tokensn = n.getId().split("__");
				String prefixn = tokensn[0];
				if (prefixn.equals(prefix)) {
					n.setStyle("-fx-effect:innershadow(one-pass-box, #a3a3a3, 50, 0, 0, 0);");
				}
			}
		}	
	}
	
	@FXML
	private void onMouseExitedTerritoryHandler(MouseEvent event) {
		ObservableList<Node> ListNodes = ContainerSVG.getChildren();

		for (Node n:ListNodes)
			n.setStyle("");
		
	}
	
	/**
     * Event appelé en cas d'appui sur "Quitter le jeu" ou raccourci "Ctrl + A"
     */
	@FXML
	private void closeGameAction() {
	    Platform.exit();
	}
	
	public void setPartie(Partie p) {
		this.partie=p;
	}
}
