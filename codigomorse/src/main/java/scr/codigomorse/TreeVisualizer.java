package scr.codigomorse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    private static ArvoreMorse arvore;
    private static Stage primaryStageRef;

    public static void setArvore(ArvoreMorse arv) {
        arvore = arv;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStageRef = primaryStage;
        primaryStage.setTitle("Visualizador de Árvore Morse");

        if (arvore == null || arvore.raiz == null) {
            System.out.println("Árvore não inicializada!");
            Platform.exit();
            return;
        }

        int height = getHeight(arvore.raiz);
        int canvasHeight = Math.max(600, 100 + height * 120);
        int canvasWidth = Math.max(1200, (int) Math.pow(2, height) * 80);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        drawTree(canvas, arvore.raiz);

        ScrollPane scrollPane = new ScrollPane(canvas);
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(false);

        Button btnFechar = new Button("Fechar");
        btnFechar.setOnAction(e -> primaryStage.close());

        HBox bottomBar = new HBox(10);
        bottomBar.setStyle("-fx-padding: 10; -fx-alignment: center;");
        bottomBar.getChildren().add(btnFechar);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, Math.min(1200, canvasWidth + 20), Math.min(800, canvasHeight + 80));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int getHeight(NoMorse node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.esquerda), getHeight(node.direita));
    }

    private void drawTree(Canvas canvas, NoMorse root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        gc.setFill(Color.DARKBLUE);
        gc.fillText("ÁRVORE DE CÓDIGO MORSE", canvas.getWidth() / 2 - 120, 30);
        gc.setFill(Color.BLACK);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gc.setFill(Color.BLUE);
        gc.fillText("Azul = ponto (.)", 20, 30);
        gc.setFill(Color.RED);
        gc.fillText("Vermelho = traço (-)", 150, 30);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(300, 18, 15, 15);
        gc.setFill(Color.BLACK);
        gc.strokeRect(300, 18, 15, 15);
        gc.fillText("= Caractere", 320, 30);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(420, 18, 15, 15);
        gc.setFill(Color.BLACK);
        gc.strokeRect(420, 18, 15, 15);
        gc.fillText("= Nó intermediário", 440, 30);

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        drawNode(gc, root, canvas.getWidth() / 2, 70, canvas.getWidth() / 4, "");
    }

    private void drawNode(GraphicsContext gc, NoMorse node, double x, double y, double xOffset, String path) {
        if (node == null) {
            return;
        }

        double nodeRadius = 25;

        if (node.esquerda != null) {
            double newX = x - xOffset;
            double newY = y + 100;

            gc.setStroke(Color.BLUE);
            gc.setLineWidth(2);
            gc.strokeLine(x, y + nodeRadius, newX, newY - nodeRadius);

            gc.setFill(Color.BLUE);
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            gc.fillText(".", (x + newX) / 2 - 15, (y + newY) / 2);

            drawNode(gc, node.esquerda, newX, newY, xOffset / 2, path + ".");
        }

        if (node.direita != null) {
            double newX = x + xOffset;
            double newY = y + 100;

            gc.setStroke(Color.RED);
            gc.setLineWidth(2);
            gc.strokeLine(x, y + nodeRadius, newX, newY - nodeRadius);

            gc.setFill(Color.RED);
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            gc.fillText("-", (x + newX) / 2 + 10, (y + newY) / 2);

            drawNode(gc, node.direita, newX, newY, xOffset / 2, path + "-");
        }

        Color nodeColor = (node.caractere != '\0') ? Color.LIGHTGREEN : Color.LIGHTGRAY;

        gc.setFill(nodeColor);
        gc.fillOval(x - nodeRadius, y - nodeRadius, nodeRadius * 2, nodeRadius * 2);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeOval(x - nodeRadius, y - nodeRadius, nodeRadius * 2, nodeRadius * 2);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        String text = (node.caractere != '\0') ? String.valueOf(node.caractere) : "○";
        double textWidth = text.length() * 8;
        gc.fillText(text, x - textWidth / 2, y + 6);

        if (!path.isEmpty()) {
            gc.setFill(Color.DARKBLUE);
            gc.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
            double pathWidth = path.length() * 7;
            gc.fillText(path, x - pathWidth / 2, y - 32);
        }

        gc.setStroke(Color.BLACK);
    }

    public static void visualizar(ArvoreMorse arv) {
        setArvore(arv);

        if (primaryStageRef != null) {
            Platform.runLater(() -> {
                try {
                    Stage newStage = new Stage();
                    TreeVisualizer visualizer = new TreeVisualizer();
                    visualizer.start(newStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            new Thread(() -> {
                Application.launch(TreeVisualizer.class);
            }).start();
        }
    }
}