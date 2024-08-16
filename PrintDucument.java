package myfx;

import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrintDucument extends Application{
	private TextArea txtA = new TextArea();
	private Button btn1 = new Button("PrinterList");
	private Button btn2 = new Button("PrintText");
	private Button btn3 = new Button("Printer Scene");
	private Label lbl= new Label("Status");
	GridPane root = new GridPane();
	public static void main(String[] args) {
		launch(args);
	
	  }
	
	
	@Override
	public void start(Stage st) throws Exception {
		root.add(txtA, 0, 0, 3, 1);
		root.addRow(1, btn1,btn2, btn3);
		root.setStyle("-fx-font-size: 14;"
				+"-fx-padding:5;"
				+"-fx-hgap: 5;"+
				"-fx-vpag:5;");
		root.addRow(2, lbl);
		
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("Printing...");
		st.show();
		btn1.setOnAction(e-> showPrinters());
		btn2.setOnAction(e-> print(txtA));
		btn3.setOnAction(e-> print(root));
	}
	public void showPrinters() {
		txtA.clear();
		ObservableSet<Printer> allPrinters = Printer.getAllPrinters();
		for(Printer printer:allPrinters) {
			txtA.appendText(printer.getName()+"\n");
		}
		Printer defaultPrinter = Printer.getDefaultPrinter();
		}
	public void print(Node node) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job == null) {
			lbl.setText("cannot create print job...");
			return;
		}
		boolean proceed = job.showPrintDialog(root.getScene().getWindow());
		if(!proceed)
			return;
		lbl.textProperty().unbind();
		lbl.textProperty().bind(job.jobStatusProperty().asString());
		if(job.printPage(node)) {
			job.endJob();
		}else {
			lbl.setText("Cannot print...");
			lbl.textProperty().unbind();
		}
	}
	}
		
	
		
		
		