Simple project for managing a cinema.

Build and run GUI: \
mvn clean compile assembly:single
java --module-path "PATH-TO-JAVAFX" --add-modules javafx.controls,javafx.fxml -jar .\cinema-manager-gui-jar-with-dependencies.jar \
OR \
mvn clean install \
mvn javafx:run


Build and run CLI: \
mvn clean compile assembly:single \
java -jar cinema-manager-cli-jar-with-dependencies.jar [option] (parameters)

