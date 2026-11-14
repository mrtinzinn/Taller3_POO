
package taller03;

public class SobrevivirAmanecer
        implements Minijuego, VisitorMinijuego {

    @Override
    public String getNombre() {
        return "Sobrevivir al Amanecer";
    }

    @Override
    public String getDescripcion() {
        return "Sobrevive a los primeros rayos del sol.";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Regenerarse";
    }

    @Override
    public double calcularPotencial(Monstruos monstruo) {
        return monstruo.aceptar(this);
    }

    @Override
    public double visitar(Zombie z) {
        return z.getResistencia() * z.getVida() / 100.0;
    }

    @Override
    public double visitar(Vampiro v) {
        return v.getResistencia()
                * v.getResistenciaSol() / 100.0;
    }

    @Override
    public double visitar(Momia m) {
        return (m.getResistencia() + m.getVida()) / 2.0
                * (1 + m.getLongitudVendajes() / 300.0);
    }

    @Override
    public double visitar(HombreLobo h) {
        return (h.getResistencia() + h.getVida()) / 2.0
                * (5 - h.getFaseLunar()) / 4.0;
    }
}
