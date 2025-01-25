module com.sayouti.sayotp {
    requires javafx.controls;
    requires javafx.fxml;
    requires googleauth;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.sayouti.sayotp to javafx.fxml;
    exports com.sayouti.sayotp;
    opens com.sayouti.sayotp.Controller to javafx.fxml;
    exports com.sayouti.sayotp.Controller;
}