import java.awt.*;
import java.lang.reflect.Array;
import java.lang.Math;
// import java.io.*;
import java.util.*;
public class Solution {
 
  // Add constants for particle types here.
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int LAVA = 4;
  public static final int ICE = 5;
  public static final int STONE = 6;
  public static final int HOTSTONE = 7;
  public static final int MOLTENSTONE = 8;
  public static final int STEAM = 9;
  public static final int SEED = 10;
  public static final int PLANT = 11;
  Color black = Color.black;
  Color gray = Color.gray;
  Color yellow = Color.YELLOW;
  Color brown = new Color (75,75,20);
  Color skyBlue = new Color(0,204,255);
  Color shallowBlue = Color.cyan;
  Color mediumBlue = new Color(5,88,135);
  Color deepBlue = new Color(2,43,66);
  Color red = Color.red;
  Color darkgray = Color.darkGray;
  Color orange = Color.orange;
  Color hotOrange = new Color(255,228,207);
  Color lightBlue = new Color(201,253,255,175);
  Color lightGray = new Color(200,200,200,175);
  Color amber = new Color(230, 173, 50);
  Color green = Color.green;
  Color lightYellow = new Color(240, 200, 70);
  int startTime;
 
  public static final String[] NAMES = {"Empty", "Metal","Sand","Water","Lava","Ice","Stone","Hot Stone","Molten Stone","Steam","Seed"};
 
  // Do not add any more fields as part of Lab 5.
  private int[][] grid;
  private int[][] timeGrid;
  private int[][] pastParticle;
  private boolean[][] colorGrid;
  private boolean[][] vectorGrid;
  private SandDisplayInterface display;
  private RandomGenerator random;
  int stepCount = 0,depth;
  int skyDepth;
  int clickCheck;
 
  /**
   * Constructor.
   *
   * @param display The display to use for this run
   * @param random The random number generator to use to pick random points
   */
  public Solution(SandDisplayInterface display, RandomGenerator random) {
    this.display = display;
    this.random = random;
    grid = new int [display.getNumRows()][display.getNumColumns()];
    timeGrid = new int [display.getNumRows()][display.getNumColumns()];
    pastParticle = new int [display.getNumRows()][display.getNumColumns()];
    colorGrid = new boolean [display.getNumRows()][display.getNumColumns()];
    vectorGrid = new boolean [display.getNumRows()][display.getNumColumns()];
  }
 
