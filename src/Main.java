import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private BufferedImage image;
    private ImageDisplay imageDisplay;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        setupUI();
    }

    private void setupUI() {
        JButton openButton = new JButton("Open Image");
        openButton.addActionListener(e -> chooseImageFile());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 5));
        buttonPanel.add(createFilterButton("Negative", this::applyNegative));
        buttonPanel.add(createFilterButton("Borders", this::applyBorders));
        buttonPanel.add(createFilterButton("B/W", this::applyBlackAndWhite));
        buttonPanel.add(createFilterButton("Grey", this::applyGreyScale));
        buttonPanel.add(createFilterButton("Shift Right", this::applyShiftRight));
        buttonPanel.add(createFilterButton("Shift Left", this::applyShiftLeft));
        buttonPanel.add(createFilterButton("Original", this::applyOriginalColors));
        buttonPanel.add(createFilterButton("Brighter", this::applyBrighter));
        buttonPanel.add(createFilterButton("Darker", this::applyDarker));
        buttonPanel.add(createFilterButton("Mirror", this::applyMirror));

        this.setLayout(new BorderLayout());
        this.add(openButton, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);

        imageDisplay = new ImageDisplay(null);
        this.add(imageDisplay, BorderLayout.CENTER);

        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                image = ImageIO.read(file);
                updateImageDisplay();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JButton createFilterButton(String name, Runnable action) {
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            if (image != null) {
                action.run();
                updateImageDisplay();
            }
        });
        return button;
    }

    private void updateImageDisplay() {
        imageDisplay.setImage(image);
        imageDisplay.repaint();
    }

    private void applyNegative() {
        image = imageDisplay.negative(image);
    }

    private void applyBorders() {
        image = imageDisplay.showBorders(image);
    }

    private void applyBlackAndWhite() {
        image = imageDisplay.blackAndWhite(image);
    }

    private void applyGreyScale() {
        image = imageDisplay.greyScale(image);
    }

    private void applyShiftRight() {
        image = imageDisplay.shiftRight(image);
    }

    private void applyShiftLeft() {
        image = imageDisplay.shiftLeft(image);
    }

    private void applyOriginalColors() {
        image = imageDisplay.originalColors(image);
    }

    private void applyBrighter() {
        image = imageDisplay.brighter(image);
    }

    private void applyDarker() {
        image = imageDisplay.darker(image);
    }

    private void applyMirror() {
        image = imageDisplay.mirror(image);
    }
}