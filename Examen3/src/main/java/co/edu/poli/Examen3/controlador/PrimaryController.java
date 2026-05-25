package co.edu.poli.Examen3.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.poli.Examen3.modelo.Examen;
import co.edu.poli.Examen3.modelo.ExamenOrina;
import co.edu.poli.Examen3.servicios.ImplementacionExamen;

public class PrimaryController {

	@FXML
	private TextField txtCodigoExamen;
	@FXML
	private TextField txtNombrePaciente;
	@FXML
	private TextField txtCosto;
	@FXML
	private ComboBox<String> cmbNivelGlucosa;
	@FXML
	private TextField txtPh;
	@FXML
	private TextArea txtAreaMensajes;

	private ImplementacionExamen servicioExamen = new ImplementacionExamen();

	private static final String NOMBRE_ARCHIVO = "examenes.bin";

	@FXML
	public void initialize() {
	}

	@FXML
	private void handleGuardar() {
		try {
			String codigoIdentificacion = txtCodigoExamen.getText().trim();
			String nombrePaciente = txtNombrePaciente.getText().trim();
			String costoTexto = txtCosto.getText().trim();
			String nivelGlucosa = cmbNivelGlucosa.getValue();
			String phTexto = txtPh.getText().trim();

			if (codigoIdentificacion.isEmpty() || nombrePaciente.isEmpty() || costoTexto.isEmpty()
					|| phTexto.isEmpty()) {
				txtAreaMensajes.setText("Todos los campos son obligatorios");
				return;
			}
			if (nivelGlucosa == null) {
				txtAreaMensajes.setText("Debe seleccionar el nivel de glucosa");
				return;
			}

			double costoExamen = Double.parseDouble(costoTexto);
			double valorPh = Double.parseDouble(phTexto);

			ExamenOrina nuevoExamen = new ExamenOrina(codigoIdentificacion, nombrePaciente, "2026-05-25 ", costoExamen,
					nivelGlucosa, valorPh);
			;

			String resultado = servicioExamen.crear(nuevoExamen);
			txtAreaMensajes.setText(resultado);
			limpiarCampos();

		} catch (NumberFormatException errorNumero) {
			txtAreaMensajes.setText("Costo y pH deben ser valores numericos");
		} catch (Exception error) {
			txtAreaMensajes.setText("Error " + error.getMessage());
		}
	}

	@FXML
	private void handleListar() {
		Examen[] todosLosExamenes = servicioExamen.consultarTodos();
		StringBuilder resultado = new StringBuilder();
		int contador = 0;

		for (int i = 0; i < todosLosExamenes.length; i++) {
			if (todosLosExamenes[i] != null) {
				resultado.append(todosLosExamenes[i].toString()).append("\n");
				contador++;
			}
		}

		if (contador == 0) {
			txtAreaMensajes.setText("No hay examenes registrados.");
		} else {
			txtAreaMensajes.setText("Total: " + contador + " examen(es)" + resultado.toString());
		}
	}

	@FXML
	private void handleSerializar() {
		try {
			String resultado = servicioExamen.serializar(NOMBRE_ARCHIVO);
			txtAreaMensajes.setText(resultado);
		} catch (Exception error) {
			txtAreaMensajes.setText("Error al serializar " + error.getMessage());
		}
	}

	@FXML
	private void handleDeserializar() {
		try {
			Examen[] examenesLeidos = servicioExamen.deserializar(NOMBRE_ARCHIVO);
			servicioExamen.setListaExamenes(examenesLeidos);
			txtAreaMensajes.setText("Datos cargados correctamente. Presione Listar para verlos");
		} catch (Exception error) {
			txtAreaMensajes.setText("Error al deserializar " + error.getMessage());
		}
	}

	private void limpiarCampos() {
		txtCodigoExamen.clear();
		txtNombrePaciente.clear();
		txtCosto.clear();
		txtPh.clear();
		cmbNivelGlucosa.setValue(null);
	}
}