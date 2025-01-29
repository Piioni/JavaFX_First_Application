import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuPrincipal {
    private final ListaProductos listaProductos;
    private final Stage stage;

    public MenuPrincipal(Stage stage, ListaProductos listaProductos) {
        this.stage = stage;
        this.listaProductos = listaProductos;
    }

    public Scene getScene() {
        // Creation de la estructura de la escena
        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        // Creation del título
        Label titulo = new Label("Menú Principal");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.getStyleClass().add("label-title");

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        // Creation de los botones
        Button btnProductos = new Button("Products management");
        btnProductos.setOnAction(e -> MainApp.mostrarVentanaProductos());
        btnProductos.setPrefWidth(200);

        Button btnCargar = new Button("Load");
        btnCargar.setOnAction(e -> cargar());
        btnCargar.setPrefWidth(200);

        Button btnSalir = new Button("Exit");
        btnSalir.setOnAction(e -> System.exit(0));
        btnSalir.setPrefWidth(200);

        menuLayout.getChildren().addAll(tituloBox, btnProductos, btnCargar, btnSalir);

        Scene scene = new Scene(menuLayout, 450, 400);
        scene.getStylesheets().add("stylesMenu.css");

        return scene;
    }


    private void cargar() {
        // Permite al usuario seleccionar un archivo para cargar
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Archivo");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                // Limpiar la lista de productos y carga los productos desde el archivo
                listaProductos.clear();
                listaProductos.cargarProductos(file.toPath());
                MainApp.setPath(file.toPath()); // Guardar la ubicación del archivo para futuras referencias
                System.out.println("Cargado desde: " + file.getAbsolutePath());
                MainApp.mostrarVentanaProductos();
            } catch (IOException e) {
                System.out.println("Error al cargar el archivo: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo 2: " + e.getMessage());
            }
        }
    }

}