  /**
   * Called when the user clicks on a location.
   *
   * @param row
   * @param col
   * @param tool
   */
  private void locationClicked(int row, int col, int tool) {
    grid[row][col] = tool;
    clickCheck = stepCount;
  }
  public int depthCheck(int row, int col,int particle){
    int depth = 0;
    while (row > 0 && grid[row][col] == particle) {
      depth ++;
      row --;
    }
    return depth;
  }
  public int[] vectorCheck(int row, int col,int particle){
    int[] vector = {0,0};
    while (row > 0 && grid[row][col] == particle) {
      vector[0] --;
      row --;
    }
    while (col > 0 && grid[row][col] == particle) {
      vector[1] --;
      col --;
    }
    while (row < grid.length && grid[row][col] == particle) {
      vector[0] ++;
      row ++;
    }
    while (col < grid[0].length && grid[row][col] == particle) {
      vector[1] ++;
      col ++;
    }
    return vector;
  }
  public int perimiterCheck (int row,int col, int particle) {
    int result = 0;
      if (row-1 > 0 && grid[row-1][col] == particle) {
        result ++;
      }
      if (row + 1 < grid.length && grid[row+1][col] == particle) {
        result ++;
      }
    
      if(col - 1 > 0 && grid[row][col-1] == particle) {
        result ++;
      }
    
      if(col + 1 < grid[0].length && grid[row][col+1] == particle) {
        result ++;
      }
    
      if(row - 1 > 0 && col + 1 < grid[0].length && grid[row-1][col+1] == particle) {
        result ++;
      }
      if(row - 1 > 0 && col - 1 > 0 && grid[row-1][col-1] == particle) {
        result ++;
      }
      if(row + 1 < grid.length && col - 1 > 0 && grid[row+1][col-1] == particle) {
        result ++;
      }
      if(row + 1 < grid.length && col + 1 < grid[0].length && grid[row+1][col+1] == particle) {
        result ++;
      }
    return result;
  }
  /** Copies each element of grid into the display. */
  public void updateDisplay() {
    for (int x = 0; x < grid.length; x++) {
      for(int y =0; y< grid[0].length; y++) {
        if(grid[x][y] == METAL) {
          display.setColor(x, y, gray);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == SAND) {
          int randomColor = random.getRandomColor();
          if (randomColor == 0 && colorGrid[x][y] != true) { 
          display.setColor(x, y, yellow);
          colorGrid[x][y] = true;
          }
           if (randomColor == 1 && colorGrid[x][y] != true) { 
            display.setColor(x, y, amber);
            colorGrid[x][y] = true;
          }
           if (randomColor == 2 && colorGrid[x][y] != true) { 
             display.setColor(x, y, lightYellow);
             colorGrid[x][y] = true;
            }
        }
        else if(grid[x][y] == WATER) {
          colorGrid[x][y] = false;
          depth = depthCheck(x,y,WATER)*5;
          int redVal=10-depth,greenVal=130-depth, blueVal = 200-depth;
          if(redVal < 0) {redVal = 0;}
          if(greenVal < 0) {greenVal = 0;}
          if(blueVal < 0) {blueVal=0;}
          Color waterBlue = new Color(redVal,greenVal,blueVal);
          display.setColor(x, y, waterBlue);
          colorGrid[x][y] = true;
          
        }
       
       
        else if(grid[x][y] == LAVA) {
          display.setColor(x, y, red);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == STONE) {
          display.setColor(x, y, darkgray);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == HOTSTONE) {
          display.setColor(x, y, orange);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == MOLTENSTONE) {
          display.setColor(x, y, hotOrange);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == ICE) {
          display.setColor(x, y, lightBlue);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == STEAM) {
          display.setColor(x, y, lightGray);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == SEED) {
          display.setColor(x, y, brown);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == PLANT) {
          display.setColor(x, y, green);
          colorGrid[x][y] = true;
        }
        else if(grid[x][y] == EMPTY) {
          skyDepth = depthCheck(x,y, EMPTY)*2;
          colorGrid[x][y] = false;
          int redVal = 0+skyDepth;
          int greenVal = 204+skyDepth;
          if (redVal > 255) {redVal = 255;}
          if (greenVal> 255) {greenVal = 255;}
          Color skyBlue = new Color(redVal,greenVal,255);
          display.setColor(x, y, skyBlue);
        }
        
      }
    }
  }
 
