package codigo;
 
import java.sql.SQLException;
import java.util.ArrayList;

import codigo.cfg.Config;
import codigo.db.DbObject;
import codigo.models.Persona;
import codigo.models.Producto;
import codigo.models.Usuario;

public class Principal {

	public static void main(String[] args) throws SQLException { 
		
		
		Config.getInstance();
		
		
		try {
			
			Persona per = new Persona();
			Usuario usr = new Usuario();
			Producto prd= new Producto();
			
			prd.setPrecio(40);
			prd.setDesc(20);
			prd.setName("zapatos adidas");
			prd.setStock(10);
			prd.setId(1);
			prd.insertar();
			
			prd.setPrecio(100);
			prd.setDesc(00);
			prd.setName("tacones ");
			prd.setStock(40);
			prd.insertar();
			
			usr.setUser("ana");
			usr.setPassword("1234");
			usr.setId(1);
			usr.insertar();
			
			usr.setUser("mike");
			usr.setPassword("4567");
			usr.insertar();
			
			per.name     = "Don Jose";
			per.lastName = "Pepito";
			per.setId(1);
			per.insertar();
			
			per.name     = "Jose Don";
			per.lastName = "Jose";
			per.insertar();
			
			ArrayList<Persona> listaPer = listaPersonas(per.list());
			
			for (Persona persona : listaPer) {
				System.out.println(persona.getId() + "Persona:"+persona.name
						+ " Apellido: " + persona.lastName);
			}
			
			System.out.println("\n\n");
			
			ArrayList<Usuario> listaUsr = listaUsuarios(usr.list());
			
			for (Usuario usuario : listaUsr) {
				System.out.println(usuario.getId() + "Usuario:"+usuario.getUser() + 
						" Pass: " + usuario.getPassword());
			}
			
			System.out.println("\n\n");
			
			ArrayList<Producto> listaPrd = listaProductos(prd.list());
			
			for (Producto producto : listaPrd) {
				System.out.println(producto.getId() + "Producto:"+producto.getName());
			}
			
			System.out.println("\n\n");
			
			per.name     = "Don Pepe";
			per.lastName = "Pepe";
			
			per.update(selectorRandom(listaPer.size()));
			usr.setPassword("paquete");
			usr.update(selectorRandom(listaUsr.size()));
			prd.setName("Costilla");
			prd.update(selectorRandom(listaPrd.size()));
			
			listaPer = listaPersonas(per.list());
			for(Persona persona : listaPer) {
				System.out.println(persona.getId() + "Persona:"+persona.name + 
						" Apellido: " + persona.lastName);
			}
			
			System.out.println("\n\n");
			
			listaUsr = listaUsuarios(usr.list());
			
			for (Usuario usuario : listaUsr) {
				System.out.println(usuario.getId() + "Usuario:"+usuario.getUser()
						+" Pass: " + usuario.getPassword());
			}	
			
			System.out.println("\n\n");
			
			listaPrd = listaProductos(prd.list());
			
			for (Producto producto : listaPrd) {
				System.out.println(producto.getId() + "Producto:"+producto.getName());
			}
			
			System.out.println("\n");
			
			prd.delete(selectorRandom(listaPer.size()));
			per.delete(selectorRandom(listaPer.size()));
			usr.delete(selectorRandom(listaUsr.size()));
			
			listaPer = listaPersonas(per.list());
			listaPrd = listaProductos(prd.list());
			listaUsr = listaUsuarios(usr.list());
			
			for(Persona persona : listaPer) {
				System.out.println(persona.getId() + "Persona:"+persona.name
						+" Apellido: "+ persona.lastName);
			}
			
			System.out.println("\n\n");
			
			for (Producto producto : listaPrd) {
				System.out.println(producto.getId() + "Producto:"+producto.getName());
			}
			
			System.out.println("\n\n");
			
			for (Usuario usuario : listaUsr) {
				System.out.println(usuario.getId() + "Usuario:"+usuario.getUser()
						+" Pass: " + usuario.getPassword());
			}
			
		}catch(SQLException e) {
			System.out.println( e.getMessage() );
		}  
		
		
		
		// create a database connection

		/*
		 * 
		 * statement.executeUpdate("drop table if exists person");
		 * 
		 * statement.executeUpdate("create table person (id integer, name string)");
		 * 
		 * statement.executeUpdate("insert into person values(1, 'leo')");
		 * statement.executeUpdate("insert into person values(2, 'yui')");
		 * 
		 * 
		 * 
		 * ResultSet rs = statement.executeQuery("select * from person");
		 * while(rs.next()) { // read the result set System.out.println("name = " +
		 * rs.getString("name")); }
		 * 
		 * 
		 * //
		 */

	}
	
	public static ArrayList<Persona> listaPersonas(ArrayList lista){
		ArrayList<Persona> listaPersona;
		listaPersona = lista;
		return listaPersona;
	}
	
	public static ArrayList<Usuario> listaUsuarios(ArrayList lista){
		ArrayList<Usuario> listaUsuarios;
		listaUsuarios = lista;
		return listaUsuarios;
	}
	
	public static ArrayList<Producto> listaProductos(ArrayList lista){
		ArrayList<Producto> listaProductos;
		listaProductos = lista;
		return listaProductos;
	}
	
	public static int selectorRandom(int rango) {
		double randomDbl;
		int random;
		
		
		randomDbl = (Math.random() * rango) +1;
		random = (int) Math.floor(randomDbl);
		
		return random;
	}

}
