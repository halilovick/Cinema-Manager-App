
# Cinema manager

A simple project for managing a cinema.
Running and testing the project is not possible without database connection details.


## Run Locally

Clone the project

```bash
  git clone https://github.com/halilovick/rpr-projekat
```

Go to the project directory

```bash
  cd rpr-projekat
```

Install dependencies

```bash
  mvn clean install
```

To run the CLI

```bash
mvn package -P cli-app 
java -jar cinema-manager-cli-jar-with-dependencies.jar [option] (parameters)
```

To run the GUI

```bash
mvn package -P gui-app
java --module-path "PATH-TO-JAVAFX" --add-modules javafx.controls,javafx.fxml -jar cinema-manager-gui-jar-with-dependencies.jar 
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```

