package game.controller;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorAdjustBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BattleGUI extends Application {

	private HBox hBox1,hBox2,hBox3;
	private Stage lane;
	public Lane l,L2,L3;
	public RadioButton rb1,rb2,rb3,rb4,lane1E,lane2E,lane3E;
	int code;
	private  Lane pickedLane;
	private Battle Ebattle;
    private Stage primaryStage;
    private Scene scene1,weaponShopSceneH,easyGameModeScene,hardGameModeScene,LoseE,LoseH,ModeScene;
    Parent root;
 

    @Override
    public void start(Stage primaryStage) throws IOException, InvalidLaneException, InsufficientResourcesException {
        this.primaryStage = primaryStage;
        Battle Ebattle=new Battle(1,0,50,3,250);
      

       primaryStage.setWidth(2800);
        primaryStage.setHeight(1600);
      
        
        // 2nd SCENE: MODE SCENE 
        
        Button backButton = new Button("Back to start menu");
        backButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
        
        backButton.setStyle(
        		 "-fx-background-color: rgba(0, 0, 0, 0.5);" +  // Transparent background
        	                "-fx-text-fill: white; " +              // Text color
        	                "-fx-border-color: white; " +     // Transparent border
        	                "-fx-border-width: 2px;"           
            );
        
        backButton.setOnMouseEntered(e -> {
            scaleDown(backButton).stop();
            scaleUp(backButton).play();
        });

      
        backButton.setOnMouseExited(e -> {
            scaleDown(backButton).play();
            scaleUp(backButton).stop();
        });
  

        Label labelMode = new Label("Choose a game mode");
//        labelMode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 100));
//        labelMode.setFill(Color.WHITE);
//        labelMode.setStroke(Color.BLACK);
//        //GameName.setStrokeWidth(2.5); 

   styleLabel2(labelMode);
   
        Button easyModeButtonT=new Button("EASY MODE");
     
        easyModeButtonT.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
        easyModeButtonT.setStyle(
       		 "-fx-background-color: rgba(0, 0, 0, 0.5);" +
                     "-fx-text-fill: green; " +             
                     "-fx-border-color: green; " +   
                     "-fx-border-width: 2px;"                
                 );
        
    
        
      
        
        Label easyLabel = new Label("Easy mode start with "+"\n"+"more resources to"+"\n"+" build and upgrade "+"\n"+"your defenses");
        
        textShow(easyModeButtonT,easyLabel);
        
        
        
        Button hardModeButton = new Button("HARD MODE");
        
        hardModeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
        
        hardModeButton.setStyle(
        		 "-fx-background-color: rgba(0, 0, 0, 0.5);" + 
                "-fx-text-fill: red; " +              
                "-fx-border-color: red; " +    
                "-fx-border-width: 2px;"               
            );
    
        
        
HBox labelHbox=new HBox();
labelHbox.setAlignment(Pos.CENTER);
labelHbox.getChildren().add(labelMode);
       VBox modeSceneVBox=new VBox();
        HBox buttonBox = new HBox(40);
        HBox labelText =new HBox(20);
        labelText.setPadding(new Insets(150)); 
        



 Label hardLabel = new Label("Hard mode start with "+"\n"+"less resources to"+"\n"+" challlenge the player");
 
 textShow(hardModeButton,hardLabel);
 

    
        buttonBox.getChildren().addAll(easyModeButtonT, hardModeButton,backButton);
        labelText.getChildren().addAll(easyLabel,hardLabel);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a BorderPane to organize the layout
        VBox root = new VBox(35);
        root.setPadding(new Insets(100, 0, 0, 0));
//        root.setTop(labelMode);
//        root.setCenter(buttonBox);
//        root.setBottom(labelText);
        root.getChildren().addAll(labelHbox,buttonBox,labelText);
        root.setAlignment(Pos.CENTER);
        
        // MODE SCENE
        
        Image modeImg = new Image("file:/C:/Users/nourm/OneDrive/Pictures/mode22.jpg");
		ImageView modeView=new ImageView(modeImg);
		
		BorderPane imgPane2=new BorderPane();
		imgPane2.getChildren().add(modeView);
		
		modeView.setPreserveRatio(false); 
		imgPane2.setCenter(root);
	
        
        Scene ModeScene = new Scene(imgPane2, 1800, 1500);
        modeView.fitWidthProperty().bind(ModeScene.widthProperty());
		modeView.fitHeightProperty().bind(ModeScene.heightProperty());
        
        
        backButton.setOnAction(e -> {
            
            primaryStage.setScene(scene1);
            
                  });
        

    
        
        Image modeImg2 = new Image("file:/C:/Users/nourm/OneDrive/Pictures/mode22.jpg");
		ImageView modeView2=new ImageView(modeImg2);
		
		BorderPane imgPane3=new BorderPane();
		imgPane3.getChildren().add(modeView2);
		
		modeView.setPreserveRatio(false); 
		
	HBox secondLayout=new HBox(300);
        VBox labelsForSecond=new VBox(15);
       VBox ButtonsForSecond=new VBox(90);
       secondLayout.getChildren().addAll(ButtonsForSecond,labelsForSecond);
       
       ButtonsForSecond.setPadding(new Insets(0, 0, 0, 50));
        Button modeButton=new Button("Game Mode");
        
        modeButton.setOnAction(e -> {
            
            primaryStage.setScene(ModeScene);
        
                  });
        
        
        modeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        
        modeButton.setStyle(
                "-fx-background-color: transparent; " + 
                "-fx-text-fill: white; " +           
                "-fx-border-color: transparent; " +    
                "-fx-border-width: 0px;"            
            );
        
        Label modeLabel = new Label("click here to"+"\n"+" select the game mode"+"\n"+"to start the game");
        
        modeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        modeLabel.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.5);" + 
                "-fx-text-fill: white; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px;"
               
        );
        modeLabel.setVisible(false); // Initially invisible
        modeLabel.setPadding(new Insets(20)); // Padding for the label content

     
        modeButton.setOnMouseEntered(e ->{ 
        	modeLabel.setVisible(true);
        	scaleUp(modeButton).play();
        	scaleDown(modeButton).stop();
        });
        

      
        modeButton.setOnMouseExited(e ->{ 
        	modeLabel.setVisible(false);
        	scaleUp(modeButton).stop();
        	scaleDown(modeButton).play();
        	});
 
        
        Button settingsButton=new Button("Settings"); 
        settingsButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        
        settingsButton.setStyle(
                "-fx-background-color: transparent; " + 
                "-fx-text-fill: white; " +            
                "-fx-border-color: transparent; " +   
                "-fx-border-width: 0px;"              
            );
        
        //AUDIO
     
        AudioManager.initialize("file:///C:/Users/nourm/OneDrive/Pictures/gameAudio.mp3");
       
        //SLIDER
        
        Slider volumeSlider = new Slider(0,100,50); // Min value, Max value, Initial value
        
        volumeSlider.setMaxSize(800, 200);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);

        // Bind the slider value to the media player's volume
       // mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        AudioManager.getMediaPlayer().volumeProperty().bind(volumeSlider.valueProperty());
        //BRIGHTNESS
        @SuppressWarnings({ "rawtypes", "deprecation" })
       ColorAdjust colorAdjust = new ColorAdjust();
               
               // Create a slider for brightness adjustment
               Slider brightnessSlider = new Slider(-1, 1, 0); // Min value, Max value, Initial value
               brightnessSlider.setShowTickLabels(true);
               brightnessSlider.setShowTickMarks(true);
               
               // Bind the slider value to the brightness property of the ColorAdjust effect
               colorAdjust.brightnessProperty().bind(brightnessSlider.valueProperty());
               
               // Create a label for the slider
               Label brightnessLabel = new Label("Adjust Brightness:");
               
               // Create the root layout and apply the ColorAdjust effect to it
             
              
        Button backToOptionScene=new Button("Return");
        
       
        
        
        
        // Create a layout and add the slider
        VBox settingRoot = new VBox(volumeSlider, brightnessLabel, brightnessSlider,backToOptionScene);
        settingRoot.setEffect(colorAdjust); 
        settingRoot.setAlignment(Pos.CENTER);
        // Set up the scene and stage
        Scene settingScene = new Scene(settingRoot, 300, 100);
       
    
        
        
        
        
        settingsButton.setOnAction(e -> {
    		primaryStage.setScene(settingScene);
    		
    	 });
       
        Label settingLabel = new Label("You can adjust"+"\n"+" game settings here");
     
        settingLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        settingLabel.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.5);" + 
                "-fx-text-fill: white; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px;"+
                "-fx-font-size: 40px;"
        );
        settingLabel.setVisible(false);
        settingLabel.setPadding(new Insets(20)); 

        settingsButton.setOnMouseEntered(e -> {
       	 settingLabel.setVisible(true);
           scaleDown(settingsButton).stop();
           scaleUp(settingsButton).play();
       });

     
       settingsButton.setOnMouseExited(e -> {
       	 settingLabel.setVisible(false);
           scaleDown(settingsButton).play();
           scaleUp(settingsButton).stop();
       });

        
        Button infoAboutGame=new Button("Game Info");
        infoAboutGame.setStyle(
                "-fx-background-color: transparent; " + 
                "-fx-text-fill: white; " +            
                "-fx-border-color: transparent; " +   
                "-fx-border-width: 0px;"              
            );
        infoAboutGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        
        Label infoLabel = new Label("More information"+"\n"+"about the game");
    
       
       textShow(infoAboutGame,infoLabel);
        
        Button photoGallery=new Button(" Photo Gallery");
        photoGallery.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        
        photoGallery.setStyle(
                "-fx-background-color: transparent; " +
                "-fx-text-fill: white; " +              
                "-fx-border-color: transparent; " +  
                "-fx-border-width: 0px;"           
            );
        
        
        Label photoLabel = new Label("    Go to"+"\n"+"photo gallery");
        
        
        textShow(photoGallery,photoLabel);

        
        
        Label options=new Label("Please choose an options");
        styleLabel2(options);

        
        ButtonsForSecond.getChildren().addAll(options,modeButton,settingsButton,infoAboutGame,photoGallery);
        labelsForSecond.getChildren().addAll(modeLabel,settingLabel,infoLabel,photoLabel);
        ButtonsForSecond.setAlignment(Pos.CENTER_LEFT);
        labelsForSecond.setAlignment(Pos.CENTER);
        
        imgPane3.setCenter(secondLayout);
        modeView2.fitWidthProperty().bind(imgPane3.widthProperty());
        modeView2.fitHeightProperty().bind(imgPane3.heightProperty());
        modeView2.setPreserveRatio(false); 
   	Scene secondScene=new Scene(imgPane3, 400, 300);
   	imgPane3.setEffect(colorAdjust); 

   	
    backToOptionScene.setOnAction(e -> {
		primaryStage.setScene(secondScene);
		
	 });
        
    //1ST SCENE STARTS HERE 
    
        // Create buttons for Scene 1
        Button startButton = new Button("Start game");
    
        startButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
      
        startButton.setStyle(
                "-fx-background-color: transparent; " + 
                "-fx-text-fill: white; " +             
                "-fx-border-color: transparent; " +  
                "-fx-border-width: 0px;"              
            );
        
        startButton.setOnMouseEntered(e-> {
        
            scaleDown(startButton).stop();
            scaleUp(startButton).play();
        });

      
        startButton.setOnMouseExited(e -> {
        
            scaleDown(startButton).play();
            scaleUp(startButton).stop();
        });
        
        
        Button instructionsButton = new Button("Instructions");
        instructionsButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        
        instructionsButton.setStyle(
                "-fx-background-color: transparent; " + 
                "-fx-text-fill: white; " +            
                "-fx-border-color: transparent; " +   
                "-fx-border-width: 0px;"           
            );
        
        instructionsButton.setOnMouseEntered(e -> {
            
            scaleDown(instructionsButton).stop();
            scaleUp(instructionsButton).play();
        });

      
        instructionsButton.setOnMouseExited(e -> {
        
            scaleDown(instructionsButton).play();
            scaleUp(instructionsButton).stop();
        });
        
        
        
     // GETTING LANES FOR EASY BATTLE 
        PriorityQueue<Lane> LTemp = new PriorityQueue<>();
        
        Lane l= Ebattle.getLanes().poll();
        LTemp.add(l);
         Lane L2= Ebattle.getLanes().poll();
         LTemp.add(L2);
         Lane L3= Ebattle.getLanes().poll();
         LTemp.add(L3);
         Ebattle.getLanes().addAll(LTemp);
       
        startButton.setOnAction(e -> {
          
            primaryStage.setScene(secondScene);
        
        });


        	instructionsButton.setOnAction(e -> {
        		
        		showDialog("INSTRUCTIONS","You must select a game mode ."+"\n"+ "Once selected , the game will start. You should"+"\n"+" either pass turn or purchase a weapon"+"\n"+" and deploy it into your lane of choice"+"\n"+" in the weapon shop. The game will terminate  "+"\n"+"once all the lanes have been lost.");
        	
        	 });
     
     //GAME NAME 
        Text GameName = new Text("   ATTACK ON"+"\n"+" TITAN: UTOPIA");
        GameName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 100));
        GameName.setFill(Color.WHITE);
        GameName.setStroke(Color.BLACK);
        //GameName.setStrokeWidth(2.5); 

        VBox vbox1 = new VBox(20);
        vbox1.getChildren().addAll(GameName,startButton, instructionsButton);
        vbox1.setAlignment(Pos.CENTER); 

        // Create Scene 1
    
        
    	Image myImage=new Image("file:/C:/Users/nourm/OneDrive/Pictures/ATTACKOT.jpg");
		ImageView mv=new ImageView(myImage);
		
		BorderPane imgPane=new BorderPane();
		imgPane.getChildren().add(mv);
		
		  mv.setPreserveRatio(false); 
		  imgPane.setCenter(vbox1);
		    scene1 = new Scene(imgPane, 1800, 1500);
		    imgPane.setEffect(colorAdjust); 
		
		primaryStage.setScene(scene1);
		primaryStage.setResizable(false);
		primaryStage.show();
	
		  mv.fitWidthProperty().bind(scene1.widthProperty());  // Bind width to scene's width
	        mv.fitHeightProperty().bind(scene1.heightProperty()); 
     
	        
