<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Candidatos Electoral</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>

<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>

<script src="../gestion-candidatos/AñadirCandidato.js"></script>
    
</head>
<body class="bg-light">

<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid px-4">
            <a class="navbar-brand" href="dashboard.html">
                <i class="fas fa-th-large me-3"></i>
                Panel Administrative Electoral
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
            <a href="dashboard.html" class="btn btn-outline-primary btn-sm rounded-2 px-3">
                <i class="fas fa-arrow-left me-2"></i> VOLVER AL DASHBOARD
            </a>
        </div>

        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">
            <div>
                <h1 class="h3 mb-1 fw-bold text-dark">Gestión de Candidatos</h1>
                <p class="text-muted mb-0" style="font-size: 0.9rem;">Administre los candidatos que aparecerán en la boleta electoral</p>
            </div>
            <button class="btn btn-primary px-4 fw-medium shadow-sm d-inline-flex align-items-center rounded-3" data-bs-toggle="modal" data-bs-target="#modalCandidato" onclick="prepararModalAgregar()">
                <i class="fas fa-plus me-2"></i> AGREGAR CANDIDATO
            </button>
        </div>

        <div class="card border-0 shadow-sm rounded-4 p-4 mb-4 bg-white">
            <div class="d-flex flex-column gap-4" id="contenedor-candidatos">
            </div>
        </div>

    </div>
</main>

<div class="modal fade" id="modalCandidato" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 480px;">
        <div class="modal-content border-0 shadow-lg rounded-3 p-4 bg-white">
            
            <div class="modal-header border-0 p-0 mb-4">
                <h4 class="modal-title fw-bold text-dark" id="modalCandidatoLabel" style="font-size: 1.3rem;">Agregar Nuevo Candidato</h4>
            </div>
            
            <div class="modal-body p-0 mb-4">
                <form id="formCandidato">
                    <input type="hidden" id="candidatoId">
                    
                    <div class="mb-4">
                        <input type="text" class="form-control px-3 py-2.5 border rounded" id="nombreCompleto" placeholder="Nombre del Candidato" style="font-size: 0.95rem;" required>
                    </div>
                    
                    <div class="mb-4">
                        <input type="text" class="form-control px-3 py-2.5 border rounded" id="partido" placeholder="Partido Político" style="font-size: 0.95rem;" required>
                    </div>

                    <div class="mb-1 d-flex align-items-center gap-3 p-2 border rounded bg-light-subtle">
                        <label for="colorPartido" class="form-label mb-0 text-muted ps-1" style="font-size: 0.9rem;">Color identificador:</label>
                        <input type="color" class="form-control form-control-color border-0 rounded-circle" id="colorPartido" value="#2563eb" style="width: 38px; height: 38px; cursor: pointer;">
                    </div>
                    <small class="text-muted d-block ps-1" style="font-size: 0.75rem;">Color asignado para gráficos estadísticos y boleta</small>
                </form>
            </div>
            
            <div class="modal-footer border-0 p-0 d-flex justify-content-end gap-3">
                <button type="button" class="btn btn-link text-decoration-none fw-semibold text-primary px-2" data-bs-dismiss="modal" style="font-size: 0.88rem; letter-spacing: 0.5px;">CANCELAR</button>
                <button type="button" class="btn px-4 py-2 fw-semibold border-0 text-muted rounded shadow-sm" id="btnGuardarCandidato" style="background-color: #e0e0e0; font-size: 0.88rem; letter-spacing: 0.5px;">AGREGAR CANDIDATO</button>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
// Intenta recuperar los datos del localStorage; si no existen, inicializa la lista por defecto
let listaCandidatosBD = JSON.parse(localStorage.getItem('listaCandidatos')) || [
    { id: 1, nombreCompleto: "María Rodríguez", partido: "Partido Progresista", colorPartido: "#2563eb", votos: 203 },
    { id: 2, nombreCompleto: "Carlos Fernández", partido: "Unión Nacional", colorPartido: "#dc2626", votos: 187 },
    { id: 3, nombreCompleto: "Ana Martínez", partido: "Frente del Pueblo", colorPartido: "#16a34a", votos: 133 }
];

let bootstrapModal;

document.addEventListener("DOMContentLoaded", () => {
    bootstrapModal = new bootstrap.Modal(document.getElementById('modalCandidato'));
    renderizarListaCandidatos();

    document.getElementById('btnGuardarCandidato').addEventListener('click', guardarCandidato);
});

// Función auxiliar para actualizar el almacenamiento local del navegador
function actualizarAlmacenamiento() {
    localStorage.setItem('listaCandidatos', JSON.stringify(listaCandidatosBD));
}

