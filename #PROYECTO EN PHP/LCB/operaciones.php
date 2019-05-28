<?php

function traeProductos()
{
    $conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
    $result = $conn->query("select nombre from producto");
    $listaProductos=array();

    while ($row = $result->fetch_assoc()) {
        array_push($listaProductos, $row["nombre"]);
    }
    return $listaProductos;
}


function realizaPedido()
{
    $conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    date_default_timezone_set('Europe/Madrid');
    $fecha=date("Y-m-d H:i:s");
    $nombre=$_SESSION["nombreUsuario"];

    $sql = "INSERT INTO pedido (fecha,Usuario_usuario)
  VALUES ( \"$fecha\" , \"$nombre\" )";


    if ($conn->query($sql) === true) {
        $ultimoId = $conn->insert_id;
        echo "El pedido se ha realizado satisfactoriamente a las " . $fecha;
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    for ($i=0; $i <count($_POST) ; $i++) {
        $sql = "INSERT INTO productospedidos (producto_id,pedido_id,cantidad) VALUES(
  $sql .=  $i,
  $sql .= $ultimoId,
  $sql .= $_POST[$i]);";

        $sql = "INSERT INTO productospedidos (producto_id,pedido_id,cantidad)
  VALUES ($i, $ultimoId, $_POST[$i])";

        if ($conn->query($sql) === true) {
        }
    }
    $conn->close();
}


function muestraRestaurantes()
{
    $conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
    $result = $conn->query("select codigoRestaurante from restaurante");
    $listaRestaurantes="";
    while ($row = $result->fetch_assoc()) {
        $listaRestaurantes=$listaRestaurantes . '<option value="'.$row["codigoRestaurante"].'">'.$row["codigoRestaurante"].'</option>';
    }
    return $listaRestaurantes;
}

function validaUsuario()
{
    $_SESSION['prev']=null;
    $usuario=$_POST["usuario"];
    $contrasena=$_POST["contrasena"];
    $codigoRestaurante=$_POST["opcionesRestaurante"];



    $errores=array();

    if (trim($_POST["usuario"], " ")=="") {
        array_push($errores, 0);
    }

    if (strlen($_POST["contrasena"])<6) {
        array_push($errores, 1);
    } elseif ($_POST["contrasena"]!=$_POST["repite"]) {
        array_push($errores, 2);
    }
    if ($_POST["opcionesRestaurante"]=="Elige un restaurante") {
        array_push($errores, 3);
    }
    $conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
    $result = $conn->query("select nombre from usuario");
    while ($row = $result->fetch_assoc()) {
        if ($_POST["usuario"]==$row["nombre"]) {
            array_push($errores, 4);
            break;
        }
    }

    if (count($errores)>0) {
        return $errores;
    } else {
        $sql = "INSERT INTO usuario
    VALUES (\"$usuario\", \"$contrasena\",\"$codigoRestaurante\" )";
        if ($conn->query($sql) === true) {
            array_push($errores, 5);
            return $errores;
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    }
}

function traeLista()
{
    $_SESSION["day"] =$_POST["day"];
    ;
}
