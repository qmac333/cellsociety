package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;

import java.util.LinkedList;
import java.util.List;

public class TriangleShapeManager extends ShapeManager{
    @Override
    public List<Coordinate> getNeighborsLeft(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()-1));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getNeighborsRight(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()+1));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getNeighborsUp(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getNeighborsDown(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getAllNeighborsLeft(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()-2));
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()-2));
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()-1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()-1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getAllNeighborsRight(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()+2));
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()+2));
        myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()+1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()+1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getAllNeighborsUp(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()-2));
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()-1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()));
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()+1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()+2));
        return myNeighbors;
    }

    @Override
    public List<Coordinate> getAllNeighborsDown(Coordinate cellCoordinates) {
        List<Coordinate> myNeighbors = new LinkedList<>();
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()-2));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()-1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()+1));
        myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()+2));
        return myNeighbors;
    }
}
