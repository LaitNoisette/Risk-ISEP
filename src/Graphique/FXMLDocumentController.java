package Graphique;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.event.EventQueue;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
	
	@FXML private SVGPath Country1;
	@FXML private SVGPath Country2;
	@FXML private SVGPath Country3;
	@FXML private MenuItem CloseButton;
	@FXML private Slider sliderVolumeOST;
	@FXML private StackPane Info_Territory;
	@FXML private Pane Territory_Picture;
	@FXML private Text Territory_NameTerritory;
	
	private boolean isOpened_Info_Territory = false;
	private TranslateTransition translateTransition_Info_Territory = new TranslateTransition(); 
	
	@FXML
	private void handleMouseAction(MouseEvent event) {	
		// On récupère l'id du territoire
		@SuppressWarnings("unused")
		String idFromClick;
		System.out.println(event.getPickResult().getIntersectedNode().getId()); // https://stackoverflow.com/questions/24302636/better-way-for-getting-id-of-the-clicked-object-in-javafx-controller
		idFromClick = event.getPickResult().getIntersectedNode().getId();	
		// On change le texte en fonction du territoire cliqué
		Territory_NameTerritory.setText(idFromClick);
		// On change l'image en fonction du territoire cliqué
		Territory_Picture.setStyle("-fx-background-image: url(\"resources/img/country/"+idFromClick+".jpg\");-fx-background-size: 440 152;-fx-background-position: center;");
		// On active le panel
		Info_Territory.setVisible(true);
		
		Info_Territory.setLayoutX(-500);
		
		if (!isOpened_Info_Territory) { 
		      
		    //Setting the duration of the transition  
			translateTransition_Info_Territory.setDuration(Duration.millis(250)); 
		      
		    //Setting the node for the transition 
			translateTransition_Info_Territory.setNode(Info_Territory); 
		      
		    //Setting the value of the transition along the x axis. 
			translateTransition_Info_Territory.setByX(500); 
		      
		    //Setting the cycle count for the transition Pas besoin de répéter
			//translateTransition_Info_Territory.setCycleCount(2); 
		      
		    //Setting auto reverse value to false 
			//translateTransition_Info_Territory.setAutoReverse(false); 
		      
		    //Playing the animation 
			translateTransition_Info_Territory.play(); 
		}
		else {
			translateTransition_Info_Territory.stop();
		}
	
		isOpened_Info_Territory = true;
		event.consume();	
	}
	
	@FXML
	private void closeGameAction() {
	    Platform.exit();
	}
	
	@FXML
	private void handleMouseClickOutsideInfoTerritory(MouseEvent event) {
		// On vérifie que le clic n'est pas sur un territoire
		if (isOpened_Info_Territory) {
			System.out.println("pouet");
	
			translateTransition_Info_Territory.setByX(-500);
			translateTransition_Info_Territory.play(); 
			
			//Info_Territory.setVisible(false); Pas besoin avec l'animation
			isOpened_Info_Territory = false;
			
			
			
			event.consume();
		}	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Info_Territory.setVisible(false);
		/* Initialisation des musiques */
		String musicFile = "src/ost/ost1.mp3";     // For example
		 
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
		
		
	}
}
