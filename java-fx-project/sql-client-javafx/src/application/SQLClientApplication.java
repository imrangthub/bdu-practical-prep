package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SQLClientApplication extends Application {

	// Connection to the database
	private Connection connection;

	// Text area to enter SQL commands
	private TextArea txArSqlCmn = txArSqlCmn = new TextArea("SELECT * FROM BOOK;");

	// Text area to display results from SQL commands
	private TextArea txArShowRs = new TextArea();

	// JDBC info for a database connection
	TextField txfUsrName = new TextField("root");
	TextField txfUsrPass = new TextField("root");

	ComboBox comBxJdbDrv = new ComboBox(
			FXCollections.observableArrayList("com.mysql.jdbc.Driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"sun.jdbc.odbc.JdbcOdbcDriver", "oracle.jdbc.driver.OracleDriver"));

	ComboBox comBxDbUrl = new ComboBox(FXCollections.observableArrayList("jdbc:mysql://localhost:3306/springbootdb",
			"jdbc:sqlserver://s16988308.onlinehome-server.com:1433;databaseName=CUNY_DB;integratedSecurity=false",
			"jdbc:mysql://liang.armstrong.edu/javabook", "jdbc:odbc:exampleMDBDataSource",
			"jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"));

	// Create titled borders
	Label lbSqlCmn = lbSqlCmn = new Label("Enter an SQL Command");
	Label lbArShowRs = new Label("SQL Execution Result");
	Label lbDbInfo = new Label("Enter Database Information");
	Label lbDbConMsg = new Label("No connection now");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// App Title
		primaryStage.setTitle("SQLClient Application");
		lbDbInfo.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
		lbSqlCmn.setFont(Font.font("Cambria", FontWeight.BOLD, 15));

		// JDBC Driver section
		Label lbJdbDrv = new Label("JDBC Driver");
		lbJdbDrv.setMinWidth(100);
		HBox hbxJdbDrv = new HBox();
		comBxJdbDrv.setEditable(true);
		comBxJdbDrv.setValue("com.mysql.jdbc.Driver");
		comBxJdbDrv.setPrefWidth(500);
		hbxJdbDrv.getChildren().addAll(lbJdbDrv, comBxJdbDrv);

		// Database URL section
		Label lbDbUrl = new Label("Database URL");
		lbDbUrl.setMinWidth(100);
		HBox hbxDbUrl = new HBox();
		comBxDbUrl.setEditable(true);
		comBxDbUrl.setPrefWidth(500);
		comBxDbUrl.setValue("jdbc:mysql://localhost:3306/springbootdb");
		hbxDbUrl.getChildren().addAll(lbDbUrl, comBxDbUrl);

		// Username section
		Label lbUsrName = new Label("Username");
		HBox hbUsrNam = new HBox();
		lbUsrName.setMinWidth(100);
		HBox.setHgrow(txfUsrName, Priority.ALWAYS);
		hbUsrNam.getChildren().addAll(lbUsrName, txfUsrName);

		// Password section
		Label lbPass = new Label("Password");
		lbPass.setMinWidth(100);
		HBox hbPass = new HBox();
		HBox.setHgrow(txfUsrPass, Priority.ALWAYS);
		hbPass.getChildren().addAll(lbPass, txfUsrPass);

		// Top section
		HBox topHbox = new HBox();
		// Top Left side (Database information)
		VBox hbTopLeft = new VBox();
		hbTopLeft.setPrefHeight(210);
		hbTopLeft.setSpacing(10);
		hbTopLeft.setPadding(new Insets(5, 5, 5, 5));
		hbTopLeft.setStyle("-fx-background-color: #3366;");
		hbTopLeft.getChildren().addAll(lbDbInfo, hbxJdbDrv, hbxDbUrl, hbUsrNam, hbPass);
		// Top Right side (SQL Command)
		VBox hbTopRight = new VBox();
		hbTopRight.setPrefHeight(210);
		hbTopRight.setPadding(new Insets(5, 2, 5, 2));
		hbTopRight.setStyle("-fx-background-color: #3366;");
		hbTopRight.getChildren().addAll(lbSqlCmn, txArSqlCmn);
		hbTopRight.setFillWidth(true);

		HBox.setHgrow(hbTopRight, Priority.ALWAYS);
		topHbox.setPrefHeight(220);
		topHbox.setSpacing(5);
		topHbox.setStyle("-fx-background-color: #3366;");
		topHbox.setStyle("-fx-border-style: solid inside;");
		topHbox.getChildren().addAll(hbTopLeft, hbTopRight);

		// Middle section
		VBox vbMidl = new VBox();
		vbMidl.setPrefHeight(200);
		HBox hbMidTop = new HBox();
		hbMidTop.setPrefHeight(50);

		VBox hbMdTopLert = new VBox();
		hbMdTopLert.setPrefHeight(45);
		hbMdTopLert.setPadding(new Insets(5, 0, 5, 5));
		Button btnConToDB = new Button("Connect to DB");
		btnConToDB.setStyle("-fx-font: 15 Cambria; -fx-base: lightgray;");
		hbMdTopLert.getChildren().addAll(btnConToDB, lbDbConMsg);

		HBox hbMdTopRight = new HBox();
		hbMdTopRight.setPrefHeight(45);
		hbMdTopRight.setSpacing(5);
		hbMdTopRight.setPadding(new Insets(20, 0, 5, 5));

		Button btnClrSqlCmd = new Button("Clear");
		btnClrSqlCmd.setStyle("-fx-font: 15 Cambria; -fx-base: lightgray;");
		Button btnExcSqlCmd = new Button("Execute SQL Command");
		btnExcSqlCmd.setStyle("-fx-font: 15 Cambria; -fx-base: lightgray;");
		hbMdTopRight.getChildren().addAll(btnClrSqlCmd, btnExcSqlCmd);
		hbMdTopRight.setAlignment(Pos.CENTER_RIGHT);

		HBox.setHgrow(hbMdTopLert, Priority.ALWAYS);
		HBox.setHgrow(hbMdTopRight, Priority.ALWAYS);
		hbMidTop.getChildren().addAll(hbMdTopLert, hbMdTopRight);
		hbMidTop.setPadding(new Insets(0, 5, 0, 0));

		lbArShowRs.setPadding(new Insets(5, 0, 5, 5));
		lbArShowRs.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
		txArShowRs.setStyle("-fx-pref-height:800; -fx-text-fill: black; -fx-font-size: 14px;");
		txArShowRs.setPadding(new Insets(5, 0, 5, 5));
		vbMidl.getChildren().addAll(hbMidTop, lbArShowRs, txArShowRs);

		// Bottom section
		Button btnClearRs = new Button("Clear Result");
		btnClearRs.setStyle("-fx-font: 15 Cambria; -fx-base: lightgray;");
		HBox bottHbox = new HBox();
		bottHbox.setPrefHeight(50);
		bottHbox.setPadding(new Insets(15, 12, 15, 12));
		bottHbox.setSpacing(10);
		bottHbox.setStyle("-fx-background-color: #3366;");
		bottHbox.getChildren().addAll(btnClearRs);

		// Main Scene Layout
		BorderPane bodPan = new BorderPane();
		bodPan.setTop(topHbox);
		bodPan.setCenter(vbMidl);
		bodPan.setBottom(bottHbox);
		Scene scene = new Scene(bodPan, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

		// Button actions
		DBUtil dbUtilObj = new DBUtil();
		btnConToDB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lbDbConMsg.setText("");
				String driTxt = comBxJdbDrv.getValue().toString();
				String txfDBUrlTxt = comBxDbUrl.getValue().toString();
				String txfUsrNameTxt = txfUsrName.getText();
				String txfUsrPassTxt = txfUsrPass.getText();
				connection = dbUtilObj.connectToDB(driTxt, txfDBUrlTxt, txfUsrNameTxt, txfUsrPassTxt);
				if (connection == null) {
					txArShowRs.setText("Please connect to a database first");
				} else {
					System.out.println("Connected to " + txfDBUrlTxt);
					lbDbConMsg.setText("Connected to " + txfDBUrlTxt);
				}
			}
		});
		btnClrSqlCmd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txArSqlCmn.setText("");
			}
		});
		btnExcSqlCmd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txArShowRs.setText("");
				if (connection == null) {
					txArShowRs.setText("Please connect to a database first");
				} else {
					if (txArSqlCmn.getText().isEmpty()) {
						txArShowRs.setText("Can not issue empty query");
						return;
					}
					txArShowRs.setText(dbUtilObj.executeSQL(txArSqlCmn.getText()));
				}
			}
		});
		btnClearRs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txArShowRs.setText("");
			}
		});
	}

	/** Main method */
	public static void main(String[] args) {
		launch(args);

	}
}
