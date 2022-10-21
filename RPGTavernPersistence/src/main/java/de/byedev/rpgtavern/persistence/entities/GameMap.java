package de.byedev.rpgtavern.persistence.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameMap implements GameGraphic {

    private String name;
    private boolean visible;
    private String description;
    private String imageUrl;
    private List<MapMarker> markers = new ArrayList<>();

//    private BufferedImage fowImg;

    private boolean fogOfWarActive;

    private boolean[][] fow;
    private int width;
    private int height;

    public GameMap(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        setImageUrl(imageUrl);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean hasImage() {
        return false;
    }

    @Override
    public boolean hasImageUrl() {
        return true;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        updateFow();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public List<MapMarker> getMarkers() {
        if (markers == null)
            markers = new ArrayList<>();
        return markers;
    }

    @Override
    public boolean isFogOfWarActive() {
        return fogOfWarActive;
    }

    @Override
    public void setFogOfWarActive(boolean fogOfWarActive) {
        this.fogOfWarActive = fogOfWarActive;
    }

    @Override
    public boolean[][] getFow() {
        if (fow == null)
            updateFow();
        return fow;
    }

    @Override
    public void setFow(boolean[][] fow) {
        this.fow = fow;
    }

    @Override
    public int getWidth() {
        if (width <= 0)
            updateFow();
        return width;
    }

    @Override
    public int getHeight() {
        if (height <= 0)
            updateFow();
        return height;
    }

    private void updateFow(){
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            width = image.getWidth();
            height = image.getHeight();
            if (width > 0 && height > 0)
                fow = new boolean[width][height];
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
