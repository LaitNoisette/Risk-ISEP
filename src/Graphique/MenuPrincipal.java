package Graphique;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {
String routerExecution=new String();

	@Override
	 public void start(Stage primaryStage) {
		primaryStage.setTitle("Lancement de la partie !");
        Group root = new Group();
       
        
        Scene scene = new Scene(root,1280 , 720, Color.WHITE);
       MenuLancementPartie m=new MenuLancementPartie();
       m.prefWidth(scene.getWidth());
        root.getChildren().add(m);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("cc je compile");
		Application.launch(MenuPrincipal.class,args);
		
	}

}
