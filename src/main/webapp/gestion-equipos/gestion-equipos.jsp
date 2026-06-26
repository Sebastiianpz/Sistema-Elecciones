<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Control de Equipos de Votación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body class="bg-light">

<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0d1b2a;">
        <div class="container-fluid px-4">
            <a class="navbar-brand fw-bold text-warning" href="dashboard.html">
                <i class="fas fa-th-large me-3"></i>
                Panel Administrativo Electoral
            </a>
            <div class="d-flex align-items-center gap-3 text-white font-monospace text-end d-none d-md-block" style="font-size: 0.85rem;">
                <div>Supervisor Electoral</div>
                <div class="text-white-50">DNI: 99999999</div>
            </div>
        </div>
    </nav>
</header>

<main class="main-content py-4">
    <div class="container">
        
        <div class="mb-4">
    <a class="btn btn-outline-primary btn-sm rounded-2 px-3"
       onclick="window.location.href='/dashboard/dashboard.jsp'">
        <i class="fas fa-arrow-left me-2"></i> VOLVER AL DASHBOARD
    </a>
</div>

        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">
            <div>
                <h1 class="h3 mb-1 fw-bold text-dark">Control de Equipos de Votación</h1>
                <p class="text-muted mb-0" style="font-size: 0.9rem;">Gestione los equipos habilitados por MAC Address</p>
            </div>
            <button class="btn btn-primary px-4 fw-medium shadow-sm d-inline-flex align-items-center rounded-3" data-bs-toggle="modal" data-bs-target="#modalEquipo" onclick="prepararModalAgregar()">
                <i class="fas fa-plus me-2"></i> AGREGAR EQUIPO
            </button>
        </div>

        <div class="card border-0 shadow-sm rounded-4 p-4 mb-4 bg-white">
            <div class="d-flex flex-column gap-4" id="contenedor-equipos">
                </div>
        </div>

        <div class="alert alert-info border-0 shadow-sm rounded-3 d-flex align-items-start gap-3 p-3" role="alert" style="background-color: #e0f2fe; border-left: 4px solid #0ea5e9 !important;">
            <div class="text-info fs-5 mt-0"><i class="fas fa-info-circle text-primary"></i></div>
            <div style="font-size: 0.88rem; color: #0369a1; line-height: 1.5;">
                <strong>Información:</strong> Lista de terminales actualmente registradas en la red del sistema electoral. Cada equipo está identificado de manera única por su dirección física de red (MAC Address).
            </div>
        </div>

    </div>
</main>

<div class="modal fade" id="modalEquipo" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 480px;">
        <div class="modal-content border-0 shadow-lg rounded-3 p-4 bg-white">
            
            <div class="modal-header border-0 p-0 mb-4">
                <h4 class="modal-title fw-bold text-dark" id="modalEquipoLabel" style="font-size: 1.3rem;">Agregar Nuevo Equipo de Votación</h4>
            </div>
            
            <div class="modal-body p-0 mb-4">
                <form id="formEquipo">
                    <input type="hidden" id="equipoId">
                    
                    <div class="mb-4">
                        <label class="form-label text-muted small fw-bold">Nombre del Equipo</label>
                        <input type="text" class="form-control px-3 py-2 border rounded" id="nombreMac" placeholder="Ej: PC Mesa 1" style="font-size: 0.95rem;" required>
                    </div>
                    
                    <div class="mb-2">
                        <label class="form-label text-muted small fw-bold">MAC Address</label>
                        <input type="text" class="form-control px-3 py-2 border rounded font-monospace" id="macAddress" placeholder="Ej: 00:1B:44:11:3A:B7" style="font-size: 0.95rem;" required>
                    </div>
                    <small class="text-muted d-block ps-1" style="font-size: 0.75rem;">Identificador único físico del equipo.</small>
                </form>
            </div>
            
            <div class="modal-footer border-0 p-0 d-flex justify-content-end gap-3">
                <button type="button" class="btn btn-link text-decoration-none fw-semibold text-primary px-2" data-bs-dismiss="modal" style="font-size: 0.88rem; letter-spacing: 0.5px;">CANCELAR</button>
                <button type="button" class="btn btn-primary px-4 py-2 fw-semibold border-0 rounded shadow-sm" id="btnGuardarEquipo" style="font-size: 0.88rem; letter-spacing: 0.5px;">AGREGAR EQUIPO</button>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
// Carga desde localStorage o inserta los valores iniciales de tus capturas
let listaEquiposBD = JSON.parse(localStorage.getItem('listaEquipos')) || [
    { id: 1, nombreMac: "PC Mesa 1", macAddress: "00:1B:44:11:3A:B7", votos: 45 },
    { id: 2, nombreMac: "PC Mesa 2", macAddress: "A4:5E:60:E8:2D:91", votos: 38 },
    { id: 3, nombreMac: "PC Mesa 3", macAddress: "3C:22:FB:9A:7B:4E", votos: 0 }
];

let bootstrapModal;

