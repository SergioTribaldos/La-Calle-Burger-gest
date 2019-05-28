<?php

$_POST['idPedido'];
$_POST['nombreRestaurante'];

$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
$result = $conn->query("select productospedidos.*,producto.nombre
from productospedidos
join producto on productospedidos.producto_id=producto.id
where pedido_id=".$_POST['idPedido'].";");
    $cantidad=Array();
    $nombreProducto=Array();

    while ($row = $result-> fetch_assoc()) {
        array_push($cantidad,$row["cantidad"]);
        array_push($nombreProducto,$row["nombre"]);

    }

 ?>
 <!DOCTYPE html>
 <html lang="en" dir="ltr">
   <head>
     <meta charset="utf-8">
     <title></title>
     <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
     <style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:8px 3px;border-style:solid;border-width:2px;overflow:hidden;word-break:normal;border-color:black;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:2px;overflow:hidden;word-break:normal;border-color:black;}
.tg .tg-xldj{border-color:inherit;text-align:left;text-align: center;}
.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
tr:nth-child(even){
  background-color:#f2f2f2;

}
*{
  font-family: 'Montserrat', sans-serif !important;
}
.producto{

  font-size: 18px !important;
}
.cantidad{
  font-size: 28px !important;
  text-align: center !important;
}
</style>
   </head>
   <body>
     <h1>Pedido de: <?php echo $_POST['nombreRestaurante'] ?></h1>
     <table class="tg" style="undefined;table-layout: fixed; width: 633px">
<colgroup>
<col style="width: 535px">
<col style="width: 98px">
</colgroup>
  <tr>
    <th class="tg-xldj"><h2>Producto</h2> </th>
    <th class="tg-xldj"> <h2>Cant.</h2> </th>
  </tr>

    <?php
    for ($i=0; $i < count($cantidad); $i++) {
      echo "<tr>";
      echo "<td class=\"tg-0pky producto\">".$nombreProducto[$i]."</td>";
      echo "<td class=\"tg-0pky cantidad\">".$cantidad[$i]."</td>";
      echo "</tr>";

    }
     ?>

</table>

   </body>
 </html>
