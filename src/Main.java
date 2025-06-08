import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int salida = 7;
        int opcionElegida = 0;
        while (salida != opcionElegida) {
            cargarMenu();
            opcionElegida = InputHelper.leerEntero("Seleccione una opción: ");
            procesarOpcion(opcionElegida);
        }
    }

    // carga el menu de opciones
    public static void cargarMenu() {
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
    }

    // procesa cada una de las opciones y abre el menu correspondiente
    public static void procesarOpcion(int opcion){
        switch (opcion){
            case 1:
                System.out.println();
                agregarProducto();
                System.out.println();
                break;
            case 2:
                System.out.println();
                listarProductos();
                System.out.println();
                break;
            case 3:
                System.out.println();
                buscarProducto();
                System.out.println();
                break;
            case 4:
                System.out.println();
                eliminarProducto();
                System.out.println();
                break;
            case 5:
                System.out.println();
                crearPedido();
                System.out.println();
                break;
            case 6:
                System.out.println();
                listarPedidos();
                System.out.println();
                break;
            case 7:
                System.out.println();
                salir();
                break;
            default:
                break;
        }
    }

    public static void agregarProducto(){
        System.out.println("Ingresar información del producto:");
        String nombre = InputHelper.leerTexto("Nombre del producto: ");
        double precio = InputHelper.leerDouble("Precio: ");
        int stock = InputHelper.leerEntero("Stock: ");
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        Producto.agregarProducto(producto);
    }

    public static void listarProductos(){
        ArrayList<Producto> productos = Producto.getListaProductos();
        if(!productos.isEmpty()){
            for(Producto p: productos){
                p.mostrarInfo();
                System.out.println();
            }
        }else{
            System.out.println("Aún no hay productos agregados");
        }
    }

    public static void buscarProducto(){
        Producto productoEncontrado = null;
        System.out.println("Buscar por nombre o por ID? (1/2)");
        System.out.println("1) nombre");
        System.out.println("2) ID");
        int respuesta = InputHelper.leerEntero("");
        if(respuesta == 1){
            String nombre = InputHelper.leerTexto("Nombre del producto: ");
            productoEncontrado = Producto.buscarProductoPorNombre(nombre);
        } else if (respuesta == 2) {
            int id = InputHelper.leerEntero("ID del producto: ");
            productoEncontrado =  Producto.buscarProductoPorId(id);
        }

        if(productoEncontrado != null){
            productoEncontrado.mostrarInfo();
        }else{
            System.out.println("Producto no encontrado");
        }
    }

    public static void eliminarProducto(){
        int id = InputHelper.leerEntero("Ingrese ID del producto: ");
        String confirmacion = InputHelper.leerTexto("Seguro desea eliminar el producto #" + id + " (s/n): ");
        if(Producto.eliminarProducto(id) && confirmacion.equalsIgnoreCase("s")){
            System.out.println("Producto eliminado");
        }else{
            System.out.println("Error al eliminar producto");
        }
        System.out.println();
    }

    public static void crearPedido() {
        Orden orden = new Orden();
        orden.setId(Orden.generateId());
        System.out.println("Nuevo pedido:");
        boolean seguirAgregando = true;

        while (seguirAgregando) {
            listarProductos();

            int idProducto = InputHelper.leerEntero("Ingrese el ID del producto para agregarlo: ");
            Producto producto = Producto.buscarProductoPorId(idProducto);

            if (producto == null) {
                System.out.println("No se encontró el producto");
            } else {
                int cantidad = InputHelper.leerEntero("Ingrese cantidad: ");
                if (cantidad <= 0) {
                    System.out.println("Cantidad inválida, debe ser mayor a cero");
                } else if (cantidad > producto.getStock()) {
                    System.out.println("No hay stock suficiente de este producto");
                } else {
                    producto.restarStock(cantidad);
                    ProductoPedido productoPedido = new ProductoPedido(producto, cantidad);
                    orden.agregarProducto(productoPedido);
                }
            }

            String seguir = InputHelper.leerTexto("¿Desea agregar otro producto? (s/n): ");
            if (!seguir.equalsIgnoreCase("s")) {
                seguirAgregando = false;
            }
        }

        orden.calcularTotal();
        System.out.println();
        System.out.println("Resumen de la orden:");
        orden.mostrarInfo();
        Orden.guardarOrden(orden);
    }

    public static void listarPedidos(){
        System.out.println("--- Lista de órdenes ---");
        System.out.println();
        for(Orden o: Orden.getPedidosRealizados()){
            o.mostrarInfo();
        }
    }

    public static void salir(){
        System.out.println("Salió del sistema");
    }

}