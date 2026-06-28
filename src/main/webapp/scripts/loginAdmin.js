$(function() {
    // ── Login ──────────────────────────────────────────
    $("#btn-login").on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        var usuario = $("#username").val().trim();
        var password = $("#password").val().trim();
        if (!usuario || !password) {
            Swal.fire("Atención", "Por favor, ingresá tu usuario y contraseña.", "warning");
            return;
        }
        $.LoadingOverlay("show", { text: "Cargando..." });
        $.ajax({
            url: contextPath + '/loginAdministrador',
            type: 'POST',
            dataType: 'JSON',
            data: { username: usuario, password: password },
            cache: false,
            success: function(data) {
                $.LoadingOverlay("hide");
                if (data.ok === true) {
                    window.location.href = contextPath + "/dashboard/dashboard.jsp";
                } else {
                    Swal.fire("Error de acceso", data.error || "Usuario o contraseña incorrectos.", "error");
                }
            },
            error: function(xhr) {
                $.LoadingOverlay("hide");
                try {
                    var res = JSON.parse(xhr.responseText);
                    Swal.fire("Error", res.error, "error");
                } catch(e) {
                    Swal.fire("Error", "Código: " + xhr.status, "error");
                }
            }
        });
    });

    // ── Logout ─────────────────────────────────────────
    var btnLogout = document.getElementById('btnLogout');
    if (btnLogout) {
        btnLogout.addEventListener('click', function () {
            Swal.fire({
                title: '¿Cerrar sesión?',
                text: 'Se cerrará tu sesión de administrador.',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#e74c3c',
                cancelButtonColor: '#6c757d',
                confirmButtonText: 'Sí, salir',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = contextPath + '/logoutAdministrador';
                }
            });
        });
    }
});