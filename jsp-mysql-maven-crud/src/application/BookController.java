package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookController implements Initializable {

	BookService bookService = new BookService();

	@FXML
	private TextField tfBookId;

	@FXML
	private TextField tfBookName;

	@FXML
	private TextField tfBookType;

	@FXML
	private TableView<Book> tvBook;

	@FXML
	private TableColumn<Book, Long> colId;

	@FXML
	private TableColumn<Book, String> colName;

	@FXML
	private TableColumn<Book, String> colType;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showDate();
	}

	public void showDate() {
		ObservableList<Book> bookList = bookService.getBooksList();
		if (bookList == null || bookList.size() == 0) {
			System.out.println("no book found");
		}

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colType.setCellValueFactory(new PropertyValueFactory<>("type"));

		tvBook.setItems(bookList);
	}

	@FXML
	private void onClickAddBtn() {

		if (tfBookId.getText().isBlank() || tfBookName.getText().isBlank() || tfBookType.getText().isBlank()) {
			System.out.println("Please book Info");
			showAlert("Please add book Info properly");
			return;
		}
		try {
			Long.parseLong(tfBookId.getText());
		} catch (Exception e) {
			showAlert("Invalid Book Id, Please add a number.");
		}
		Book bookObj = new Book(Long.parseLong(tfBookId.getText()), tfBookName.getText(), tfBookType.getText());

		boolean isSuccessInsert = bookService.insertBook(bookObj);
		if (!isSuccessInsert) {
			System.out.println("Insert failded, some problem !");
			showAlert("Operation failed, some internal problem !");
			return;
		}
		showDate();
		System.out.println("Insert successful");
		showAlert("Record add successfully !");
		tfBookId.setText("");
		tfBookName.setText("");
		tfBookType.setText("");

	}

	public void showAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Book");
		alert.setHeaderText("Message");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	@FXML
	private void onClickUpdateBtn(ActionEvent event) {
		showAlert("Under construction !");
	}

	@FXML
	private void onClickDeleteBtn(ActionEvent event) {
		int currentIndex = tvBook.getSelectionModel().getSelectedIndex();
		if (currentIndex < 0) {
			showAlert("Plese select a item for delete !");
			tvBook.getSelectionModel().clearSelection();
			return;
		}

		String currentId = String.valueOf(tvBook.getItems().get(currentIndex).getId());

		boolean isSuccess = bookService.deletBook(currentId);

		if (!isSuccess) {
			showAlert("Operation failed, some internal problem !");
			return;
		}
		showDate();
		showAlert("Record delete successfully !");
		tvBook.getSelectionModel().clearSelection();


	}

}
