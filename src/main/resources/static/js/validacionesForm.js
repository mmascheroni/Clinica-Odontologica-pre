function validarInputMayorACero(input) {
    if (input.length > 0) {
        return input;
    }
}

function validarInputMayorAOcho(input) {
    if (input.length > 8) {
        return input;
    }
}

function validarInputMayorACincuenta(input) {
    if (input.length > 50) {
        return input;
    }
}

function validarInputSoloLetras(input) {
    const soloLetras = /^[a-zA-Z]+$/;

    if (soloLetras.test(input)) {
        return input;
    }
}

function validarNumeros(input) {
    var patron = /^[0-9]+$/;
    if (patron.test(input)) {
        return input;
    }
}

function validarFecha(fecha) {
    let fechaSeleccionada = new Date(fecha);

    fechaSeleccionada.setDate(fechaSeleccionada.getDate() + 1);

    let fechaActual = new Date();

    // console.log('Fecha seleccionada: ' + fechaSeleccionada);

    // console.log('Fecha Actual: ' + fechaActual);
    // Comparar las fechas
    if (fechaActual.getDay() <= fechaSeleccionada.getDay()) {
        return fechaSeleccionada;
    }
}

function validarFechaYHora(fechaHora) {
    const fechaYHoraFormateada = fechaHora.replace('T', ' ');

    let fechaSeleccionada = new Date(fechaYHoraFormateada);

    let fechaActual = new Date();

    // console.log('Fecha Seleccionada: ' + fechaSeleccionada);

    // console.log('Fecha Actual: ' + fechaActual);

    // Comparar las fechas
    if (fechaActual <= fechaSeleccionada) {
        return fechaSeleccionada;
    }
}

function normalizarTexto(texto) {
    return texto.trim();
}
