package taller03;

public interface VisitorMinijuego {

    double visitar(Zombie z);

    double visitar(Vampiro v);

    double visitar(Momia m);

    double visitar(HombreLobo h);
}