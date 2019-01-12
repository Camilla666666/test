public class Rectangle extends Figure {
    private int a;
    private int b;

    Rectangle(int length,int width){
        this.a = length;
        this.b = width;
    }

    Rectangle(int a){
        this.a = a;
        this.b = a;
    }
    @Override
    protected double square() {
        return a * b;
    }
}
