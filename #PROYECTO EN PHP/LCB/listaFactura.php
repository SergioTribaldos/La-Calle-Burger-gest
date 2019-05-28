<?php
$idPedido = $_POST['id'];
$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");


$pedido=[];
$cantidad=array();
$result = $conn->query('select producto.*,productospedidos.*
from productospedidos
join producto  on producto.id=productospedidos.producto_id
where pedido_id='.$idPedido.';');
while ($row = $result->fetch_assoc()) {
  $id=$row["producto_id"];
  $cantidad=$row["cantidad"];
  $lote=$row["lote"];
  $nombreProducto=$row["nombre"];


  array_push($pedido, [
'producto_id'=>$id,
'cantidad'=>$cantidad,
'lote'=>$lote,
'nombre'=>$nombreProducto,
'idPedido'=>$idPedido,
]);

}

$conn->close();
$arrayJSON = json_encode($pedido);
echo $arrayJSON;

?>
