<?php
  $fecha = $_POST['fecha'];

  $listaPedidos="";
  $conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");


  $objetos=[];
  $cantidad=array();
  $result = $conn->query('select pedido.*,usuario.nombre,productospedidos.*,restaurante.*
										from productospedidos
										join pedido  on pedido.id=productospedidos.pedido_id
										join usuario on pedido.Usuario_usuario=usuario.nombre
										join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante
										where DATE(pedido.fecha)="'.$fecha.'"
										order by pedido_id desc, producto_id asc;');
  while ($row = $result->fetch_assoc()) {
      array_push($cantidad, $row["cantidad"]);

      if ($row["producto_id"]==36) {
          $restaurante=$row["codigoRestaurante"];
          $pedido=[$cantidad,$restaurante];
          $fechaYHora=new DateTime($row["Fecha"]);
          $fecha=$fechaYHora->format('d/m/Y H:i');
          $usuario=$row["nombre"];
          $id=$row["id"];
          $facturado=$row["facturado"];

          array_push($objetos, [
        'cantidad'=>$cantidad,
        'restaurante'=>$restaurante,
        'fecha'=>$fecha,
        'usuario'=>$usuario,
        'id'=>$id,
        'facturado'=>$facturado

        ]);
          $cantidad=array();
      }

  }
  $conn->close();
  $arrayJSON = json_encode($objetos);
  echo $arrayJSON;
