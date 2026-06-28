// ── Contador regresivo ──────────────────────────────────────────────
const INTERVALO_SEG = 30;
let segundosRestantes = INTERVALO_SEG;
 
function actualizarContador() {
    document.getElementById('countdownText').textContent =
        `Próxima actualización en ${segundosRestantes}s`;
    segundosRestantes--;
    if (segundosRestantes < 0) segundosRestantes = INTERVALO_SEG;
}
setInterval(actualizarContador, 1000);
 
// ── Helper: animar número ───────────────────────────────────────────
function animarNumero(elemento, valorFinal, sufijo = '') {
    const duracion = 800;
    const inicio   = Date.now();
    const esFloat  = valorFinal % 1 !== 0;
 
    function tick() {
        const progreso = Math.min((Date.now() - inicio) / duracion, 1);
        const ease     = 1 - Math.pow(1 - progreso, 3);
        const actual   = esFloat
            ? (valorFinal * ease).toFixed(1)
            : Math.round(valorFinal * ease);
        elemento.textContent = actual + sufijo;
        if (progreso < 1) requestAnimationFrame(tick);
    }
    requestAnimationFrame(tick);
}
 
// ── Revelar elemento con fade (sin duplicar la animación en refresh) ─
function revelar(el) {
    el.style.display = 'block';
    el.classList.remove('fade-in');
    void el.offsetWidth; // fuerza reflow para reiniciar la animación
    el.classList.add('fade-in');
}
 
// ── Ocultar skeleton y mostrar contenido real ───────────────────────
function reemplazar(idSkeleton, idContenido) {
    const sk = document.getElementById(idSkeleton);
    const ct = document.getElementById(idContenido);
    if (sk) {
        sk.style.display = 'none';
        sk.style.visibility = 'hidden'; // doble seguro
    }
    if (ct) {
        ct.style.visibility = 'visible';
        revelar(ct);
    }
}
 
// ══════════════════════════════════════════════════════════════════════
// CARGA 1: Métricas generales → GET /dashboard-stats
// Responde: { totalPadron, habilitados, votosEmitidos,
//             deshabilitados, equiposActivos, participacion }
// ══════════════════════════════════════════════════════════════════════
async function cargarMetricas() {
    try {
        const res = await fetch(`${contextPath}/dashboard-stats`, {
            headers: { 'Accept': 'application/json' }
        });
        if (!res.ok) throw new Error('HTTP ' + res.status);
        const d = await res.json();
 
        // FIX: participación nunca supera 100%
        const participacion = Math.min(d.participacion, 100.0);
 
        const campos = [
            { id: 'totalPadron',    val: d.totalPadron,    suf: '' },
            { id: 'habilitados',    val: d.habilitados,    suf: '' },
            { id: 'votosEmitidos',  val: d.votosEmitidos,  suf: '' },
            { id: 'deshabilitados', val: d.deshabilitados, suf: '' },
            { id: 'equiposActivos', val: d.equiposActivos, suf: '' },
            { id: 'participacion',  val: participacion,    suf: '%' }
        ];
 
        campos.forEach(({ id, val, suf }) => {
            const el = document.getElementById(id);
            el.innerHTML = '';
            animarNumero(el, val, suf);
        });
 
    } catch (err) {
        console.error('Error métricas:', err);
        ['totalPadron','habilitados','votosEmitidos',
         'deshabilitados','equiposActivos','participacion']
            .forEach(id => {
                document.getElementById(id).innerHTML =
                    '<span style="color:#ef4444;font-size:.8rem">Error</span>';
            });
    }
}
 
// ══════════════════════════════════════════════════════════════════════
// CARGA 2: Resultados por candidato + ganador → GET /dashboard-resultados
// Responde: [{ nombre_completo, partido, votos, color_partido }, ...]
// ══════════════════════════════════════════════════════════════════════
async function cargarResultados() {
    try {
        const res = await fetch(`${contextPath}/dashboard-resultados`, {
            headers: { 'Accept': 'application/json' }
        });
        if (!res.ok) throw new Error('HTTP ' + res.status);
        const candidatos = await res.json();
 
        const total = candidatos.reduce((sum, c) => sum + c.votos, 0);
 
        // ── Gráfico de torta ────────────────────────────────────────
        let acum = 0;
        const gradientPartes = candidatos.map(c => {
            const pct  = (c.votos * 100) / total;
            const parte = `${c.color_partido} ${acum.toFixed(2)}% ${(acum + pct).toFixed(2)}%`;
            acum += pct;
            return parte;
        });
        document.getElementById('graficoCircular').style.background =
            `conic-gradient(${gradientPartes.join(',')})`;
 
        // ── Lista de candidatos ─────────────────────────────────────
        let htmlLista = '';
        candidatos.forEach(c => {
            const pct = ((c.votos * 100) / total).toFixed(1);
            htmlLista += `
            <div class="resultado-item">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <span class="color" style="background:${c.color_partido}"></span>
                        ${c.nombre_completo}
                    </div>
                    <strong>${pct}%</strong>
                </div>
            </div>`;
        });
        document.getElementById('listaResultados').innerHTML = htmlLista;
 
        // aca reemplazamos oculta el skeleton gris y muestra el gráfico real para que se vean lo candidatos
        reemplazar('skeletonGrafico', 'contenidoGrafico');
        document.getElementById('skeletonLista').style.display = 'none';
 
        // ── Ganador ─────────────────────────────────────────────────
        const ordenados  = [...candidatos].sort((a, b) => b.votos - a.votos);
        const ganador    = ordenados[0];
        const segundo    = ordenados[1];
        const pctGanador = ((ganador.votos * 100) / total).toFixed(1);
        const ventaja    = ganador.votos - (segundo ? segundo.votos : 0);
 
        document.getElementById('ganadorNombre').textContent     = ganador.nombre_completo;
        document.getElementById('ganadorPartido').textContent    = ganador.partido;
        document.getElementById('ganadorVotos').textContent      = ganador.votos;
        document.getElementById('ganadorPorcentaje').textContent = pctGanador + '%';
        document.getElementById('textoVentaja').textContent      = segundo
            ? `Ventaja: ${ventaja} voto${ventaja !== 1 ? 's' : ''} sobre el segundo lugar`
            : 'Único candidato con votos';
 
        reemplazar('skeletonGanador', 'contenidoGanador');
 
    } catch (err) {
        console.error('Error resultados:', err);
        document.getElementById('skeletonGrafico').innerHTML =
            '<p class="text-danger text-center mt-3">Error al cargar resultados</p>';
        document.getElementById('skeletonLista').style.display = 'none';
    }
}
 
// ── Carga inicial y auto-refresh cada 30 segundos ──────────────────
function cargarTodo() {
    cargarMetricas();
    cargarResultados();
}
 
window.addEventListener('DOMContentLoaded', cargarTodo);
setInterval(cargarTodo, INTERVALO_SEG * 1000);