package taller2;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio 
{
	
private String Lider;
private String estado;
private List<Pokemon> pokemons = new ArrayList<>();


public Gimnasio( String lider, String estado) {
	this.Lider = lider;
	this.estado = estado;
}

public void agregarPokemon(Pokemon pokemon) 
{
pokemons.add(pokemon);
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public String getLider() {
	return Lider;
}

public List<Pokemon> getPokemons() {
	return pokemons;
}


}