//1ST SCENE ENDS HERE
    
        
        // 4TH SCENE - WEAPON SHOP SCENE STARTS HERE
        Button goToWeaponShopButton = new Button("Go to Weapon Shop");

        VBox vbox2 = new VBox(10); 
        vbox2.getChildren().addAll( goToWeaponShopButton);
        vbox2.setAlignment(Pos.CENTER); 

      
        //REDUNDANT START HERE BUT NOT SURE WHERE ELSE IS USED
        //RADIO BUTTON LANES FOR EASY MODE SCENE HERE
        RadioButton lane1E = new RadioButton("Lane 1");
        RadioButton lane2E = new RadioButton("Lane 2");
        RadioButton lane3E = new RadioButton("Lane 3");
        
       
    
        //create and set toggle group for lanes
        ToggleGroup toggleGroupLanes = new ToggleGroup();
        lane1E.setToggleGroup(toggleGroupLanes);
        lane2E.setToggleGroup(toggleGroupLanes);
        lane3E.setToggleGroup(toggleGroupLanes);
        //REDUNDANT END HERE
      
        // Create radio buttons for WeaponShop scene
      
        
        // Create a label for WeaponShop scene
       // Label label = new Label("Choose a Weapon:");
      //  label.setStyle("-fx-font-weight: bold;");
        
        //Create label for resources in easy mode
      
        
