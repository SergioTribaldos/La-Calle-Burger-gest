/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lacalleburger;

import java.util.ArrayList;
import pedidos.Pedido;
import productos.Bocadillo;
import productos.Entrante;
import productos.Hamburguesa;
import productos.Postre;
import productos.Producto;
import productos.Salsa;
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
      /*  final Producto [] listaProductos=new Producto[37];
        listaProductos[0]=new Hamburguesa((short)0,"HAMBURGUESA DE AGUJA 20ud",1,20);
        listaProductos[1]=new Hamburguesa((short)1,"HAMBURGUESA DE ANGUS 20ud",1,20);
        listaProductos[2]=new Hamburguesa((short)2,"HAMBURGUESA DE ENTRAÑA 20ud",1,20);
        listaProductos[3]=new Hamburguesa((short)3,"HAMBURGUESA DE GARBANZOS 30ud",1,30);
        listaProductos[4]=new Hamburguesa((short)4,"HAMBURGUESA DE LENTEJAS 30ud",1,30);
        listaProductos[5]=new Hamburguesa((short)5,"HAMBURGUESA DE VACIO 20ud",1,20);
        listaProductos[6]=new Hamburguesa((short)6,"HAMBURGUESA DOBLE 30ud",1,30);
        listaProductos[7]=new Entrante((short)7,"ALITAS DE POLLO 2kg",1,2.0);
        listaProductos[8]=new Entrante((short)8,"BACON CRUJIENTE bolsa 400gr",1,0.4);
        listaProductos[9]=new Entrante((short)9,"CEBOLLA AL VINO bolsa 1,5kg",1,1.5);
        listaProductos[10]=new Entrante((short)10,"CHAMPIÑON PORTOBELLO bolsa 1kg",1,1.0);
        listaProductos[11]=new Entrante((short)11,"COSTILLAS DE CERDO bolsa 2kg",1,2.0);
        listaProductos[12]=new Entrante((short)12,"ENTRAÑA PARA BROCHETA bolsa 500gr",1,0.5);
        listaProductos[13]=new Entrante((short)13,"FINGERS DE POLLO bolsa 2kg",1,2.0);
        listaProductos[14]=new Entrante((short)14,"PIMIENTO CONFITADO",1,12);
        listaProductos[15]=new Entrante((short)15,"POLLO MARINADO bandeja 2,5kg",1,2.5);
        listaProductos[16]=new Entrante((short)16,"SALSA CALAMBRITO bolsa 2 k",1,2.0);
        listaProductos[17]=new Salsa((short)17,"CALDO PARA SALTEADO bolsa 1 kg",1,1.0);
        listaProductos[18]=new Salsa((short)18,"KETCHUP LA CALLE bolsa 1 kg",1,1.0);
        listaProductos[19]=new Salsa((short)19,"MAYONESA LA CALLE bolsa 2 kg",1,2.0);
        listaProductos[20]=new Salsa((short)20,"MAYONESA DE CHIMICHURRI bolsa 2 kg",1,2.0);
        listaProductos[21]=new Salsa((short)21,"MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg",1,2.0);
        listaProductos[22]=new Salsa((short)22,"MAYONESA SWEET CHILI bolsa 1 kg",1,1.0);
        listaProductos[23]=new Salsa((short)23,"MAYONESA VEGANA bolsa 500 g",1,0.5);
        listaProductos[24]=new Salsa((short)24,"SALSA BBQ bolsa 2 kg",1,2.0);
        listaProductos[25]=new Salsa((short)25,"SALSA BBQ TOFFEE bolsa 1 kg",1,1.0);
        listaProductos[26]=new Salsa((short)26,"SALSA CALLEJERA bolsa 2 kg",1,2.0);
        listaProductos[27]=new Salsa((short)27,"SALSA CÉSAR bolsa 1 kg",1,1.0);
        listaProductos[28]=new Salsa((short)28,"SALSA DE ALITAS bolsa 2 kg",1,2.0);
        listaProductos[29]=new Salsa((short)29,"VINAGRETA PARA ENSALADA DE COL bolsa 1 kg",1,1.0);
        listaProductos[30]=new Bocadillo((short)30,"COCHINITA PIBIL bolsa 1 kg",1,1.0);
        listaProductos[31]=new Bocadillo((short)31,"ENTRECOT MARINADO bolsa 500 g",1,0.5);
        listaProductos[32]=new Bocadillo((short)32,"SALCHICHAS bolsa 8 ud",1,8);
        listaProductos[33]=new Postre((short)33,"TARTA OREO caja 16 ud",1,16);
        listaProductos[34]=new Postre((short)34,"TARTA QUESO caja 16 ud",1,16);
        listaProductos[35]=new Postre((short)35,"TARTA SACHER caja 20 ud",1,20);
        listaProductos[36]=new Postre((short)36,"TARTA ZANAHORIA caja 20 ud",1,20);*/
        
        ArrayList<Restaurante> listaRestaurantes=new ArrayList<Restaurante>();
        listaRestaurantes.add(new Restaurante("S7741870E","Centro","Calle Mosquera 3. Málaga","951 46 58 72",CENTRO));
        listaRestaurantes.add(new Restaurante("S3341870E","Teatinos","Av. de Gregorio Prieto 27. Málaga ","951 02 30 50",TEATINOS));
        listaRestaurantes.add(new Restaurante("S5541870E","Fuengirola","Calle Marconi 32, Fuengirola Málaga","951 10 11 35",FUENGIROLA));
        listaRestaurantes.add(new Restaurante("S4441870E","Pedregalejo","Paseo Marítimo El Pedregal 11. Málaga Pedregalejo","951 50 35 64",PEDREGALEJO));
        listaRestaurantes.add(new Restaurante("S8841870E","Americas","Avenida de las Américas 9. Málaga","951 07 97 94",AMERICAS));
        listaRestaurantes.add(new Restaurante("S1141870E","Plaza Mayor","Calle Alfonso Ponce de León 3. Málaga","952 02 64 64",PLAZA_MAYOR));
        listaRestaurantes.add(new Restaurante("S0041870E","Parque Oeste","Calle Diamantino García Acosta 1. Málaga","951 91 77 15",PARQUE_OESTE));
        listaRestaurantes.add(new Restaurante("S0041870E","Gamarra","Calle Sondalezas 33. Málaga","951 77 70 93",GAMARRA));
        listaRestaurantes.add(new Restaurante("S0041870E","San Pedro","Avenida de Burgos 22. San Pedro de Alcántara","951 482 590",SAN_PEDRO));
        listaRestaurantes.add(new Restaurante("S0041870E","Marbella","Avenida Miguel Cano 1. Edif. Milenium. Marbella","951 812 128",MARBELLA));
        
        
        Usuario[] usuariosCentro=new Usuario[3];
        usuariosCentro[0]=new Usuario(CENTRO,listaRestaurantes,"Javier","pk34ers");
        usuariosCentro[1]=new Usuario(CENTRO,listaRestaurantes,"Paco","oj45er");
        usuariosCentro[2]=new Usuario(CENTRO,listaRestaurantes,"Pepe","is55me");
        
        usuariosCentro[0].hacerPedido();
        
    }
    
}
