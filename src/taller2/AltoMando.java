package taller2;

import java.util.ArrayList;
import java.util.List;

public class AltoMando 
{

private String Lider; 
private List<Pokemon> pokemons = new ArrayList<>();


public AltoMando( String Lider) {

	this.Lider = Lider;
}

public void agregarPokemon(Pokemon pokemon)
{
	pokemons.add(pokemon);
}

public String getLider() {
	return Lider;
}

public List<Pokemon> getPokemons() {
	return pokemons;
}


}