//        
       
     //   Label TotalResources=new Label("Total Resources:");
        
      //  VBox VresourcesE=new VBox(10);
   
        
        
        	//TotalResources.setText("Total Resources:"+Ebattle.getResourcesGathered());
        	// VresourcesE.getChildren().addAll(TotalResources);
           //  VresourcesE.setAlignment(Pos.CENTER_RIGHT);
            
             
    
             
             Button backtoEbattleScene = new Button("Back to Game");
           
        // Create a vertical box layout for WeaponShop scene
        HBox LanesEbox=new HBox(10);
        VBox weaponShopVBox = new VBox(10); // 10 is spacing between elements
       
       
        
        LanesEbox.getChildren().addAll(lane1E,lane2E,lane3E);
        
        weaponShopVBox.setAlignment(Pos.CENTER_LEFT); // Align to the left 
       LanesEbox.setAlignment(Pos.TOP_CENTER);

  
        Image weaponShopBackground=new Image("file:/C:/Users/nourm/OneDrive/Pictures/weaponshop2.jpg");
      		ImageView weaponShopImageView=new ImageView(weaponShopBackground);
      	
      		BorderPane wsImgPane=new BorderPane();
      		wsImgPane.getChildren().add(weaponShopImageView);
      		
      		weaponShopImageView.setPreserveRatio(false); 
      		
      		//PC VBOX CONTENT
      		VBox piercingCannonBox=new VBox(40);
      		piercingCannonBox.setAlignment(Pos.CENTER);
      		piercingCannonBox.setMaxHeight(70);
      		piercingCannonBox.setPadding(new Insets(20));
      		piercingCannonBox.setStyle(
                   "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                   "-fx-border-color: grey; " +
                   "-fx-border-width: 2px;"
           );
      		
      		Label PCweaponNameLabel=new Label("ANTI-TITAN SHELL");
      		PCweaponNameLabel.setAlignment(Pos.CENTER);
      		PCweaponNameLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 50));
      		PCweaponNameLabel.setStyle(
                  "-fx-text-fill: white; " 
                 
          );
      		
      		Label PCdamageLabel= new Label("Damage points: 10");
      		PCdamageLabel.setAlignment(Pos.CENTER);
      		PCdamageLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		PCdamageLabel.setStyle(
      			     "-fx-text-fill: white; " 
      		          );
      		
      		Label PCtypeLabel=new Label("Type: Piercing Cannon");
      		PCtypeLabel.setAlignment(Pos.CENTER);
      		PCtypeLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		PCtypeLabel.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		Label pricePC=new Label("Price: $25.00");
      		pricePC.setAlignment(Pos.CENTER);
      		pricePC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		pricePC.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		
      		
      		Button viewWeaponButtonPC=new Button("VIEW WEAPON");
      		viewWeaponButtonPC.setAlignment(Pos.CENTER);
      		viewWeaponButtonPC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
             
      		viewWeaponButtonPC.setStyle(
      			  "-fx-background-color: white;" +
      					
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		viewWeaponButtonPC.setOnMouseEntered(e ->{ 	
             	scaleUp(viewWeaponButtonPC).play();
             	scaleDown(viewWeaponButtonPC).stop();
             });
             

           
      		viewWeaponButtonPC.setOnMouseExited(e ->{ 
             	scaleUp(viewWeaponButtonPC).stop();
             	scaleDown(viewWeaponButtonPC).play();
             	});
      		
      		
      		Button purchaseButtonPC=new Button(" BUY WEAPON ");
      		purchaseButtonPC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
            
      		purchaseButtonPC.setStyle(
                     "-fx-background-color: green; " +
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		purchaseButtonPC.setOnMouseEntered(e->{ 	
             	scaleUp(purchaseButtonPC).play();
             	scaleDown(purchaseButtonPC).stop();
             });
            
           
      		purchaseButtonPC.setOnMouseExited(e ->{ 
             	scaleUp(purchaseButtonPC).stop();
             	scaleDown(purchaseButtonPC).play();
             	});
      		
      		//LANE SCENE 
      		 Image laneBackground=new Image("file:/C:/Users/nourm/OneDrive/Pictures/weaponshop2.jpg");
       		ImageView laneImageView=new ImageView(laneBackground);
       	
       		
       		
       		weaponShopImageView.setPreserveRatio(false); 
      		BorderPane laneImgPane=new BorderPane();
      		laneImgPane.getChildren().add(laneImageView);
      		Scene pickLaneScene=new Scene(laneImgPane,1800, 1500);
      		laneImgPane.setEffect(colorAdjust); 
      		
      		purchaseButtonPC.setOnAction(e -> {
                 code=1;
                 Stage lane=new Stage();
                 lane.setMaxWidth(700);
                 lane.setHeight(700);
                 lane.setScene(pickLaneScene);
                 lane.show();
                       });
      		
      		
      		
      		
      		
HBox LanesButtons=new HBox(30);
LanesButtons.setAlignment(Pos.CENTER);
Button LaneOne=new Button("Lane 1");
styleButton(LaneOne);


LaneOne.setOnAction(e -> {
    
	pickedLane=l;

          });


Button LaneTwo=new Button("Lane 2");
styleButton(LaneTwo);
LaneTwo.setOnAction(e -> {
    
	pickedLane=L2;
	
          });

Button LaneThree=new Button("Lane 3");
styleButton(LaneThree);
LaneThree.setOnAction(e -> {
    
	pickedLane=L3;
	
          });

styleButton(LaneTwo);
LanesButtons.getChildren().addAll(LaneOne,LaneTwo,LaneThree);

HBox continueButtons=new HBox(30);
continueButtons.setAlignment(Pos.CENTER);
Button continueButton=new Button("CONTINUE");
styleButton(continueButton);
Label resourcesView=new Label();
styleLabel(resourcesView);
resourcesView.setText("Total Resources:" +"\n" +"       $"+ Ebattle.getResourcesGathered()+".00");

continueButtons.getChildren().addAll(continueButton);
 

	VBox laneSceneLayout=new VBox(30);
		laneSceneLayout.setAlignment(Pos.CENTER);
		laneSceneLayout.getChildren().addAll(LanesButtons,continueButtons,resourcesView);
		laneImgPane.setCenter(laneSceneLayout);
		laneImgPane.setEffect(colorAdjust); 


continueButton.setOnAction(e -> {
    try {
        Ebattle.purchaseWeapon(code, pickedLane);
    } catch (InsufficientResourcesException e1) {
        showDialog("","UNABLE TO PURCHASE,"+"\n"+"NOT ENOUGH RESOURCES");
    } catch (InvalidLaneException e1) {
        showDialog("","LANE IS NO LONGER AVAILABLE");
    } catch (Exception e1) {
        // Handle any other exceptions
        e1.printStackTrace();
    }
    
    resourcesView.setText("Total Resources:" +"\n" +"$"+ Ebattle.getResourcesGathered()+".00" );
});

	
      		 Image imageOfPC = new Image("file:/C:/Users/nourm/OneDrive/Pictures/w1.png");
     		ImageView modeViewPC=new ImageView(imageOfPC);
      		piercingCannonBox.getChildren().addAll(modeViewPC,PCweaponNameLabel,PCdamageLabel,PCtypeLabel,pricePC,viewWeaponButtonPC,purchaseButtonPC);
      		
      		
      		//SC VBOX CONTENT
    		VBox sniperCannonBox=new VBox(40);
    		sniperCannonBox.setAlignment(Pos.CENTER);
    		sniperCannonBox.setMaxHeight(70);
    		sniperCannonBox.setPadding(new Insets(20));
    		sniperCannonBox.setStyle(
                   "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                   "-fx-border-color: grey; " +
                   "-fx-border-width: 2px;"
           );
      		
      		Label SCweaponNameLabel=new Label("LONG RANGE SPEARS");
      		SCweaponNameLabel.setAlignment(Pos.CENTER);
      		SCweaponNameLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 50));
      		SCweaponNameLabel.setStyle(
                  "-fx-text-fill: white; " 
                 
          );
      		
      		Label SCdamageLabel= new Label("Damage points: 35");
      		SCdamageLabel.setAlignment(Pos.CENTER);
      		SCdamageLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		SCdamageLabel.setStyle(
      			     "-fx-text-fill: white; " 
      		          );
      		
      		Label SCtypeLabel=new Label("Type: Sniper Cannon");
      		SCtypeLabel.setAlignment(Pos.CENTER);
      		SCtypeLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		SCtypeLabel.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		Label priceSC=new Label("Price: $25.00");
      		priceSC.setAlignment(Pos.CENTER);
      		priceSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		priceSC.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		
      		
      		Button viewWeaponButtonSC=new Button("VIEW WEAPON");
      		viewWeaponButtonSC.setAlignment(Pos.CENTER);
      		viewWeaponButtonSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
             
      		viewWeaponButtonSC.setStyle(
      			  "-fx-background-color: white;" +
      					
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		viewWeaponButtonSC.setOnMouseEntered(e ->{ 	
             	scaleUp(viewWeaponButtonSC).play();
             	scaleDown(viewWeaponButtonSC).stop();
             });
             

           
      		viewWeaponButtonSC.setOnMouseExited(e ->{ 
             	scaleUp(viewWeaponButtonSC).stop();
             	scaleDown(viewWeaponButtonSC).play();
             	});
      		
      		
      		Button purchaseButtonSC=new Button(" BUY WEAPON ");
      		purchaseButtonSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
            
      		purchaseButtonSC.setStyle(
                     "-fx-background-color: green; " +
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		purchaseButtonSC.setOnMouseEntered(e ->{ 	
             	scaleUp(purchaseButtonSC).play();
             	scaleDown(purchaseButtonSC).stop();
             });
            
           
      		purchaseButtonSC.setOnMouseExited(e ->{ 
             	scaleUp(purchaseButtonSC).stop();
             	scaleDown(purchaseButtonSC).play();
             	});
      		
      		
      		purchaseButtonSC.setOnAction(e -> {
                 code=2;
                 Stage lane=new Stage();
                 lane.setMaxWidth(700);
                 lane.setHeight(700);
                 lane.setScene(pickLaneScene);
                 lane.show();
                       });
      			
      		 Image imageOfSC = new Image("file:/C:/Users/nourm/OneDrive/Pictures/w2.png");
     		ImageView modeViewSC=new ImageView(imageOfSC);
     		sniperCannonBox.getChildren().addAll(modeViewSC,SCweaponNameLabel,SCdamageLabel,SCtypeLabel,priceSC,viewWeaponButtonSC,purchaseButtonSC);
      		
      		
      		
      		//VSC
     		VBox volleySpreadCannonBox=new VBox(40);
     		volleySpreadCannonBox.setAlignment(Pos.CENTER);
     		volleySpreadCannonBox.setMaxHeight(70);
     		volleySpreadCannonBox.setPadding(new Insets(20));
     		volleySpreadCannonBox.setStyle(
                   "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                   "-fx-border-color: grey; " +
                   "-fx-border-width: 2px;"
           );
      		
      		Label VSCweaponNameLabel=new Label("WALL SPREAD CANNON");
      		VSCweaponNameLabel.setAlignment(Pos.CENTER);
      		VSCweaponNameLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 50));
      		VSCweaponNameLabel.setStyle(
                  "-fx-text-fill: white; " 
                 
          );
      		
      		Label VSCdamageLabel= new Label("Damage points: 5");
      		VSCdamageLabel.setAlignment(Pos.CENTER);
      		VSCdamageLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		VSCdamageLabel.setStyle(
      			     "-fx-text-fill: white; " 
      		          );
      		
      		Label VSCtypeLabel=new Label("Type: Volley Spread Cannon");
      		VSCtypeLabel.setAlignment(Pos.CENTER);
      		VSCtypeLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		VSCtypeLabel.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		Label priceVSC=new Label("Price: $100.00");
      		priceVSC.setAlignment(Pos.CENTER);
      		priceVSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		priceVSC.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		
      		
      		Button viewWeaponButtonVSC=new Button("VIEW WEAPON");
      		viewWeaponButtonVSC.setAlignment(Pos.CENTER);
      		viewWeaponButtonVSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
             
      		viewWeaponButtonVSC.setStyle(
      			  "-fx-background-color: white;" +
      					
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		viewWeaponButtonVSC.setOnMouseEntered(event ->{ 	
             	scaleUp(viewWeaponButtonVSC).play();
             	scaleDown(viewWeaponButtonVSC).stop();
             });
             

           
      		viewWeaponButtonVSC.setOnMouseExited(event ->{ 
             	scaleUp(viewWeaponButtonVSC).stop();
             	scaleDown(viewWeaponButtonVSC).play();
             	});
      		
      		
      		Button purchaseButtonVSC=new Button(" BUY WEAPON ");
      		purchaseButtonVSC.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
            
      		purchaseButtonVSC.setStyle(
                     "-fx-background-color: green; " +
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		purchaseButtonVSC.setOnMouseEntered(event ->{ 	
             	scaleUp(purchaseButtonVSC).play();
             	scaleDown(purchaseButtonVSC).stop();
             });
            
           
      		purchaseButtonVSC.setOnMouseExited(event ->{ 
             	scaleUp(purchaseButtonVSC).stop();
             	scaleDown(purchaseButtonVSC).play();
             	});
      		
      	
      		
      		purchaseButtonVSC.setOnAction(e -> {
                 code=3;
                 Stage lane=new Stage();
                 lane.setMaxWidth(700);
                 lane.setHeight(700);
                 lane.setScene(pickLaneScene);
                 lane.show();
                       });
      		
      	
      		 Image imageOfVSC = new Image("file:/C:/Users/nourm/OneDrive/Pictures/w3.png");
     		ImageView modeViewVSC=new ImageView(imageOfVSC);
     		volleySpreadCannonBox.getChildren().addAll(modeViewVSC,VSCweaponNameLabel,VSCdamageLabel,VSCtypeLabel,priceVSC,viewWeaponButtonVSC,purchaseButtonVSC);
      		
     		//WALL TRAP 
     		
     		VBox wallTrapBox=new VBox(40);
     		wallTrapBox.setAlignment(Pos.CENTER);
     		wallTrapBox.setMaxHeight(60);
     		wallTrapBox.setPadding(new Insets(20));
     		wallTrapBox.setStyle(
                   "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                   "-fx-border-color: grey; " +
                   "-fx-border-width: 2px;"
           );
      		
      		Label WTweaponNameLabel=new Label("PROXIMITY TRAP");
      		WTweaponNameLabel.setAlignment(Pos.CENTER);
      		WTweaponNameLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 50));
      		WTweaponNameLabel.setStyle(
                  "-fx-text-fill: white; " 
                 
          );
      		
      		Label WTdamageLabel= new Label("Damage points: 100");
      		WTdamageLabel.setAlignment(Pos.CENTER);
      		WTdamageLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		WTdamageLabel.setStyle(
      			     "-fx-text-fill: white; " 
      		          );
      		
      		Label WTtypeLabel=new Label("Type: Wall Trap");
      		WTtypeLabel.setAlignment(Pos.CENTER);
      		WTtypeLabel.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		WTtypeLabel.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		Label priceWT=new Label("Price: $75.00");
      		priceWT.setAlignment(Pos.CENTER);
      		priceWT.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
      		priceWT.setStyle(
     			     "-fx-text-fill: white; " 
     		          );
      		
      		
      		
      		Button viewWeaponButtonWT=new Button("VIEW WEAPON");
      		viewWeaponButtonWT.setAlignment(Pos.CENTER);
      		viewWeaponButtonWT.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
             
      		viewWeaponButtonWT.setStyle(
      			  "-fx-background-color: white;" +
      					
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		viewWeaponButtonWT.setOnMouseEntered(event ->{ 	
             	scaleUp(viewWeaponButtonWT).play();
             	scaleDown(viewWeaponButtonWT).stop();
             });
             

           
      		viewWeaponButtonWT.setOnMouseExited(event ->{ 
             	scaleUp(viewWeaponButtonWT).stop();
             	scaleDown(viewWeaponButtonWT).play();
             	});
      		
      		
      		Button purchaseButtonWT=new Button(" BUY WEAPON ");
      		purchaseButtonWT.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 40));
            
      		purchaseButtonWT.setStyle(
                     "-fx-background-color: green; " +
                     "-fx-text-fill: black; " +            
                     "-fx-border-color: transparent; " +   
                     "-fx-border-width: 0px;"              
                 );
      		
      		purchaseButtonWT.setOnMouseEntered(event ->{ 	
             	scaleUp(purchaseButtonWT).play();
             	scaleDown(purchaseButtonWT).stop();
             });
            
           
      		purchaseButtonWT.setOnMouseExited(event ->{ 
             	scaleUp(purchaseButtonWT).stop();
             	scaleDown(purchaseButtonWT).play();
             	});
      		
      	
      		
      		purchaseButtonWT.setOnAction(e -> {
                 code=4;
                 Stage lane=new Stage();
                 lane.setMaxWidth(700);
                 lane.setHeight(700);
                 lane.setScene(pickLaneScene);
                 lane.show();
                       });
      		
      	
      		 Image imageOfWT = new Image("file:/C:/Users/nourm/OneDrive/Pictures/w4.png");
     		ImageView modeViewWT=new ImageView(imageOfWT);
     		wallTrapBox.getChildren().addAll(modeViewWT,WTweaponNameLabel,WTdamageLabel,WTtypeLabel,priceWT,viewWeaponButtonWT,purchaseButtonWT);
      
     		//WEAPONSHOP LAYOUT
     	
     		VBox xButtonBox=new VBox();
     		Button xButton=new Button();
     		xButtonBox.setAlignment(Pos.BASELINE_RIGHT);
     		xButtonBox.getChildren().addAll(xButton);
     		xButton.setOnAction(e -> {
           primaryStage.setScene(easyGameModeScene);
                      });
     		
     		
     		Image imageOfX = new Image("file:/C:/Users/nourm/OneDrive/Pictures/x.png");
     		ImageView modeViewX=new ImageView(imageOfX);
     		xButton.setGraphic(modeViewX);
     		
     		VBox wsNameBox=new VBox();
     		Label weaponShopName=new Label("WEAPON SHOP");
     		styleLabel2(weaponShopName);
     		wsNameBox.getChildren().add(weaponShopName);
     		wsNameBox.setAlignment(Pos.CENTER);
     		HBox weaponShopLayout1=new HBox(1000);
     		weaponShopLayout1.setMinWidth(2000);
     		weaponShopLayout1.setMaxWidth(2000);
     		weaponShopLayout1.getChildren().addAll(xButtonBox,wsNameBox);
     		//weaponShopLayout1.setAlignment(Pos.BASELINE_RIGHT);
      		HBox weaponShopLayoutNew1=new HBox(35);
      		VBox weaponShopLayoutNew2=new VBox(35);
      		weaponShopLayoutNew2.setPadding(new Insets(40));
      		weaponShopLayoutNew1.setAlignment(Pos.CENTER);
      	   weaponShopLayoutNew1.setPadding(new Insets(100));
      		weaponShopLayoutNew1.getChildren().addAll(piercingCannonBox,sniperCannonBox,volleySpreadCannonBox,wallTrapBox);
      		weaponShopLayoutNew2.getChildren().addAll(weaponShopLayout1,weaponShopLayoutNew1);
      		wsImgPane.setCenter(weaponShopLayoutNew2);
      		weaponShopImageView.fitWidthProperty().bind(wsImgPane.widthProperty());
      		weaponShopImageView.fitHeightProperty().bind(wsImgPane.heightProperty());
             
      		
            Scene easyWeaponShopScene=new Scene(wsImgPane,1800, 1500);
           

            goToWeaponShopButton.setOnAction(e -> {
                
                primaryStage.setScene(easyWeaponShopScene);
            });

            //WEAPON SHOP ENDS HERE

        
     // Create a label for the current score
        Label currentScoreLabel = new Label("Current Score: ");
        Label currentTurnLabel = new Label("Current Turn: ");
        Label currentphaseLabel = new Label("Current phase: ");
        Label currentResourcesLabel = new Label("Current Resources: ");
       
        currentScoreLabel.setText("Current Score: "+Ebattle.getScore());
        currentScoreLabel.setStyle("-fx-font-weight: bold;");
          
        
        Label AvailableLanesE = new Label(" Available lanes: ");
     AvailableLanesE.setText("Available lanes: "+Ebattle.getLanes().size());
     AvailableLanesE.setStyle("-fx-font-weight: bold;");
      
        
        currentTurnLabel.setText(" Current Turn: "+Ebattle.getNumberOfTurns());
        currentTurnLabel.setStyle("-fx-font-weight: bold;");
        
        currentphaseLabel.setText(" Current phase: "+Ebattle.getBattlePhase());
        currentphaseLabel.setStyle("-fx-font-weight: bold;");
        
        currentResourcesLabel.setText("Current Resources: "+Ebattle.getResourcesGathered());
        currentResourcesLabel.setStyle("-fx-font-weight: bold;");
        
       
        // Create a horizontal box layout for the top of easyGameMode scene
        HBox topHBox = new HBox(10); // 10 is spacing between elements
      
        topHBox.getChildren().addAll(currentScoreLabel,currentTurnLabel,currentphaseLabel,currentResourcesLabel,AvailableLanesE);
        topHBox.setAlignment(Pos.TOP_CENTER); // Center align the content in the top HBox
        topHBox.setStyle("-fx-background-color: #336699;"); 
        
        // Create a horizontal box layout for titans info for the bottom of easyGameMode scene
        HBox bottomHBox = new HBox(10); // 10 is spacing between elements
       // bottomHBox.getChildren().addAll(new Button("Button 1"), new Button("Button 2"));
       // bottomHBox.setAlignment(Pos.BOTTOM_CENTER); // Center align the content in the bottom HBox

        
        //SCORE-RESOURCES-LANES WONT UPDATE WONT UPDATE 
        Button PassTurnButton = new Button("Pass Turn");
       
        	Button backToStartButton = new Button("Back to start menu");

        		Button WeaponShopButtonE = new Button("weapon shop");
       
      VBox  rightVBox=new VBox(10);
    rightVBox.getChildren().addAll(WeaponShopButtonE,PassTurnButton,backToStartButton);
    rightVBox.setAlignment(Pos.CENTER_RIGHT);
        
    backToStartButton.setOnAction(e -> {
        // Switch to weapon shop
        primaryStage.setScene(scene1);
    
      
    });
    
    //easy mode weapon shop button to go to weapon shop
    WeaponShopButtonE.setOnAction(e -> {
        // Switch to weapon shop
        primaryStage.setScene(easyWeaponShopScene);
    
      
    });
    HBox nestedHBox = new HBox(10);
    

    //EASY SCENE STARTS HERE
    
    HBox hBox1 = new HBox();
    Label wallLabel=new Label("Wall");
    wallLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25; -fx-alignment: center;");
    wallLabel.setAlignment(Pos.CENTER_LEFT);
    hBox1.getChildren().add(wallLabel);
    
    HBox hBox2 = createHBox(Color.GREEN);
    Label wallLabel2=new Label("Wall");
    wallLabel2.setStyle("-fx-font-weight: bold; -fx-font-size: 25; -fx-alignment: center;");
    wallLabel2.setAlignment(Pos.CENTER_LEFT);
    hBox2.getChildren().add(wallLabel2);
    
    HBox hBox3 = createHBox(Color.BLUE);
    Label wallLabel3=new Label("Wall");
    wallLabel3.setStyle("-fx-font-weight: bold; -fx-font-size: 25; -fx-alignment: center;");
    wallLabel3.setAlignment(Pos.CENTER_LEFT);
    hBox3.getChildren().add(wallLabel3);
    
    HBox wallHealthInfo=new HBox(10);
    wallHealthInfo.setStyle("-fx-background-color: #336699;");
    wallHealthInfo.setAlignment(Pos.TOP_CENTER);
    wallHealthInfo.setPrefWidth(1500);
   
    HBox wallDangerInfo=new HBox(10);
    wallDangerInfo.setStyle("-fx-background-color: #336699;");
    wallDangerInfo.setAlignment(Pos.TOP_CENTER);
    wallDangerInfo.setPrefWidth(1500);
   
    
    //DISPLAY INFO OF WALL FOR EACH LANE 
    
    Label Lane1WallHealth=new Label("LANE 1 WALL HEALTH:"+l.getLaneWall().getCurrentHealth());
    Label Lane2WallHealth=new Label("LANE 2 WALL HEALTH:"+L2.getLaneWall().getCurrentHealth());
    Label Lane3WallHealth=new Label("LANE 3 WALL HEALTH:"+L3.getLaneWall().getCurrentHealth());
    Label Lane1DangerLevel=new Label("LANE 1 DANGER LEVEL:"+l.getDangerLevel());
    Label Lane2DangerLevel=new Label("LANE 2 DANGER LEVEL:"+L2.getDangerLevel());
    Label Lane3DangerLevel=new Label("LANE 3 DANGER LEVEL:"+L3.getDangerLevel());
    Lane1WallHealth.setStyle("-fx-font-weight: bold;");
    Lane2WallHealth.setStyle("-fx-font-weight: bold;");
    Lane3WallHealth.setStyle("-fx-font-weight: bold;");
    Lane1DangerLevel.setStyle("-fx-font-weight: bold;");
    Lane2DangerLevel.setStyle("-fx-font-weight: bold;");
    Lane3DangerLevel.setStyle("-fx-font-weight: bold;");

    wallHealthInfo.getChildren().addAll(Lane1WallHealth,Lane2WallHealth,Lane3WallHealth,Lane1DangerLevel,Lane2DangerLevel,Lane3DangerLevel);
    wallDangerInfo.getChildren().addAll(Lane1DangerLevel,Lane2DangerLevel,Lane3DangerLevel);
    // Create the VBox
    VBox vBox = createVBox();

    // Stack the HBoxes vertically
    VBox vBoxes = new VBox(10);
    vBoxes.getChildren().addAll(hBox1, hBox2, hBox3);

    // Create an HBox to hold the stacked HBoxes and the VBox
    HBox roota = new HBox(10);
    roota.getChildren().addAll(vBoxes, vBox);
    roota.setAlignment(Pos.CENTER);

    // Set spacing and padding
    roota.setPadding(new Insets(20));
    
    HBox healthBox=new HBox(10);
    HBox heightBox=new HBox(10);
    HBox speedBox=new HBox(10);
    HBox positionBox=new HBox(10);
    
   
   //PASS TURN 
    
  
    
    
    
   // VBox.setVgrow(vBox, Priority.ALWAYS);
    nestedHBox.setMinWidth(1500);
    nestedHBox.setAlignment(Pos.BOTTOM_CENTER);

   