  /** Called repeatedly. Causes one random particle to maybe do something. */
  public void step() {
    Point randomPoint = random.getRandomPoint();
    int x = randomPoint.getRow();
    int y = randomPoint.getColumn();
    int currentParticle = grid[x][y],dispParticle;
    int dispPartL = EMPTY,dispPartR = EMPTY;
     //SEED Drops
    if (currentParticle == SEED) {
    if (x+1 < grid.length 
    && depthCheck(x-1,y, SAND) < 5 
    ) {
      dispParticle = grid[x+1][y];
      if(dispParticle == EMPTY || dispParticle ==SAND) {
      grid[x][y] =  dispParticle;
      grid[x+1][y] = currentParticle;
      colorGrid[x][y] = false;
      colorGrid[x+1][y] = false; 
      }   
    }
    //Plant Grows
    if(stepCount - timeGrid[x][y] >= 100000000 && perimiterCheck(x, y, SAND) >= 6) {
        colorGrid[x][y] = false;
        colorGrid[x-1][y] = false;
        grid[x-1][y] = PLANT;
      
    }
  }
    //Sand
    if (x+1 < grid.length 
      && (currentParticle == SAND)) {
      dispParticle = grid[x+1][y];
      if((dispParticle == EMPTY || dispParticle == WATER)) {
      grid[x][y] =  dispParticle;
      grid[x+1][y] = currentParticle;
      colorGrid[x][y] = false;
      colorGrid[x+1][y] = false; 
    }
     //Falling Sand Simulation
    
    if (x+1 < grid.length && (currentParticle == SAND || currentParticle == ICE)
      && (grid[x+1][y] == SAND || grid[x+1][y] == METAL || grid[x+1][y] == ICE )
      && dispParticle != EMPTY) {
 
      if(y < grid[0].length -1) {dispPartR = grid[x+1][y+1];}
      if(y  > 0) {dispPartL = grid[x+1][y-1];}
 
      if(y < grid[0].length -1 
        && (dispPartR == EMPTY 
        || dispPartR == WATER)) {
 
          grid[x][y] =  dispPartR;
          grid[x+1][y + 1] = currentParticle;
        
          colorGrid[x][y] = false;
          colorGrid[x+1][y+1] = false;
          
        }
      
      else if (y  > 0 
        && (dispPartL == EMPTY 
        || dispPartL == WATER)) {
          grid[x][y] = dispPartL;
          grid[x+1][y - 1] = currentParticle;
       
            colorGrid[x][y] = false;
            colorGrid[x+1][y-1] = false;   
      }
      }
      }
    //ICE 
    if (currentParticle == ICE) {
      if(x+1 < grid.length && grid[x+1][y] == EMPTY) {
        grid[x][y] = EMPTY;
        grid[x+1][y]=currentParticle;
      }
      if(x+1 < grid.length && stepCount -timeGrid[x][y] >= 7500000 && grid[x+1][y] == WATER) {
       grid[x][y] = WATER;
 
 
    }
    
    else if (grid[x-1][y] == WATER) { //ICE floats
      grid[x][y] = WATER;
    grid[x-1][y] = currentParticle; 
    }
  }
    //Water
    if (currentParticle == WATER || currentParticle == LAVA) {
      int randomDirection = random.getRandomDirection(),direction; 
      if (randomDirection == 1) {direction = 1;}
      else if (randomDirection == 2) {direction = -1;}
      else {direction = 0;}
    
    if (direction == 0 
        && x+1 < grid.length
        && grid[x + 1][y] == EMPTY) {
        grid[x][y] =  EMPTY;
        grid[x+1][y] = currentParticle;
        if (pastParticle[x][y] != currentParticle) {
          colorGrid[x][y] = false;
          colorGrid[x+1][y] = false;
        }
      }
    else if (
        direction == 1
        && y < grid[0].length - 1
      
        && grid[x][y + direction] == EMPTY )
        {
        grid[x][y] =  EMPTY;
        grid[x][y + direction] = currentParticle;
        if (pastParticle[x][y] != currentParticle) {
          colorGrid[x][y] = false;
          colorGrid[x][y+direction] = false;
        }
    }
    else if (
      direction == -1
      && y > 0 
      && grid[x][y + direction] == EMPTY )
      {
      grid[x][y] =  EMPTY;
      grid[x][y + direction] = currentParticle;
      if (pastParticle[x][y] != currentParticle) {
        colorGrid[x][y] = false;
        colorGrid[x][y+direction] = false;
      }
  }
 
  }
//Steam Floats and Billows
  if (currentParticle== STEAM) {
    if (x == 0) {
      grid[x][y] = EMPTY;
    }
    int randomDirection = random.getRandomDirection(),direction; 
    if (randomDirection == 1) {direction = 1;}
      else if (randomDirection == 2) {direction = -1;}
      else {direction = 0;}
    
      if (direction == 0 
          && x > 0
          && grid[x - 1][y] == EMPTY) {
          grid[x][y] =  EMPTY;
          grid[x-1][y] = currentParticle;
        }
      else if (
          direction == 1
          && y < grid[0].length - 1
        
          && grid[x][y + direction] == EMPTY )
          {
          grid[x][y] =  EMPTY;
          grid[x][y + direction] = currentParticle;
      }
      else if (
        direction == -1
        && y > 0 
        && grid[x][y + direction] == EMPTY )
        {
        grid[x][y] =  EMPTY;
        grid[x][y + direction] = currentParticle;
    }
    
  }  
 
//Lava - Interactions
if(currentParticle == LAVA) {
 
  if(stepCount -timeGrid[x][y] >= 10000000 && x<grid.length - 1 && perimiterCheck(x, y, WATER) >=1) {
 
    grid[x+1][y] = STONE;
    grid[x-1][y] = STEAM;
    grid[x][y] = EMPTY;
 
  }
  if(stepCount -timeGrid[x][y] >= 10000000 && x<grid.length - 1 && perimiterCheck(x, y, ICE)>=1) {
 
    grid[x+1][y] = STONE;
    grid[x-1][y] = STEAM;
    grid[x][y] = WATER;
 
  }
 
  if(stepCount -timeGrid[x][y] >= 10000000 && x<grid.length - 1 && perimiterCheck(x,y,STONE)>=1) {
      grid[x+1][y] = HOTSTONE;
    
  }
 
if(stepCount -timeGrid[x][y] >= 10000000 && x<grid.length - 1 && perimiterCheck(x,y,HOTSTONE)>=1) {
    
  grid[x+1][y] = MOLTENSTONE;
 
}
if(stepCount -timeGrid[x][y] >= 10000000 && x<grid.length - 1 && perimiterCheck(x,y,MOLTENSTONE)>=1) {
    
  grid[x+1][y] = LAVA;
  grid[x][y] = EMPTY;
 
}
  
}
 
if(currentParticle == STONE || currentParticle == HOTSTONE || currentParticle == MOLTENSTONE) {
  //Stone Floats ...It's obsidian!
  if(grid[x-1][y]==WATER){
    if (grid[x-1][y] == WATER) { 
      grid[x][y] = WATER;
    grid[x-1][y] = currentParticle; 
    }
  }
  //Stone Chills
  if(x + 1 < grid.length
    && x > 0
    && y + 1 < grid[0].length
    && y > 0
    && grid[x+1][y]!=LAVA
    && grid[x+1][y+1]!=LAVA
    && grid[x][y+1]!=LAVA
    && grid[x][y-1]!=LAVA
    && grid[x-1][y-1]!=LAVA
    && grid[x-1][y+1]!=LAVA
    && grid[x-1][y]!=LAVA
    && currentParticle > 6
    && currentParticle <= 8
    && grid[x+1][y-1]!=LAVA && stepCount -timeGrid[x][y] >= 20000000) {
      grid[x][y] = currentParticle-1;
  }
}
if (pastParticle[x][y]!= currentParticle) {
startTime = stepCount;
colorGrid[x][y] = false;
timeGrid[x][y] = startTime;
pastParticle[x][y] = currentParticle;
}
}
 
