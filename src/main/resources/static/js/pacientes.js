const baseUrlPaciente = 'http://localhost:8082/pacientes';

document.addEventListener('DOMContentLoaded', () => {
    listarTodos();
});

btnRefreshTablaPaciente.addEventListener('click', () => {
    listarTodos();
});

btnAgregarPaciente.addEventListener('click', () => {
    sectionFormModificarPaciente.style.display = 'none';
    sectionFormAgregarPaciente.style.display = 'block';
});

btnFormAgregarPaciente.addEventListener('click', (e) => {
    e.preventDefault();

    divErrorAgregarPaciente.innerHTML = '';
    divErrorAgregarPaciente.style.display = 'block';

    const settings = {
        nombre: '',
        apellido: '',
        dni: '',
        fecha: '',
        calle: '',
        numero: '',
        localidad: '',
        provincia: '',
    };

    // Nombre

    if (
        validarInputMayorACero(inputAgregarNombre.value) &&
        !validarInputMayorACincuenta(inputAgregarNombre.value) &&
        validarInputSoloLetras(inputAgregarNombre.value)
    ) {
        settings.nombre = normalizarTexto(inputAgregarNombre.value);
    } else {
        if (!validarInputMayorACero(inputAgregarNombre.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarNombre.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarInputSoloLetras(inputAgregarNombre.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede contener numeros o simbolos</p>
            `;
        }
    }

    // Apellido
    if (
        validarInputMayorACero(inputAgregarApellido.value) &&
        !validarInputMayorACincuenta(inputAgregarApellido.value) &&
        validarInputSoloLetras(inputAgregarApellido.value)
    ) {
        settings.apellido = normalizarTexto(inputAgregarApellido.value);
    } else {
        if (!validarInputMayorACero(inputAgregarApellido.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarApellido.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarInputSoloLetras(inputAgregarApellido.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede contener numeros o simbolos</p>
            `;
        }
    }

    // dni

    // DNI
    if (
        validarInputMayorACero(inputAgregarDni.value) &&
        !validarInputMayorAOcho(inputAgregarDni.value) &&
        !validarInputMayorACincuenta(inputAgregarDni.value) &&
        validarNumeros(inputAgregarDni.value)
    ) {
        settings.dni = normalizarTexto(inputAgregarDni.value);
    } else {
        if (!validarInputMayorACero(inputAgregarDni.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede quedar vacio</p>
            `;
        } else if (validarInputMayorAOcho(inputAgregarDni.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede tener mas de 8 caracteres</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarDni.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarNumeros(inputAgregarDni.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede contener letras</p>
            `;
        }
    }

    // Fecha de Ingreso
    if (
        validarInputMayorACero(inputAgregarFechaIngreso.value) &&
        validarFecha(inputAgregarFechaIngreso.value)
    ) {
        settings.fecha = inputAgregarFechaIngreso.value;
    } else {
        if (!validarInputMayorACero(inputAgregarFechaIngreso.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La Fecha no puede quedar vacia</p>
            `;
        } else if (!validarFecha(inputAgregarFechaIngreso.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La fecha no puede ser menor a la actual</p>
            `;
        }
    }

    // DOMICILIO

    // CALLE

    if (
        validarInputMayorACero(inputAgregarCalle.value) &&
        !validarInputMayorACincuenta(inputAgregarCalle.value)
    ) {
        settings.calle = normalizarTexto(inputAgregarCalle.value);
    } else {
        if (!validarInputMayorACero(inputAgregarCalle.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La calle no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarCalle.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La calle no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // NUMERO

    if (
        validarInputMayorACero(inputAgregarNumero.value) &&
        !validarInputMayorACincuenta(inputAgregarNumero.value)
    ) {
        settings.numero = normalizarTexto(inputAgregarNumero.value);
    } else {
        if (!validarInputMayorACero(inputAgregarNumero.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El Numero no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarNumero.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ El Numero no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // Localidad

    if (
        validarInputMayorACero(inputAgregarLocalidad.value) &&
        !validarInputMayorACincuenta(inputAgregarLocalidad.value)
    ) {
        settings.localidad = normalizarTexto(inputAgregarLocalidad.value);
    } else {
        if (!validarInputMayorACero(inputAgregarLocalidad.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La Localidad no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarLocalidad.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La Localidad no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // Provincia
    if (
        validarInputMayorACero(inputAgregarProvincia.value) &&
        !validarInputMayorACincuenta(inputAgregarProvincia.value)
    ) {
        settings.provincia = normalizarTexto(inputAgregarProvincia.value);
    } else {
        if (!validarInputMayorACero(inputAgregarProvincia.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La Provincia no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputAgregarProvincia.value)) {
            divErrorAgregarPaciente.innerHTML += `
                <p class="error">⚠️ La Provincia no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    if (
        settings.nombre.length > 1 &&
        settings.apellido.length > 1 &&
        settings.dni.length > 1 &&
        settings.calle.length > 1 &&
        settings.fecha.length > 1 &&
        settings.numero.length > 1 &&
        settings.localidad.length > 1 &&
        settings.provincia.length > 1
    ) {
        divErrorAgregarPaciente.style.display = 'none';
        agregarPaciente();
        formularioAgregarPaciente.reset();
    }
});

btnSearchPacientes.addEventListener('click', (e) => {
    e.preventDefault();
    listarPacienteByCriterio(inputSearchPacientes.value);
});

function renderizarPacientesATabla(pacientes, selectorTabla) {
    selectorTabla.innerHTML = '';

    pacientes.forEach((paciente) => {
        const fila = selectorTabla.insertRow();
        const celdaId = fila.insertCell();
        const celdaNombre = fila.insertCell();
        const celdaApellido = fila.insertCell();
        const celdaDni = fila.insertCell();
        const celdaFechaDeAlta = fila.insertCell();
        const celdaDomicilioId = fila.insertCell();
        const celdaCalle = fila.insertCell();
        const celdaNumero = fila.insertCell();
        const celdaLocalidad = fila.insertCell();
        const celdaProvincia = fila.insertCell();
        const botones = fila.insertCell();

        celdaId.innerHTML = `<button id='${paciente.id}' class='btn-table btn-id'>${paciente.id}</button>`;
        celdaNombre.innerHTML = `${paciente.nombre}`;
        celdaApellido.innerHTML = `${paciente.apellido}`;
        celdaDni.innerHTML = `${paciente.dni}`;
        celdaFechaDeAlta.innerHTML = `${paciente.fechaDeAlta}`;
        celdaDomicilioId.innerHTML = `${paciente.domicilio.id}`;
        celdaCalle.innerHTML = `${paciente.domicilio.calle}`;
        celdaNumero.innerHTML = `${paciente.domicilio.numero}`;
        celdaLocalidad.innerHTML = `${paciente.domicilio.localidad}`;
        celdaProvincia.innerHTML = `${paciente.domicilio.provincia}`;
        botones.innerHTML = `<button id='${paciente.id}' class="btn-table btn-modificar" onclick='cargarFormModificarPaciente(this.id)'>
                            <a href='#modificar-paciente'>Modificar</a>
                            </button>
                            <button id='${paciente.id}' class="btn-table btn-eliminar" onclick='eliminarPacienteById(this.id)'>
                                Eliminar
                            </button>
                            `;
    });
}

function renderizarPacienteATabla(paciente, selectorTabla) {
    const fila = selectorTabla.insertRow();
    const celdaId = fila.insertCell();
    const celdaNombre = fila.insertCell();
    const celdaApellido = fila.insertCell();
    const celdaDni = fila.insertCell();
    const celdaFechaDeAlta = fila.insertCell();
    const celdaDomicilioId = fila.insertCell();
    const celdaCalle = fila.insertCell();
    const celdaNumero = fila.insertCell();
    const celdaLocalidad = fila.insertCell();
    const celdaProvincia = fila.insertCell();
    const botones = fila.insertCell();

    celdaId.innerHTML = `<button id='${paciente.id}' class='btn-table btn-id'>${paciente.id}</button>`;
    celdaNombre.innerHTML = `${paciente.nombre}`;
    celdaApellido.innerHTML = `${paciente.apellido}`;
    celdaDni.innerHTML = `${paciente.dni}`;
    celdaFechaDeAlta.innerHTML = `${paciente.fechaDeAlta}`;
    celdaDomicilioId.innerHTML = `${paciente.domicilio.id}`;
    celdaCalle.innerHTML = `${paciente.domicilio.calle}`;
    celdaNumero.innerHTML = `${paciente.domicilio.numero}`;
    celdaLocalidad.innerHTML = `${paciente.domicilio.localidad}`;
    celdaProvincia.innerHTML = `${paciente.domicilio.provincia}`;
    botones.innerHTML = `<button id='${paciente.id}' class="btn-table btn-modificar" onclick='cargarFormModificarPaciente(this.id)'>
                            <a href='#modificar-paciente'>Modificar</a>
                            </button>
                            <button id='${paciente.id}' class="btn-table btn-eliminar" onclick='eliminarPacienteById(this.id)'>
                                Eliminar
                            </button>
                            `;
}

// GET Paciente
const listarPacienteById = async (id) => {
    const res = await fetch(baseUrlPaciente + `/${id}`);

    const paciente = await res.json();

    return paciente;
};

// GET Paciente By Criterio
async function listarPacienteByCriterio(criterio) {
    const res = await fetch(
        `${baseUrlPaciente}/paciente?criterio=` +
            encodeURIComponent(criterio.toLowerCase())
    );

    const pacientes = await res.json();

    if (pacientes.length > 0) {
        renderizarPacientesATabla(pacientes, tbodyTablaPacientes);
    } else if (pacientes.length == 1) {
        renderizarPacienteATabla(pacientes, tbodyTablaPacientes);
    } else {
        console.log('No se encontraron resultados');
        listarTodos();
    }
}

// GET Todos los Paciente

async function listarTodos() {
    const res = await fetch(baseUrlPaciente);

    const pacientes = await res.json();

    renderizarPacientesATabla(pacientes, tbodyTablaPacientes);
}

// DELETE Paciente by ID
async function eliminarPacienteById(id) {
    await fetch(`${baseUrlPaciente}/eliminar/${id}`, {
        method: 'DELETE',
    });

    listarTodos();
}

// POST Paciente
async function agregarPaciente() {
    const payload = {
        nombre: inputAgregarNombre.value,
        apellido: inputAgregarApellido.value,
        dni: inputAgregarDni.value,
        fechaDeAlta: inputAgregarFechaIngreso.value,
        domicilio: {
            calle: inputAgregarCalle.value,
            numero: inputAgregarNumero.value,
            localidad: inputAgregarLocalidad.value,
            provincia: inputAgregarProvincia.value,
        },
    };

    const res = await fetch(`${baseUrlPaciente}/registrar`, {
        method: 'POST',
        body: JSON.stringify(payload),
        headers: {
            'Content-Type': 'application/json',
        },
    });

    const paciente = await res.json();

    renderizarPacienteATabla(paciente, tbodyTablaPacientes);
}

async function cargarFormModificarPaciente(id) {
    sectionFormModificarPaciente.style.display = 'block';
    sectionFormAgregarPaciente.style.display = 'none';

    const paciente = await listarPacienteById(id);
    inputId.value = paciente.id;
    inputModificarNombre.value = paciente.nombre;
    inputModificarApellido.value = paciente.apellido;
    inputModificarDni.value = paciente.dni;
    inputModificarFechaIngreso.value = paciente.fechaDeAlta;
    inputModificarDomicilioId.value = paciente.domicilio.id;
    inputModificarCalle.value = paciente.domicilio.calle;
    inputModificarNumero.value = paciente.domicilio.numero;
    inputModificarLocalidad.value = paciente.domicilio.localidad;
    inputModificarProvincia.value = paciente.domicilio.provincia;
}

// PUT Modificar Paciente
async function modificarPaciente() {
    const payload = {
        id: inputId.value,
        nombre: inputModificarNombre.value,
        apellido: inputModificarApellido.value,
        dni: inputModificarDni.value,
        fechaDeAlta: inputModificarFechaIngreso.value,
        domicilio: {
            id: inputModificarDomicilioId.value,
            calle: inputModificarCalle.value,
            numero: inputModificarNumero.value,
            localidad: inputModificarLocalidad.value,
            provincia: inputModificarProvincia.value,
        },
    };

    console.log(payload);

    const res = await fetch(`${baseUrlPaciente}/actualizar`, {
        method: 'PUT',
        body: JSON.stringify(payload),
        headers: {
            'Content-Type': 'application/json',
        },
    });

    res.json();

    listarTodos();
}

btnFormModificarPaciente.addEventListener('click', (e) => {
    e.preventDefault();

    divErrorModificarPaciente.innerHTML = '';
    divErrorModificarPaciente.style.display = 'block';

    const settings = {
        nombre: '',
        apellido: '',
        dni: '',
        fecha: '',
        calle: '',
        numero: '',
        localidad: '',
        provincia: '',
    };

    // Nombre

    if (
        validarInputMayorACero(inputModificarNombre.value) &&
        !validarInputMayorACincuenta(inputModificarNombre.value) &&
        validarInputSoloLetras(inputModificarNombre.value)
    ) {
        settings.nombre = normalizarTexto(inputModificarNombre.value);
    } else {
        if (!validarInputMayorACero(inputModificarNombre.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarNombre.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarInputSoloLetras(inputModificarNombre.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El nombre no puede contener numeros o simbolos</p>
            `;
        }
    }

    // Apellido
    if (
        validarInputMayorACero(inputModificarApellido.value) &&
        !validarInputMayorACincuenta(inputModificarApellido.value) &&
        validarInputSoloLetras(inputModificarApellido.value)
    ) {
        settings.apellido = normalizarTexto(inputModificarApellido.value);
    } else {
        if (!validarInputMayorACero(inputModificarApellido.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarApellido.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarInputSoloLetras(inputModificarApellido.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El Apellido no puede contener numeros o simbolos</p>
            `;
        }
    }

    // DNI
    if (
        validarInputMayorACero(inputModificarDni.value) &&
        !validarInputMayorAOcho(inputModificarDni.value) &&
        !validarInputMayorACincuenta(inputModificarDni.value) &&
        validarNumeros(inputModificarDni.value)
    ) {
        settings.dni = normalizarTexto(inputModificarDni.value);
    } else {
        if (!validarInputMayorACero(inputModificarDni.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede quedar vacio</p>
            `;
        } else if (validarInputMayorAOcho(inputModificarDni.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede tener mas de 8 caracteres</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarDni.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede tener mas de 50 caracteres</p>
            `;
        } else if (!validarNumeros(inputModificarDni.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El dni no puede contener letras</p>
            `;
        }
    }

    // Fecha de Ingreso
    if (
        validarInputMayorACero(inputModificarFechaIngreso.value) &&
        validarFecha(inputModificarFechaIngreso.value)
    ) {
        settings.fecha = inputModificarFechaIngreso.value;
    } else {
        if (!validarInputMayorACero(inputModificarFechaIngreso.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La Fecha no puede quedar vacia</p>
            `;
        } else if (!validarFecha(inputModificarFechaIngreso.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La fecha no puede ser menor a la actual</p>
            `;
        }
    }

    // DOMICILIO

    // CALLE

    if (
        validarInputMayorACero(inputModificarCalle.value) &&
        !validarInputMayorACincuenta(inputModificarCalle.value)
    ) {
        settings.calle = normalizarTexto(inputModificarCalle.value);
    } else {
        if (!validarInputMayorACero(inputModificarCalle.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La calle no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarCalle.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La calle no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // NUMERO

    if (
        validarInputMayorACero(inputModificarNumero.value) &&
        !validarInputMayorACincuenta(inputModificarNumero.value)
    ) {
        settings.numero = normalizarTexto(inputModificarNumero.value);
    } else {
        if (!validarInputMayorACero(inputModificarNumero.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El Numero no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarNumero.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ El Numero no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // Localidad

    if (
        validarInputMayorACero(inputModificarLocalidad.value) &&
        !validarInputMayorACincuenta(inputModificarLocalidad.value)
    ) {
        settings.localidad = normalizarTexto(inputModificarLocalidad.value);
    } else {
        if (!validarInputMayorACero(inputModificarLocalidad.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La Localidad no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarLocalidad.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La Localidad no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    // Provincia
    if (
        validarInputMayorACero(inputModificarProvincia.value) &&
        !validarInputMayorACincuenta(inputModificarProvincia.value)
    ) {
        settings.provincia = normalizarTexto(inputModificarProvincia.value);
    } else {
        if (!validarInputMayorACero(inputModificarProvincia.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La Provincia no puede quedar vacio</p>
            `;
        } else if (validarInputMayorACincuenta(inputModificarProvincia.value)) {
            divErrorModificarPaciente.innerHTML += `
                <p class="error">⚠️ La Provincia no puede tener mas de 50 caracteres</p>
            `;
        }
    }

    if (
        settings.nombre.length > 1 &&
        settings.apellido.length > 1 &&
        settings.dni.length > 1 &&
        settings.calle.length > 1 &&
        settings.fecha.length > 1 &&
        settings.numero.length > 1 &&
        settings.localidad.length > 1 &&
        settings.provincia.length > 1
    ) {
        divErrorModificarPaciente.style.display = 'none';
        modificarPaciente();
        formularioModificarPaciente.reset();
    }
});
