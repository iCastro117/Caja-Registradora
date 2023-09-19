import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Producto {
    private final String nombre;
    private final double precio;
    private final int codigo;

    public Producto(String nombre, double precio, int codigo) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + "\nPrecio: $" + precio + "\nCodigo: " + codigo;
    }
}

public class CajaRegistradora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de productos: ");
        int cantidadProductos = obtenerEnteroPositivo(scanner);

        for (int i = 0; i < cantidadProductos; i++) {
            scanner.nextLine(); // Consumir la nueva línea pendiente

            // Solicitar los detalles de cada producto al usuario
            System.out.println("Producto #" + (i + 1));
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();
            double precio = obtenerDoublePositivo(scanner);
            int codigo = obtenerEnteroPositivo(scanner);

            // Crear un objeto Producto y agregarlo a la lista
            productos.add(new Producto(nombre, precio, codigo));
        }

        double total = 0;
        System.out.println("\nRecibo de compra:");

        // Mostrar los detalles de cada producto en el recibo
        for (Producto producto : productos) {
            System.out.println(producto + "\n");
            total += producto.getPrecio();
        }

        // Mostrar el total de la compra
        System.out.println("Total de la compra: $" + total);
    }

    // Función para obtener un entero positivo desde la entrada del usuario
    private static int obtenerEnteroPositivo(Scanner scanner) {
        while (true) {
            try {
                int numero = scanner.nextInt();
                if (numero > 0) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número entero positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero positivo válido.");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        }
    }

    // Función para obtener un número decimal positivo desde la entrada del usuario
    private static double obtenerDoublePositivo(Scanner scanner) {
        while (true) {
            try {
                double numero = scanner.nextDouble();
                if (numero > 0) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número decimal positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número decimal positivo válido.");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        }
    }
}
