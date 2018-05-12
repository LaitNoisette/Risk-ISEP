package Graphique;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuLancementPartie extends Application {

	 public void start(Stage primaryStage) {
	        primaryStage.setTitle("Hello World");
	        Group root = new Group();
	        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
	        Button btn = new Button();
	        btn.setLayoutX(100);
	        btn.setLayoutY(80);
	        btn.setText("Hello World");
	        btn.setOnAction(new EventHandler<ActionEvent>() {

	            public void handle(ActionEvent event) {
	                System.out.println("Hello World");
	            }
	        });
	        root.getChildren().add(btn);        
	        primaryStage.setScene(scene);
	        //primaryStage.setVisible(true);
	        primaryStage.setFullScreen(true);
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("cc je compile");
		Application.launch(MenuLancementPartie.class,args);
		
	}

}
