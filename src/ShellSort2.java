public class ShellSort2 {

    static class Avion {
        String nombre;
        double consumoCombustible;

        public Avion(String nombre, double consumoCombustible) {
            this.nombre = nombre;
            this.consumoCombustible = consumoCombustible;
        }

        public String getNombre() {
            return nombre;
        }

        public double getConsumoCombustible() {
            return consumoCombustible;
        }

        @Override
        public String toString() {
            return nombre + " - Consumo de combustible: " + consumoCombustible;
        }
    }

    public static void shellSort(Avion[] aviones) {
        int n = aviones.length;

        // Calcular el espacio inicial (gap)
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                Avion key = aviones[i];
                int j = i;
                while (j >= gap && aviones[j - gap].getConsumoCombustible() > key.getConsumoCombustible()) {
                    aviones[j] = aviones[j - gap];
                    j -= gap;
                }
                aviones[j] = key;
            }
            gap /= 3;
        }
    }

    public static void main(String[] args) {
        Avion avion1 = new Avion("Avion1", 12.5);
        Avion avion2 = new Avion("Avion2", 8.2);
        Avion avion3 = new Avion("Avion3", 10.8);
        Avion avion4 = new Avion("Avion4", 6.9);
        Avion avion5 = new Avion("Avion5", 9.3);

        Avion[] aviones = {avion1, avion2, avion3, avion4, avion5};

        System.out.println("Aviones antes de ordenar:");
        for (Avion avion : aviones) {
            System.out.println(avion);
        }

        shellSort(aviones);

        System.out.println("\nAviones después de ordenar por consumo de combustible:");
        for (Avion avion : aviones) {
            System.out.println(avion);
        }
    }
}