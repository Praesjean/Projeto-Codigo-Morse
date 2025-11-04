module scr.codigomorse {
    requires javafx.controls;
    requires javafx.fxml;


    opens scr.codigomorse to javafx.fxml;
    exports scr.codigomorse;
}