// Create the easyGameMode scene
        VBox easyGameModeLayout = new VBox(100);// 20 is spacing between HBoxes
       
        easyGameModeLayout.getChildren().addAll(healthBox,heightBox,speedBox,wallHealthInfo,wallDangerInfo,topHBox,roota, rightVBox,nestedHBox);
        easyGameModeLayout.setAlignment(Pos.CENTER); // Center align the combined layout

         easyGameModeScene = new Scene(easyGameModeLayout, 1500, 1500);
         easyGameModeLayout.setEffect(colorAdjust); 

     
          
          
             // Create the easyGameMode scene
           
                  backtoEbattleScene.setOnAction(event -> {
                     
                      primaryStage.setScene(easyGameModeScene);
                      
                    //  AvailableLanes.setText("Available lanes: "+Ebattle.getLanes().size());
                     // AvailableLanes.setStyle("-fx-font-weight: bold;");
                      currentScoreLabel.setText("Current Score: "+Ebattle.getScore());
                      currentScoreLabel.setStyle("-fx-font-weight: bold;");
                      
                      currentTurnLabel.setText(" Current Turn: "+Ebattle.getNumberOfTurns());
                      currentTurnLabel.setStyle("-fx-font-weight: bold;");
                      
                      currentphaseLabel.setText(" Current phase: "+Ebattle.getBattlePhase());
                      currentphaseLabel.setStyle("-fx-font-weight: bold;");
                      
                      currentResourcesLabel.setText("Current Resources: "+Ebattle.getResourcesGathered());
                      currentResourcesLabel.setStyle("-fx-font-weight: bold;");
                      
                  });
             
     //new easyModeScene
                  
                  
                  HBox aboveLanesHbox=new HBox();
                  
                  Image backgroundImageDirt = new Image("file:/C:/Users/nourm/OneDrive/Pictures/AOT pictures/dirt2.png");
                  
                  BackgroundImage backgroundDirt = new BackgroundImage(
                		  backgroundImageDirt,
                      BackgroundRepeat.REPEAT,
                      BackgroundRepeat.REPEAT,
                      BackgroundPosition.CENTER,
                      new BackgroundSize(100, 100, true, true, true, false)
                  );
                  aboveLanesHbox.setBackground(new Background(backgroundDirt));
                  
                  aboveLanesHbox.setPrefWidth(1500);
                  aboveLanesHbox.setMaxWidth(1500);
                  aboveLanesHbox.setPrefHeight(150);
                  
                  //volleySpreadCannonBox.setAlignment(Pos.CENTER);
