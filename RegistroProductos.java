import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RegistroProductos {
    record Producto(String nombre, double precio, int codigo) {
        public String toString() {
            return "Producto: " + nombre + "\nPrecio: $" + precio + "\nCodigo: " + codigo;
        }
    }

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

            System.out.print("¿El producto " + producto.nombre() + " tiene descuento? (1) Sí   (2) No   (3) Aplicar descuento personalizado: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    double descuento = producto.precio() * 0.10;
                    producto = new Producto(producto.nombre(), producto.precio() - descuento, producto.codigo());
                    System.out.println("Descuento del 10% aplicado a " + producto.nombre());
                    break;
                case 2:
                    System.out.println("No se aplica ningún descuento");
                    // No se aplica ningún descuento
                    break;
                case 3:
                    System.out.print("Ingrese el monto del descuento: $");
                    double descuentoPersonalizado = obtenerPrecioNice(scanner);
                    producto = new Producto(producto.nombre(), producto.precio() - descuentoPersonalizado, producto.codigo());
                    System.out.println("Descuento personalizado de $" + descuentoPersonalizado + " aplicado a " + producto.nombre());
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            total += producto.precio();
        }

        System.out.println("Total de la compra: $" + total);

        // Solicitar al usuario un monto recibido y calcular el cambio
        System.out.print("Ingrese la cantidad de dinero recibido: ");
        double dineroRecibido = obtenerPrecioNice(scanner);

        double cambio = dineroRecibido - total;
        System.out.println("Cantidad de monto a devolver: $" + cambio);

        // Solicitar al usuario un código de producto
        System.out.print("Ingrese un código de producto para obtener información: ");
        int codigoBuscado = obtenerCodigoNice(scanner);

        // Buscar y mostrar información sobre el producto correspondiente al código ingresado
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.codigo() == codigoBuscado) {
                System.out.println("--------------------------------------------");
                System.out.println("El producto es " + producto.nombre());
                System.out.println("El precio es $" + producto.precio());
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
                if (numero >= 0) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número decimal no negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número decimal válido.");
                scanner.nextLine();
            }
        }
    }
}
