package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import controller.RentExceptions;
import controller.RentalRecordOperation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Apartment;
import model.Property;
import utilites.DateTime;



public class MainProgramWindows extends Application {

	int signal=0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button btHello=new Button("Hello");
		MenuBar menubar=new MenuBar();
		menubar.prefWidthProperty().bind(primaryStage.widthProperty());
		

		
		btHello.setOnAction(event->{
			btHello.setText("Hello World, I am JavaFx!");
		});

		Group root=new Group();
		ScrollPane pane1=new ScrollPane();
		Scene scene=new Scene(root,800,800);
		GridPane pane=new GridPane();
	    
		
		pane1.setPannable(true);
		pane1.prefWidthProperty().bind(primaryStage.widthProperty());
		pane1.prefHeightProperty().bind(primaryStage.heightProperty());
		pane1.setContent(pane);
		root.getChildren().add(pane1);
		
		
	
		pane.setAlignment(Pos.TOP_CENTER);
		pane.setHgap(20);
		pane.setVgap(20);
		
		pane.setPadding(new Insets(0,0,0,0));

 
		menubar=AddEvent();
		pane.add(menubar, 0, 0,40,1);
		addMainPageContent(pane);
		
			
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World");
		primaryStage.show();
	}
	
	public GridPane addMainPageContent(GridPane pane) {
		Button[] textMatrix=new Button[50];
		RentalRecordOperation rro=new RentalRecordOperation();
		Map<String, String> map=rro.selectAllRentalProperties();
		Set<String> set=map.keySet();
		Iterator<String> iter=set.iterator();
		while(iter.hasNext()) {
			String a=iter.next();
			System.out.print(a);
			System.out.print("\t\t");
			System.out.println(map.get(a));
			
		}
		for(int i=0;i<=15;i++) {
			Button b=new Button();
			textMatrix[i]=b;
		}
		for(int i=0;i<(map.size())/7;i++) {
			ImageView imageview=new ImageView();
			String s="";
			s="image/image"+(i+1)+".jpg";
			if(i>=15) {
				s="image/image.jpg";
			}
			Image image=new Image(s);
			imageview.setImage(image);
			imageview.setFitWidth(320);
			imageview.setFitHeight(320);
			BorderPane subpane=new BorderPane();
			subpane.setMaxWidth(320);
			subpane.setMaxHeight(113);
			
			
			Button b=new Button();
			Label t=new Label();
			String streetname="STREET_NAME%"+(i+1);
			Label t1=new Label();
			t1.setFont(Font.font(18));
			t.setText("This beautiful double storey home is situated in a peaceful & secluded location right in the heart of the Newport Village. This stylish home features three spacious bedrooms, master bedroom includes a walk in robe and a stylish ensuite also featuring a motorised awning on balcony overlooking spectacular views, spacious family room, great design, spacious open plan kitchen meals area, dishwasher included, as new appliances, great quality finishes, leading out to a spacious low maintenance court yard - great for entertainment, central sleek bathroom, this home also features x3 toilets, perfect size powder room, European laundry, split systems for convenience, plenty of cupboard space, floor boards and carpet throughout, double lock up garage, security system and so so much more. Perfect location,  Pet Friendly, be quick to inspect.\r\n" + 
					"");
			t.setWrapText(true);
			t.setTextOverrun(OverrunStyle.ELLIPSIS);
			System.out.println(streetname);
			System.out.println(map.get(streetname));
			t1.setText(map.get(streetname));
			textMatrix[i]=b;
			BackgroundFill bgf=new BackgroundFill(Color.LINEN, new CornerRadii(1), 
				     new Insets(0.0,0.0,0.0,0.0));
			Background bg=new Background(bgf);
			subpane.setBackground(bg);
			subpane.setRight(b);
			subpane.setCenter(t);
			subpane.setTop(t1);
			if(i%2==0) {
				pane.add(imageview, 1, 1+((i/2)+(i%2))*22,22,22);
				pane.add(subpane, 1, 1+((i/2)+(i%2))*22+17,17,6);
			}else if(i%2==1) {
				pane.add(imageview, 19, 1+((i/2)+(i%2)-1)*22,22,22);
				pane.add(subpane, 19, 1+((i/2)+(i%2)-1)*22+17,17,6);
			}
		}
		showPropertyDetail(textMatrix,map.size()/7);
		return pane;
	}
	
	public void showPropertyDetail(Button[] textMatrix,int length) {
		int i=0;
		for(i=0;i<length;i++) {
			textMatrix[i].setText("book");
		}
		textMatrix[0].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(1);
		});
		textMatrix[1].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(2);
		});
		textMatrix[2].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(3);
		});
		textMatrix[3].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(4);
		});
		textMatrix[4].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(5);
		});
		textMatrix[5].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(6);
		});
		textMatrix[6].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(7);
		});
		textMatrix[7].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(8);
		});
		textMatrix[8].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(9);
		});
		textMatrix[9].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(10);
		});
		textMatrix[10].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(11);
		});
		textMatrix[11].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(12);
		});
		textMatrix[12].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(13);
		});
		textMatrix[13].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(14);
		});
		textMatrix[14].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(15);
		});
		textMatrix[15].setOnMouseClicked(ActionEvent ->{
			CreatePropertyDetailWindow(16);
		});
	}
	
	public void EventAddProperty(MenuItem addProperty) {
		addProperty.setOnAction(event ->{
			Map<String,String> map=new HashMap<String,String>();
			map=CreateAddPropertyDialog();
			System.out.println(map.isEmpty());
			
			if(!map.isEmpty()&&!(signal==1)) {
				RentalRecordOperation rro=new RentalRecordOperation();
				rro.insertApartment(map);
				System.out.println(signal);
			}
		});
	}
	
	public void EventExitProgram(MenuItem exitMenuitem) {
		exitMenuitem.setOnAction(actionEvent -> Platform.exit());
	}
	
	
	
	public MenuBar AddEvent() {
		MenuBar menubar=new MenuBar();
		Menu fileMenu=new Menu("File");
		MenuItem exitMenuitem=new MenuItem("Exit Program");
		MenuItem addProperty=new MenuItem("Add Property");

		MenuItem returnProperty=new MenuItem("Reture Property");
		MenuItem propertyMaintenance=new MenuItem("Property Maintenance");
		MenuItem completeMaintenance=new MenuItem("Complete Maintenance");
		MenuItem displayAllProperty=new MenuItem("Display All Property");
		
		fileMenu.getItems().add(addProperty);

		fileMenu.getItems().add(returnProperty);
		fileMenu.getItems().add(propertyMaintenance);
		fileMenu.getItems().add(completeMaintenance);
		fileMenu.getItems().add(displayAllProperty);
		fileMenu.getItems().add(exitMenuitem);
		
		EventAddProperty(addProperty);
		EventExitProgram(exitMenuitem);


		menubar.getMenus().add(fileMenu);
		return menubar;
	}
	



	public Map<String, String> CreateAddPropertyDialog() {
		MenuBar menubar=new MenuBar();
		BorderPane borderPane=new BorderPane();
		
		
	    Dialog<Map<String,String>> dialog = new Dialog<>();
	    dialog.setTitle("TestName");
	    dialog.setWidth(300);
	    dialog.setHeight(300);
	
	    ButtonType buttonType = new ButtonType("OK", ButtonData.OK_DONE);
	    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	    dialog.getDialogPane().getButtonTypes().addAll(buttonType,buttonTypeCancel, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField Property_id = new TextField();
	    TextField Street_number = new TextField();
	    TextField Street_name= new TextField();
	    TextField Suburb=new TextField();
	    TextField Number_bedroom=new TextField();

	    menubar=AddEvent();
	    borderPane.setTop(menubar);
	    
	    gridPane.add(new Label("Property_id"), 0, 0);
	    gridPane.add(Property_id, 3, 0);
	    gridPane.add(new Label("Street_number:"), 0, 3);
	    gridPane.add(Street_number, 3, 3);
	    gridPane.add(new Label("Street_name:"), 0, 5);
	    gridPane.add(Street_name, 3, 5);
	    gridPane.add(new Label("Suburb:"), 0, 7);
	    gridPane.add(Suburb, 3, 7);
	    gridPane.add(new Label("Number_bedroom:"), 0, 9);
	    gridPane.add(Number_bedroom, 3, 9);

	    borderPane.setCenter(gridPane);
	    dialog.getDialogPane().setContent(borderPane);

	   
	    Platform.runLater(() -> Property_id.requestFocus());
	    Map<String, String> map=new HashMap<String, String>();
	    try {
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == buttonType) {
	        	System.out.println(Property_id.getText());
	        	if(!(Property_id.getText().startsWith("A_")||Property_id.getText().startsWith("S_"))) {
	        		System.out.println("enter");
	        		CreateAlert("Property_id has wrong input","please input a String witch startsWith A_ or S_ : ");
	        		CreateAddPropertyDialog();
	        	}
	        	int number=0;
	        	try {
	        		number=Integer.parseInt(Number_bedroom.getText());
	        	} catch(Exception e){
	        		CreateAlert("Number_bedroom has wrong input","please input a number ");
	        		CreateAddPropertyDialog();
	        		signal=1;
	        	}
	        	if(number>3||number<=0) {
	        		CreateAlert("Number_bedroom has wrong input","please input a number between 1 and 3 ");
	        		CreateAddPropertyDialog();
	        		signal=1;
	        	}
	        	System.out.println("the length is"+Property_id.getLength());
	        	
	        	if(Property_id.getText().isEmpty() ||Street_name.getText().isEmpty()) {
	        		CreateAlert("Property id or street name can not be empty","please input again ");
	        		System.out.println("enter");
	        		signal=1;
	        		
	        		return null;
	        	}
	        	System.out.println(signal);
	        
	        	
	        	if( Property_id.getText().startsWith("A_")) {
	        		map.put("Property_type", "Apartment");
	        	}else {
	        		map.put("Property_type", "PremiumSuite");
	        	}
	        	map.put("Property_id", Property_id.getText()+Street_name.getText().substring(0, 4));
	        	System.out.println(Property_id.getText()+Street_name.getText().substring(0, 4));
	        	map.put("Street_number", Street_number.getText());
	        	map.put("Street_name", Street_name.getText());
	        	map.put("Suburb", Suburb.getText());
	        	map.put("Number_bedroom", Number_bedroom.getText());
	        	map.put("Property_status", "empty");
	        	return map;
	        }
	        if (dialogButton == buttonTypeCancel) {
	        	Platform.exit();
	        	System.out.println("heiheihie");
	        	return null;
	        }
	    
	        return null;
	    });
	    }catch(Exception e) {
	    	CreateAlert("Error Happens", e.getMessage());
	    }
	    Optional<Map<String, String>> result = dialog.showAndWait();
	    return map;
	}

    private void CreateAlert(String string, String string2) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText(string2);
    	alert.setContentText(string);

    	alert.showAndWait();
		
	}
    
    public void CreatePropertyDetailWindow(int numberOfProperty) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	System.out.println(numberOfProperty);
    	alert.setWidth(400);
    	alert.setHeight(500);
    	GridPane gridPane=new GridPane();
    	BorderPane borderpane=new BorderPane();
    	
    	MenuBar menubar=new MenuBar();
    	menubar=AddEvent();
    	borderpane.setTop(menubar);
    	ButtonType buttonTypeOne = new ButtonType("rent");
    	ButtonType buttonTypeTwo = new ButtonType("return");
    	ButtonType buttonTypeThree = new ButtonType("maintance");
    	ButtonType buttonTypefour=new ButtonType("complete maintance");
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	RentalRecordOperation rro=new RentalRecordOperation();
    	Map<String,String> map=rro.selectAllRentalProperties();
    	
    	
    	TextField Street_number=new TextField();
    	Street_number.setPromptText(map.get("STREET_NUMBER%"+numberOfProperty));
    	TextField Street_name=new TextField();
    	Street_name.disabledProperty();
    	Street_name.setPromptText(map.get("STREET_NAME%"+numberOfProperty));
    	TextField Suburb=new TextField();
    	Suburb.disabledProperty();
    	Suburb.setPromptText(map.get("SUBURB%"+numberOfProperty));
    	TextField Number_bedroom=new TextField();
    	Number_bedroom.disabledProperty();
    	Number_bedroom.setPromptText(map.get("NUMBER_BEDROOM%"+numberOfProperty));
    	TextField Property_type=new TextField();
    	Property_type.disabledProperty();
    	Property_type.setPromptText(map.get("PROPERTY_TYPE%"+numberOfProperty));
    	TextField Property_status=new TextField();
    	Property_status.disabledProperty();
    	Property_status.setPromptText(map.get("PROPERTY_STATUS%"+numberOfProperty));

    	ImageView iv=new ImageView();
    	Image image=new Image("image/image"+numberOfProperty+".jpg");
    	iv.setImage(image);
	    gridPane.add(new Label("Street_number:"), 0, 3);
	    gridPane.add(Street_number, 3, 3);
	    gridPane.add(new Label("Street_name:"), 0, 5);
	    gridPane.add(Street_name, 3, 5);
	    gridPane.add(new Label("Suburb:"), 0, 7);
	    gridPane.add(Suburb, 3, 7);
	    gridPane.add(new Label("Number_bedroom:"), 0, 9);
	    gridPane.add(Number_bedroom, 3, 9);
	    gridPane.add(new Label("Property_type"), 0, 11);
	    gridPane.add(Property_type, 3, 11);
	    gridPane.add(new Label("Property_type"), 0, 13);
	    gridPane.add(Property_status, 3, 13);
	    gridPane.add(iv, 9, 0,14,10);
    	borderpane.setCenter(gridPane);
    	
    	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypefour,buttonTypeCancel);
    	alert.getDialogPane().setContent(borderpane);
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonTypeOne){
    		System.out.println("number of property++++++"+numberOfProperty);
    	    createRent(map.get("PROPERTY_ID"+"%"+numberOfProperty));
    	   
    	} else if (result.get() == buttonTypeTwo) {
    	    // ... user chose "Two"
    	} else if (result.get() == buttonTypeThree) {
    	    // ... user chose "Three"
    	} else {
    	    
    	}
    }


	private List<String> createRent(String property_id) {
		System.out.println("number of property"+property_id);
		MenuBar menubar=new MenuBar();
		BorderPane borderPane=new BorderPane();
		Property property=new Apartment();
		property.setPropertyId(property_id);
		
	    Dialog<List<String>> dialog = new Dialog<>();
	    dialog.setTitle("TestName");
	    dialog.setWidth(300);
	    dialog.setHeight(300);
	
	    ButtonType buttonType = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField custom_Id = new TextField();
	    TextField rent_Date = new TextField();
	    TextField estimated_Return_Date= new TextField();


	    menubar=AddEvent();
	    borderPane.setTop(menubar);
	    
	    gridPane.add(new Label("Custom_id:"), 0, 0);
	    gridPane.add(custom_Id, 3, 0);
	    gridPane.add(new Label("Rent_date"), 0, 3);
	    gridPane.add(rent_Date, 3, 3);
	    gridPane.add(new Label("Estimated_return_date"), 0, 5);
	    gridPane.add(estimated_Return_Date, 3, 5);
	   

	    borderPane.setCenter(gridPane);
	    dialog.getDialogPane().setContent(borderPane);

	   
	    Platform.runLater(() -> custom_Id.requestFocus());
	    
	    System.out.println("custom_id"+custom_Id.getText());
	    
	    List<String> l= new ArrayList<String>();
	    try {
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == buttonType) {
	        	int signal1=0;
	        	System.out.println(custom_Id.getText());
	        	if(custom_Id.getText().isEmpty()) {
	        		CreateAlert("custom_id has wrong imput", "custom_id can not be empty");
	        		createRent(property_id);
	        		signal1=1;
	        	}
	        	if(rent_Date.getText().isEmpty()) {
	        		CreateAlert("rent date can not be empty", "rent date can not be empty");
	        		createRent(property_id);
	        		signal1=1;
	        	}
	        	if(!DateTime.isValidDate(rent_Date.getText())){
	        		CreateAlert("rent data has wrong input","please input a dd/mm/yyyy Date:");
	        		createRent(property_id);
	        		signal1=1;
	        	}
	        	
	        	if(!DateTime.isValidDate(estimated_Return_Date.getText())) {
	        		CreateAlert("estimated return date has wrong input","please input a dd/mm/yyyy Date:");
	        		createRent(property_id);
	        		signal1=1;
	        	}
	        	if(estimated_Return_Date.getText().isEmpty()) {
	        		CreateAlert("estimated_Return_date has wrong input", "estimated_return_date can not be empty");
	        		createRent(property_id);
	        		signal1=1;
	        	}

	        	String[] dates = rent_Date.getText().split("/");
	        	String[] dates1=estimated_Return_Date.getText().split("/");
	        	int numberofRentDate=(Integer.parseInt(dates1[2])-Integer.parseInt(dates[2]))*365+(Integer.parseInt(dates1[1])-Integer.parseInt(dates[1]))*30+(Integer.parseInt(dates1[0])-Integer.parseInt(dates[0]));
	        	System.out.println(numberofRentDate);
	        	if(numberofRentDate<0) {
	        		CreateAlert("estimated_Return_date can not before the rent date","estimated_Return_date can not before the rent date");
	        		createRent(property_id);
	        		signal1=1;
	        	}
	        	DateTime dt=new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));
	        
	        	try {
	        		if(signal==0) {
	        			property.setPropertyId(property_id);
		        		System.out.println("prperty_id is "+property_id);
						property.rent(custom_Id.getText(), dt, numberofRentDate);
	        		}
	        		
					
				} catch (RentExceptions e) {
					CreateAlert("propertyid is null", "property id can not be null");
				}
	        	return l;
	        }
	        return null;

	    });
	    }catch(Exception e) {
	    	CreateAlert("Error Happens", e.getMessage());
	    }
	    Optional<List<String>> result = dialog.showAndWait();	
	    return null;
	}

	public static void main(String[] args) {
    	launch(args);
    }
}
