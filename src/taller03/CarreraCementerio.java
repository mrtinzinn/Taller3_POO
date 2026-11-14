package taller03;

public class CarreraCementerio
        implements Minijuego, VisitorMinijuego {

    @Override
    public String getNombre() {
        return "Carrera del Cementerio";
    }

    @Override
    public String getDescripcion() {
        return "Recorre el cementerio antes de que te descubran.";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Volar";
    }

    @Override
    public double calcularPotencial(Monstruos monstruo) {
        return monstruo.aceptar(this);
    }

    @Override
    public double visitar(Zombie z) {
        return z.getVelocidad() * z.getResistencia() / 100.0;
    }

    @Override
    public double visitar(Vampiro v) {
        return (v.getVelocidad() + 2.0 * v.getVelocidadVuelo()) / 2.0;
    }

    @Override
    public double visitar(Momia m) {
        return m.getVelocidad() * 100.0
                / (100.0 + m.getLongitudVendajes());
    }

    @Override
    public double visitar(HombreLobo h) {
        return (h.getVelocidad() + h.getFerocidad() / 5.0)
                * h.getFaseLunar() / 4.0;
    }
}