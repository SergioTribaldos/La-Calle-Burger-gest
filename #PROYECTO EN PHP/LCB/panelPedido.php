<?php
session_start();
include ("operaciones.php");
$listaProductos=traeProductos();
$cantidades=Array();
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../estilos.css" type="text/css">
    <link rel="stylesheet" href="../css/panelPedido.css" type="text/css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

  </head>
<body>

	<h1>Hola <?php echo $_SESSION["nombreUsuario"] . "      Restaurante: " . $_SESSION["restaurante"];?></h1>
	<form action="../pedidoRealizado.php" method="post">
  <div id="contenedorTablas">

		<?php


		for ($i=0; $i <count($listaProductos) ; $i++) {
      if($i==0){
        echo "<div class=\"tablaPedidos\">";
        echo "<table>";
				echo '<tr> <td colspan="2" id="titulos"> Hamburguesas </td></tr>';
			}
			echo '<tr>';
			echo '<td><p id="productoNombre">' . $listaProductos[$i] . '</td><td><input type="number" id="entraNumero" name="'.$i.'" value=0 min="0" ></td>';
			echo '</tr>';

			if($i==6){
				echo '<tr> <td colspan="2" id="titulos"> Entrantes y guarniciones </td></tr>';
			}
			if($i==15){
        echo "</table>";
        echo "</div>";
        echo "<div class=\"tablaPedidos\">";
        echo "<table>";
				echo '<tr> <td colspan="2" id="titulos"> Salsas </td></tr>';
			}
			if($i==29){
				echo '<tr> <td colspan="2" id="titulos"> Bocadillos </td></tr>';
			}
			if($i==33){
				echo '<tr> <td colspan="2" id="titulos"> Postres </td></tr>';
			}
		}
    echo "</table>";
    echo "</div>";
		 ?>
   </div>

	<button type="button" class="btn btn-primary btn-lg boton">Realizar pedido</button>
	</form>
<script>
var pulsado = false;
$(".boton").click(function(){
	if(pulsado==false){
		$(".boton").html('Pulsa de nuevo para enviar el pedido');
		pulsado=true;
	}else{
		$(".boton").prop("type", "submit");
	}


});

</script>
</body>
</html>
