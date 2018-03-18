package library.assistant.ui.addBook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryAssistant extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("add_book.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //Minimun size of Stage addBook
        stage.setMinWidth(441);
        stage.setMinHeight(269);
        //Maximus size of Stage addBook
        stage.setMaxWidth(445);
        stage.setMaxHeight(273);
        
        stage.setTitle("addBook");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    } 
}
