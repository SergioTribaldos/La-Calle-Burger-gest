$(window).bind("load", function() {
  /////////////FUNCIONES DE CALENDARIO///////////

  $("#datepicker").datepicker();
  $('#calendar').datepicker({
    dateFormat: 'yy-m-d',
    inline: true,
    maxDate: 0,
    onSelect: function (dateText, inst) {
      var date = $(this).datepicker('getDate'),
        day = date.getDate(),
        month = date.getMonth() + 1,
        year = date.getFullYear();
      var fecha = year + "-" + month + "-" + day;

      $.ajax({
        type: "POST",
        data: {
          fecha
        },
        url: "listaPedidos.php",
        dataType: "json",
        success: function(listaPedidos) {
          var pedidos = [];
          $("#cuerpoLista").empty();
          for (var i in listaPedidos) {
            if (listaPedidos.hasOwnProperty(i)) {

              var pedido = {
                cantidad: listaPedidos[i]["cantidad"],
                restaurante: listaPedidos[i]["restaurante"],
                fecha: listaPedidos[i]["fecha"],
                usuario: listaPedidos[i]["usuario"],
                id: listaPedidos[i]["id"],
                facturado: listaPedidos[i]["facturado"]

              }
            }
            pedidos.push(pedido);

            $("#cuerpoLista").append("<tr data-value=" + pedidos[i].id+ " data-restaurante="+pedidos[i].restaurante+"><td>" + pedidos[i].restaurante + "</td><td>" + pedidos[i].fecha + "</td><td>" + pedidos[i].usuario.toUpperCase() + "<td>" + pedidos[i].facturado + "</td></td></tr>");
            $("#selectable").selectable("option", "disabled", false);
            $('#selectable').css('cursor', 'pointer');
            $("tr td").last().css("font-weight", "bold")
            if (($("tr td").last().text()) == 1) {
              $("tr td").last().html("SI");
              $("tr td").last().css('color', '#1d933f')

            } else {
              $("tr td").last().html("NO");
              $("tr td").last().css('color', '#ce2b2b')

            }


          }
          if (pedidos.length == 0) {
            $("#cuerpoLista").append("<tr><td colspan=4 style=\"color:gray\">NO HAY PEDIDOS</td></tr>")
            $("#selectable").selectable("option", "disabled", true);
            $('#selectable').css('cursor', 'no-drop');
          }
          activaBotonesFactura();
        }
      });
    }
  });

  //CARGA AUTOMATICAMENTE EL EVENTO DE RECIBIR PEDIDOS DEL CALENDARIO AL CARGAR LA PAGINA///
  $('.ui-datepicker-current-day').click();

  //////////////////FUNCIONES DE ALTA DE USUARIO////////////////////////
  $("#botonAlta").click(function() {
    var usuario = $("#campoUsuario").val();
    var contrasena = $("#campoContrasena").val();
    var repite = $("#campoRepite").val();
    var restaurante = $("#campoRestaurante").val();
    $.ajax({
      type: "POST",
      data: {
        usuario,
        contrasena,
        repite,
        restaurante
      },
      url: "altaUsuario.php",
      dataType: "json",
      success: function(errores) {
        for (var i in errores) {
          if (errores[0].noError == true) {
            $("#5").html(errores[0].error);
            $("#5").show('fast');

              alert("el usuario ha sido dado de alta");


          } else if (errores[i].error != "") {
            $("#" + i).html(errores[i].error);
            $("#" + i).show(300,'linear');
          }

        }

      }
    });
  })

  ////////////////////FUNCIONES DE LISTA DE PEDIDOS////////////////////
  $("#selectable").selectable({
    filter: "tr",
    cancel: "#cabeceraLista",
    selected: function( event, ui ) {

      if($(".ui-selected").find(':nth-child(4)').html()=="SI"){
        $('.botonGenerarFactura').attr("disabled", true);
        $('.botonVerFactura').attr("disabled", false);
        $('.botonVerPedido').attr("disabled", false);

      }else{
        $('.botonGenerarFactura').attr("disabled", false);
        $('.botonVerFactura').attr("disabled", true);
        $('.botonVerPedido').attr("disabled", false);


      }

    }
  });
  activaBotonesFactura();

///////////ABRE EL PANEL DE FACTURA///////////////
  $("#panelFactura").dialog({
    autoOpen: false,
    minWidth: 600,
    modal: true,
    show: { effect: "puff", duration: 200 },
    close: function( event, ui ) {$("#tablaFacturas").empty()}
  });


  $("#opener").click(function() {

    $("#panelFactura").dialog("open");

    var id = $(".ui-selected").data().value;


    $.ajax({
      type: "POST",
      data: {
        id
      },
      url: "listaFactura.php",
      dataType: "json",
      success: function(listaFactura) {
        var idPedido=listaFactura[0]["idPedido"];
        $("#panelFactura").empty();
        $("#panelFactura").append("<table id=\"tablaFacturas\">");
        $("#tablaFacturas").append("<thead id=\"cabeceraFacturas\"><tr><th scope=\"col\">Lote</th><th scope=\"col\">Producto</th><th scope=\"col\">Cantidad</th></tr></thead>")
        for (var i in listaFactura) {
          var factura = {
            producto_id: listaFactura[i]["producto_id"],
            cantidad: listaFactura[i]["cantidad"],
            lote: listaFactura[i]["lote"],
          }
          if (listaFactura[i].cantidad != 0) {
            $("#tablaFacturas").append("<tr><td><input type=\"text\" class=\"datosLote\" data-value="+listaFactura[i].producto_id+"></input> " +"</td> <td>" + listaFactura[i].nombre + "</td><td>" + listaFactura[i].cantidad + "</tr>");
          }else{
            $("#tablaFacturas").append("<tr style=\"display:none\"><td><input type=\"text\"  class=\"datosLote\" data-value="+listaFactura[i].producto_id+"></input> " +"</td> <td>" + listaFactura[i].nombre + "</td><td>" + listaFactura[i].cantidad + "</tr>");
          }
        }
        $("#panelFactura").append("<button type=\"button\" data-value="+idPedido+" name=\"submit\" id=\"botonEnviarFactura\" class=\"btn btn-primary btn-lg boton\">Enviar factura</button>");

      }
    });

  });


  ////////CUANDO PULSAMOS EL BOTON ENVIAR FACTURA, LA ENVIA A LA BASE DE DATOS/////////////
$("body").on('click','#botonEnviarFactura',function(){
    var alertaConfirmacion = confirm("Desea enviar la factura?");
    if(alertaConfirmacion==false){
      return false;
    }
    var listaLotes=new Array();
    var listaIds=new Array();
    var idPedido=$("#botonEnviarFactura").data('value');
    var validado=true;
  $(".datosLote").each(function(){
    listaIds.push($(this).data('value'));
    listaLotes.push($(this).val());
    if(($(this).val()==""&&($(this).is(':visible')))){
      alert("Algunos de los campos de lote está vacio");
      validado=false;
      return false;
    }
  })
  if(validado){
    $.ajax({
      type:"POST",
      data: {listaLotes,listaIds,idPedido},
      url: "enviarFactura.php",
      dataType: "text",
      success:function(pedidoRealizado){
        if(pedidoRealizado){
          alert("Pedido realizado");
          $("#panelFactura").dialog("close");
          $('#botonEnviarFactura').attr("disabled", true);
          location.reload();

        }

      }
    })
  }

});

//////////////////////FUNCIONES DE CONSULTAR FACTURA////////////////
$('.botonVerFactura').click(function(){
    var idPedido=$(".ui-selected").data('value');
    var r=$("#inputId").val(idPedido);


})

//////////////////////FUNCIONES DE CONSULTAR PEDIDO////////////////
$('.botonVerPedido').click(function(){
    var idPedido=$(".ui-selected").data('value');
    var nombreRestaurante=$(".ui-selected").data('restaurante');
    var r=$("#inputIdFactura").val(idPedido);
    var r=$("#inputNombreRestaurante").val(nombreRestaurante);

})





  function activaBotonesFactura(){
    var r=$( ".ui-selectee");
    for(var i=0;i<r.length;i++){
      var e=$( r ).hasClass(".ui-selected")
      if(e==true){
        break;
      }else{
        $('.botonGenerarFactura').attr("disabled", true);
        $('.botonVerFactura').attr("disabled", true);
        $('.botonVerPedido').attr("disabled", true);
      }
    }
  }


});