//           		volleySpreadCannonBox.setMaxHeight(70);
//         		volleySpreadCannonBox.setPadding(new Insets(20));
//         		volleySpreadCannonBox.setStyle(
//                       "-fx-background-color: rgba(0, 0, 0, 0.7);" +
//                       "-fx-border-color: grey; " +
//                       "-fx-border-width: 2px;"
              // );
                  
      
                  Pane laneOneUpdated =new Pane();
                 
         
                 // laneOneUpdated.setAlignment(Pos.CENTER);

                 
                  Pane laneTwoUpdated = new Pane();
                  
                 // laneTwoUpdated.setAlignment(Pos.CENTER);

                  
                  Pane laneThreeUpdated = new Pane();

                 // laneThreeUpdated.setAlignment(Pos.CENTER);

                      
                  Image backgroundImage = new Image("file:/C:/Users/nourm/OneDrive/Pictures/AOT pictures/stonePath.png");
                  
                          BackgroundImage background = new BackgroundImage(
                              backgroundImage,
                              BackgroundRepeat.REPEAT,
                              BackgroundRepeat.REPEAT,
                              BackgroundPosition.CENTER,
                              new BackgroundSize(100, 100, true, true, true, false)
                          );
                          
                          laneOneUpdated.setBackground(new Background(background));
                          
                          laneTwoUpdated.setBackground(new Background(background));
                          
                          laneThreeUpdated.setBackground(new Background(background));

                     HBox HBoxForLanes=new HBox(10);
                     
                     HBoxForLanes.setMaxWidth(1500);
                     HBoxForLanes.setPrefHeight(1200);
                     
                     
                     HBoxForLanes.setAlignment(Pos.CENTER);
                     
                     HBoxForLanes.getChildren().addAll(laneOneUpdated,laneTwoUpdated,laneThreeUpdated);
                     
                     HBoxForLanes.setAlignment(Pos.CENTER);
                     
                     HBoxForLanes.setPadding(Insets.EMPTY); // No padding around edges

                     
                     HBox.setHgrow(laneOneUpdated, Priority.ALWAYS);
                     HBox.setHgrow(laneTwoUpdated, Priority.ALWAYS);
                     HBox.setHgrow(laneThreeUpdated, Priority.ALWAYS);
                     
                     laneOneUpdated.setMaxWidth(Double.MAX_VALUE);
                     laneTwoUpdated.setMaxWidth(Double.MAX_VALUE);
                     laneThreeUpdated.setMaxWidth(Double.MAX_VALUE);

                     
                     HBoxForLanes.setStyle(
                    		    "-fx-background-color: black;" +
                    		    "-fx-border-color: #1F261F; " +
                    		    "-fx-border-width: 2px;"
                    		);
                     
                  HBox generalDescription=new HBox(30);
                  
             //     generalDescription.setPrefWidth(1400);

                  
                  Label currentScore = new Label("Current Score: ");
                  
                  currentScore.setText("Current Score: "+Ebattle.getScore());
                  
                  currentScore.setStyle("-fx-font-weight: bold;");
                  
                  
                  
                  
                  Label currentTurn = new Label("Current Turn: ");
                  
                  currentTurn.setText(" Current Turn: "+Ebattle.getNumberOfTurns());
                  
                  currentTurn.setStyle("-fx-font-weight: bold;");
                  
                  
                  
                  
                  Label currentPhase = new Label("Current phase: ");
                  
                  currentPhase.setText(" Current phase: "+Ebattle.getBattlePhase());
                  
                  currentPhase.setStyle("-fx-font-weight: bold;");
                  
                  
                  
                  Label currentResources = new Label("Current Resources: ");
                  
                  currentResources.setText("Current Resources: "+Ebattle.getResourcesGathered());
                  
                  currentResources.setStyle("-fx-font-weight: bold;");
                 
                  
                  
                  Label availableLanes = new Label(" Available lanes: ");
                  
               availableLanes.setText("Available lanes: "+Ebattle.getLanes().size());
               
               availableLanes.setStyle("-fx-font-weight: bold;");
               
               
               
               String labelStyle = 
            		   "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
                               "-fx-font-size: 35px;" + // Larger font size
                               "-fx-font-weight: bold;" + 
                               "-fx-text-fill: linear-gradient(#DAA520, #FFD700);" + // Gold gradient for text
                               "-fx-text-fill: linear-gradient(to bottom, #FFD700, #FFA500);" + // Gold gradient text
                               "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);" + // Dark shadow for depth
                               "-fx-background-color: #2A332A;" + // Dark green background
                               "-fx-border-color: #1F261F;" + // Dark purple border
                               "-fx-border-width: 3px;" +
                               "-fx-padding: 10px;" ;
                              
               
               currentScore.setStyle(labelStyle);
               
               currentTurn.setStyle(labelStyle);
               
               currentPhase.setStyle(labelStyle);
               
               currentResources.setStyle(labelStyle);
               
               availableLanes.setStyle(labelStyle);
                
               generalDescription.getChildren().addAll(currentScore,currentTurn,currentPhase,currentResources,availableLanes);

               
               
               //create wall descriptors
               
               
               String healthLabelStyle = 
            	        "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
            	        "-fx-font-size: 40x;" + // Larger font size
            	        "-fx-font-weight: bold;" + 
            	        "-fx-text-fill: #006400;" + // Dark green text
            	        "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);" + // Dark shadow for depth
            	        "-fx-background-color: #90EE90;" + // Light green background
            	        "-fx-border-color: #006400;" + // Dark green border
            	        "-fx-border-width: 3px;" +
            	        "-fx-padding: 10px;";
               
               String dangerLabelStyle = 
            	        "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
            	        "-fx-font-size: 30px;" + // Larger font size
            	        "-fx-font-weight: bold;" + 
            	        "-fx-text-fill: #8B0000;" + // Dark red text
            	        "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);" + // Dark shadow for depth
            	        "-fx-background-color: #FFC0CB;" + // Light pink background
            	        "-fx-border-color: #8B0000;" + // Dark red border
            	        "-fx-border-width: 3px;" +
            	        "-fx-padding: 10px;";


                  
               Label LaneOneWallHealth=new Label("LANE 1 WALL HEALTH: "+l.getLaneWall().getCurrentHealth());
               
               LaneOneWallHealth.setStyle(healthLabelStyle);
               
               Label LaneOneDangerLevel=new Label("LANE 1 DANGER LEVEL: "+l.getDangerLevel());

               LaneOneDangerLevel.setStyle(dangerLabelStyle);

               
               
               Label LaneTwoWallHealth=new Label("LANE 2 WALL HEALTH: "+L2.getLaneWall().getCurrentHealth());
              
               LaneTwoWallHealth.setStyle(healthLabelStyle);
               
               Label LaneTwoDangerLevel=new Label("LANE 2 DANGER LEVEL: "+L2.getDangerLevel());

               LaneTwoDangerLevel.setStyle(dangerLabelStyle);
               
               
               Label LaneThreeWallHealth=new Label("LANE 3 WALL HEALTH: "+L3.getLaneWall().getCurrentHealth());
             
               LaneThreeWallHealth.setStyle(healthLabelStyle);
               
               Label LaneThreeDangerLevel=new Label("LANE 3 DANGER LEVEL: "+L3.getDangerLevel());

               LaneThreeDangerLevel.setStyle(dangerLabelStyle);

               
               
                     VBox combinedDescriptorsBox=new VBox(70);
                     

                     combinedDescriptorsBox.setAlignment(Pos.CENTER);
                     
                    
                     
                     combinedDescriptorsBox.getChildren().addAll(LaneOneWallHealth,LaneOneDangerLevel,
                    LaneTwoWallHealth , LaneTwoDangerLevel, LaneThreeWallHealth, LaneThreeDangerLevel);

                     
                     
                     //buttons in scene
                     
                     String buttonStyle = 
                    	        "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
                    	        "-fx-font-size: 45px;" + // Adjusted font size for buttons
                    	        "-fx-font-weight: bold;" + 
                    	        "-fx-text-fill: linear-gradient(#DAA520, #FFD700);" + // Gold gradient for text
                    	        "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);" + // Dark shadow for depth
                    	       "-fx-background-color: #2A332A;" + // Dark green background
                    	        "-fx-border-width: 3px;" +
                    	        "-fx-padding: 10px;" + 
                    	        "-fx-background-radius: 5px;" + // Rounded edges for buttons
                    	        "-fx-border-radius: 5px;"; // Matching rounded borders
                     
                 
                     
                     VBox mainSceneButtons=new VBox(50);
                     
                     Button pauseGame=new Button("Pause");
                 
                     pauseGame.setStyle(buttonStyle);
                     animateButton(pauseGame);
                     pauseGame.setAlignment(Pos.CENTER);
                     
                     Button passTurn=new Button("pass Turn");
                     
                     passTurn.setStyle(buttonStyle);
                     animateButton(passTurn);
                     passTurn.setAlignment(Pos.CENTER);

                     
                     Button EndGame=new Button("End Game");

                     EndGame.setStyle(buttonStyle);
                     animateButton(EndGame);
                     EndGame.setAlignment(Pos.CENTER);

                     
                     Button goToWeaponShop=new Button("Weapon Shop");
                     
                     goToWeaponShop.setStyle(buttonStyle);
                     animateButton(goToWeaponShop);
                     goToWeaponShop.setAlignment(Pos.CENTER);

                     //functionalities for buttons
                     
                     goToWeaponShop.setOnAction(e -> {
                         
                         primaryStage.setScene(easyWeaponShopScene);
                     });
                     
                  
                     
                 	
                     
                     
                     mainSceneButtons.getChildren().addAll(pauseGame,passTurn,goToWeaponShop , EndGame);
                     
                     mainSceneButtons.setAlignment(Pos.CENTER);
                  
                     
                   //creating layout
                     
                     
                     VBox map=new VBox();
                     
                     map.setAlignment(Pos.CENTER);
                     
                     map.getChildren().addAll(HBoxForLanes,aboveLanesHbox);
                     
                     VBox mainGameSceneLayout=new VBox(40);
                     
                     mainGameSceneLayout.setAlignment(Pos.CENTER);
                     
                     mainGameSceneLayout.getChildren().addAll(generalDescription,map);
                     
                     
                     HBox mainGameSceneLayout2=new HBox(70);
                     
                     mainGameSceneLayout2.setAlignment(Pos.CENTER);
                     
                     mainGameSceneLayout2.getChildren().addAll(combinedDescriptorsBox,mainGameSceneLayout,mainSceneButtons);
                     
                     
                     Image mainSceneBG = new Image("file:C:\\Users\\nourm\\OneDrive\\Pictures\\AOT pictures\\bg3.png");
             		ImageView mainSceneIV=new ImageView(mainSceneBG);
             		
             		BorderPane bpMain=new BorderPane();
             		bpMain.getChildren().add(mainSceneIV);
             		
             		mainSceneIV.setPreserveRatio(false); 
             		bpMain.setCenter(mainGameSceneLayout2);
             		 
             		bpMain.setEffect(colorAdjust); 
             		
             		bpMain.widthProperty().addListener((obs, oldVal, newVal) -> {
             		    mainSceneIV.setFitWidth(newVal.doubleValue());  // Resize ImageView width to match the BorderPane width
             		});

             		bpMain.heightProperty().addListener((obs, oldVal, newVal) -> {
             		    mainSceneIV.setFitHeight(newVal.doubleValue());  // Resize ImageView height to match the BorderPane height
             		});
                     
                     
                     Scene mainGameScene=new Scene(bpMain,1900,1600);
                     
                     easyModeButtonT.setOnAction(e -> {
                         
                         primaryStage.setScene(mainGameScene);
                     
                         PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
                         pause.setOnFinished(event -> {
                             wpPopup(easyWeaponShopScene);
                         });
                         pause.play(); 
                               });
                     
                     xButton.setOnAction(e -> {
                         primaryStage.setScene(mainGameScene);
                                    });
                     
                     
                     //tooltip
                     
                     
                    
                     
                     //updated pass turn 
                  
                     //align children too bottom so that titans spawn at bottom
                     
                 //   laneOneUpdated.setAlignment(Pos.BOTTOM_CENTER);
                     
                  //   laneTwoUpdated.setAlignment(Pos.BOTTOM_CENTER);

                   //  laneThreeUpdated.setAlignment(Pos.BOTTOM_CENTER);

                     
                     //list to store each type of titan image
                     List<ImageView> spriteList = new ArrayList<>();
                     
                     //experimentation 
                     final int[] currentSpriteIndex = {0}; // Current sprite index
                     final int[] frameCounter = {0}; // Frame counter to control sprite switching
                     final double[] yPosition = {0}; 
                     
                     //priority queue to store all titans present currently in lanes
                     
                     //should i make a seperate pq for each lane?
                     Queue<PureTitan> pureTitansinPresentLaneOne = new LinkedList<>();

                     PriorityQueue<AbnormalTitan> abnormalTitansPresent = new PriorityQueue<>();

                     PriorityQueue<ArmoredTitan> armoredTitansPresent = new PriorityQueue<>();

                     PriorityQueue<ColossalTitan> colossalTitansPresent = new PriorityQueue<>();
                     
                    
                     
                     //hashmap for hover details 
                     
                     Map<Titan, ImageView> titanImageMap = new HashMap<>();

                     
                     passTurn.setOnAction(e -> {
                    	 
                         Ebattle.passTurn();
                        
                        
                        
                         availableLanes.setText(" Available lanes: "+Ebattle.getLanes().size());
                         
                         currentScore.setText("Current Score: "+Ebattle.getScore());
                         
                         currentTurn.setText(" Current Turn: "+Ebattle.getNumberOfTurns());
                         
                         
                         if ( Ebattle.getNumberOfTurns()==15) labelGrowEffect(currentPhase);

                         if (Ebattle.getNumberOfTurns()>=15 && Ebattle.getNumberOfTurns()<30){
                        	 String intensePhaseStyle = 
                        			 "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
                        					 "-fx-font-size: 35px;" + // Larger font size
                        					 "-fx-font-weight: bold;" + 
                        					 "-fx-text-fill: linear-gradient(to bottom, #FFA500, #FF4500);" + // Orange gradient for text
                        					 "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);"+ // Dark shadow for depth
                                             "-fx-background-color: #2A332A;" + // Dark green background
                                             "-fx-border-color: #1F261F;" + // Dark purple border
                                             "-fx-border-width: 3px;" +
                                             "-fx-padding: 10px;" ;
                        	 currentPhase.setStyle(intensePhaseStyle);
                        	   currentPhase.setText(" Current phase: "+Ebattle.getBattlePhase());
}
                       if ( Ebattle.getNumberOfTurns()==30) labelGrowEffect(currentPhase);
                         if (Ebattle.getNumberOfTurns()>=30){
                        	 String intensePhaseStyle = 
                        			 "-fx-font-family: 'Serif';" + // Close to Elden Ring's serif font
                        					 "-fx-font-size: 35px;" + // Larger font size
                        					 "-fx-font-weight: bold;" + 
                        					 "-fx-text-fill: linear-gradient(to bottom, #FF6347, #B22222);" + // Red gradient for text
                        					 "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2);"+ // Dark shadow for depth
                                             "-fx-background-color: #2A332A;" + // Dark green background
                                             "-fx-border-color: #1F261F;" + // Dark purple border
                                             "-fx-border-width: 3px;" +
                                             "-fx-padding: 10px;" ;
                        	 currentPhase.setStyle(intensePhaseStyle);
                        	   currentPhase.setText(" Current phase: "+Ebattle.getBattlePhase());
                        	  
                         }
                         else    currentPhase.setText(" Current phase: "+Ebattle.getBattlePhase());;
                         
                         
                         currentResources.setText("Current Resources: "+Ebattle.getResourcesGathered());
                        
                         
                         LaneOneWallHealth.setText("LANE 1 WALL HEALTH:"+l.getLaneWall().getCurrentHealth());
                         
                         LaneOneDangerLevel.setText("LANE 1 DANGER LEVEL:"+l.getDangerLevel());

                         
                         LaneTwoWallHealth.setText("LANE 2 WALL HEALTH:"+L2.getLaneWall().getCurrentHealth());
                         
                         LaneTwoDangerLevel.setText("LANE 2 DANGER LEVEL:"+L2.getDangerLevel());

                         
                         LaneThreeWallHealth.setText("LANE 3 WALL HEALTH:"+L3.getLaneWall().getCurrentHealth());

                         LaneThreeDangerLevel.setText("LANE 3 DANGER LEVEL:"+L3.getDangerLevel());

//                       
                          
                          if (Ebattle.isGameOver()==true) gameOver(Ebattle);
                          
                        //  PriorityQueue<Titan> reorderTitan = new PriorityQueue<>();
                        //  PriorityQueue<Lane> reorderLanes = new PriorityQueue<>();
                          
                        if (!Ebattle.getLanes().isEmpty()) {
                          
                        	  Lane laneeTemp = null;
                        	  int minDangerLevel = Integer.MAX_VALUE;
                          	    
                        	  
                        	  for (Lane lane : Ebattle.getLanes()) {
                        		    if (lane.getDangerLevel() < minDangerLevel) {
                        		        minDangerLevel = lane.getDangerLevel();
                        		        laneeTemp = lane;
                        		    }
                        		}
                            
                              if (laneeTemp.isLaneLost()!=true ){
                              	if ( laneeTemp.getTitans()!= null  && !laneeTemp.getTitans().isEmpty()){
                                  
                                  
                                      Titan Try =  laneeTemp.getTitans().poll();
                                     
                                      
                                      int tempTitanCode=Try.getDangerLevel();
                                      
                                    //  int tempDamageCode=Try.getDamage();
                                      
                                      int tempLaneCode=laneeTemp.getDangerLevel();
                                     
                                     //	Button b=new Button();

                                      	   if (tempTitanCode==1){
                                      		   
                                      		   ImageView pureTitanCharacter = createImageView("file:C:\\Users\\nourm\\OneDrive\\Pictures\\AOT pictures\\ptOne.png");

                                      		   
                                      	   if(tempLaneCode==l.getDangerLevel()){
                                      		 
                                      	
                                      	            // Set the initial image
                                      	       
                                      
                                      		  //added photo to lane
                                      	         laneOneUpdated.getChildren().add(pureTitanCharacter);
                                      	           
                                      	         //added to list containing images
                                      	         spriteList.add(pureTitanCharacter);
                                      	          
                                      	         //added to priorityQueue of pure titans present in lanes
                                      	      //   pureTitansinPresentLaneOne.add((PureTitan) Try);
                                      	         
                                      	         //add to map list- useful for hover functionality
                                      	       // titanImageMap.put(Try, pureTitanCharacter);
                                      	      
                                 	           createMoveDownAnimation(pureTitanCharacter,laneOneUpdated);

                                      	           //impose this on a list containing the images instead
                                      	     //   for (ImageView imageView : spriteList) {
                                      	        //	if(imageView.getLayoutY()< laneOneUpdated.getHeight() - imageView.getFitHeight())
                                      	       //     createMoveDownAnimation(imageView,laneOneUpdated);
                                      	        //	else {for (ImageView imageView1 : laneOneUpdated){
                                      	        	//	laneOneUpdated.remove(imageView1);}}
                                      	      //  }
                                      	         
                                      	      
                                      		  
                                      	  }
                                  
                                      	   else if(tempLaneCode==L2.getDangerLevel()){                                  	          //  character.setImage(spriteList.get(0));
                                  		  
                                  		  //added photo to lane
                                  	         laneTwoUpdated.getChildren().add(pureTitanCharacter);
                                  	           
                                  	         //added to list containing images
                                  	         spriteList.add(pureTitanCharacter);
                                  	          
                                  	         //added to priorityQueue of pure titans present in lanes
                                  	       //  pureTitansinPresentLaneOne.add((PureTitan) Try);
                                  	         
                                  	         //add to map list- useful for hover functionality
                                  	      //  titanImageMap.put(Try, pureTitanCharacter);
                                  	        
                              	           createMoveDownAnimation(pureTitanCharacter,laneTwoUpdated);

                                      	  }
                                 
                                      	  else if(tempLaneCode==L3.getDangerLevel() ){
                                      		 
                                  	          //  character.setImage(spriteList.get(0));
                                  		  
                                  		  //added photo to lane
                                  	         laneThreeUpdated.getChildren().add(pureTitanCharacter);
                                  	           
                                  	         //added to list containing images
                                  	         spriteList.add(pureTitanCharacter);
                                  	         
                               	           createMoveDownAnimation(pureTitanCharacter,laneThreeUpdated);

                                  	          
                                  	         //added to priorityQueue of pure titans present in lanes
                                  	       //  pureTitansinPresentLaneOne.add((PureTitan) Try);
                                  	         
                                  	         //add to map list- useful for hover functionality
                                  	      //  titanImageMap.put(Try, pureTitanCharacter);
                                      	  }
                                      	   }
                                      	    if (tempTitanCode==2){ 

                                      	    	ImageView AbnormalTitanCharacter = createImageView("file:/C:/Users/nourm/OneDrive/Pictures/AOT pictures/at2.png");

                                      		   
                                      	   if(tempLaneCode==l.getDangerLevel()){
                                      		 
                                     	       
                                              
                                      		  //added photo to lane
                                      	         laneOneUpdated.getChildren().add(AbnormalTitanCharacter);
                                      	           
                                      	         //added to list containing images
                                      	         spriteList.add(AbnormalTitanCharacter);
                                      	          
                                      	         //added to priorityQueue of pure titans present in lanes
                                      	     //    pureTitansinPresentLaneOne.add((PureTitan) Try);
                                      	         
                                      	         //add to map list- useful for hover functionality
                                      	        //titanImageMap.put(Try, pureTitanCharacter);
                                   	         
                                      	         createMoveDownAnimation(AbnormalTitanCharacter,laneOneUpdated);

                                      	   }
                                  
                                      	   else if(tempLaneCode==L2.getDangerLevel()){
                                   	       
                                             
                                     		  //added photo to lane
                                     	         laneTwoUpdated.getChildren().add(AbnormalTitanCharacter);
                                     	           
                                     	         //added to list containing images
                                     	         spriteList.add(AbnormalTitanCharacter);
                                     	         
                                      	         createMoveDownAnimation(AbnormalTitanCharacter,laneTwoUpdated);

                                      		   
                                      	  }
                                 
                                      	  else if(tempLaneCode==L3.getDangerLevel() ){
                                      		 
                                   	       
                                             
                                     		  //added photo to lane
                                     	         laneThreeUpdated.getChildren().add(AbnormalTitanCharacter);
                                     	           
                                     	         //added to list containing images
                                     	         spriteList.add(AbnormalTitanCharacter);
                                     	         
                                      	         createMoveDownAnimation(AbnormalTitanCharacter,laneThreeUpdated);

                                      	  }
                                      	   }
                                      	    if (tempTitanCode==3){ 

                                      	    	ImageView armoredTitanCharacter = createImageView("file:/C:/Users/nourm/OneDrive/Pictures/AOT pictures/a1.png");

                                      		   
                                      	   if(tempLaneCode==l.getDangerLevel()){
                                      		 
                                     	       
                                    		  //added photo to lane
                                    	         laneOneUpdated.getChildren().add(armoredTitanCharacter);
                                    	           
                                    	         //added to list containing images
                                    	         spriteList.add(armoredTitanCharacter);
                                    	         
                                      	         createMoveDownAnimation(armoredTitanCharacter,laneOneUpdated);

                                      	  }
                                  
                                      	   else if(tempLaneCode==L2.getDangerLevel()){
                                      		 
                                   	           
                                   		  //added photo to lane
                                   	         laneTwoUpdated.getChildren().add(armoredTitanCharacter);
                                   	           
                                   	         //added to list containing images
                                   	         spriteList.add(armoredTitanCharacter);
                                      	 
                                  	         createMoveDownAnimation(armoredTitanCharacter,laneTwoUpdated);

                                      	   }
                                 
                                      	  else if(tempLaneCode==L3.getDangerLevel() ){
                                      		 
                                   	       
                                   		  //added photo to lane
                                   	         laneThreeUpdated.getChildren().add(armoredTitanCharacter);
                                   	           
                                   	         //added to list containing images
                                   	         spriteList.add(armoredTitanCharacter);
                                      	 
                                  	         createMoveDownAnimation(armoredTitanCharacter,laneThreeUpdated);

                                      	  }
                                      	   }
                                      	   if (tempTitanCode==4){ 

                                      		 ImageView coossalTitanCharacter = createImageView("file:/C:/Users/nourm/OneDrive/Pictures/AOT pictures/ct1.png");

                                      		   
                                      	   if(tempLaneCode==l.getDangerLevel()){
                                      		 
                                     	       
                                      		  //added photo to lane
                                      	         laneOneUpdated.getChildren().add(coossalTitanCharacter);
                                      	           
                                      	         //added to list containing images
                                      	         spriteList.add(coossalTitanCharacter);
                                      	         
                                      	         createMoveDownAnimation(coossalTitanCharacter,laneOneUpdated);

                                      	  }
                                  
                                      	   else if(tempLaneCode==L2.getDangerLevel()){                                   	       
                                     		  //added photo to lane
                                     	         laneTwoUpdated.getChildren().add(coossalTitanCharacter);
                                     	           
                                     	         //added to list containing images
                                     	         spriteList.add(coossalTitanCharacter);
                                     	         
                                      	         createMoveDownAnimation(coossalTitanCharacter,laneTwoUpdated);

                                      	  }
                                 
                                      	  else if(tempLaneCode==L3.getDangerLevel() ){
                                      		 
                                   	       
                                     		  //added photo to lane
                                     	         laneThreeUpdated.getChildren().add(coossalTitanCharacter);
                                     	           
                                     	         //added to list containing images
                                     	         spriteList.add(coossalTitanCharacter);
                                     	         
                                      	         createMoveDownAnimation(coossalTitanCharacter,laneThreeUpdated);
                                      	  }
                                      	  }
                                      	// laneeTemp.getTitans().add(Try);
                          
                              	}
                              	
                             }
                                 
                             
                                    	 
                                    	  Ebattle.getLanes().add(laneeTemp);

                                                                }
                          
                        
                
//                         while(!pureTitansinPresentLaneOne.isEmpty()){
//                	        	 
//                        	 Titan currTitan=pureTitansinPresentLaneOne.remove();
//              	        	int currentHealth=currTitan.getCurrentHealth();
//              	        	
//              	        	if(currentHealth==0){
//              	        		
//              	        		 ImageView associatedImage = titanImageMap.remove(pureTitansinPresentLaneOne.remove(currTitan));	
//              	        		
//              	        		//pureTitansinPresentLaneOne.remove(t);
//              	        		 
//              	        		 laneOneUpdated.getChildren().remove(associatedImage);
//              	        		 
//              	        		spriteList.remove(associatedImage);
//              	        		 
//              	        	}
//              	        	else pureTitansinPresentLaneOne.add((PureTitan) currTitan);
//           	         }
                          
                         //   for (ImageView imageView : spriteList) {
                	        //	if(imageView.getLayoutY()< laneOneUpdated.getHeight() - imageView.getFitHeight())
//                	           createMoveDownAnimation(imageView,laneOneUpdated);
//                	           createMoveDownAnimation(imageView,laneTwoUpdated);
//                	           createMoveDownAnimation(imageView,laneThreeUpdated);

                	        //	else {for (ImageView imageView1 : laneOneUpdated){
                	        	//	laneOneUpdated.remove(imageView1);}}
                	     //  }
                   
                      
                         
                          });
        
                     
                   



        // Set the title of the window
        primaryStage.setTitle("ATTACK ON TITAN:UTOPIA");

        
        // Set Scene 1 as the initial scene
        primaryStage.setScene(scene1);
         
    primaryStage.show();
        
        }
    

