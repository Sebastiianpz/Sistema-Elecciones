$(document).ready(function() {

       $.ajax({
           url: contextPath + '/ingresarMesa',
           method: 'GET',
           dataType: 'json',
           success: function(response) {
               if (!response.permitido) {
                   Swal.fire({
                       title: 'Pc Bloqueada',
                       text: response.mensajeError,
                       icon: 'error',
                       allowOutsideClick: false,
                       allowEscapeKey: false,
                       showConfirmButton: false
                   });
                   $("#inputDni").prop("disabled", true);
                   $("#btnValidar").prop("disabled", true);
               } else {
                   console.log("Pc autorizada. ID de PC guardado en sesión.");
               }
           },
           error: function(xhr) {
               console.error("No se pudo verificar el estado de la terminal.");
           }
       });
   });