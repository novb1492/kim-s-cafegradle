
let squaretable;
let value = 0;
let square=100;
let anoters;
let circle;
let circlesize=200;
function setup() {
  createCanvas(displayWidth, displayHeight);
}
function setup() {
    var framerate=60;
    frameRate(framerate);
    let canvas=createCanvas(displayWidth,displayHeight);
    canvas.parent('canvas');
    squaretable=new Square();
    anoters=new Anothers();
    circle=new Circletable();

  }


function draw() 
{  
  background('#fae');
  anoters.draw();
  fill(value);
  squaretable.draw(10,200,200,50);
  squaretable.draw(10,500,200,50);
  squaretable.draw(10,800,200,50);

  squaretable.draw(300,displayHeight/2+25,square,square);
  squaretable.draw(500,displayHeight/2+25,square,square);
  squaretable.draw(700,displayHeight/2+25,square,square);
  squaretable.draw(300,displayHeight/2-25,square,-square);
  squaretable.draw(500,displayHeight/2-25,square,-square);
  squaretable.draw(700,displayHeight/2-25,square,-square);

  circle.draw(400,displayHeight-circlesize+50,circlesize);

  squaretable.draw(displayWidth/2-50,displayHeight/4,100,500);

  
  squaretable.draw(displayWidth-square-100-400,displayHeight/2+25,square,square);
  squaretable.draw(displayWidth-square-100-200,displayHeight/2+25,square,square);
  squaretable.draw(displayWidth-square-100,displayHeight/2+25,square,square);
  squaretable.draw(displayWidth-square-100-400,displayHeight/2-25,square,-square);
  squaretable.draw(displayWidth-square-100-200,displayHeight/2-25,square,-square);
  squaretable.draw(displayWidth-square-100,displayHeight/2-25,square,-square);
}