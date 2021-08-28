package prueba;

import datos.PersonaDAO;
import datos.UsuarioDAO;
import dominio.Persona;
import dominio.Usuario;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin Jaldin
 */
public class ManejoPersonas {

	public static void main(String[] args) {

		//CON ESTAS INSTANCIAS PODREMOS ACCEDER A LOS RESPECTIVOS METODOS DE ACCESO A DATOS.
		PersonaDAO personaDao = new PersonaDAO();
		UsuarioDAO usuarioDao = new UsuarioDAO();

	int opc = 0;
		String menu1 = "1. Gestion Personal\n"
				+ "2. Gestion de Usuarios\n"
				+ "3. Salir\n"
				+ "Elija una opcion:";
		boolean salir = false;

		try {
			while (salir != true) {
				opc = Integer.valueOf(JOptionPane.showInputDialog(null, menu1, "MENU PRINCIPAL", JOptionPane.QUESTION_MESSAGE));
				switch (opc) {
					case 1://GESTION DE PERSONAS
						gestionPersonal(personaDao);
						break;
					case 2://GESTION DE USUARIOS
						gestionUsuarios(usuarioDao);
						break;
					case 3://SALIR DEL MENU PRINCIPAL
						JOptionPane.showMessageDialog(null, "Hata pronto!!");
						salir = true;
						break;
					default://EN CASO DE ELEGIR OTRA OPCION
						JOptionPane.showMessageDialog(null, "Opcion incorrecta!!", "Error", JOptionPane.WARNING_MESSAGE);
						break;
				}
			}
		} catch (HeadlessException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar opcion", "Error de entrada", JOptionPane.WARNING_MESSAGE);
		}

	}

	public static void gestionPersonal(PersonaDAO personaDao) {
		int opc = 0;
		boolean salir = false;
		String menu = "1. Listar Personas\n"
				+ "2. Insertar Persona\n"
				+ "3. Actualizar Persona\n"
				+ "4. Eliminar Persona\n"
				+ "5. Volver";
		try {

			while (salir != true) {
				opc = Integer.valueOf(JOptionPane.showInputDialog(null, menu, JOptionPane.QUESTION_MESSAGE));
				switch (opc) {
					case 1://LISTAMOS LOS OBJETOS PERSONA DE LA BD.(SELECT)
						imprimirPersonas(personaDao);
						break;
					case 2://INSERTAMOS UN NUEVO OBJETO DE TIPO PERSONA (INSERT)
						insertarPersona(personaDao);
						break;
					case 3://ACTUALIZAMOS UN REGISTRO (UPDATE)
						actualizarPersona(personaDao);
						break;
					case 4://ELIMINAMOS UN REGISTRO (DELETE)
						eliminarPersona(personaDao);
						break;
					case 5://VOLVER
						salir = true;
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opcion incorrecta!!", "Error", JOptionPane.WARNING_MESSAGE);
						break;
				}
			}
		} catch (HeadlessException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar opcion", "Error de entrada", JOptionPane.WARNING_MESSAGE);
		}
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
		String nombre, apellido, email, tlfno;
		nombre = JOptionPane.showInputDialog("Nombre:");
		apellido = JOptionPane.showInputDialog("Apellido:");
		email = JOptionPane.showInputDialog("Email:");
		tlfno = JOptionPane.showInputDialog("Telefono:");

		Persona personaNueva = new Persona(nombre, apellido, email, tlfno);
		registros = personaDao.insertar(personaNueva);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS INSERTADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void actualizarPersona(PersonaDAO personaDao) {
		int idPersona, registros = 0;
		String nombre, apellido, email, tlfno;
		idPersona = Integer.valueOf(JOptionPane.showInputDialog("Id Persona a modificar: "));
		nombre = JOptionPane.showInputDialog("Nombre:");
		apellido = JOptionPane.showInputDialog("Apellido:");
		email = JOptionPane.showInputDialog("Email:");
		tlfno = JOptionPane.showInputDialog("Telefono:");

		Persona persona = new Persona(idPersona, nombre, apellido, email, tlfno);
		registros = personaDao.actualizar(persona);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ACTUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void eliminarPersona(PersonaDAO personaDao) {
		int idPersona, registros = 0;
		idPersona = Integer.valueOf(JOptionPane.showInputDialog("Id Persona a eliminar: "));
		Persona persona = new Persona(idPersona);
		registros = personaDao.eliminar(persona);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ELIMINADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	//********************************************************************
	public static void gestionUsuarios(UsuarioDAO usuarioDao) {
		int opc = 0;
		boolean salir = false;
		String menu = "1. Listar Usuarios\n"
				+ "2. Insertar Usuario\n"
				+ "3. Actualizar Usuario\n"
				+ "4. Eliminar Usuario\n"
				+ "5. Volver";
		try {
			while (salir != true) {
				try {
					opc = Integer.valueOf(JOptionPane.showInputDialog(null, menu, JOptionPane.QUESTION_MESSAGE));
					switch (opc) {
						case 1://LISTAMOS LOS OBJETOS PERSONA DE LA BD.(SELECT)
							imprimirUsuarios(usuarioDao);
							break;
						case 2://INSERTAMOS UN NUEVO OBJETO DE TIPO PERSONA (INSERT)
							insertarUsuario(usuarioDao);
							break;
						case 3://ACTUALIZAMOS UN REGISTRO (UPDATE)
							actualizarUsuario(usuarioDao);
							break;
						case 4://ELIMINAMOS UN REGISTRO (DELETE)
							eliminarUsuario(usuarioDao);
							break;
						case 5://VOLVER
							salir = true;
							break;
						default:
							JOptionPane.showMessageDialog(null, "Opcion incorrecta!!", "Error", JOptionPane.WARNING_MESSAGE);
							break;
					}
				} catch (HeadlessException | NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error al introducir opcion");
				}

			}
		} catch (HeadlessException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar opcion", "Error de entrada", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void imprimirUsuarios(UsuarioDAO usuarioDao) {
		List<Usuario> usuarios = usuarioDao.listaUsuarios();
		String res = new String();
		for (Usuario usuario : usuarios) {
			res += usuario;
		}
		JOptionPane.showMessageDialog(null, res, "USUARIOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void insertarUsuario(UsuarioDAO usuarioDao) {
		int registros = 0;
		String nombreUsu, pass;
		nombreUsu = JOptionPane.showInputDialog("Usuario:");
		pass = JOptionPane.showInputDialog("Password:");

		Usuario usuarioNuevo = new Usuario(nombreUsu, pass);
		registros = usuarioDao.insertar(usuarioNuevo);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS INSERTADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void actualizarUsuario(UsuarioDAO usuarioDao) {
		int idUsu, registros = 0;
		String nombreUsu, pass;
		idUsu = Integer.valueOf(JOptionPane.showInputDialog("Id Usuario a actualizar: "));
		nombreUsu = JOptionPane.showInputDialog("Usuario:");
		pass = JOptionPane.showInputDialog("Password:");

		Usuario usuarioNuevo = new Usuario(idUsu, nombreUsu, pass);
		registros = usuarioDao.actualizar(usuarioNuevo);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ACTUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void eliminarUsuario(UsuarioDAO usuarioDao) {
		int idUsu, registros = 0;
		idUsu = Integer.valueOf(JOptionPane.showInputDialog("Id Usuario a eliminar: "));
		Usuario usuarioNuevo = new Usuario(idUsu);
		registros = usuarioDao.eliminar(usuarioNuevo);
		JOptionPane.showMessageDialog(null, registros, "REGISTROS ELIMINADOS", JOptionPane.INFORMATION_MESSAGE);
	}
}
