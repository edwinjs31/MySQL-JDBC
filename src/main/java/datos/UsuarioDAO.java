package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edwin Jaldin
 */
public class UsuarioDAO {

	private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
	private static final String SQL_INSERT = "INSERT INTO usuario(usuario,password) VALUES(?,?)";
	private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?, password=? WHERE id_usuario=?";
	private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=? ";

	public List<Usuario> listaUsuarios() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario user = null;
		List<Usuario> usuarios = new ArrayList<>();

		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();//ejecucion de la consulta
			while (rs.next()) {
				int idUsuario = rs.getInt("id_usuario");
				String usuario = rs.getString("usuario");
				String pass = rs.getString("password");

				user = new Usuario(idUsuario, usuario, pass);
				usuarios.add(user);

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

		return usuarios;
	}
	
	public int insertar(Usuario user) {
		Connection conexion = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			//al importar la clase estatica completa se puede llamar directamente al metodo 'Connection'.
			conexion = getConnection();
			stmt = conexion.prepareStatement(SQL_INSERT);
			//como vamos hacer un 'INSERT' asignamos los valores a insertar.
			//el indice corresponde a la posicion dentro del parentecis de 'VALUES' 
			stmt.setString(1, user.getUsuario());
			stmt.setString(2, user.getPassword());

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
	
	public int actualizar(Usuario user) {

		Connection conet = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {

			conet = Conexion.getConnection();
			stmt = conet.prepareStatement(SQL_UPDATE);

			stmt.setString(1, user.getUsuario());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getIdUsuario());

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
