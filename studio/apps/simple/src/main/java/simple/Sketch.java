package simple;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

// setup draw mouseMoved mousePressed disableMouseDrag float color size

public class Sketch extends PApplet {

//  PImage leaf;
//
  public void settings() {
    fullScreen();
  }


//
//  public void setup() {
//    leaf = loadImage("leaf.png");
//    imageMode(CENTER);
//  }
//
//  public void draw() {
//    background(9);
//    image(leaf, mouseX, mouseY);
//  }





//  public void setup() {
////    size(500, 500);
//    fill(0);
//    noStroke();
//    rectMode(CENTER);
//    frameRate(30);
//    noiseDetail(2, 0.9f);
//  }
//
//  public void draw() {
//    background(255);
//    for (int x = 10; x < width; x += 10) {
//      for (int y = 10; y < height; y += 10) {
//        float n = noise(x * 0.005f, y * 0.005f, frameCount * 0.05f);
//        pushMatrix();
//        translate(x, y);
//        rotate(TWO_PI * n);
//        scale(10 * n);
//        rect(0, 0, 1, 1);
//        popMatrix();
//      }
//    }
//
//  }



//  Particle[] particles;
//  float alpha;
//
//  public void setup() {
//    //size(1080, 1920);
//    background(0);
//    noStroke();
//    setParticles();
//  }
//
//  public void draw() {
//    frameRate(20);
//    alpha = map(mouseX, 0, width, 5, 35);
//    fill(0, alpha);
//    rect(0, 0, width, height);
//
//    loadPixels();
//    for (Particle p : particles) {
//      p.move();
//    }
//    updatePixels();
//  }
//
//  void setParticles() {
//    particles = new Particle[6000];
//    for (int i = 0; i < 6000; i++) {
//      float x = random(width);
//      float y = random(height);
//      float adj = map(y, 0, height, 255, 0);
//      int c = color(40, adj, 255);
//      particles[i]= new Particle(x, y, c);
//    }
//  }
//
//  public void mousePressed() {
//    setParticles();
//  }
//
//  class Particle {
//    float posX, posY, incr, theta;
//    int  c;
//
//    Particle(float xIn, float yIn, int cIn) {
//      posX = xIn;
//      posY = yIn;
//      c = cIn;
//    }
//
//    public void move() {
//      update();
//      wrap();
//      display();
//    }
//
//    void update() {
//      incr +=  .008;
//      theta = noise(posX * .006f, posY * .004f, incr) * TWO_PI;
//      posX += 2 * cos(theta);
//      posY += 2 * sin(theta);
//    }
//
//    void display() {
//      if (posX > 0 && posX < width && posY > 0  && posY < height) {
//        pixels[(int)posX + (int)posY * width] =  c;
//      }
//    }
//
//    void wrap() {
//      if (posX < 0) posX = width;
//      if (posX > width ) posX =  0;
//      if (posY < 0 ) posY = height;
//      if (posY > height) posY =  0;
//    }
//  }





  /*
Flow field waterfall

Particles are driven by a flow field until it reaches the yellow line.
Then their dynamics are enabled and take over to make them fall.
Inspired by Robert D'arcy's sketch Surfs up (openprocessing.org/sketch/298646)

Controls:
    - Click to change to the next flow preset.
    - Move mouse along the width to set where gravity takes over.

Author:
  Jason Labbe

Site:
  jasonlabbe3d.com
*/


