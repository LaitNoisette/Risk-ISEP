package Graphique;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Affiche tous les renforts que le joueur doit placer sur les territoires qu'il possède
 */
public class AfficherMenuRenforts {
	private StackPane Info_TerritoryPlayer;
	private ScrollPane ScrollPaneAddBackups;
	private GridPane Info_TerritoryPlayer_GridPane;
	
	/**
     * Constructeur
     */
	public AfficherMenuRenforts(StackPane stp, ScrollPane sp, GridPane gp) {
		this.Info_TerritoryPlayer = stp;
		this.ScrollPaneAddBackups=sp;
		this.Info_TerritoryPlayer_GridPane=gp;
	}
	
	public void DisplayInformations () {
		final int row = 9;
		double defaultHeight = Info_TerritoryPlayer.getPrefHeight();
		
		GridPane GridPaneAddBackupsManual = new GridPane();
		Text Labelr0c0 = new Text();
		Text Labelr0c3 = new Text();
		
		ScrollPaneAddBackups.setVisible(true);
		Info_TerritoryPlayer_GridPane.setVisible(true);
		Info_TerritoryPlayer.setPrefHeight(defaultHeight);
		// Ajout du Scroll Pane
		Info_TerritoryPlayer_GridPane.addRow(row, ScrollPaneAddBackups);
		ScrollPaneAddBackups.getStyleClass().add("PlayerListTerritory");
		// Ajout du Grid Pane contenant les informations des renforts
		ScrollPaneAddBackups.setContent(GridPaneAddBackupsManual);
		
		// Définition du tableau : colonnes
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(284);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPrefWidth(20);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPrefWidth(73);
		GridPaneAddBackupsManual.getColumnConstraints().addAll(col1,col2,col3);
		// Définition du tableau : lignes
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		GridPaneAddBackupsManual.getRowConstraints().addAll(row1,row2);
		
		// Configuration des labels de la première ligne qui sont fixes
		Labelr0c0.setText("Nombre de points disponibles :");
		Labelr0c3.setText("[Nb]");
		Labelr0c0.getStyleClass().add("firstRowAddUnityLabel");
		Labelr0c3.getStyleClass().addAll("firstRowAddUnityLabel", "firstRowAddUnityNumber");

		GridPane.setConstraints(Labelr0c0, 0, 0);
		GridPane.setColumnSpan(Labelr0c0, 2);
		GridPane.setMargin(Labelr0c0, new Insets(0, 0, 0, 20));	
		GridPane.setConstraints(Labelr0c3, 2, 0);
		GridPane.setColumnSpan(Labelr0c3, 1);
		GridPane.setMargin(Labelr0c3, new Insets(0, 0, 0, 20));
		
		GridPaneAddBackupsManual.getChildren().add(Labelr0c0);		
		GridPaneAddBackupsManual.getChildren().add(Labelr0c3);			
		GridPaneAddBackupsManual.getRowConstraints().get(0).setPrefHeight(40);
		
		// Pour chaque territoire que le joueur possède, on ajoute les différents éléments du menu
		for (int h=0;h<4;h++) {
			// 5 = 1 separateur + 1 label descriptif + 3 labels (3 unités)
			for (int i=0;i<5;i++) {	
				RowConstraints r = new RowConstraints();
				GridPaneAddBackupsManual.getRowConstraints().add(r);
				
				// Séparateur
				if (i==0) {
					Separator separator = new Separator();
					GridPaneAddBackupsManual.getRowConstraints().get(h*5+1).setPrefHeight(15);
					GridPane.setConstraints(separator, 0, h*5+1);
					GridPane.setColumnSpan(separator, 3);
					separator.getStyleClass().addAll("SeparatorInfoPlayer");
					GridPaneAddBackupsManual.getChildren().add(separator);				
				}
				// Label descriptif du territoire et de la région
				else if (i==1) {
					Text t = new Text();
					t.setText("[Nom du Territoire] ([Région]) :");
					GridPane.setConstraints(t, 0, h*5+1+i);
					GridPane.setMargin(t, new Insets(0, 0, 0, 20));
					GridPaneAddBackupsManual.getChildren().add(t);	
					t.getStyleClass().add("AddUnityLabel");
					GridPaneAddBackupsManual.getRowConstraints().get(h*5+1+i).setPrefHeight(40);
					t.setId("NomTerritoire_Region__"+h);
					System.out.println(t.getId());
				}
				// 3 labels pour les 3 unités
				else {
					for (int j=0;j<3;j++) {
						Text t2 = new Text();
						if (j==0) {
							t2.setText("[Nb] [Nom Unité1] ([Coût = X])");
							GridPane.setMargin(t2, new Insets(0, 0, 0, 40));
							t2.getStyleClass().add("AddUnityLabel");
							t2.setId("NomTerritoire_Region__"+h+"__Unite__"+j+"__Label");
							System.out.println(t2.getId());
						}
						else if (j==1) {
							t2.setText("+");
							t2.getStyleClass().add("buttonPlusAddUnity");
							t2.setId("NomTerritoire_Region__"+h+"__Unite__"+j+"__Plus");
							System.out.println(t2.getId());
						}
						else {
							t2.setText("[Nb]");
							GridPane.setMargin(t2, new Insets(0, 0, 0, 20));
							t2.getStyleClass().add("AddUnityLabel");
							t2.setId("NomTerritoire_Region__"+h+"__Unite__"+j+"__Nb");
							System.out.println(t2.getId());
						}
						GridPane.setConstraints(t2, j, h*5+1+i);	
						GridPaneAddBackupsManual.getChildren().add(t2);	
					}
				}					
			}
		}
	}
}
