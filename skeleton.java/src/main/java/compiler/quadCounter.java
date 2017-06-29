package compiler;


public class quadCounter {
    private Quad quad;
    private int position;

    public quadCounter(Quad q, int position){
        this.quad = new Quad(q);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Quad getQuad() {
        return quad;
    }
}
