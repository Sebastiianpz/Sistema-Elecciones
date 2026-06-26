<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="UTF-8">

<title>Consulta de Habilitación</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <div class="card busqueda-card">

        <h2>Consulta Electoral</h2>

        <p>
            Ingresá un número de documento para verificar si la persona
            se encuentra habilitada para votar.
        </p>

        <div class="input-wrap">

            <input
                type="text"
                id="input-dni"
                class="form-control"
                placeholder="Ingrese DNI">

            <button
                id="btn-consultar"
                class="btn btn-primary">

                Buscar

            </button>

        </div>

        <div id="resultado"></div>

    </div>

</div>


<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>

<script>

const ctx = "${pageContext.request.contextPath}";

$("#btn-consultar").click(function(){

    consultar();

});

$("#input-dni").keypress(function(e){

    if(e.which==13){

        consultar();

    }

});

function consultar(){

    let dni=$("#input-dni").val().trim();

    if(dni==""){

        $("#resultado").html(

            '<div class="resultado-error">'+
            'Debe ingresar un número de documento.'+
            '</div>'

        );

        return;

    }

    $("#btn-consultar")
        .prop("disabled",true)
        .text("Buscando...");

    $.ajax({

        url: ctx + "/Busqueda",

        type:"GET",

        data:{
            dni:dni
        },

        dataType:"json",

        success:function(data){

            if(data.ok){

                let clase=data.habilitado ?
                        "habilitado" :
                        "inhabilitado";

                let icono=data.habilitado ?
                        "✅" :
                        "❌";

                let estado=data.habilitado ?
                        "Habilitado para votar" :
                        "No habilitado para votar";

                $("#resultado").html(

                    '<div class="resultado-box '+clase+'">'+

                        '<div class="resultado-icono">'+
                        icono+
                        '</div>'+

                        '<div class="resultado-nombre">'+
                        data.apellido+', '+data.nombre+
                        '</div>'+

                        '<div class="resultado-dni">'+
                        'DNI: '+data.dni+
                        '</div>'+

                        '<div class="resultado-estado">'+
                        estado+
                        '</div>'+

                    '</div>'

                );

            }else{

                $("#resultado").html(

                    '<div class="resultado-error">'+
                    data.error+
                    '</div>'

                );

            }

        },

        error:function(xhr){

            let mensaje="Error al conectar con el servidor.";

            if(xhr.responseJSON){

                mensaje=xhr.responseJSON.error;

            }

            $("#resultado").html(

                '<div class="resultado-error">'+
                mensaje+
                '</div>'

            );

        },

        complete:function(){

            $("#btn-consultar")
                .prop("disabled",false)
                .text("Buscar");

        }

    });

}

</script>

</body>

</html>