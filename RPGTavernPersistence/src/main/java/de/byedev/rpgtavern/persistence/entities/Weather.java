package de.byedev.rpgtavern.persistence.entities;

public class Weather {

    private double temperature;
    private int windSpeed;
    private SkyCondition condition;

    public Weather() {
        temperature = 20;
        windSpeed = 0;
        condition = SkyCondition.SUNNY;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public SkyCondition getCondition() {
        return condition;
    }

    public void setCondition(SkyCondition condition) {
        this.condition = condition;
    }

    public static enum SkyCondition {
        RAINY, CLOUDY, SUNNY, SNOWY, FOGGY, LIGHTNING, TORNADO;
    }
}
