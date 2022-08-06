
import java.awt.print.Book;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Main {
	public static void main(String[] args) {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("https://my-json-server.typicode.com/").path("Himuravidal/anchorBooks/books");
		Invocation.Builder invocacionBuilder = target.request(MediaType.APPLICATION_JSON);
		Response respuesta = invocacionBuilder.get();
		
		List<Books> lista = respuesta.readEntity(new GenericType <List<Books>>() {});
		
		
		mostrarLista(lista);
		mostrarListaAutorTitulo(lista);
		
		mostrarLibro(lista,3);
		mostrarLibro(lista,8);
		
		System.out.println("");
		System.out.println("");
		
		
		//Parte 2:
		
		Client cliente2 = ClientBuilder.newClient();
		WebTarget target2 = cliente2.target("https://my-json-server.typicode.com/").path("Himuravidal/anchorBooks/bookDetail");
		Invocation.Builder invocacionBuilder2 = target2.request(MediaType.APPLICATION_JSON);
		Response respuesta2 = invocacionBuilder2.get();
		
		//List<Book> lista = respuesta.readEntity(new GenericType <List<Book>>() {});
		List<BookDetail> lista2 = respuesta2.readEntity(new GenericType <List<BookDetail>>() {});
		
		mostrarListaDetalles(lista2);
		System.out.println("");
		System.out.println("");
		mostrarLibrosUnknown(lista2);
		System.out.println("");
		System.out.println("");
		mostrarLibrosDelivery(lista2);
	}
	

	
	
	public static void mostrarLibrosDelivery(List<BookDetail> listaLibros) {
		for (int i = 0; i<listaLibros.size();i++) {
			BookDetail bd = listaLibros.get(i);
			
			if (bd.isDelivery()) {
				System.out.println("Libro: "+bd.getTitle());
			}
		}
		
	}
	
	public static void mostrarLibrosUnknown(List<BookDetail> listaLibros) {
		for (int i = 0; i<listaLibros.size();i++) {
			BookDetail bd = listaLibros.get(i);
			
			if (bd.getAuthor().equals("Unknown")) {
				System.out.println("Autor: "+bd.getAuthor()+" - libro: "+bd.getTitle());
			}
		}
		
	}
	
	public static void mostrarListaDetalles(List<BookDetail> listaLibros) {
		for (int i = 0; i<listaLibros.size();i++) {
			System.out.println(listaLibros.get(i));
		}
		
	}
	
	public static void mostrarLibro(List<Books> listaLibros, int libroIndex) {
		
		System.out.println("Libro numero "+libroIndex+": "+listaLibros.get(libroIndex).getAuthor()+", "+listaLibros.get(libroIndex).getTitle()+", "+listaLibros.get(libroIndex).getCountry());
		
		
	}
	
	public static void mostrarLista(List<Books> listaLibros) {
		for (int i = 0; i<listaLibros.size();i++) {
			System.out.println(listaLibros.get(i));
		}
		
	}
	
	public static void mostrarListaAutorTitulo(List<Books> listaLibros) {
		for (int i = 0; i<listaLibros.size();i++) {
			System.out.println((listaLibros.get(i).getAuthor())+": "+(listaLibros.get(i).getTitle()));
		}
		
	}
	
	
}


