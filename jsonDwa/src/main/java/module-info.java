module com.example.jsondwa {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.jsondwa to javafx.fxml;
    exports com.example.jsondwa;
}