  /********************************************************************/
  /********************************************************************/
  /**
   * DO NOT MODIFY
   *
   * <p>The rest of this file is UI and testing infrastructure. Do not modify as part of pre-GDA Lab
   * 5.
   */
  /********************************************************************/
  /********************************************************************/
 
  private static class Point {
    private int row;
    private int column;
 
    public Point(int row, int column) {
      this.row = row;
      this.column = column;
    }
 
    public int getRow() {
      return row;
    }
 
    public int getColumn() {
      return column;
    }
  }
 
 /*  public boolean hasColor(int row, int col){
    if(grid[row][col] != EMPTY) {
    return true;
    }
    return false;
  } */
  /**
   * Special random number generating class to help get consistent results for testing.
   *
   * <p>Please use getRandomPoint to get an arbitrary point on the grid to evaluate.
   *
   * <p>When dealing with water, please use getRandomDirection.
   */
  public static class RandomGenerator {
    private static Random randomNumberGeneratorForPoints;
    private static Random randomNumberGeneratorForDirections;
    private static Random randomNumberGeneratorForColors;
    private int numRows;
    private int numCols;
 
    public RandomGenerator(int seed, int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random(seed);
      randomNumberGeneratorForDirections = new Random(seed);
      randomNumberGeneratorForColors = new Random(seed);
      this.numRows = numRows;
      this.numCols = numCols;
    }
 
