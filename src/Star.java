public class Star {
    double x,y,z,pz;
    int height,width;

    public static int speed=1;
    Star(int height,int width){
        x=(0.5-Math.random())*width;
        y=(0.5-Math.random())*height;
        z=Math.random()*width/2;
        this.height=height;
        this.width=width;
        pz=z;
    }

    public void update(){
        pz=z;
        z-=speed;
        x=(x/z*width/2);
        y=(y/z*height/2);
        if(z<1){
            z=width/2;
            x=(0.5-Math.random())*width;
            y=(0.5-Math.random())*height;
            pz=z;
        }
    }
}
