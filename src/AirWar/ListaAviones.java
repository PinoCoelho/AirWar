package AirWar;

public class ListaAviones {

    /**
     * Clase para crear los nodos
     */
    class Node {
        private Map.Aviones data; // el data de los nodos es un array
        private Node next; // next es el siguiente nodo

        public Node(Map.Aviones data) {
            this.next = null;
            this.data = data;
        }

        /**
         * Metodo para obtener los valores de un nodo
         * @return el data de un nodo en forma de array
         */
        public Map.Aviones getData() {
            return this.data;
        }

        /**
         * Se delimita el valor del nodo
         * @param data un array
         */
        public void setData(Map.Aviones data) {
            this.data = data;
        }

        /**
         * Se obtiene el siguiente nodo
         * @return el siguiente nodo
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Se determina el valor del siguiente nodo
         * @param node un nodo
         */
        public void setNext(Node node) {
            this.next = node;
        }
    }

    private Node head;
    private int size;

    /**
     * Constructor para crear
     * y modificar las listas
     * enlazadas
     */
    public ListaAviones() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Se determina si una lista esta vacia
     * @return true si la lista esta vacia
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * @return el tamaño de la lista
     */
    public int size() {
        return this.size;
    }

    /**
     * Se inserta un array a la lista
     * en el primer lugar
     * @param data un array
     */
    public void insertFirst(Map.Aviones data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    /**
     * Se borra el primer valor de la lista
     * @return el valor que se borro en forma de array
     */
    public Map.Aviones deleteFirst() {
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp.getData();
        }
        else {
            return null;
        }
    }

    /**
     * Se borra el primer valor
     * de la lista sin retornar
     * el valor que se borra
     */
    public void deleteFirstNotReturn() {
        if (this.head != null) {
            this.head = this.head.next;
            this.size--;
        }
    }

    /**
     * Se borra un valor determinado de la lista
     * @param id identificacion del platillo a borrar
     * @return el array que se borra
     */
    public Map.Aviones delete(int id) {
        Node current = this.head;
        Node previous = this.head;

        while (current != null) {
            if (current.getData().id == id) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    this.size--;
                }
                return current.getData();
            }
            else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Metodo que inserta el ultimo nodo
     * @param data
     */
    public void insertLast(Map.Aviones data)
    {
        Node newNode = new Node(data);
        Node temp = this.head;
        if (this.head == null) {
            newNode.next = this.head;
            this.head = newNode;
        }
        else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

    }

    /**
     * Se obtiene el array del primer nodo
     * @return el array del primer nodo
     */
    public Map.Aviones getFirst() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }

    /**
     * Se determina si la lista contiene
     * un array con determinados valores
     * @param id identidficacion del platillo
     * @return true si el array esta en la lista, false en caso contrario
     */
    public boolean contains(int id) {
        Node current = this.head;
        while (current != null) {
            if (current.getData().id == id) {
                return true;
            }
            else {
                current = current.getNext();
            }
        }
        return false;
    }

    /**
     * Se imprime en consola los
     * valores del array de una lista
     * enlazada de arrays.
     */
    public void recorrerLista() {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.getData().id);
            System.out.println("");
            current = current.getNext();
        }
    }

    /**
     * Se vacia una lista
     */
    public void empty() {
        this.head = null;
    }

    /**
     * Se obtiene el array del primer nodo de una lista enlazada
     * @return el array del primer nodo
     */
    public Map.Aviones getHead() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }

    /**
     * Se obtiene el array del nodo
     * de una lista enlazada dado un indice.
     * @param searchValue lugar o indice del arreglo que se quiere obtener
     * @return arreglo en la posicion del indice
     */
    public Map.Aviones get(int searchValue) {
        Node current = this.head;
        int counter = 0;
        while (current != null) {
            if (counter == searchValue) {
                return current.getData();
            }
            else {
                current = current.getNext();
                counter++;
            }
        }
        return null;
    }

    /**
     * Se borra un nodo en una posicion
     * especifica dado un indice.
     * @param i lugar o indice del nodo que se quiere borrar
     */
    public void deleteIndex(int i) {
        Node current = this.head;
        Node previous = this.head;
        int counter = 0;

        while (current != null) {
            if (counter == i) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    counter++;
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    counter++;
                    this.size--;
                }
            }
            else {
                previous = current;
                current = current.getNext();
                counter++;
            }
        }
    }

    /**
     * Metodo que copia la lista enlazada
     * @param list
     * @return new list
     */
    public ListaAviones copy(ListaAviones list) {
        int counter = 0;
        ListaAviones newList = new ListaAviones();
        while (list.size > counter) {
            newList.insertFirst(list.get(counter));
            counter++;
        }
        return newList;
    }

    /**
     * Se le asigna un 0 al tamaño de la lista
     */
    public void resetSize() {
        this.size = 0;
    }

}
