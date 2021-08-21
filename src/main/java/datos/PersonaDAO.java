/*
 *DAO=Acces Data Object 
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Persona;
import java.sql.*;
import java.util.*;

/**
 * Clase de acceso a datos: recuperacion de datos y creacion de objetos 'Persona'.
 *
 * @author Edwin Jaldin
 */
public class PersonaDAO {

	private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
	private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona=?";
	private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

	/**
	 * Esta funcion llamando a los metodos de la clase conexion, se encarga de leer (consultar-SELECT) los registros de la BD y agregarlos en una lista de personas.
	 *
	 * @return Retorna una lista de objetos de tipo 'Persona' extraidos de la BD.
	 */
	public List<Persona> listaPersonas() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona persona = null;
		List<Persona> personas = new ArrayList<>();

		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();//ejecucion de la consulta
			while (rs.next()) {
				int idPersona = rs.getInt("id_persona");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");

				persona = new Persona(idPersona, nombre, apellido, email, telefono);
				personas.add(persona);

			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			try {
				//cerramos conexiones llamando a los metodos estaticos de la clase 'Conexion'
				Conexion.close(rs);
				Conexion.close(stmt);
				Conexion.close(conn);
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}
		}

		return personas;
	}

	/**
	 * Con esta funcion podemos hacer un INSERT de un nuevo objeto 'Persona'.
	 *
	 * @param persona Recibe como parametro un objeto 'Persona', al que se le agregara a la BD.
	 * @return Retorna el numero de registros modificados.
	 */
	public int insertar(Persona persona) {
		Connection conexion = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			//al importar la clase estatica completa se puede llamar directamente al metodo 'Connection'.
			conexion = getConnection();
			stmt = conexion.prepareStatement(SQL_INSERT);
			//como vamos hacer un 'INSERT' asignamos los valores a insertar.
			//el indice corresponde a la posicion dentro del parentecis de 'VALUES' 
			stmt.setString(1, persona.getNombre());
			stmt.setString(2, persona.getApellido());
			stmt.setString(3, persona.getEmail());
			stmt.setString(4, persona.getTelefono());

			//significa los cambios en la BD, valido para UPDATE, INSERT, DELETE.
			//ejecuta la sentencia INSERT y devuelve el numero de registros afectados.
			registros = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			try {
				//al importar la clase conexion y el metodo estatico se puede llamar directamente,
				close(stmt);
				close(conexion);
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}

		}
		return registros;
	}

	public int actualizar(Persona persona) {

		Connection conet = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {

			conet = Conexion.getConnection();
			stmt = conet.prepareStatement(SQL_UPDATE);

			stmt.setString(1, persona.getNombre());
			stmt.setString(2, persona.getApellido());
			stmt.setString(3, persona.getEmail());
			stmt.setString(4, persona.getTelefono());
			stmt.setInt(5, persona.getIdPersona());

			registros = stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			try {
				close(stmt);
				close(conet);
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}
		}
		return registros;
	}

	public int eliminar(Persona persona) {
		Connection conet = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {

			conet = Conexion.getConnection();
			stmt = conet.prepareStatement(SQL_DELETE);

			stmt.setInt(1, persona.getIdPersona());

			registros = stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			try {
				close(stmt);
				close(conet);
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}
		}
		return registros;
	}
}
