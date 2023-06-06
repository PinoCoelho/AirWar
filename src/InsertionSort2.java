public class InsertionSort2 {
    static class Avion {
        String nombre;
        int velocidad;

        public Avion(String nombre, int velocidad) {
            this.nombre = nombre;
            this.velocidad = velocidad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getVelocidad() {
            return velocidad;
        }

        @Override
        public String toString() {
            return nombre + " - Velocidad: " + velocidad;
        }
    }

    public static void insertionSort(Avion[] aviones) {
        int n = aviones.length;

        for (int i = 1; i < n; ++i) {
            Avion key = aviones[i];
            int j = i - 1;

            while (j >= 0 && aviones[j].getVelocidad() > key.getVelocidad()) {
                aviones[j + 1] = aviones[j];
                j = j - 1;
            }

            aviones[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Avion avion1 = new Avion("Avion1", 600);
        Avion avion2 = new Avion("Avion2", 800);
        Avion avion3 = new Avion("Avion3", 500);
        Avion avion4 = new Avion("Avion4", 1000);
        Avion avion5 = new Avion("Avion5", 300);

        Avion[] aviones = { avion1, avion2, avion3, avion4, avion5 };

        System.out.println("Aviones antes de ordenar:");
        for (Avion avion : aviones) {
            System.out.println(avion);
        }

        insertionSort(aviones);

        System.out.println("\nAviones después de ordenar por velocidad:");
        for (Avion avion : aviones) {
            System.out.println(avion);
        }
    }
}