document.addEventListener("DOMContentLoaded", () => {
    bootstrapModal = new bootstrap.Modal(document.getElementById('modalEquipo'));
    renderizarListaEquipos();

    document.getElementById('btnGuardarEquipo').addEventListener('click', guardarEquipo);
});

// Sincroniza el estado actual con el almacenamiento local del navegador
function actualizarAlmacenamientoEquipos() {
    localStorage.setItem('listaEquipos', JSON.stringify(listaEquiposBD));
}

function renderizarListaEquipos() {
    const contenedor = document.getElementById('contenedor-equipos');
    if (!contenedor) return;
    
    contenedor.innerHTML = "";

    if (listaEquiposBD.length === 0) {
        contenedor.innerHTML = `<div class="text-center py-4 text-muted">No hay terminales de votación registradas.</div>`;
        return;
    }

    listaEquiposBD.forEach((equipo, index) => {
        const esUltimo = index === listaEquiposBD.length - 1 ? "" : "border-bottom pb-4";
        
        const filaHtml = `
            <div class="row align-items-center ${esUltimo} g-3">
                <div class="col-12 col-md-8 d-flex align-items-center gap-4">
                    <div class="bg-light rounded-3 d-flex align-items-center justify-content-center text-secondary border shadow-sm" 
                         style="width: 54px; height: 54px; min-width: 54px; font-size: 1.5rem;">
                        <i class="fas fa-desktop"></i>
                    </div>
                    <div>
                        <div class="d-flex align-items-center gap-2">
                            <h5 class="mb-0 fw-bold text-dark" style="font-size: 1.1rem;">${equipo.nombreMac}</h5>
                        </div>
                        <div class="text-muted mt-1" style="font-size: 0.85rem;">
                            <span class="me-3">MAC Address: <code class="px-2 py-0.5 rounded text-dark font-monospace" style="background-color: #f1f5f9;">${equipo.macAddress}</code></span>
                            <span>Votos emitidos en este equipo: <strong class="text-dark">${equipo.votos}</strong></span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-4 d-flex justify-content-md-end justify-content-start align-items-center">
                    <div class="d-flex gap-2">
                        <button class="btn btn-outline-primary btn-sm rounded-2 px-3 fw-medium" onclick="prepararModalEditar(${equipo.id})" title="Editar terminal">
                            <i class="fas fa-edit me-1"></i> Editar
                        </button>
                        <button class="btn btn-outline-danger btn-sm rounded-2 px-3 fw-medium" onclick="eliminarEquipo(${equipo.id})" title="Eliminar terminal">
                            <i class="fas fa-trash-alt me-1"></i> Eliminar
                        </button>
                    </div>
                </div>
            </div>
        `;
        contenedor.insertAdjacentHTML('beforeend', filaHtml);
    });
}

function prepararModalAgregar() {
    document.getElementById('modalEquipoLabel').innerText = "Agregar Nuevo Equipo de Votación";
    
    const btnGuardar = document.getElementById('btnGuardarEquipo');
    btnGuardar.innerText = "AGREGAR EQUIPO";

    document.getElementById('formEquipo').reset();
    document.getElementById('equipoId').value = ""; 
}

function prepararModalEditar(id) {
    const equipo = listaEquiposBD.find(e => e.id === id);
    
    if (equipo) {
        document.getElementById('modalEquipoLabel').innerText = "Modificar Equipo de Votación";
        
        const btnGuardar = document.getElementById('btnGuardarEquipo');
        btnGuardar.innerText = "MODIFICAR EQUIPO";

        document.getElementById('equipoId').value = equipo.id;
        document.getElementById('nombreMac').value = equipo.nombreMac;
        document.getElementById('macAddress').value = equipo.macAddress;
        
        bootstrapModal.show();
    }
}

function guardarEquipo() {
    const id = document.getElementById('equipoId').value;
    const nombreMac = document.getElementById('nombreMac').value.trim();
    const macAddress = document.getElementById('macAddress').value.trim().toUpperCase();

    if (!nombreMac || !macAddress) {
        alert("Por favor, complete todos los campos requeridos.");
        return;
    }

    if (id === "") {
        // Alta de equipo
        listaEquiposBD.push({
            id: Date.now(),
            nombreMac: nombreMac,
            macAddress: macAddress,
            votos: 0
        });
    } else {
        // Modificación de equipo
        const index = listaEquiposBD.findIndex(e => e.id == id);
        if (index !== -1) {
            listaEquiposBD[index].nombreMac = nombreMac;
            listaEquiposBD[index].macAddress = macAddress;
        }
    }

    actualizarAlmacenamientoEquipos();
    bootstrapModal.hide();
    renderizarListaEquipos();
}

function eliminarEquipo(id) {
    if (confirm("¿Está seguro de que desea eliminar este equipo del sistema? Las PCs eliminadas no podrán registrar votos.")) {
        listaEquiposBD = listaEquiposBD.filter(e => e.id !== id);
        actualizarAlmacenamientoEquipos();
        renderizarListaEquipos();
    }
}
</script>
</body>
</html>