package taller2;

import java.util.ArrayList;
import java.util.List;

public class Entrenador 
{
private String nombre;
private List<String> medallas = new ArrayList<>();;
private List<Pokemon> PC = new ArrayList<>();
private List<Pokemon> equipo = new ArrayList<>();


public Entrenador(String nombre) {
	this.nombre = nombre;
}


public void agregarPokemon(Pokemon pokemon) 
{
	
	boolean repetido = verificarRepetidos(pokemon.getNombre());
	
	
	if(repetido)
	{
		System.out.println("Este pokemon ya forma parte de tu equipo/PC");
	
	}else if (equipo.size()<6) 
	{
		
	System.out.println("Se agrego " + pokemon.getNombre() + " a tu equipo");
	equipo.add(pokemon);
		
	}else
	{
		System.out.println("Tu equipo esta lleno " + pokemon.getNombre() + " se fue al PC");
		PC.add(pokemon);
	}
	
}


public boolean verificarRepetidos(String nombre) 
{
	for (Pokemon p : equipo) {
        if (p.getNombre().equalsIgnoreCase(nombre)) return true;
    }
    for (Pokemon p : PC) {
        if (p.getNombre().equalsIgnoreCase(nombre)) return true;
    }
	return false;
}

public void mostrarEquipo()
{
	if(equipo.size()== 0)
	{
		System.out.println("No tienes Pokemons en tu equipo");
	}else 
	{
		
		System.out.println("Tu equipo pokemon:");
		for (Pokemon p: equipo)
		{
			System.out.print(p + "||");
			
		}
		System.out.println();
	}
}
public void mostrarPC()
{
	if(PC.size()== 0)
	{
		System.out.println("No tienes Pokemons almacenados en el PC");
	}else 
	{
		int i = 0;
		System.out.println("Tus pokemons almacenados:");
		for (Pokemon p: PC)
		{
			i++;
			System.out.println(i + ") " + p );
			
		}
	}
}

public void  agregarMedallas(String medalla)
{
	medallas.add(medalla);
}





public List<String> getMedallas() {
	return medallas;
}


public String getNombre() {
	return nombre;
}
public void cambiarenPC(int opcion, int pos) 
{
	
		Pokemon seleccionado = PC.get(opcion);
		PC.set(opcion,equipo.get(pos));
		equipo.set(pos, seleccionado);
		System.out.println("Se cambio correctamente su pokemon");
}


public List<Pokemon> getPC() {
	return PC;
}


public List<Pokemon> getEquipo() {
	return equipo;
}




public void curarPokemons()
{
	for (Pokemon p : equipo)
	{
		if(!p.isVivo())
		{
			p.setVivo(true);
		}

	}
	System.out.println("Todos tus pokemons han sido curados");
}



}


