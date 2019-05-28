<?php

$usuario=$_POST['usuario'];
$contrasena=$_POST['contrasena'];
$repite=$_POST['repite'];
$restaurante=$_POST['restaurante'];

$hayErrores=false;
$errores=[];
$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
$result = $conn->query("select nombre from usuario");
while($row = $result->fetch_assoc()) {
    if($usuario==$row["nombre"]){
      array_push($errores,['error'=>"El usuario ya existe"]);
      $hayErrores=true;
      break;
    }
}
 if(trim($usuario," ")==""){
    array_push($errores,['error'=>"Debes introducir un nombre"]);
    $hayErrores=true;
}else{
  array_push($errores,['error'=>""]);
}

if(strlen($contrasena)<6){
  array_push($errores,['error'=>"La contraseña debe tener mas de 6 caracteres"]);
  $hayErrores=true;
}else{
  array_push($errores,['error'=>""]);
}

 if($contrasena!=$repite){
   array_push($errores,['error'=>"Las contraseñas no coinciden"]);
   $hayErrores=true;
}else{
  array_push($errores,['error'=>""]);
}

if($restaurante=="Elige un restaurante"){
  array_push($errores,['error'=>"Debes elegir un restaurante"]);
  $hayErrores=true;
}

if($hayErrores==false){
  $sql = "INSERT INTO usuario
  VALUES ( \"$usuario\" , \"$contrasena\", \"$restaurante\" )";


  if ($conn->query($sql) === TRUE) {
    $conn->close();
    $errores=[];
    array_push($errores,['noError'=>true,'mensaje'=>"El usuario ha sido dado de alta!!"]);
    $arrayJSON = json_encode($errores);
    echo $arrayJSON;
}

}else{
  
  $conn->close();
  $arrayJSON = json_encode($errores);
  echo $arrayJSON;
}





?>
