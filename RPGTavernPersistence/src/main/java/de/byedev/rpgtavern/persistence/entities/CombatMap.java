package de.byedev.rpgtavern.persistence.entities;

public class CombatMap {

    private Token[][] tokens;

    private String bgUrl;

    private String gridColor = "rgba(0,0,0,0.25)";

    public CombatMap(int width, int height) {
        tokens = new Token[width][height];
    }

    public Token[][] getTokens() {
        return tokens;
    }

    public void setToken(int x, int y, Token token) {
        if (x < 0 || y < 0 || x >= tokens.length || y >= tokens[0].length)
            return;
        tokens[x][y] = token;
    }

    public void resize(int width, int height) {
        tokens = new Token[width][height];
    }

    public int getWidth() {
        return tokens.length;
    }

    public int getHeight() {
        return tokens[0].length;
    }

    public String getBackgroundUrl() {
        return bgUrl;
    }

    public void setBackgroundUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    public String getGridColor() {
        return gridColor;
    }

    public void setGridColor(String gridColor) {
        this.gridColor = gridColor;
    }

    public void clearTokens() {
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0 ; j < tokens[0].length; j++) {
                tokens[i][j] = null;
            }
        }
    }
}