public void addTooltipsOnMouseEnter(Titan t,ImageView i) {
 
        // Create a Tooltip (optional for initial state)
        Tooltip tooltip = new Tooltip();
        Tooltip.install(i, tooltip);

        // Set the event handler for mouse enter
        i.setOnMouseEntered(event -> {
            // Update the tooltip with the Titan's current details
            tooltip.setText(t.toString());
        });
    
}
    
    public void openSmallerScene() {
        // Create a new stage for the smaller scene
        Stage smallerStage = new Stage();
        StackPane rootInst = new StackPane();
        Scene instScene = new Scene(rootInst, 400, 300);

        // Set the background color to a light grey-blue color
        instScene.setFill(Color.web("#ADD8E6")); // Light Steel Blue color

        smallerStage.setScene(instScene);
        smallerStage.setTitle("JavaFX Scene with Light Grey Blue Background");
        smallerStage.show();
        
        
        
//        	BorderPane smallerLayout = new BorderPane();
//        Scene instructionScene = new Scene(smallerLayout, 400, 300);
//        instructionScene.setFill(Color.web("#B0C4DE")); // Light Steel Blue color
//        // Add content to the smaller scene
//        smallerLayout.getChildren().add(new Button("cancel"));
//        smallerStage.setTitle("INSTRUCTIONS");
//        // Set the smaller scene in the new stage
//        smallerStage.setScene(instructionScene);
//        smallerStage.show();
    }
    
    private Label styleLabel(Label label){
    	label.setAlignment(Pos.CENTER);
    	label.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 35));
    	label.setStyle(
  			     "-fx-text-fill: white; " 
  		          );
    	return label;
    }
    
    private Label styleLabel2(Label label){
    	label.setAlignment(Pos.CENTER);
    	label.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.EXTRA_BOLD, 70));
    	label.setStyle(
  			     "-fx-text-fill: white; " 
  		          );
    	  InnerShadow innerShadow = new InnerShadow();
          innerShadow.setColor(Color.BLACK);
          innerShadow.setWidth(6);
          innerShadow.setHeight(6);
          label.setEffect(innerShadow);
    	
    	
    	return label;
    }
    
    
    
    private Button styleButton(Button b){
    b.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.BOLD, 40));

    b.setStyle(
    		 "-fx-background-color: rgba(0, 0, 0, 0.5);" +  // Transparent background
    	                "-fx-text-fill: white; " +              // Text color
    	                "-fx-border-color: white; " +     // Transparent border
    	                "-fx-border-width: 2px;"           
        );

    b.setOnMouseEntered(event -> {
        scaleDown(b).stop();
        scaleUp(b).play();
    });


    b.setOnMouseExited(event -> {
        scaleDown(b).play();
        scaleUp(b).stop();
    });
    return b;
    }
    
    private VBox createVBox() {
        VBox vBox = new VBox();

       
        return vBox;
    }
    
  private void gameOver(Battle b) {
		
    	Stage window = new Stage();
    	window.setWidth(850);
    	window.setHeight(850);
    	
    	
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("GAME OVER");
        Label message = new Label("GAME OVER");
        Label finalResources=new Label("final resources: "+b.getResourcesGathered());
        styleLabel(finalResources);
        styleLabel(message);
        Button exit = new Button("back to Start menu");
        styleButton(exit);
        exit.setOnAction(e -> {
        	
        window.close();
        primaryStage.setScene(scene1);
        }
    );
        VBox layout = new VBox(30);
        Image image = new Image("file:/C:/Users/nourm/OneDrive/Pictures/pop.jpeg");

        // Create a BackgroundImage
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        // Set the BackgroundImage to the VBox
   
        layout.setBackground(new Background(backgroundImage));
        
        layout.getChildren().addAll(message, exit,finalResources);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
		
    
}
 
        private void showDialog(String title ,String wrongMove) {
    		
        	Stage window = new Stage();
        	window.setWidth(850);
        	window.setHeight(850);
        	
        	
    		window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            Label message = new Label(wrongMove);
            styleLabel(message);
            Button exit = new Button("cancel");
            styleButton(exit);
            exit.setOnAction(e -> window.close());
            VBox layout = new VBox(30);
            Image image = new Image("file:/C:/Users/nourm/OneDrive/Pictures/pop.jpeg");

            // Create a BackgroundImage
            BackgroundImage backgroundImage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

            // Set the BackgroundImage to the VBox
       
            layout.setBackground(new Background(backgroundImage));
            
            layout.getChildren().addAll(message, exit);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
    }
        
        private void wpPopup(Scene s) {
        Stage window = new Stage();
     	window.setWidth(1000);
     	window.setHeight(1000);
     	
     	window.initOwner(primaryStage); // Set the main window as the owner
     	window.initModality(Modality.WINDOW_MODAL); 
 		//window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle("Notice: VISIT WEAPON SHOP");
         
         Label message = new Label("          make sure  "+"\n"+" to visit weapon shop!  ");
         
         
         styleLabel(message);
         
         message.setFont(Font.font("Bahnschrift SemiBold Condensed", FontWeight.BOLD,70));
         
         
         Button cancel = new Button("cancel");
         
         styleButton(cancel);
         
       
         cancel.setOnAction(e -> {
        	 window.close();
                        });
         
         Button wpRedirect=new Button("   Visit Weapon Shop");
         
         styleButton(wpRedirect);

         wpRedirect.setOnAction(e -> { 
             window.close();
             primaryStage.setScene(s); 
         });

         
         
         
         VBox layout = new VBox(40);
         
         Image image = new Image("file:/C:/Users/nourm/OneDrive/Pictures/pop.jpeg");

         // Create a BackgroundImage
         BackgroundImage backgroundImageForPopup = new BackgroundImage(image,
                 BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

         // Set the BackgroundImage to the VBox
    
         layout.setBackground(new Background(backgroundImageForPopup));
         
         layout.getChildren().addAll(message, wpRedirect , cancel);
        
         layout.setAlignment(Pos.CENTER);
        
         Scene scene = new Scene(layout);
        
         window.setScene(scene);
         window.show();
         
        }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
  
    private HBox createHBox(Color color) {
        HBox hBox = new HBox();
       hBox.setPrefSize(1800, 100); // Set preferred size for HBox
        hBox.setStyle("-fx-background-color: " + toHexString(color) + ";");
        return hBox;
    }
    
    private Label textShow(Button button, Label label){
    	 
           label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
           label.setStyle(
                   "-fx-background-color: rgba(0, 0, 0, 0.5);" + 
                   "-fx-text-fill: white; " +
                   "-fx-border-color: white; " +
                   "-fx-border-width: 2px;" 
                  
           );
           label.setVisible(false); // Initially invisible
           label.setPadding(new Insets(20)); // Padding for the label content

           // Create FadeTransitions
           FadeTransition fadeIn = new FadeTransition(Duration.millis(250), label);
           fadeIn.setFromValue(0.0);
           fadeIn.setToValue(1.0);

           FadeTransition fadeOut = new FadeTransition(Duration.millis(250), label);
           fadeOut.setFromValue(1.0);
           fadeOut.setToValue(0.0);

           // Set mouse event handlers
           button.setOnMouseEntered(event -> {
               label.setVisible(true);
               fadeOut.stop();
               fadeIn.play();
               scaleUp(button).play();
               scaleDown(button).stop();
           });

           button.setOnMouseExited(event -> {
               fadeIn.stop();
               fadeOut.play();
               fadeOut.setOnFinished(e -> label.setVisible(false));
               scaleUp(button).stop();
               scaleDown(button).play();
           });
		return label;
    }
    
    private Label titanDetailsDisplay(ImageView imageView,Titan t){
   	 
    	Label label=new Label(t.toString());
    	
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        label.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.5);" + 
                "-fx-text-fill: white; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px;" 
               
        );
        label.setVisible(false); // Initially invisible
        label.setPadding(new Insets(20)); // Padding for the label content

        // Create FadeTransitions
        FadeTransition fadeIn = new FadeTransition(Duration.millis(250), label);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(250), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Set mouse event handlers
        imageView.setOnMouseEntered(event -> {
            label.setVisible(true);
            fadeOut.stop();
            fadeIn.play();
           
        });

        imageView.setOnMouseExited(event -> {
            fadeIn.stop();
            fadeOut.play();
            fadeOut.setOnFinished(e -> label.setVisible(false));
           
        });
		return label;
 }
    
    private ScaleTransition scaleUp(Button b){ 
    	ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), b);
    scaleUp.setToX(1.2);
    scaleUp.setToY(1.2);
    return scaleUp;
    }
    
    public void labelGrowEffect(Label l){
    	 Timeline timeline = new Timeline(
                 new KeyFrame(Duration.ZERO, e -> {
                 	l.setScaleX(1.0);
                 	l.setScaleY(1.0);
                 }),
                 new KeyFrame(Duration.millis(100), e -> {
                 	l.setScaleX(1.05);
                 	l.setScaleY(1.05);
                 }),
                 
                 new KeyFrame(Duration.millis(150), e -> {
                  	l.setScaleX(1.1);
                  	l.setScaleY(1.1);
                  }),
                 
                 new KeyFrame(Duration.millis(200), e -> {
                 	l.setScaleX(1.15);
                 	l.setScaleY(1.15);
                 }),
                 new KeyFrame(Duration.millis(250), e -> {
                  	l.setScaleX(1.15);
                  	l.setScaleY(1.15);
                  }),
                 new KeyFrame(Duration.millis(300), e -> {
                 	l.setScaleX(1.1);
                 	l.setScaleY(1.1);
                 }),
                 
                 
                 new KeyFrame(Duration.millis(350), e -> {
                 	l.setScaleX(1.0);
                 	l.setScaleY(1.0);
                 })
             );
    	 timeline.setCycleCount(1);
             timeline.setAutoReverse(true);
             timeline.play();
    }
    
    
    private ScaleTransition scaleDown(Button b){ 
    	ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), b);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);
    return scaleDown;
    }
    
    private Button animateButton(Button b){

        b.setOnMouseEntered(event -> {
            scaleDown(b).stop();
            scaleUp(b).play();
        });


        b.setOnMouseExited(event -> {
            scaleDown(b).play();
            scaleUp(b).stop();
        });
        return b;
        }
    
    private ImageView createImageView(String imagePath) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(130);  // Adjust size as needed
        imageView.setFitHeight(130); // Adjust size as needed
        return imageView;
    }

    private void createMoveDownAnimation(ImageView imageView,Pane pane) {
      
    	Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
            	 double currentY = imageView.getLayoutY();
                 
                 // Check if the image is within the bounds of the pane
               //  if (currentY < pane.getHeight() - imageView.getFitHeight()) {  // Stop moving if it reaches the bottom of the pane
                     // Move the image down by 5px every second using setLayoutY()
                     imageView.setLayoutY(currentY + 20);
                // } else {
                     // Stop the animation if the image reaches the bottom of the pane
                //     timeline.stop();
                // }
            })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);  // Infinite loop
        timeline.play();  // Start the animation
       
    }

    
    public static void main(String[] args) {
        launch();
    }
    
    
    public static class AudioManager {

        private static MediaPlayer mediaPlayer;

        public static void initialize(String audioFilePath) {
            if (mediaPlayer == null) {
                Media media = new Media(audioFilePath);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
            }
        }

        public static MediaPlayer getMediaPlayer() {
            return mediaPlayer;
        }

        public void stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        }
    }

    
}

