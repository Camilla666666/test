public class Circle extends Figure {
    private int radius;

    Circle(int radius){
        this.radius = radius;
    }

    Circle(){

    }

    public void setRadius(){
        this.radius = radius;
    }

    @Override
    protected double square() {
        return PI * radius;
    }
}
