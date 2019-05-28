<?php

session_start();
session_destroy();
include("operaciones.php");

$usuario;
$contrasena;
$repite;
$restaurante;
$errores=array();
if (isset($_POST['submit'])) {
    $errores=validaUsuario();
}

 ?>

 <!DOCTYPE html>
 <html lang="en" dir="ltr">

 <head>
   <meta charset="utf-8">
   <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
   <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
   <script src="http://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="panelAdministrador.js"></script>
   <link rel="stylesheet" href="css/panelAdministrador.css" type="text/css">

   <title></title>
 </head>

 <body>

   <div class="container">
     <ul class="nav nav-pills">
       <li class="active"><a data-toggle="pill" href="#home">Pedidos</a></li>
       <li><a data-toggle="pill" href="#menu2">Alta usuario</a></li>

     </ul>

     <div class="tab-content">
       <!--///////////////////////////////////TAB 1////////////////////////////////////////-->
       <div id="home" class="tab-pane fade in active">
         <h3>Pedidos</h3>

         <div class="col-lg-4 col-md-4 " id="divCalendario">
           <div class="row">
               <div id="calendar"></div>
           </div>
           <div class="row">
               <button id="opener" class="btn btn-primary btn-lg boton botonGenerarFactura">Generar factura</button>
              <div id="panelFactura"> </div>
           </div>
           <div class="row">
             <form class="" action="vistaFactura.php" target="_blank" method="post">
               <input type="text" id="inputId" style="display:none" name="idPedido" value="">
               <button type="submit"  class="btn btn-primary btn-lg boton botonVerFactura">Ver factura</button>
             </form>
           </div>
           <div class="row">
             <form class="" action="vistaPedido.php" target="_blank" method="post">
               <input type="text" id="inputIdFactura" style="display:none" name="idPedido" value="">
               <input type="text" id="inputNombreRestaurante" style="display:none" name="nombreRestaurante" value="">
               <button type="submit"  class="btn btn-primary btn-lg boton botonVerPedido">Ver pedido</button>
             </form>
           </div>

         </div>
         <div class="col-lg-8 col-md-8" id="divTabla">
           <table class="table table-striped" id="selectable">
             <thead>
               <tr id="cabeceraLista">
                 <th scope="col">Restaurante</th>
                 <th scope="col">Fecha</th>
                 <th scope="col">Usuario</th>
                 <th scope="col">Facturado</th>
               </tr>
             </thead>
             <tbody id="cuerpoLista">
             </tbody>
           </table>
         </div>
       </div>
       <!--///////////////////////////////////TAB 2////////////////////////////////////////-->
       <div id="menu2" class="tab-pane fade ">
         <h3>Alta Usuario</h3>

         <form action="" method="post" id="formularioAlta">
           <div class="input-group input-group-lg">
             <div class="input-group-prepend">
               <span class="input-group-text" id="inputGroup-sizing-lg">Usuario</span>
             </div>

             <input type="text" id="campoUsuario" name="usuario" class="form-control campoIntroduce" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
              <div id="0" class="alert alert-danger" style="float:left;margin-left:5px;" role="alert"></div>
           </div>

           <div class="input-group input-group-lg">
             <div class="input-group-prepend">
               <span class="input-group-text" id="inputGroup-sizing-lg">Contraseña</span>
             </div>

             <input type="password" id="campoContrasena" name="contrasena" class="form-control campoIntroduce" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
             <div id="1" class="alert alert-danger" style="float:left;margin-left:5px" role="alert"></div>
           </div>

           <div class="input-group input-group-lg">
             <div class="input-group-prepend">
               <span class="input-group-text" id="inputGroup-sizing-lg">Repite contraseña</span></div>
             <input type="password" id="campoRepite" name="repite" class="form-control campoIntroduce" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
             <div id="2" class="alert alert-danger" style="float:left;margin-left:5px" role="alert"></div>
           </div>

           <div>

             <select name="opcionesRestaurante" id="campoRestaurante" class="browser-default custom-select campoIntroduce">
               <option selected>Elige un restaurante</option>
               <?php echo muestraRestaurantes() ?>
             </select>
             <div id="3" class="alert alert-danger" style="float:none;margin-left:5px" role="alert"></div>
           </div>


           <button type="button" name="submit" id="botonAlta" class="btn btn-primary btn-lg boton campoIntroduce">Alta de usuario</button>
         </form>
         <div id="5" class="alert alert-success" role="alert"></div>


       </div>
     </div>
   </div>

 </body>

 </html>
