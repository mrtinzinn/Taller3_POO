
package taller03;

public class LanzamientoLapidas
        implements Minijuego, VisitorMinijuego {

    @Override
    public String getNombre() {
        return "Lanzamiento de Lapidas";
    }

    @Override
    public String getDescripcion() {
        return "Lanza una lapida lo mas lejos posible.";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Enfurecerse";
    }

    @Override
    public double calcularPotencial(Monstruos monstruo) {
        return monstruo.aceptar(this);
    }

    @Override
    public double visitar(Zombie z) {
        return z.getFuerza() * z.getResistencia() / 100.0;
    }

    @Override
    public double visitar(Vampiro v) {
        return v.getFuerza() + v.getVelocidadVuelo() / 4.0;
    }

    @Override
    public double visitar(Momia m) {
        return m.getFuerza() + m.getLongitudVendajes() / 10.0;
    }

    @Override
    public double visitar(HombreLobo h) {
        return h.getFuerza()
                + h.getFerocidad() * h.getFaseLunar() / 8.0;
    }
}
