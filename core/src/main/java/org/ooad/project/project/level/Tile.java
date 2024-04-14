package main.java.org.ooad.project.project.level;

public class Tile {

    private Boolean isWalkable;
    private Tile nextTile;
    private Tile previousTile;
    private Boolean isFirstTile;
    private Boolean isLastTile;

    private Double xCoordinate;
    private Double yCoordinate;
    private Integer xIndex;
    private Integer yIndex;

    private Float height;
    private Float width;


    public Tile(boolean isWalkable) {
        this.isWalkable = isWalkable;

        this.isFirstTile = false;
        this.isLastTile = false;
        this.nextTile = null;

        this.xCoordinate = 0.0;
        this.yCoordinate = 0.0;
        this.xIndex = 0;
        this.yIndex = 0;

        this.height = 50.0f;
        this.width = 50.0f;
    }

    public Boolean isWalkable() {
        return isWalkable;
    }


    public void setWalkable(Boolean isWalkable) {
        this.isWalkable = isWalkable;
    }

    public Tile getNextTile() {
        return nextTile;
    }

    public void setNextTile(Tile nextTile) {
        this.nextTile = nextTile;
    }

    public Boolean isFirstTile() {
        return isFirstTile;
    }

    public void setFirstTile(Boolean isFirstTile) {
        this.isFirstTile = isFirstTile;
    }

    public Boolean isLastTile() {
        return isLastTile;
    }

    public void setLastTile(Boolean isLastTile) {
        this.isLastTile = isLastTile;
    }

    public Double getXCoordinate() {
        return xCoordinate;
    }

    public Double getYCoordinate() {
        return yCoordinate;
    }

    public Integer getXIndex() {
        return xIndex;
    }

    public Integer getYIndex() {
        return yIndex;
    }

    public void setXCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setXIndex(Integer xIndex) {
        this.xIndex = xIndex;
    }

    public void setYIndex(Integer yIndex) {
        this.yIndex = yIndex;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setWidth(Float width) {
        this.width = width;
    }


    public Float getCenterX() {
        return (float) (xCoordinate + width / 2);
    }

    public Float getCenterY() {
        return (float) (yCoordinate + height / 2);
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
    }

    public Tile getPreviousTile() {
        return previousTile;
    }


}
