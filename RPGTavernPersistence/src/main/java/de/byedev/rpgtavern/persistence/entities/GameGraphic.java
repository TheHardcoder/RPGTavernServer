package de.byedev.rpgtavern.persistence.entities;

import java.awt.*;
import java.util.List;

public interface GameGraphic {

    String getName();

    void setName(String name);

    boolean hasImage();

    boolean hasImageUrl();

    Image getImage();

    String getImageUrl();

    void setImageUrl(String imageUrl);

    String getDescription();

    void setDescription(String description);

    boolean isVisible();

    void setVisible(boolean visible);

    List<MapMarker> getMarkers();

    boolean isFogOfWarActive();

    void setFogOfWarActive(boolean fogOfWarActive);

    boolean[][] getFow();
    void setFow(boolean[][] fow);

    int getWidth();
    int getHeight();
}
