package com.example.game.Model.WhackAnAlien;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Vector;

public class AlienList implements Iterable<Alien> {
  private Vector<Alien> listOfAliens;

  public AlienList() {
    listOfAliens = new Vector<>();
  }

  @Override
  @NonNull
  public Iterator<Alien> iterator() {
    return new AlienIterator();
  }

  public void add(Alien alien) {
    listOfAliens.add(alien);
  }

  public Alien get(int index) {
    return listOfAliens.get(index);
  }

  public int size() {
    return listOfAliens.size();
  }

  public void removeTrash() {
    listOfAliens.removeIf(Alien::isDead);
    listOfAliens.removeIf(Alien::isHere);
  }

  public boolean contains(Alien alien) {
    return listOfAliens.contains(alien);
  }

  private class AlienIterator implements Iterator<Alien> {
    private int index;

    AlienIterator() {
      index = 0;
    }

    @Override
    public boolean hasNext() {
      if (listOfAliens.size() == 0) {
        return false;
      }
      return (index < listOfAliens.size());
    }

    @Override
    public Alien next() {
      return listOfAliens.get(index++);
    }
  }
}
