package prueba;

import datos.PersonaDAO;
import dominio.Persona;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin Jaldin
 */
public class ManejoPersonas {

	public static void main(String[] args) {

		//CON ESTA INSTANCIA DE LA CLASE 'PersonaDAO' PODREMOS ACCEDER A LOS METODOS DE ACCESO A DATOS.
		PersonaDAO personaDao = new PersonaDAO();

		//INSERTAMOS UN NUEVO OBJETO DE TIPO PERSONA (INSERT)
		//insertarPersona(personaDao);
		
		//ACTUALIZAMOS UN REGISTRO (UPDATE)
		//actualizarPersona(personaDao);

		//ELIMINAMOS UN REGISTRO (DELETE)
		//eliminarPersona(personaDao);
		
		//LISTAMOS LOS OBJETOS PERSONA DE LA BD.(SELECT)
		imprimirPersonas(personaDao);
	}

	public static void imprimirPersonas(PersonaDAO personaDao) {
		List<Persona> personas = personaDao.listaPersonas();
		String res = new String();
		for (Persona persona : personas) {
			res += persona;
		}
		JOptionPane.showMessageDialog(null, res, "PERSONAS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void insertarPersona(PersonaDAO personaDao) {
		int registros = 0;
		Persona personaNueva = new Persona("Ander", "Garcia", "ande@hotmal.com", "665151707");
		registros = personaDao.insertar(personaNueva);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS INSERTADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void actualizarPersona(PersonaDAO personaDao) {
		int registros = 0;
		Persona persona = new Persona(8, "Ander", "Soto", "ande@hotmal.com", "665151000");
		registros = personaDao.actualizar(persona);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ACTUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void eliminarPersona(PersonaDAO personaDao) {
		int registros = 0;
		Persona persona = new Persona(10);
		registros = personaDao.eliminar(persona);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ELIMINADOS", JOptionPane.INFORMATION_MESSAGE);
	}
}
