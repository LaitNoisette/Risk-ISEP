package Graphique;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Jeu.Carte;
import Jeu.Partie;
import Jeu.Territoire;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuPrincipal extends Application {	
	private int resolutionHeight, resolutionWidth;
	
	public MenuPrincipal() {
		this.resolutionHeight = 1080;
		this.resolutionWidth=1920;
	}	
	
	public static FXMLDocumentController controller;
	public static Partie partie;
	
	@Override
	public void start(Stage stage) throws Exception {
		// Localisation du fichier FXML.
		URL url = getClass().getResource("ui.fxml");
		// Chargement du bundle:
        //ResourceBundle bundle = ResourceBundle.getBundle("test/strings");
		ResourceBundle bundle = null;
        // Création du loader.
        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
        
        // Accès au contrôleur.
        FXMLDocumentController controller = (FXMLDocumentController) fxmlLoader.getController();
        MenuPrincipal.controller=(FXMLDocumentController) fxmlLoader.getController();
        
        //controller.setPartie(new Partie(3, Carte.CarteTestNY()));
        // Chargement du FXML.
        BorderPane root = (BorderPane) fxmlLoader.load();
        
        
        //fxmlLoader.setController(new FXMLDocumentController(new Partie(3, Carte.CarteTestNY())));
        // Création de la scène.
        Scene scene = new Scene(root, this.resolutionWidth, this.resolutionHeight); 
        
        stage.setMaximized(true);
        stage.setFullScreen(true);
        
        /*PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-2000);
        camera.setNearClip(0.1);
        camera.setFarClip(3000.0);
        //camera.setFieldOfView(35);
        scene.setCamera(camera);*/
	    
		scene.getStylesheets().add("Graphique/style.css"); 
	    stage.setTitle("Risk'ISEP");
	    stage.setScene(scene);
	    
	    //stage.getIcons().add(new Image(Graphique.class.getResourceAsStream("file:background_ocean.png")));
	    
	    stage.show();
	}
	
	public static void main(String[] args) {
		Partie p=new Partie(2, Carte.CarteNY());
		
		/*
		for (Territoire T : p.getCarte().getAllTerritoire()) {
			System.out.println(T.getNom());
		}
		
		*/
		//System.out.println(p.getCarte().recupererTerritoireNOM("Port Richmond").getNom());
		
		//FXMLDocumentController.partieController=p;
		System.out.println("cc je compile");
		Application.launch(MenuPrincipal.class,args);	
		//MenuPrincipal.controller.setPartie(p);
	}
	
	public int getResolutionWidth() {
		return this.resolutionWidth;
	}
	
	public int getResolutionHeight() {
		return this.resolutionHeight;
	}
}
