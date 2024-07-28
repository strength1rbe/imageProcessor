import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
class ImageDisplay extends JPanel {
    private BufferedImage image;

    ImageDisplay(BufferedImage image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public BufferedImage negative(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                int negativeRed = 255 - currentColor.getRed();
                int negativeGreen = 255 - currentColor.getGreen();
                int negativeBlue = 255 - currentColor.getBlue();
                Color updatedColor = new Color(negativeRed, negativeGreen, negativeBlue);
                image.setRGB(i, j, updatedColor.getRGB());
            }
        }
        return image;
    }

    public BufferedImage showBorders(BufferedImage image) {
        int borderDiff = 125;
        for (int i = 0; i < image.getWidth() - 1; i++) {
            for (int j = 0; j < image.getHeight() - 1; j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                Color right = new Color(image.getRGB(i + 1, j));
                Color down = new Color(image.getRGB(i, j + 1));
                int redDiff = Math.abs(currentColor.getRed() - right.getRed());
                int greenDiff = Math.abs(currentColor.getGreen() - right.getGreen());
                int blueDiff = Math.abs(currentColor.getBlue() - right.getBlue());
                int totalDiff = redDiff + greenDiff + blueDiff;
                if (totalDiff > borderDiff) {
                    image.setRGB(i, j, Color.cyan.getRGB());
                } else {
                    redDiff = Math.abs(currentColor.getRed() - down.getRed());
                    greenDiff = Math.abs(currentColor.getGreen() - down.getGreen());
                    blueDiff = Math.abs(currentColor.getBlue() - down.getBlue());
                    totalDiff = redDiff + greenDiff + blueDiff;
                    if (totalDiff > borderDiff) {
                        image.setRGB(i, j, Color.cyan.getRGB());
                    }
                }
            }
        }
        return image;
    }

    public BufferedImage blackAndWhite(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                int sum = currentColor.getRed() + currentColor.getGreen() + currentColor.getBlue();
                if (sum / 3 < 128) {
                    image.setRGB(i, j, new Color(0, 0, 0).getRGB());
                } else {
                    image.setRGB(i, j, new Color(255, 255, 255).getRGB());
                }
            }
        }
        return image;
    }

    public BufferedImage greyScale(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                int sum = currentColor.getRed() + currentColor.getGreen() + currentColor.getBlue();
                int average = sum / 3;
                image.setRGB(i, j, new Color(average, average, average).getRGB());
            }
        }
        return image;
    }

    public BufferedImage shiftRight(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                image.setRGB(i, j, new Color(currentColor.getGreen(), currentColor.getBlue(), currentColor.getRed()).getRGB());
            }
        }
        return image;
    }

    public BufferedImage shiftLeft(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                image.setRGB(i, j, new Color(currentColor.getBlue(), currentColor.getRed(), currentColor.getGreen()).getRGB());
            }
        }
        return image;
    }

    public BufferedImage originalColors(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                image.setRGB(i, j, new Color(currentColor.getRed() < 128 ? 0 : 255, currentColor.getGreen() < 128 ? 0 : 255, currentColor.getBlue() < 128 ? 0 : 255).getRGB());
            }
        }
        return image;
    }

    public BufferedImage brighter(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                float by = 1.5F;
                int red = (int) (currentColor.getRed() * by);
                if (red > 255) {
                    red = 255;
                }
                int green = (int) (currentColor.getGreen() * by);
                if (green > 255) {
                    green = 255;
                }
                int blue = (int) (currentColor.getBlue() * by);
                if (blue > 255) {
                    blue = 255;
                }
                image.setRGB(i, j, new Color(red, green, blue).getRGB());
            }
        }
        return image;
    }

    public BufferedImage darker(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color currentColor = new Color(image.getRGB(i, j));
                float by = 0.5F;
                int red = (int) (currentColor.getRed() * by);
                int green = (int) (currentColor.getGreen() * by);
                int blue = (int) (currentColor.getBlue() * by);
                image.setRGB(i, j, new Color(red, green, blue).getRGB());
            }
        }
        return image;
    }

    public BufferedImage mirror(BufferedImage image) {
        for (int i = 0; i < image.getWidth() / 2; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int colorCode = image.getRGB(i, j);
                int otherSideColor = image.getRGB(image.getWidth() - i - 1, j);
                image.setRGB(i, j, otherSideColor);
                image.setRGB(image.getWidth() - i - 1, j, colorCode);
            }
        }
        return image;
    }
}