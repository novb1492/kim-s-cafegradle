class Anothers{
    constructor(){

    }
    draw(){
        line(0, displayHeight/2, displayWidth, displayHeight/2);
        line(displayWidth/2, 0, displayWidth/2, displayHeight);

        push();
        line(0, displayHeight/10, displayWidth/4, displayHeight/10);
        line(displayWidth/4, displayHeight/10, displayWidth/4, 0);
        pop();

        push();
        line(displayWidth/4, displayHeight/10-20, displayWidth/2-50, displayHeight/10-20);
        line(displayWidth/2-50, displayHeight/10-20, displayWidth/2-50, 0);
        pop();
    }
}