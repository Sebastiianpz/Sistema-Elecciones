$(function() {
    
    $("#btn-login").click(function (e) {
        
        // Show full page LoadingOverlay
        $.LoadingOverlay("show",{
            text        : "Cargando..."
        });

        // Hide it after 3 seconds
        setTimeout(function(){
            $.LoadingOverlay("hide");
        }, 2500);

            e.preventDefault(); 
            var usuario = $("#email").val(); // Mantenemos tu variable 'usuario'
            var password = $("#password").val();
            var mensaje = "";
            
                $.ajax({
                    url: contextPath + '/loginAdministrador', // Cambiado al nombre de tu servlet
                    dataType: 'JSON',
                    success: function(data){
                        // Mantenemos la estructura exacta de tu ejemplo con data[0]
                        if (data[0].estatus == "error") {
                            mensaje = data[0].msg;
                            $('#grupo-passowrd').append("<div class='alert alert-primary' role='alert'> "+mensaje+" </div>");                    
                        } else {
                            window.location.href = contextPath + "/home";
                        }
                    },
                    data: {
                        username : usuario, // Cambiado para que tu Controller lo reciba bien
                        password : password
                    },
                    cache: false,
                    type: 'POST'
                });

        }
    );

});