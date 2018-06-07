package Graphique;

import java.util.HashSet;
import java.util.Set;

import Jeu.Territoire;
import Jeu.Unite;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Affiche tous les renforts que le joueur doit placer sur les territoires qu'il possède
 */
public class AfficherMenuRenforts extends FXMLDocumentController{
	private StackPane Info_TerritoryPlayer;
	private ScrollPane ScrollPaneAddBackups;
	private GridPane Info_TerritoryPlayer_GridPane;
	double defaultHeight;
	double defaultHeightEvent = this.defaultHeight;
	
	public static FXMLDocumentController controller;
	
	/**
     * Constructeur
     */
	public AfficherMenuRenforts(FXMLDocumentController ctrl, StackPane stp, ScrollPane sp, GridPane gp, double prefH) {
		this.Info_TerritoryPlayer = stp;
		this.ScrollPaneAddBackups=sp;
		this.Info_TerritoryPlayer_GridPane=gp;
		this.defaultHeight=prefH;
	}
	
	public void DisplayInformations () {
		final int row = 10;
		//double defaultHeight = Info_TerritoryPlayer.getPrefHeight();
		
		GridPane GridPaneAddBackupsManual = new GridPane();
		Text Labelr0c0 = new Text();
		Text Labelr0c3 = new Text();
		
		ScrollPaneAddBackups.setVisible(true);
		Info_TerritoryPlayer_GridPane.setVisible(true);
		Info_TerritoryPlayer.setPrefHeight(this.defaultHeight);
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
		//Labelr0c3.setText("[Nb]");
		Labelr0c3.setText(""+FXMLDocumentController.partieController.getJoueurEnCours().getPointUnite()+"");
		
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
		GridPaneAddBackupsManual.getRowConstraints().get(0).setPrefHeight(35);
		
		// Pour chaque territoire que le joueur possède, on ajoute les différents éléments du menu
		int h=0;
			for (Territoire territoire : FXMLDocumentController.partieController.getJoueurEnCours().getListeTerritoire()) {
				
			
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
					//t.setText("Williamsburg ([Région]) :");
					t.setText(territoire.getNom()+" ("+territoire.getRegion().getNom()+") :");
					GridPane.setConstraints(t, 0, h*5+1+i);
					GridPane.setMargin(t, new Insets(0, 0, 0, 20));
					GridPaneAddBackupsManual.getChildren().add(t);	
					t.getStyleClass().add("AddUnityLabel");
					t.getStyleClass().add("CursorHand");
					//GridPaneAddBackupsManual.getRowConstraints().get(h*5+1+i).setPrefHeight(35);
					GridPaneAddBackupsManual.getRowConstraints().get(h*5+1+i).setPrefHeight(35);
					//t.setId("Williamsburg__Region__"+h);
					t.setId(territoire.getNom()+"__"+territoire.getRegion().getNom().trim()+"__"+h);
					//System.out.println(t.getId());
					AnchorPane ContainerSVG = AfficherMenuRenforts.controller.getContainerSVG();
					StackPane InfoTerritory = AfficherMenuRenforts.controller.getInfoTerritory();
					
					t.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// On récupère le nom du territoire
							String idFromClick = event.getPickResult().getIntersectedNode().getId();
							String[] tokens = idFromClick.split("__");
							String idFromClickPrefix = tokens[0];
							 
							ObservableList<Node> ListNodes = ContainerSVG.getChildren();
							for (Node n:ListNodes) {
								if (n.getId()!=null) {									
									String[] tokensn = n.getId().split("__");
									String prefixn = tokensn[0];
									if (prefixn.equals(idFromClickPrefix)) {										
										Bounds primScreenBounds = n.getBoundsInParent();
										ContainerSVG.setTranslateX(-primScreenBounds.getMinX()-(primScreenBounds.getWidth() - ContainerSVG.getPrefWidth())/2);
										ContainerSVG.setTranslateY(-primScreenBounds.getMinY()-((int)primScreenBounds.getHeight() - (int)ContainerSVG.getPrefHeight()) / 2 - (ContainerSVG.getPrefHeight()-defaultHeight));
										ContainerSVG.setScaleX(1);
										ContainerSVG.setScaleY(1);
										n.getStyleClass().add("effectHover");
										controller.setInfoTerritory(prefixn);
										InfoTerritory.setVisible(true);
										controller.setTerritorySelectedBackup(true);
										break;
									}
								}
							}	

						}
			        });
				}
				// 3 labels pour les 3 unités
				else {
					GridPaneAddBackupsManual.getRowConstraints().get(h*5+1+i).setPrefHeight(35);
					Unite uReference=null;
					int nbUniteTerritoire=0;
					//Gestion nom unite en fonction de la boucle
					if(i==2) {
						uReference=Unite.nouveauSoldat();
						//Liste Soldat 
						nbUniteTerritoire=territoire.getListeUniteSoldat().size();
					}
					else if(i==3) {
						uReference=Unite.nouveauCavalier();
						//Liste Cavalier 
						nbUniteTerritoire=territoire.getListeUniteCavalier().size();
					}
					else if(i==4) {
						uReference=Unite.nouveauCanon();
						//Liste Canon
						nbUniteTerritoire=territoire.getListeUniteCanon().size();
					}
					for (int j=0;j<3;j++) {
						
						
						Text t2 = new Text();
						if (j==0) {
							//t2.setText("[Nb] [Nom Unité1] ([Coût = X])");
							
							t2.setText(""+nbUniteTerritoire+" "+uReference.getNom()+" (Coût = "+Integer.toString(uReference.getCout())+")");
							GridPane.setMargin(t2, new Insets(0, 0, 0, 40));
							t2.getStyleClass().add("AddUnityLabel");
							//t2.setId("NomTerritoire__Region__"+h+"__Unite__"+j+"__Label");	
							
							t2.setId(territoire.getNom()+"__"+territoire.getRegion().getNom().trim()+"__"+h+"__"+uReference.getNom().trim()+"__"+j+"__Label");
							
							//System.out.println(t2.getId());
						}
						else if (j==1) {
							t2.setText("+");
							t2.getStyleClass().add("buttonPlusAddUnity");
							//t2.setId("NomTerritoire__Region__"+h+"__Unite__"+j+"__Plus");
							t2.setId(territoire.getNom()+"__"+territoire.getRegion().getNom().trim()+"__"+h+"__"+uReference.getNom().trim()+"__"+j+"__Plus");
							String[] formatDataAddUnity = t2.getId().split("__");
							int indice=0;
							for (String string : formatDataAddUnity) {
								System.out.println(indice+" : "+string);
								indice++;
							}
							//System.out.println(t2.getId());
						}
						else {
							t2.setText("Nb");
							GridPane.setMargin(t2, new Insets(0, 0, 0, 20));
							t2.getStyleClass().add("AddUnityLabel");
							//t2.setId("NomTerritoire__Region__"+h+"__Unite__"+j+"__Nb");
							t2.setId(territoire.getNom().trim()+"__"+territoire.getRegion().getNom().trim()+"__"+h+"__"+uReference.getNom().trim()+"__"+j+"__Nb");
							//System.out.println(t2.getId());
						}
						GridPane.setConstraints(t2, j, h*5+1+i);	
						GridPaneAddBackupsManual.getChildren().add(t2);	
					}
				}					
			}
			h++;
		}	
			//GridPaneAddBackupsManual.setGridLinesVisible(true);
	}
	
	static void deleteRow(GridPane grid, final int row) {
	    Set<Node> deleteNodes = new HashSet<>();
	    
	    for (Node child : grid.getChildren()) {
	        // Récupération de l'index du child
	        Integer rowIndex = GridPane.getRowIndex(child);

	        // Vérification si c'est les valeurs de l'index sont nulles
	        int r = rowIndex == null ? 0 : rowIndex;

	        if (r > row) {
	            // Supression ligne par ligne
	            GridPane.setRowIndex(child, r-1);
	        } else if (r == row) {
	            // Récupération des bonnes lignes pour supression
	            deleteNodes.add(child);
	        }
	    }
	    // Supression du node
	    grid.getChildren().removeAll(deleteNodes);
	}
}
