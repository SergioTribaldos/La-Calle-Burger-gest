/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lacalleburger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedidos.Pedido;
import productos.Producto;
import static productos.Producto.tipoProducto.BOCADILLO;
import static productos.Producto.tipoProducto.ENTRANTE;
import static productos.Producto.tipoProducto.HAMBURGUESA;
import static productos.Producto.tipoProducto.POSTRE;
import static productos.Producto.tipoProducto.SALSA;
import static productos.Producto.unidadDeMedida.KG;
import static productos.Producto.unidadDeMedida.UD;
import restaurantes.Restaurante;
import static restaurantes.Restaurante.codRestaurante.AMERICAS;
import static restaurantes.Restaurante.codRestaurante.CENTRO;
import static restaurantes.Restaurante.codRestaurante.FUENGIROLA;
import static restaurantes.Restaurante.codRestaurante.GAMARRA;
import static restaurantes.Restaurante.codRestaurante.MARBELLA;
import static restaurantes.Restaurante.codRestaurante.PARQUE_OESTE;
import static restaurantes.Restaurante.codRestaurante.PEDREGALEJO;
import static restaurantes.Restaurante.codRestaurante.PLAZA_MAYOR;
import static restaurantes.Restaurante.codRestaurante.SAN_PEDRO;
import static restaurantes.Restaurante.codRestaurante.TEATINOS;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class LaCalleBurger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
       /* final Producto [] listaProductos=new Producto[37];
        listaProductos[0]=new Producto((short)0,"HAMBURGUESA DE AGUJA 20ud",1,20,UD,HAMBURGUESA);
        listaProductos[1]=new Producto((short)1,"HAMBURGUESA DE ANGUS 20ud",1,20,UD,HAMBURGUESA);
        listaProductos[2]=new Producto((short)2,"HAMBURGUESA DE ENTRAÑA 20ud",1,20,UD,HAMBURGUESA);
        listaProductos[3]=new Producto((short)3,"HAMBURGUESA DE GARBANZOS 30ud",1,30,UD,HAMBURGUESA);
        listaProductos[4]=new Producto((short)4,"HAMBURGUESA DE LENTEJAS 30ud",1,30,UD,HAMBURGUESA);
        listaProductos[5]=new Producto((short)5,"HAMBURGUESA DE VACIO 20ud",1,20,UD,HAMBURGUESA);
        listaProductos[6]=new Producto((short)6,"HAMBURGUESA DOBLE 30ud",1,30,UD,HAMBURGUESA);
        listaProductos[7]=new Producto((short)7,"ALITAS DE POLLO 2kg",1,2.0,KG,ENTRANTE);
        listaProductos[8]=new Producto((short)8,"BACON CRUJIENTE bolsa 400gr",1,0.4,KG,ENTRANTE);
        listaProductos[9]=new Producto((short)9,"CEBOLLA AL VINO bolsa 1,5kg",1,1.5,KG,ENTRANTE);
        listaProductos[10]=new Producto((short)10,"CHAMPIÑON PORTOBELLO bolsa 1kg",1,1.0,KG,ENTRANTE);
        listaProductos[11]=new Producto((short)11,"COSTILLAS DE CERDO bolsa 2kg",1,2.0,KG,ENTRANTE);
        listaProductos[12]=new Producto((short)12,"ENTRAÑA PARA BROCHETA bolsa 500gr",1,0.5,KG,ENTRANTE);
        listaProductos[13]=new Producto((short)13,"FINGERS DE POLLO bolsa 2kg",1,2.0,KG,ENTRANTE);
        listaProductos[14]=new Producto((short)14,"PIMIENTO CONFITADO",1,12,UD,ENTRANTE);
        listaProductos[15]=new Producto((short)15,"POLLO MARINADO bandeja 2,5kg",1,2.5,KG,ENTRANTE);
        listaProductos[16]=new Producto((short)16,"SALSA CALAMBRITO bolsa 2 k",1,2.0,KG,ENTRANTE);
        listaProductos[17]=new Producto((short)17,"CALDO PARA SALTEADO bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[18]=new Producto((short)18,"KETCHUP LA CALLE bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[19]=new Producto((short)19,"MAYONESA LA CALLE bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[20]=new Producto((short)20,"MAYONESA DE CHIMICHURRI bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[21]=new Producto((short)21,"MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[22]=new Producto((short)22,"MAYONESA SWEET CHILI bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[23]=new Producto((short)23,"MAYONESA VEGANA bolsa 500 g",1,0.5,KG,SALSA);
        listaProductos[24]=new Producto((short)24,"SALSA BBQ bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[25]=new Producto((short)25,"SALSA BBQ TOFFEE bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[26]=new Producto((short)26,"SALSA CALLEJERA bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[27]=new Producto((short)27,"SALSA CÉSAR bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[28]=new Producto((short)28,"SALSA DE ALITAS bolsa 2 kg",1,2.0,KG,SALSA);
        listaProductos[29]=new Producto((short)29,"VINAGRETA PARA ENSALADA DE COL bolsa 1 kg",1,1.0,KG,SALSA);
        listaProductos[30]=new Producto((short)30,"COCHINITA PIBIL bolsa 1 kg",1,1.0,KG,BOCADILLO);
        listaProductos[31]=new Producto((short)31,"ENTRECOT MARINADO bolsa 500 g",1,0.5,KG,BOCADILLO);
        listaProductos[32]=new Producto((short)32,"SALCHICHAS bolsa 8 ud",1,8,UD,BOCADILLO);
        listaProductos[33]=new Producto((short)33,"TARTA OREO caja 16 ud",1,16,UD,POSTRE);
        listaProductos[34]=new Producto((short)34,"TARTA QUESO caja 16 ud",1,16,UD,POSTRE);
        listaProductos[35]=new Producto((short)35,"TARTA SACHER caja 20 ud",1,20,UD,POSTRE);
        listaProductos[36]=new Producto((short)36,"TARTA ZANAHORIA caja 20 ud",1,20,UD,POSTRE);
        
        ArrayList<Restaurante> listaRestaurantes=new ArrayList<Restaurante>();
        listaRestaurantes.add(new Restaurante("S7741870E","Centro","Calle Mosquera 3. Málaga","951 46 58 72","CENTRO",null));
        listaRestaurantes.add(new Restaurante("S3341870E","Teatinos","Av. de Gregorio Prieto 27. Málaga ","951 02 30 50","TEATINOS",null));
        listaRestaurantes.add(new Restaurante("S5541870E","Fuengirola","Calle Marconi 32, Fuengirola Málaga","951 10 11 35","FUENGIROLA",null));
        listaRestaurantes.add(new Restaurante("S4441870E","Pedregalejo","Paseo Marítimo El Pedregal 11. Málaga Pedregalejo","951 50 35 64","PEDREGALEJO",null));
        listaRestaurantes.add(new Restaurante("S8841870E","Americas","Avenida de las Américas 9. Málaga","951 07 97 94","AMERICAS",null));
        listaRestaurantes.add(new Restaurante("S1141870E","Plaza Mayor","Calle Alfonso Ponce de León 3. Málaga","952 02 64 64","PLAZA_MAYOR",null));
        listaRestaurantes.add(new Restaurante("S0041870E","Parque Oeste","Calle Diamantino García Acosta 1. Málaga","951 91 77 15","PARQUE_OESTE",null));
        listaRestaurantes.add(new Restaurante("S0041870E","Gamarra","Calle Sondalezas 33. Málaga","951 77 70 93","GAMARRA",null));
        listaRestaurantes.add(new Restaurante("S0041870E","San Pedro","Avenida de Burgos 22. San Pedro de Alcántara","951 482 590","SAN_PEDRO",null));
        listaRestaurantes.add(new Restaurante("S0041870E","Marbella","Avenida Miguel Cano 1. Edif. Milenium. Marbella","951 812 128","MARBELLA",null));
        
        
        Usuario[] usuariosCentro=new Usuario[3];
        usuariosCentro[0]=new Usuario("CENTRO","Javier","pk34ers");
        usuariosCentro[1]=new Usuario("CENTRO","Paco","oj45er");
        usuariosCentro[2]=new Usuario("CENTRO","Pepe","is55me");
        
        Usuario[] usuariosTeatinos=new Usuario[3];
        usuariosTeatinos[0]=new Usuario("TEATINOS","Antonio","id14ers");
        usuariosTeatinos[1]=new Usuario("TEATINOS","Lisa","mg95er");
        usuariosTeatinos[2]=new Usuario("TEATINOS","Marge","fd45me");*/
        
 
        
        
        int accion=0;
        
        do{
            System.out.println("Elige que quieres hacer [1] Logearse [2]Panel administrador [3]Introducir datos en base de datos [0] Salir");
            accion=Integer.parseInt(sc.nextLine());
            switch(accion){
                case 1:
                    logearse(sc);
                    break;
                case 2:
                    
                    break;
                case 3:
                    meterDatos(listaProductos,listaRestaurantes,usuarios);
                    break;
                case 0:
                    
                    break;
                
            }
            
        }while(accion!=0);
        
       
        
    }
    
    public static void logearse(Scanner sc){
        System.out.println("Introduce el usuario");
        String usuarioIntroducido=sc.nextLine();
        System.out.println("Introduce contraseña");
        String contraseñaIntroducida=sc.nextLine();
        
        Connection conexion;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lacalle", "root", "kaotiko666");
            Statement smt=conexion.createStatement();
            ResultSet resultado=smt.executeQuery("select * from usuario where usuario='"+usuarioIntroducido+"' and contraseña='"+contraseñaIntroducida+"';");
            String usuario="";
            String pass="";
            String restaurante="";
            System.out.println(resultado);
            
            if(resultado.next()){
            usuario=resultado.getString("usuario");
            pass=resultado.getString("contraseña");
            restaurante=resultado.getString("Restaurante_codigoRestaurante");
            
            
            System.out.println(pass+""+restaurante); 
            if(usuario.equals(usuarioIntroducido)&&pass.equals(contraseñaIntroducida)){            
                System.out.println("Bien");
                
                
                Statement smt2=conexion.createStatement();
                ResultSet resultado2=smt2.executeQuery("select * from Restaurante where codigoRestaurante='"+restaurante+"';");
                resultado2.next();
                String cif=resultado2.getString("cif");
                String nombre=resultado2.getString("nombre");
                String direccion=resultado2.getString("direccion");
                String telefono=resultado2.getString("telefono");
                Restaurante restauranteElegido=new Restaurante(cif,nombre,direccion,telefono,restaurante);
                Usuario usuarioElegido=new Usuario(restauranteElegido,usuario,pass);
                usuarioElegido.hacerPedido();
              
            }
            }else{
                System.out.println("Contraseña incorrecta");
                logearse(sc);
                
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(LaCalleBurger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void meterDatos(Producto[]listaProductos,ArrayList<Restaurante> listaRestaurantes,Usuario[][] usuarios){
        try {
            //Conectarse a la base de datos
            Connection conexion=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lacalle", "root", "kaotiko666");
            Statement smt=conexion.createStatement();
            //Crear la tabla restaurante
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS Restaurante (\n" +
                            "  `cif` VARCHAR(20) NOT NULL,\n" +
                            "  `nombre` VARCHAR(45) NOT NULL,\n" +
                            "  `direccion` VARCHAR(150) NOT NULL,\n" +
                            "  `telefono` VARCHAR(45) NOT NULL,\n" +
                            "  `codigoRestaurante` VARCHAR(45) NOT NULL,\n" +
                            "  PRIMARY KEY (`codigoRestaurante`))");
            //Crear la tabla usuario
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS Usuario (\n" +
                            "  `usuario` VARCHAR(15) NOT NULL,\n" +
                            "  `contraseña` VARCHAR(15) NOT NULL,\n" +
                            "  `Restaurante_codigoRestaurante` VARCHAR(45) NOT NULL,\n" +
                            "  PRIMARY KEY (`usuario`),\n" +
                            "  INDEX `fk_Usuario_Restaurante1_idx` (`Restaurante_codigoRestaurante` ASC) VISIBLE,\n" +
                            "  CONSTRAINT `fk_Usuario_Restaurante1`\n" +
                            "    FOREIGN KEY (`Restaurante_codigoRestaurante`)\n" +
                            "    REFERENCES Restaurante (`codigoRestaurante`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)");
            //Crear la tabla pedido
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS Pedido (\n" +
                            "  `Fecha` DATETIME NOT NULL,\n" +
                            "  `Usuario_usuario` VARCHAR(15) NOT NULL,\n" +
                            "  PRIMARY KEY (`Fecha`),\n" +
                            "  INDEX `fk_Pedido_Usuario1_idx` (`Usuario_usuario` ASC) VISIBLE,\n" +
                            "  CONSTRAINT `fk_Pedido_Usuario1`\n" +
                            "    FOREIGN KEY (`Usuario_usuario`)\n" +
                            "    REFERENCES Usuario (`usuario`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)");
            //Crear la tabla producto
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS producto (\n" +
                            "  `id` INT NOT NULL,\n" +
                            "  `nombre` VARCHAR(75) NOT NULL,\n" +
                            "  `precio` DECIMAL(5,2) NULL,\n" +
                            "  `cantidadPorUnidad` DECIMAL(5,2) NULL,\n" +
                            "  `unidadDeMedida` VARCHAR(2) NULL,\n" +
                            "  `tipoProducto` VARCHAR(15) NULL,\n" +
                            "  PRIMARY KEY (`id`, `nombre`))");
            //Crear la tabla intermedia entre producto y pedido
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS producto_has_Pedido (\n" +
                        "  `producto_id` INT NOT NULL,\n" +
                        "  `producto_nombre` VARCHAR(75) NOT NULL,\n" +
                        "  `Pedido_Fecha` DATETIME NOT NULL,\n" +
                        "  `cantidad` INT NOT NULL,\n" +
                        "  `lote` VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY (`producto_id`, `producto_nombre`, `Pedido_Fecha`),\n" +
                        "  INDEX `fk_producto_has_Pedido_Pedido1_idx` (`Pedido_Fecha` ASC) VISIBLE,\n" +
                        "  INDEX `fk_producto_has_Pedido_producto1_idx` (`producto_id` ASC, `producto_nombre` ASC) VISIBLE,\n" +
                        "  CONSTRAINT `fk_producto_has_Pedido_producto1`\n" +
                        "    FOREIGN KEY (`producto_id` , `producto_nombre`)\n" +
                        "    REFERENCES producto (`id` , `nombre`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION,\n" +
                        "  CONSTRAINT `fk_producto_has_Pedido_Pedido1`\n" +
                        "    FOREIGN KEY (`Pedido_Fecha`)\n" +
                        "    REFERENCES Pedido (`Fecha`)\n" +
                        "    ON DELETE NO ACTION\n" +
                        "    ON UPDATE NO ACTION)");
            //Introducir los datos de producto
            for(int i=0;i<listaProductos.length;i++){
               smt.executeUpdate("insert into producto values("
                       + "'"+listaProductos[i].getId()+"',"
                        + "'"+listaProductos[i].getNombre()+"',"
                        + "'"+listaProductos[i].getPrecio()+"',"         
                        + "'"+listaProductos[i].getCantidadPorUnidad()+"',"
                        + "'"+listaProductos[i].getUnidadDeMedida()+"',"
                        + "'"+listaProductos[i].getTipoProducto()+"');");
                }
            //Introducir los datos de los restaurantes
            for(int i=0;i<listaRestaurantes.size();i++){
                smt.executeUpdate("insert into restaurante values("
                        + "'"+listaRestaurantes.get(i).getCif()+"',"
                        + "'"+listaRestaurantes.get(i).getNombre()+"',"
                        + "'"+listaRestaurantes.get(i).getDireccion()+"',"         
                        + "'"+listaRestaurantes.get(i).getTelefono()+"',"
                        + "'"+listaRestaurantes.get(i).getCodigoRestaurante()+"')");
            }
            //Introducir los datos de usuario
            for(int i=0;i<usuarios.length;i++){
                for(int j=0;j<usuarios[i].length;j++){
                    smt.executeUpdate("insert into usuario values("
                        + "'"+usuarios[i][j].getUsuario()+"',"
                        + "'"+usuarios[i][j].getContraseña()+"',"
                        + "'"+usuarios[i][j].getRestaurante()+"')");
                    
                }
            }
            
            

        } catch (SQLException ex) {
            Logger.getLogger(LaCalleBurger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
