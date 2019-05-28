<?php
$idsPedido = $_POST['listaIds'];
$lotesPedido = $_POST['listaLotes'];
$idPedido= $_POST['idPedido'];
$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
$resul=false;
for ($i=0; $i <36 ; $i++) {
  $sql=("UPDATE productospedidos SET lote=\"$lotesPedido[$i]\" where producto_id=$i and pedido_id=$idPedido ;");
  $resul=$resul . $sql;

  if ($conn->query($sql) === true) {
    $resul =true;
  }else{
    $resul=false;
    break;
  }
}

if($resul==true){
  $sql=("UPDATE pedido SET facturado=1 where id=$idPedido;");
  $conn->query($sql);

}

$conn->close();


echo $resul;


 ?>
