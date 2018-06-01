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

public class MenuPrincipal extends Application /*implements EventHandler<ActionEvent>*/ {
	//String routerExecution=new String();
	SVGPath svg;
	/*@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Lancement de la partie !");
        Group root = new Group();
       
        
        Scene scene = new Scene(root,1280 , 720, Color.WHITE);
       MenuLancementPartie m=new MenuLancementPartie();
       m.prefWidth(scene.getWidth());
        root.getChildren().add(m);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}*/

	/* PREMIERE METHODE AVEC LE SVG DEFINI DANS LE CODE */
	
	/*@Override
	public void start(Stage stage) throws Exception {
		//Parent root = FXMLLoader.load(getClass().getResource("../test.fxml"));
		
		BorderPane borderRoot = new BorderPane();
		Pane map = new Pane();
		GridPane gridPane = new GridPane();
		FlowPane header = new FlowPane();
	    Scene scene = new Scene(borderRoot, 1000, 1000);
	    
	    header.setPrefHeight(40);
	    map.setPrefHeight(200); // Si trop petit, les events mouse déconnent
	    
	    
	    // Aide tuto : https://www.youtube.com/watch?v=DUBWBe8nRGc
	    svg = new SVGPath();
	    svg.setContent("M 836.39 34.09 C 842.29 34.41 846.57 28.51 852.59 29.79 C 858.25 31.43 864.07 32.69 870.01 32.56 C 874.75 32.93 881.62 32.13 884.05 37.38 C 880.74 39.42 877.36 41.29 873.97 43.17 C 874.28 43.65 874.91 44.61 875.22 45.09 C 878.03 44.44 880.83 43.74 883.63 43.00 C 887.15 45.97 890.81 48.78 894.42 51.65 C 892.13 53.63 889.75 55.48 887.42 57.40 C 892.45 63.72 897.32 70.25 903.17 75.85 C 910.35 81.68 909.87 92.10 915.23 99.21 C 909.46 100.74 903.98 103.17 898.29 104.96 C 898.85 110.08 900.49 115.47 898.84 120.52 C 894.83 123.31 891.12 128.24 885.78 127.94 C 883.18 128.09 879.68 127.44 878.27 130.29 C 878.33 133.94 880.28 137.20 881.41 140.60 C 878.38 141.55 875.41 142.64 872.51 143.92 C 868.65 140.54 864.66 137.34 860.51 134.33 C 863.89 131.72 867.43 129.25 870.66 126.42 C 865.67 121.43 860.73 116.39 855.89 111.26 C 860.27 110.18 864.63 109.16 869.04 108.13 C 866.64 105.09 864.24 102.02 861.50 99.29 C 857.61 95.69 851.80 95.84 847.31 93.50 C 843.42 90.36 841.49 85.57 839.11 81.32 C 835.89 76.46 830.28 74.27 825.55 71.23 C 818.16 67.65 813.71 58.96 804.81 58.76 C 803.60 55.05 801.40 51.81 799.08 48.72 C 798.85 48.38 798.39 47.70 798.16 47.36 C 802.44 41.78 809.74 42.57 815.92 41.57 C 815.27 40.07 814.63 38.58 814.00 37.09 C 821.39 35.60 828.88 34.79 836.39 34.09 Z");
	    svg.setStroke(Color.BLACK);
	    svg.setFill(Color.RED);
	    
	    //header.getChildren().addAll(svg); // Ancien test
	    
	    map.getChildren().addAll(svg);
	    gridPane.getChildren().addAll(map);
	    
	    //borderRoot.setTop(header);
	    borderRoot.setTop(map);
	    borderRoot.setCenter(gridPane); // Pas utile, pourquoi ?
	    
	    svg.setOnMouseClicked( new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("SVG");
            }
        });
	    
	    svg.setOnMouseEntered( new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("hover");
            	svg.setStyle("-fx-effect:innershadow(one-pass-box, #000000, 50, 0.7, 1, 1)");
            }
        });
	    
	    svg.setOnMouseExited( new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("pas hover");
            	svg.setStyle("");
            }
        });
	    
		//scene.getStylesheets().add("test.css"); 
	    stage.setTitle("Test Carte");
	    stage.setScene(scene);
	    stage.show();
	}*/
	
	
	public static FXMLDocumentController controller;
	public static Partie partie;
	
	@Override
	public void start(Stage stage) throws Exception {
		// Localisation du fichier FXML.
		URL url = getClass().getResource("test.fxml");
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
        Scene scene = new Scene(root, 1920, 1080); 
        
        stage.setMaximized(true);
        stage.setFullScreen(true);
	    
		scene.getStylesheets().add("Graphique/test.css"); 
	    stage.setTitle("FXML Welcome");
	    stage.setScene(scene);
	    
	    //stage.getIcons().add(new Image(Graphique.class.getResourceAsStream("file:background_ocean.png")));
	    
	    stage.show();
	}
	
	public static void main(String[] args) {
		Partie p=new Partie(3, Carte.CarteTestNY());
		// TODO Auto-generated method stub
		FXMLDocumentController.partieController=p;

		System.out.println("cc je compile");
		
		Application.launch(MenuPrincipal.class,args);	
		//MenuPrincipal.controller.setPartie(p);
		

	}
}