function renderizarListaCandidatos() {
    const contenedor = document.getElementById('contenedor-candidatos');
    if (!contenedor) return;
    
    contenedor.innerHTML = "";

    if (listaCandidatosBD.length === 0) {
        contenedor.innerHTML = `<div class="text-center py-4 text-muted">No hay candidatos registrados en la boleta electoral.</div>`;
        return;
    }

    listaCandidatosBD.forEach((candidato, index) => {
        const esUltimo = index === listaCandidatosBD.length - 1 ? "" : "border-bottom pb-4";
        
        const filaHtml = `
            <div class="row align-items-center ${esUltimo} g-3">
                <div class="col-12 col-md-9 d-flex align-items-center gap-4">
                    <div class="rounded-circle d-flex align-items-center justify-content-center text-white fw-bold shadow-sm" 
                         style="background-color: ${candidato.colorPartido}; width: 48px; height: 48px; min-width: 48px; font-size: 1.2rem;">
                        ${candidato.nombreCompleto.charAt(0)}
                    </div>
                    <div>
                        <h5 class="mb-0 fw-bold text-dark" style="font-size: 1.1rem;">#${index + 1} ${candidato.nombreCompleto}</h5>
                        <div class="text-muted mt-1" style="font-size: 0.85rem;">
                            <span class="me-3">Partido: <strong class="text-dark">${candidato.partido}</strong></span>
                            <span class="me-3">Color: <code class="px-2 py-0.5 rounded text-dark" style="background-color: #f1f5f9;">${candidato.colorPartido}</code></span>
                            <span>Votos: <strong class="text-dark">${candidato.votos}</strong></span>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-3 d-flex justify-content-md-end justify-content-start align-items-center">
                    <div class="d-flex gap-2">
                        <button class="btn btn-light btn-sm text-primary rounded-2 px-3 fw-medium border" onclick="prepararModalEditar(${candidato.id})" title="Editar Candidato">
                            <i class="fas fa-edit me-1"></i> Editar
                        </button>
                        <button class="btn btn-light btn-sm text-danger rounded-2 px-3 fw-medium border" onclick="eliminarCandidato(${candidato.id})" title="Eliminar Candidato">
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
    document.getElementById('modalCandidatoLabel').innerText = "Agregar Nuevo Candidato";
    
    const btnGuardar = document.getElementById('btnGuardarCandidato');
    btnGuardar.innerText = "AGREGAR CANDIDATO";
    btnGuardar.className = "btn px-4 py-2 fw-semibold border-0 text-muted rounded shadow-sm";
    btnGuardar.style.backgroundColor = "#e0e0e0"; 
    btnGuardar.style.color = "";

    document.getElementById('formCandidato').reset();
    document.getElementById('candidatoId').value = ""; 
}

function prepararModalEditar(id) {
    const candidato = listaCandidatosBD.find(c => c.id === id);
    
    if (candidato) {
        document.getElementById('modalCandidatoLabel').innerText = "Modificar Candidato";
        
        const btnGuardar = document.getElementById('btnGuardarCandidato');
        btnGuardar.innerText = "MODIFICAR CANDIDATO";
        btnGuardar.className = "btn px-4 py-2 fw-semibold border-0 text-white rounded shadow-sm btn-primary";
        btnGuardar.style.backgroundColor = ""; 

        document.getElementById('candidatoId').value = candidato.id;
        document.getElementById('nombreCompleto').value = candidato.nombreCompleto;
        document.getElementById('partido').value = candidato.partido;
        document.getElementById('colorPartido').value = candidato.colorPartido;
        
        bootstrapModal.show();
    }
}

function guardarCandidato() {
    const id = document.getElementById('candidatoId').value;
    const nombreCompleto = document.getElementById('nombreCompleto').value.trim();
    const partido = document.getElementById('partido').value.trim();
    const colorPartido = document.getElementById('colorPartido').value;

    if (!nombreCompleto || !partido) {
        alert("Por favor, complete todos los campos de información.");
        return;
    }

    if (id === "") {
        listaCandidatosBD.push({
            id: Date.now(),
            nombreCompleto: nombreCompleto,
            partido: partido,
            colorPartido: colorPartido,
            votos: 0
        });
    } else {
        const index = listaCandidatosBD.findIndex(c => c.id == id);
        if (index !== -1) {
            listaCandidatosBD[index].nombreCompleto = nombreCompleto;
            listaCandidatosBD[index].partido = partido;
            listaCandidatosBD[index].colorPartido = colorPartido;
        }
    }

    actualizarAlmacenamiento(); // Guarda el estado actual de la lista
    bootstrapModal.hide();
    renderizarListaCandidatos();
}

function eliminarCandidato(id) {
    if (confirm("¿Está seguro de que desea eliminar este candidato de la boleta electoral?")) {
        listaCandidatosBD = listaCandidatosBD.filter(c => c.id !== id);
        actualizarAlmacenamiento(); // Sincroniza la eliminación en el disco
        renderizarListaCandidatos();
    }
}
</script>
</body>
</html>