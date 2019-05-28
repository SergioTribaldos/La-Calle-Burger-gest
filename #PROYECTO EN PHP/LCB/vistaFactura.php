<?php

$_POST['idPedido'];

$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
$result = $conn->query("select pedido.*,usuario.nombre,productospedidos.*,restaurante.*,producto.*
										from producto
										join productospedidos on producto.id=productospedidos.producto_id
										join pedido  on pedido.id=productospedidos.pedido_id
										join usuario on pedido.Usuario_usuario=usuario.nombre
										join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante
										where pedido.id=".$_POST['idPedido']."
										order by pedido_id desc, producto_id asc;");
    $lote=Array();
    $cantidad=Array();
    $nombreProducto=Array();
    $precio=Array();

    while ($row = $result->fetch_assoc()) {
        array_push($lote,$row["lote"]);
        array_push($cantidad,$row["cantidad"]);
        array_push($nombreProducto,$row["nombre"]);
        array_push($precio,$row["precio"]);
        $restaurante=$row["direccion"];
        $codigoRestaurante=$row["codigoRestaurante"];
        $cif=$row["cif"];
				$fecha=$row["Fecha"];


    }

 ?>
 <!DOCTYPE html>
 <html lang="en" dir="ltr">
   <head>
     <meta charset="utf-8">
     <title></title>
     <style type="text/css">
     .tg  {border-collapse:collapse;border-spacing:0;}
     .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
     .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
     .tg .tg-hfk9{border-color:#000000;text-align:left;vertical-align:top}
     .tg .tg-p1nr{font-size:11px;border-color:inherit;text-align:left;vertical-align:top}
     .tg .tg-uys7{border-color:inherit;text-align:center}
     .tg .tg-3pun{font-size:12px;border-color:inherit;text-align:left}
     .tg .tg-gzo9{font-size:11px;border-color:inherit;text-align:center;vertical-align:top}
     .tg .tg-1plf{font-size:12px;border-color:inherit;text-align:center}
     .tg .tg-xldj{border-color:inherit;text-align:left}
     .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
     .tg .tg-73a0{font-size:12px;border-color:inherit;text-align:left;vertical-align:top}
     .tg .tg-73oq{border-color:#000000;text-align:left;vertical-align:top}
     .tg .celda{font-size:11px;border-color:inherit;text-align:left;vertical-align:top;border-top:none;border-bottom: none;padding-top: 4px;padding-bottom: 4px;}

     </style>
   </head>
   <body>

     <table class="tg" style="undefined;table-layout: fixed; width: 770px">
 <colgroup>
 <col style="width: 79px">
 <col style="width: 427px">
 <col style="width: 81px">
 <col style="width: 101px">
 <col style="width: 82px">
 </colgroup>
   <tr>
     <th class="tg-1plf" colspan="5">LA CALLE BURGER, S.L. B93612109<br>C/ Escritora Carmen Martín Gaite Nº2 Local 6 C.P.29196 Málaga</th>
   </tr>
   <tr>
     <td class="tg-uys7" colspan="5">ALBARÁN</td>
   </tr>
   <tr>
     <td class="tg-3pun">PARA:</td>
     <td class="tg-xldj"><?php echo $codigoRestaurante."   ".$cif ?></td>
     <td class="tg-3pun">FECHA:</td>
     <td class="tg-0pky" colspan="2"><?php echo $fecha ?></td>
   </tr>
   <tr>
     <td class="tg-73a0">ENTREGA:</td>
     <td class="tg-0pky"><?php echo $restaurante ?></td>
     <td class="tg-73a0">ALBARAN:</td>
     <td class="tg-0pky" colspan="2"></td>
   </tr>
   <tr>
     <td class="tg-0pky" colspan="5"></td>
   </tr>
   <tr>
     <td class="tg-gzo9">Nº LOTE</td>
     <td class="tg-gzo9">DESCRIPCION DE PRODUCTO</td>
     <td class="tg-gzo9">CANTIDAD</td>
     <td class="tg-gzo9">PRECIO UNITARIO</td>
     <td class="tg-gzo9">IMPORTE</td>
   </tr>
   <tr>

     <?php
     $filasintroducidas=0;
		 $sumaTotalSinIva=0;
     for ($i=0; $i < sizeof($cantidad); $i++) {
       if($cantidad[$i]>0){
         echo "<tr>";
         echo " <td class=\"celda\">$lote[$i]</td>";
         echo "<td class=\"celda\">$nombreProducto[$i]</td>";
         echo "<td class=\"celda\">$cantidad[$i]</td>";
         echo "<td class=\"celda\">$precio[$i]</td>";
         echo "<td class=\"celda\">".($cantidad[$i]*$precio[$i])."</td>";
         echo "</tr>";
         $filasintroducidas++;
				 $sumaTotalSinIva=$sumaTotalSinIva+$cantidad[$i]*$precio[$i];
       }

     }

     for ($i=0; $i <sizeof($cantidad)-$filasintroducidas ; $i++) {
       echo "<tr><td class=\"celda\"></td><td class=\"celda\"></td><td class=\"celda\"></td><td class=\"celda\"></td><td class=\"celda\"></td></tr>";
     }
     ?>

   </tr>
   <tr>
     <td class="tg-0pky" colspan="4">EXTRAS/ABONOS/DESCUENTOS</td>
     <td class="tg-0pky"></td>
   </tr>
   <tr>
     <td class="tg-73oq"></td>
     <td class="tg-73oq"></td>
     <td class="tg-0pky" colspan="2">IMPORTE</td>
     <td class="tg-0pky"><?php echo $sumaTotalSinIva ?></td>
   </tr>
   <tr>
     <td class="tg-73oq"></td>
     <td class="tg-hfk9"></td>
     <td class="tg-0pky" colspan="2">IVA                                        10%</td>
     <td class="tg-0pky"><?php echo ($sumaTotalSinIva*1.10)-$sumaTotalSinIva ?></td>
   </tr>
   <tr>
     <td class="tg-73oq"></td>
     <td class="tg-73oq"></td>
     <td class="tg-0pky" colspan="2">TOTAL</td>
     <td class="tg-0pky"><?php echo $sumaTotalSinIva*1.10 ?></td>
   </tr>
 </table>


   </body>
 </html>