    public RandomGenerator(int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random();
      randomNumberGeneratorForDirections = new Random();
      randomNumberGeneratorForColors = new Random();
      this.numRows = numRows;
      this.numCols = numCols;
    }
 
    public Point getRandomPoint() {
      return new Point(
          randomNumberGeneratorForPoints.nextInt(numRows),
          randomNumberGeneratorForPoints.nextInt(numCols));
    }
 
    /**
     * Method that returns a random direction.
     *
     * @return an int indicating the direction of movement: 0: Indicating the water should attempt
     *     to move down 1: Indicating the water should attempt to move right 2: Indicating the water
     *     should attempt to move left
     */
    public int getRandomDirection() {
      return randomNumberGeneratorForDirections.nextInt(3);
    }
    public int getRandomColor() {
      return randomNumberGeneratorForColors.nextInt(3);
    }
  }
 
  public static void main(String[] args) {
    // Test mode, read the input, run the simulation and print the result
    Scanner in = new Scanner(System.in);
    int numRows = in.nextInt();
    int numCols = in.nextInt();
    int iterations = in.nextInt();
    Solution lab =
        new Solution(new NullDisplay(numRows, numCols), new RandomGenerator(0, numRows, numCols));
    lab.readGridValues(in);
    lab.runNTimes(iterations);
    lab.printGrid();
  }
 
  /**
   * Read a grid set up from the input scanner.
   *
   * @param in
   */
  private void readGridValues(Scanner in) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = in.nextInt();
      }
    }
  }
 
  /** Output the current status of the grid for testing purposes. */
  private void printGrid() {
    for (int i = 0; i < grid.length; i++) {
      System.out.println(Arrays.toString(grid[i]));
    }
  }
 
  /** Runner that advances the display a determinate number of times. */
  private void runNTimes(int times) {
    for (int i = 0; i < times; i++) {
      runOneTime();
    }
  }
 
  /** Runner that controls the window until it is closed. */
  public void run() {
    while (true) {
      runOneTime();
    }
  }
 
  /**
   * Runs one iteration of the display. Note that one iteration may call step repeatedly depending
   * on the speed of the UI.
   */
  
  private void runOneTime() {
    for (int i = 0; i < display.getSpeed(); i++) {
      step();
      stepCount+=1;
    }
    updateDisplay();
    display.repaint();
    display.pause(1); // Wait for redrawing and for mouse
    int[] mouseLoc = display.getMouseLocation();
    if (mouseLoc != null) { // Test if mouse clicked
      locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
 
  /**
   * An implementation of the SandDisplayInterface that doesn't display anything. Used for testing.
   */
  static class NullDisplay implements SandDisplayInterface {
    private int numRows;
    private int numCols;
 
    public NullDisplay(int numRows, int numCols) {
      this.numRows = numRows;
      this.numCols = numCols;
    }
 
    public void pause(int milliseconds) {}
 
    public int getNumRows() {
      return numRows;
    }
 
    public int getNumColumns() {
      return numCols;
    }
 
    public int[] getMouseLocation() {
      return null;
    }
 
    public int getTool() {
      return 0;
    }
 
    public void setColor(int row, int col, Color color) {}
 
    public int getSpeed() {
      return 1;
    }
 
    public void repaint() {}
  }
 
  /** Interface for the UI of the SandLab. */
  public interface SandDisplayInterface {
    public void repaint();
 
    public void pause(int milliseconds);
 
    public int[] getMouseLocation();
 
    public int getNumRows();
 
    public int getNumColumns();
 
    public void setColor(int row, int col, Color color);
 
    public int getSpeed();
 
    public int getTool();
  }
}