  // Global variables
//  ArrayList<Pixel> allPixels = new ArrayList<Pixel>();
//  ArrayList<PVector> flowField = new ArrayList<PVector>();
//  ArrayList<ArrayList<PVector>> flowFieldPresets = new ArrayList<ArrayList<PVector>>();
//  int presetIndex = 0;
//  int columnCount = 10;
//  int columnSize;
//  int particlesPerFrame = 8;
//  int activatorPosX = 200;
//
//
//  class Pixel {
//    PVector pos = new PVector(0, 0);
//    PVector vel = new PVector(0, 0);
//    PVector acc = new PVector(0, 0);
//    int pixelColor;
//    float speed = 1.0f;
//    boolean active = false;
//    float fallRate = 0;
//    float speedLimit = 1.0f;
//
//    Pixel(int x, int y, int inputColor) {
//      this.pos.set(x, y);
//      this.pixelColor = inputColor;
//    }
//
//    int getColumnIndex() {
//      int index = (int)this.pos.x/columnSize;
//      return index;
//    }
//
//    void draw() {
//      strokeWeight(1);
//      stroke(this.pixelColor);
//      point(this.pos.x, this.pos.y);
//    }
//  }
//
//
//  public void setup() {
//    //size(800, 300);
//    noFill();
//    background(0, 30, 50);
//
//    columnSize = width/columnCount;
//
//    float[] preset1 = new float[] {-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 0.5f};
//    float[] preset2 = new float[] {0, -0.3f, -0.2f, -0.1f, 0, 1.0f, 1.25f, -1.0f, -1.0f, 0.5f};
//    float[] preset3 = new float[] {0, 0.5f, 0.25f, -0.4f, 1.15f, -1.35f, 0.25f, 0.75f, 0.5f, 0};
//    float[] preset4 = new float[] {-0.5f, 0.5f, -0.5f, 0.5f, -1, -0.2f, -0.4f, 0, 0, 0.75f};
//    float[] preset5 = new float[] {1.25f, -1.25f, 1.25f, -1.25f, 1.25f, -1.25f, 1.25f, -1.25f, 1.25f, -0.25f};
//    float[] preset6 = new float[] {-0.8f, 1.0f, -1.2f, 1.3f, -1.2f, 1.0f, -0.8f, 0.6f, -0.4f, 0.2f};
//
//    addFlowPreset(preset1);
//    addFlowPreset(preset2);
//    addFlowPreset(preset3);
//    addFlowPreset(preset4);
//    addFlowPreset(preset5);
//    addFlowPreset(preset6);
//  }
//
//
//  void addFlowPreset(float[] rotateValues) {
//    ArrayList<PVector> preset = new ArrayList<PVector>();
//
//    // Point left and rotate from that axis
//    for (int i = 0; i < rotateValues.length; i++) {
//      PVector direction = new PVector(-1, 0);
//      rotateVector(direction, rotateValues[i]);
//      direction.normalize();
//      preset.add(direction);
//    }
//
//    flowFieldPresets.add(preset);
//  }
//
//
//  // PVector.rotate() doesn't work in js mode.
//  void rotateVector(PVector vec, float angle) {
//    float prevX = vec.x;
//    vec.x = vec.x*cos(angle) - vec.y*sin(angle);
//    vec.y = prevX*sin(angle) + vec.y*cos(angle);
//  }
//
//
//  public void draw() {
//    // Motion blur
//    noStroke();
//    fill(0, 30, 50, 80);
//    rect(0, 0, width*2, height*2);
//
//    // Create a new set of particles
//    for (int x = 0; x < particlesPerFrame; x ++) {
//      int pixelColor = color(255-(x*40), 255-(x*10), 255);
//      float sourceHeight = (height/2)+sin(frameCount/20.0f)*20;
//      float pinch = 15+(sin(frameCount/50.0f)*20);
//      Pixel newPixel = new Pixel(width-1, (int)random(sourceHeight-pinch, sourceHeight+pinch), pixelColor);
//      newPixel.speed = random(0.075f, 0.1f);
//      newPixel.speedLimit = newPixel.speed * 20.0f;
//      newPixel.fallRate = random(0.05f, 0.15f);
//      allPixels.add(newPixel);
//    }
//
//    for (int i = allPixels.size()-1; i > -1; i--) {
//      Pixel p = allPixels.get(i);
//
//      if (p.pos.x < (int)random(activatorPosX-50, activatorPosX)) {
//        // Set as active if it goes pass the line
//        p.active = true;
//      } else if (p.pos.x < activatorPosX+80) {
//        // Set a few as active if it's near the line for a nicer effect
//        if ((int)random(0, 10000) < 10) {
//          p.active = true;
//        }
//      }
//
//      if (p.active) {
//        // Drop with gravity
//        PVector gravity = new PVector(0, p.fallRate);
//        p.acc.add(gravity);
//      } else {
//        // Follow the flow field
//        int index = (int)p.getColumnIndex();
//        if (index < 0) { continue; }
//        PVector direction = new PVector(flowFieldPresets.get(presetIndex).get(index).x, flowFieldPresets.get(presetIndex).get(index).y);
//        direction.normalize();
//        direction.mult(p.speed);
//        p.acc.add(direction);
//      }
//
//      p.vel.add(p.acc);
//
//      // Clamp to particle's speed limit
//      if (! p.active) {
//        if (p.vel.mag() > p.speedLimit) {
//          p.vel.normalize();
//          p.vel.mult(p.speedLimit);
//        }
//      }
//
//      p.pos.add(p.vel);
//      p.acc.mult(0);
//
//      p.draw();
//
//      // Kill particle if it goes off screen
//      if (p.pos.x < 0 || p.pos.y > height+100) {
//        allPixels.remove(p);
//      }
//    }
//
//    // Draw line
//    stroke(255, 255, 0);
//    strokeWeight(5);
//    line(activatorPosX, 0, activatorPosX, 25);
//
//    // Draw tip
//    fill(255);
//    textSize(10);
//    textAlign(CENTER);
//    text("Click for a new flow.", width/2, height-20);
//  }
//
//
//  // Change to next preset
//  public void mousePressed() {
//    allPixels.clear();
//    presetIndex += 1;
//    if (presetIndex > flowFieldPresets.size()-1) { presetIndex = 0; }
//  }
//
//
//  public void mouseMoved() {
//    activatorPosX = mouseX;
//  }

  float angle, speed;
  float l0, l1, l2;
  int n = 5;
  boolean first = true;

  public void setup() {
    //size(800, 800);
    angle = 0;
    speed = 0.01f;
    background(0);
  }

  public void draw() {
    fill(0, 10);
    rect(0, 0, width, height);

    translate(width/2, height/2);
    rotate(angle);

    l0 = map(mouseX, 0, width, 50, 200);
    l1 = map(mouseY, 0, width, 37, 150);
    l2 = map(mouseX+mouseY, 0, width+height, 25, 100);

    for (int i=0; i<n; i++) {
      fill(150, 100);
      pushMatrix();
      rotate(i*TWO_PI/n);
      translate(0, l0);
      ellipse(0, 0, 15, 15);

      for (int j=0; j<n; j++) {
        fill(200, 100);
        pushMatrix();
        rotate(j*TWO_PI/n);
        translate(0, l1);
        ellipse(0, 0, 10, 10);

        for (int k=0; k<n; k++) {
          fill(250, 100);
          pushMatrix();
          rotate(k*TWO_PI/n);
          translate(0, l2);
          ellipse(0, 0, 5, 5);
          popMatrix();
        }
        popMatrix();
      }
      popMatrix();
    }
    angle = (angle+speed)%TWO_PI;
  }

}
