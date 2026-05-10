package taller2;
//Benjamin Mundaca 221712102 ICCI
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class App 
{
	//Main donde se rellenan las listas y se ingresan al menu de juego
	public static void main(String[] args) 
	{
		List<Pokemon> pokemons = new ArrayList<>();
		rellenarPokemons(pokemons);
		List<Gimnasio> gimnasios = new ArrayList<>();
		rellenarGimnasios(gimnasios,pokemons);
		List<String> habitats = new ArrayList<>();
		rellenarHabitats(habitats);
		List<AltoMando> altosMandos = new ArrayList<>();
		rellenaraltosMandos(altosMandos,pokemons);
		
		menuInicial(pokemons, gimnasios, altosMandos, habitats);
	}
	//Metodo para agregar los pokemons a la lista de Main 
	public static void rellenarPokemons(List<Pokemon> lista)
	{
		try (Scanner Lector = new Scanner(new File("Pokedex.txt")))
		{
		
			while(Lector.hasNextLine())
			{
				int total = 0;
				String linea = Lector.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0];
				String habitat = partes[1];
				double porcentajeAparicion = Double.parseDouble(partes[2]);
				int hp = Integer.parseInt(partes[3]);
				int atk = Integer.parseInt(partes[4]);
				int def = Integer.parseInt(partes[5]);
				int atkEsp = Integer.parseInt(partes[6]);
				int defEsp = Integer.parseInt(partes[7]);
				int vel = Integer.parseInt(partes[8]);
				String tipo = partes[9];
				
				total = hp + atk + def + atkEsp + defEsp + vel;
				
				Pokemon pokemon = new Pokemon(nombre,habitat,porcentajeAparicion,total,tipo);
				lista.add(pokemon);
		
			}
			
		}catch(Exception e) 
		{
			System.out.println("No se pudo leer el archivo");
		}
	}
	//Metodo para agregar los entrenadores de gimnasio a la lista de Main
	public static void rellenarGimnasios(List<Gimnasio> lista, List<Pokemon> lista2)
	{
		try (Scanner Lector = new Scanner(new File("Gimnasios.txt")))
		{
		
			while(Lector.hasNextLine())
			{
				
				String linea = Lector.nextLine();
				String[] partes = linea.split(";");
				String Lider = partes[1];
				String Estado = partes[2];
				int cantPoke = Integer.parseInt(partes[3]);
				Gimnasio gym = new Gimnasio(Lider,Estado);
				for(int i = 1; i<cantPoke+1;i++) 
				{
					String nombrePokemon = partes[3+i].trim();
					for(Pokemon pokemon : lista2) 
					{
						if(pokemon.getNombre().equals(nombrePokemon))
						{
							gym.agregarPokemon(pokemon);
						}
						
					}
				}
				lista.add(gym);
				
		
			}
			
		}catch(Exception e) 
		{
			System.out.println("No se pudo leer el archivo");
		}
	}
	//Metodo para agregar los habitats a la lista de Main
	public static void rellenarHabitats(List<String> lista) 
	{
		try (Scanner Lector = new Scanner(new File("Habitats.txt")))
		{
		
			while(Lector.hasNextLine())
			{
	
				String linea = Lector.nextLine();
				lista.add(linea);
				
			}
			
		}catch(Exception e) 
		{
			System.out.println("No se pudo leer el archivo");
		}
	}
	//Metodo para agregar los altos mandos a la lista de Main 
	public static void rellenaraltosMandos(List<AltoMando> lista , List<Pokemon> lista2) 
	{
		try (Scanner Lector = new Scanner(new File("AltoMando.txt")))
		{
		
			while(Lector.hasNextLine())
			{
				
				String linea = Lector.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[1];
				AltoMando altoMando = new AltoMando(nombre);
				for (int i = 1; i<7 ; i++)
				{
					String nombrePokemon = partes[1+i].trim();
					for(Pokemon pokemon : lista2)
					{
						if(pokemon.getNombre().equals(nombrePokemon))
						{
							altoMando.agregarPokemon(pokemon);
						}
					}
				}
				
				lista.add(altoMando);
				
		
			}
			
		}catch(Exception e) 
		{
			System.out.println("No se pudo leer el archivo");
		}
	}
	//Menu inicial para elegir iniciar partida o continuarla
	public static void menuInicial(List<Pokemon> pokemons, List<Gimnasio> gimnasios,List<AltoMando> altosMandos,List<String> habitats) 
	{
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while(!salir)
		{
			int opcion = 0; 
			try  {
				System.out.println("¡Bienvenido a Pokemon Code!");
				System.out.println("ingresa una opcion");
				System.out.println("1) Continuar");
				System.out.println("2) Nueva Partida");
				System.out.println("3) Salir.");

				opcion = Integer.parseInt(scanner.nextLine());
				if(opcion<1 || opcion>3) throw new Exception ("");
				String nombre = "";
				switch(opcion) {
				
				case 1: 
					int id = 0;
					 nombre = revisarRegistro(id);
					if(nombre != null)
					{
						Entrenador entrenador = new Entrenador(nombre);
						registrarEntrenador(entrenador,pokemons,gimnasios);
						MenuJuego(entrenador,pokemons,gimnasios,altosMandos,habitats, scanner);
					}
					else 
					{
						System.out.println("No tienes partidas guardadas");
					}
					break;
				case 2:
					System.out.println("Ingrese su nombre de entrenador ");
					nombre = scanner.nextLine();
					Entrenador entrenador = new Entrenador(nombre);
					System.out.println("Bienvenido " + nombre);
					Sobreescribir(entrenador);
					MenuJuego(entrenador, pokemons, gimnasios, altosMandos, habitats, scanner);
					break;
				
				case 3:
					System.out.println("Adios entrenador");
					salir = true;
					break;
				
				}
			} catch (Exception e) 
			{
				System.out.println("Ingrese una opcion valida ");
			}
			
		
		}
		
	}
	//Metodo para registrar el entrenador desde el registro guardado anteriormente (en caso de continuar partida)
	public static void registrarEntrenador(Entrenador entrenador, List<Pokemon> pokemons, List<Gimnasio> gimnasios) 
	{
		try 
		{
			Scanner scanner =  new Scanner(new File("Registros.txt"));
			String linea = scanner.nextLine();
			String[] partes = linea.split(";");
			
			for(int i = 1 ; i<partes.length; i++)
			{
				String medalla = partes[i];
				entrenador.agregarMedallas(medalla);
			}
			
			while(scanner.hasNextLine())
			{
				linea = scanner.nextLine();
				partes = linea.split(";");
				String pokemon = partes[0];
				String estado = partes[1];
				for (Pokemon p : pokemons)
				{
					if (p.getNombre().equals(pokemon) && estado.equalsIgnoreCase("Muerto"))
					{
						p.setVivo(false);
						entrenador.agregarPokemon(p);
						break;
					}else if (p.getNombre().equals(pokemon))
					{
						entrenador.agregarPokemon(p);
						break;
					}
				}
				
				
			}
		scanner.close();
			
		
		} catch (FileNotFoundException e) 
		{
			
			System.out.println("Archivo no encontrado");
		}
	
	}
	//Metodo que sobreescribe la partida guardada anteriormente
	public static void Sobreescribir(Entrenador entrenador) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt", false))) 
		{
			System.out.println("Se han sobreescrito los datos de la partida anterior, Comienza una nueva aventura");
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el archivo: " + e.getMessage());
        }
		
		
	}
	//Metodo que inicia el Menu interactivo del juego con sus respectivas opciones 
	public static void MenuJuego(Entrenador entrenador, List<Pokemon> pokemons, List<Gimnasio> gimnasios,List<AltoMando> altosMandos,List<String> habitats, Scanner scanner) 
	{
		boolean salir = false;
		while(!salir)
		{
			try 
			{
				
				System.out.println(entrenador.getNombre() + ", que deseas hacer?");
				System.out.println("1) Revisar equipo.");
				System.out.println("2) Salir a capturar.");
				System.out.println("3) Acceso al PC (cambiar Pokemon del equipo).");
				System.out.println("4) Retar un gimnasio.");
				System.out.println("5) Desafio al alto mando.");
				System.out.println("6) Curar Pokemon.");
				System.out.println("7) Guardar.");
				System.out.println("8) Guardar y Salir.");
				
				int opcion = Integer.parseInt(scanner.nextLine());
				if(opcion>8 || opcion<1) throw new Exception();
				
				
				switch(opcion) 
				{
				case 1:
					entrenador.mostrarEquipo();
					break;
				case 2:
					saliraCapturar(entrenador, habitats, scanner, pokemons);
					break;
				case 3: 
					menuPC(entrenador,scanner);
					break;
				case 4:
					if(verificarVivos(entrenador.getEquipo()))
					{
						menuGym(entrenador,gimnasios,scanner);
					}else
					{
						System.out.println("No puedes pelear con tu equipo debilitado");
					}break;
				
				case 5:
					if(verificarVivos(entrenador.getEquipo()))
					{
						
						if(entrenador.getMedallas().size()==8)
						{
							pasarLista(entrenador,altosMandos, scanner);
						}else 
						{
							System.out.println("necesitas todas las medallas para desafiar a los Altos Mandos");
						}
					}else
					{
						System.out.println("No puedes pelear con tu equipo debilitado");
					}break;
					
				case 6:
					entrenador.curarPokemons();
					break;
				case 7:
					guardarRegistro(entrenador);
					break;
				case 8:
					guardarRegistro(entrenador);
					System.out.println("Hasta luego entrenador");
					salir = true;
					break;
				}
			
			
			}catch(Exception e) 
			{
				System.out.println("ingrese una opcion valida ");
			}
		}


	}
	//Metodo usado para guardar la partida
	public static void guardarRegistro(Entrenador entrenador) 
	{
		String linea = entrenador.getNombre();
		for(String M : entrenador.getMedallas())
		{
			linea += ";" + M ;
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt", false))) 
		{
		

            bw.write(linea);
            for(Pokemon p : entrenador.getEquipo())
            {
            	bw.newLine(); 
            	linea = p.getNombre() + ";" + p.getEstado();
                bw.write(linea);

            }
            for(Pokemon p : entrenador.getPC())
            {
            	bw.newLine(); 
            	linea = p.getNombre() + ";" + p.getEstado();
                bw.write(linea);

            }
            
            System.out.println("Partida guardada correctamente.");
            
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el archivo: " + e.getMessage());
        }
		
		
	}
	//Metodo usado para pasar uno a uno los altos mandos al sistema de lucha, para que las batallas sean continuas
	public static void pasarLista(Entrenador entrenador,List<AltoMando> altosMandos, Scanner scanner) 
	{
		System.out.println("Has entrado al desafio final, no hay vuelta atras");
		boolean esGym = false;
		for(AltoMando A : altosMandos)
		{
			String nombre = A.getLider();
			List<Pokemon> equipoRival = A.getPokemons();
			
			sistemaLucha(entrenador,nombre,equipoRival,scanner, esGym);
			
			if (!verificarVivos(entrenador.getEquipo())) 
			{
	            System.out.println("Has sido derrotado por " + nombre + ". Tu desafío en la Liga ha terminado.");
	            return; 
	        }
			
		}
		System.out.println("Felicidades has derrotado a los Altos Mandos");
		
	}
	//Menu para elegir a que Lider del gimnasio se desea combatir 
	public static void menuGym(Entrenador entrenador, List<Gimnasio> gimnasios, Scanner scanner) 
	{
		int i = 0;
		System.out.println("A que lider deseas desafiar?");
		for(Gimnasio trainer : gimnasios)
		{
			i++;
			System.out.println(i+") " + trainer.getLider() + " - " + trainer.getEstado());
		}
		System.out.println("9) Volver al menu");
		try
		{
			int opcion = Integer.parseInt(scanner.nextLine())-1;
			if(opcion + 1 == 9) return;
			if (opcion <0 || opcion>=gimnasios.size()) throw new Exception();
			
			if(gimnasios.get(opcion).getEstado().equalsIgnoreCase("Derrotado"))
			{
				System.out.println("Ya has derrotado a este líder y tienes su medalla.");
			
			}else if(entrenador.getMedallas().size()<opcion)
			{
				System.out.println("no puedes luchar contra " + gimnasios.get(opcion).getLider() + " antes tendras que derrotar a " + gimnasios.get(opcion-1).getLider());
				
			}else 
			{
				System.out.println("Comienza el combate");
				boolean esGym= true; 
				
				sistemaLucha(entrenador,gimnasios.get(opcion).getLider(),gimnasios.get(opcion).getPokemons(),scanner, esGym);
				if (verificarVivos(entrenador.getEquipo())) 
				{
				    gimnasios.get(opcion).setEstado("Derrotado");
				}
				
			}
			
		}catch (Exception e) 
		{
			System.out.println("ingrese una opcion valida");
		}
		
		
	}
	//Sistema que permite generar las batallas para los altos mandos y los gimnasios
	public static void sistemaLucha(Entrenador entrenador, String nombreRival, List<Pokemon> equipoRival,  Scanner scanner, boolean verificador) {
		
		int selectorPokemonAliado= 0;
		int selectorPokemonContra = 0;
		boolean rendirse = false;
		while(!rendirse)
		{
			if(!rendirse) 
			{
				
				System.out.println(entrenador.getNombre() + " saca a " + entrenador.getEquipo().get(selectorPokemonAliado).getNombre());
				System.out.println(nombreRival + " saca a " + equipoRival.get(selectorPokemonContra).getNombre());
			}
			
			System.out.println("Que deseas hacer?");
			System.out.println("1) Atacar");
			System.out.println("2) Cambiar de pokemon");
			System.out.println("3) Rendirse");
			try
			{
				int opcion = Integer.parseInt(scanner.nextLine());
				if(opcion<1 || opcion>3) throw new Exception("");
				switch(opcion)
				{
				case 1:
					String pokemonAliado = entrenador.getEquipo().get(selectorPokemonAliado).getNombre();
					String pokemonContra = equipoRival.get(selectorPokemonContra).getNombre();
					int statsAliadas = entrenador.getEquipo().get(selectorPokemonAliado).getTotalStats();
					int statsContra = equipoRival.get(selectorPokemonContra).getTotalStats();
					System.out.println(pokemonAliado + "--> " + statsAliadas );
					System.out.println(pokemonContra + "--> " + statsContra);
					double eficacia = TablaTipos.obtenerEfectividad(entrenador.getEquipo().get(selectorPokemonAliado).getTipo(), equipoRival.get(selectorPokemonContra).getTipo());
					int nuevaStat = (int)(statsAliadas*eficacia);
					if(eficacia < 1) System.out.println(pokemonAliado + " no es efectivo contra " + pokemonContra);
					if(eficacia > 1) System.out.println(pokemonAliado + " es super efectivo contra " + pokemonContra);
					System.out.println("Nuevo puntaje:");
					System.out.println(pokemonAliado + "--> " + nuevaStat );
					System.out.println(pokemonContra + "--> " + statsContra);
						
					if(nuevaStat<statsContra)
					{
						System.out.println("Ha ganado " + pokemonContra + " " + pokemonAliado + " fue derrotado");
						entrenador.getEquipo().get(selectorPokemonAliado).setVivo(false);
						if( verificarVivos(entrenador.getEquipo()))
						{
							selectorPokemonAliado =SelectordePokemon(entrenador, pokemonAliado, selectorPokemonAliado,scanner, "se ha debilitado");
							
						}else
						{
							System.out.println("Todos tus pokemons han sido debilitados, regresando al menu");
							rendirse=true;
						}
					}else
					{
						System.out.println("Ha ganado " + pokemonAliado + " " + pokemonContra + " fue derrotado");
						selectorPokemonContra++;
						
						if (selectorPokemonContra >= equipoRival.size()) {
							System.out.println("¡HAS GANADO EL COMBATE! " + nombreRival + " se ha quedado sin pokemons.");
							if(verificador)
							{
								entrenador.agregarMedallas(nombreRival); 
								rendirse = true; 
							}
						}
						
					}
					break;
					
				case 2:
					if (verificarVivos(entrenador.getEquipo())) {
				        
				        int anterior = selectorPokemonAliado;
				        selectorPokemonAliado = SelectordePokemon(entrenador, "Cambio táctico:" + entrenador.getEquipo().get(selectorPokemonAliado).getNombre(), selectorPokemonAliado, scanner, "ha vuelto");
				        
				        if (anterior == selectorPokemonAliado) {
				            System.out.println("No hubo cambios en el frente.");
				        } else {
				            System.out.println("¡Has cambiado a " + entrenador.getEquipo().get(selectorPokemonAliado).getNombre() + "!");
				        }
				    } else {
				        System.out.println("No tienes más pokemons sanos para cambiar.");
				    }
				    break;
				case 3:
					System.out.println("Te has retirado del combate...");
				    rendirse = true;
				    break;
				}
				
			}catch(Exception e)
			{
				System.out.println("Ingrese una opcion valida");
			}
			
		}
	
	}
	//Sistema usado para hacer los cambios de los pokemons a la hora de luchar 
	public static int SelectordePokemon(Entrenador entrenador, String pokemonAliado, int selectorPokemonAliado, Scanner scanner, String mensaje) 
	{
		System.out.println(pokemonAliado + " "  + mensaje + " selecciona otro pokemon");
		entrenador.mostrarEquipo();
		while(true)
		{
			try
			{
				selectorPokemonAliado = Integer.parseInt(scanner.nextLine())-1;
				if(selectorPokemonAliado>=entrenador.getEquipo().size() || selectorPokemonAliado<0) throw new Exception();
				if(entrenador.getEquipo().get(selectorPokemonAliado).getEstado().equalsIgnoreCase("Muerto"))
				{
					System.out.println("Este pokemon esta debilitado, elija otro");
				}else break;
				
			}catch(Exception e)
			{
				System.out.println("elija a un pokemon valido");
			}
			
		}
		return selectorPokemonAliado; 

	}

	//Sistema que verifica si el equipo pokemon esta vivo o no, ademas de los pokemons de manera individual
	public static boolean verificarVivos(List<Pokemon> lista) 
	{
		
		for(Pokemon p : lista)
		{
			if(p.isVivo()) return true; 
		}
		return false;
	
	}
	//Sistema que abre el menu del PC y permite generar cambios en el equipo desde el mismo 
	public static void menuPC(Entrenador entrenador,Scanner scanner) {
		boolean salir = false;
		while(!salir)
		{
			try 
			{
				entrenador.mostrarPC();

				System.out.println("Que deseas hacer?");
				System.out.println("1) Cambiar Pokemon");
				System.out.println("2) Salir");
				int opcion = Integer.parseInt(scanner.nextLine());
				if (opcion>2 || opcion<1) throw new Exception("");
				switch(opcion)
				{
				case 1:
					cambiodelPC(entrenador, scanner);
					break;
				case 2:
					salir = true;
					break;
				}
			}catch(Exception e)
			{
				System.out.println("ingrese una opcion valida");
			}
			
				
		}
		
	}

	//Sistema que permite verificar si existe una partida creada o no
	public static String revisarRegistro(int id) {
		try (Scanner scanner = new Scanner(new File("Registros.txt"))) 
		{
			
			while(scanner.hasNextLine())
			{
				String linea = scanner.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0];
				if (id == 0) return nombre;
				
			}
			
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("Archivo no encontrado");
		}
		return null;
	}
	//Sistema que se ejecuta a la hora de querer cambiar el pokemon del equipo y mandarlo al pc
	public static void cambiodelPC(Entrenador entrenador, Scanner scanner)
	{
		System.out.println("Ingrese el pokemon deseado del PC");
		try 
		{
			int eleccion = Integer.parseInt(scanner.nextLine())-1;
			
			if (eleccion<0 || eleccion>= entrenador.getPC().size()) throw new Exception ("");
			
			entrenador.mostrarEquipo();
			System.out.println("ingrese que pokemon desea cambiar");
			int seleccionador = Integer.parseInt(scanner.nextLine())-1;
			
			if(seleccionador<0 || seleccionador >= entrenador.getEquipo().size()) throw new Exception ("");
			
			entrenador.cambiarenPC(eleccion, seleccionador);
		}catch(Exception e)
		{
			System.out.println("selecciona un pokemon valido");
		}
	}

	//Sistema que hace aparecer un pokemon de manera aleatoria segun su zona 
	public static void saliraCapturar(Entrenador entrenador, List<String> lista, Scanner scanner, List<Pokemon> pokemons)
	{
		int i = 0;
		for (String habitat : lista) 
		{
			i++;
			System.out.println(i + ") " + habitat);
		}
		
			
			try
			{
				System.out.println("Ingrese el habitat en el que desea capturar");
				
				int opcion = Integer.parseInt(scanner.nextLine())-1;
				if(opcion>=lista.size() || opcion<0) throw new Exception();
				Random random = new Random();
				double porcentajeRedondeado = Math.round(random.nextDouble()*100.0)/100.0;
				double acumulador = 0.0;
				
				Pokemon pokemonAparecido = null;
				for (Pokemon pokemon : pokemons)
				{
					if(pokemon.getHabitat().equals(lista.get(opcion))) 
					{
						acumulador +=pokemon.getPorcentaje();
						if(porcentajeRedondeado<= acumulador)
						{
							pokemonAparecido = pokemon;
							break;
						}
						
					}
					
				}
				if(pokemonAparecido!= null)
				{
					System.out.println("Un " + pokemonAparecido.getNombre() + " salvaje ha aparecido");
						
					try
					{
						
						System.out.println("Que deseas hacer");
						System.out.println("1) Capturar");
						System.out.println("2) Huir");
						
						opcion = Integer.parseInt(scanner.nextLine());
						switch(opcion)
						{
						case 1:
							entrenador.agregarPokemon(pokemonAparecido);
							break;
						case 2: 
							System.out.println("Has huido con exito");
							
							break;
							
						}
						
					}catch(Exception e)
					{
						System.out.println("ingresa una opcion valida");
					}
				
				}else
				{
					System.out.println("Parece que no hay pokemons esta vez");
				}
			
			
		}catch(Exception e) 
		{
			System.out.println("ingrese un habitat valido");
		}
		
	}

}
