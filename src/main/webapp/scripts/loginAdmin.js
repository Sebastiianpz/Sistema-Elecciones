$(function() {
    
    // 🌟 NUEVO: Esto frena el envío nativo del formulario para que no limpie los inputs antes de tiempo
    $("#formAdmin").on('submit', function(e) {
        e.preventDefault();
    });
    
    $("#btn-login").click(function (e) {
        e.preventDefault(); 
        
        var usuario = $("#username").val(); 
        var password = $("#password").val();

        if (!usuario || !password || usuario.trim() === "" || password.trim() === "") {
            Swal.fire("Atención", "Por favor, ingresá tu usuario y contraseña.", "warning");
            return;
        }

        $.LoadingOverlay("show", { text : "Cargando..." });

        setTimeout(function(){
            $.LoadingOverlay("hide");
        }, 2500);
        
        $.ajax({
            url: contextPath + '/loginAdministrador', 
            type: 'POST',
            dataType: 'JSON',
            data: {
                username : usuario.trim(), 
                password : password       
            },
            cache: false,
            success: function(data) {
                if (data.ok === true) {
                    window.location.href = contextPath + "/dashboard/dashboard.jsp";
                } else {
                    var errorMsg = data.error ? data.error : "Usuario o contraseña incorrectos.";
                    Swal.fire("Error de acceso", errorMsg, "error");
                }
            },
            error: function(xhr, status, error) {
                try {
                    var responseJson = JSON.parse(xhr.responseText);
                    Swal.fire("Error", responseJson.error, "error");
                } catch(e) {
                    Swal.fire("Error", "No se pudo procesar la solicitud (Código: " + xhr.status + ").", "error");
                }
            }
        });
    });
});