package com.codecrate.shard.grid;

/** 
 * Exception if PathFinder is unable to find a complete path
 */
public class PathNotFoundException extends RuntimeException {
  
  public PathNotFoundException(GridSquare start, GridSquare end) {
    super("Unable to find path from " + start + " to " + end);
  }
}
