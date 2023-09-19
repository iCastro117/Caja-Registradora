import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


record Producto(String nombre, double precio, int codigo) {
    public String toString() {
        return "Producto: " + nombre + "\nPrecio: $" + precio + "\nCodigo: " + codigo;
    }
}
 

public class RegistroProductos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> productos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de productos: ");
        int cantidadProductos = obtenerCodigoNice(scanner);

        for (int i = 0; i < cantidadProductos; i++) {
            scanner.nextLine();  

            System.out.println("Producto #" + (i + 1));
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio del producto: ");
            double precio = obtenerPrecioNice(scanner);

            System.out.print("Codigo del producto: ");
            int codigo = obtenerCodigoNice(scanner);

            Producto producto = new Producto(nombre, precio, codigo);
            productos.add(producto);
        }

        double total = 0;
        System.out.println("\nRecibo de compra:");
        for (Producto producto : productos) {
            System.out.println(producto + "\n");
            total += producto.precio();
        }

        System.out.println("Total de la compra: $" + total);

        // Solicitar al usuario un código de producto
        System.out.print("Ingrese un código de producto para obtener información: ");
        int codigoBuscado = obtenerCodigoNice(scanner);

        // Buscar y mostrar información sobre el producto correspondiente al código ingresado
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.codigo() == codigoBuscado) {
                System.out.println("--------------------------------------------");
                System.out.println("El producto es " + producto.nombre());
                System.out.println(" El precio es $" + producto.precio());
                System.out.println("--------------------------------------------");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró un producto con el código " + codigoBuscado);
        }
    }

    private static int obtenerCodigoNice(Scanner scanner) {
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
                scanner.nextLine(); 
            }
        }
    }

    private static double obtenerPrecioNice(Scanner scanner) {
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
                scanner.nextLine();  
            }
        }
    }
}
