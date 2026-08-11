package com.example.backendpares.controller;
import com.example.backendpares.Enum.Evolution;
import com.example.backendpares.model.Pokemon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Controller responsible by the endpoints related with products
 *
 * <p>This class makes HTTP operations available to do CRUD operations</p>
 *
 * <p>The data are maintained in memory only for learning purposes</p>
 *
 * @author Eduardo Geffert da Silva and Enzo Venturi
 * @since v0.1
 */
@Tag(
        name = "Controller",
        description = "Controller, this is the entry of our system."
)
@RestController
@RequestMapping("/v1/pokemons")
public class PokemonController {

    static List<Pokemon> pokemons = new CopyOnWriteArrayList<>();

    public PokemonController() {
        Pokemon pokemon = new Pokemon("Pikachu", "ELECTRICITY", 100, 20, 5, 70, "BASE", false, false);
        Pokemon pokemon1 = new Pokemon("Charizard", "FIRE", 300, 50, 20, 99, "MAX", false, true);
        Pokemon pokemon2 = new Pokemon("Pikach", "ELECTRICITY", 100, 20, 5, 70, "BASE", false, false);
        Pokemon pokemon3 = new Pokemon("Pikac", "ELECTRICITY", 100, 20, 5, 70, "BASE", false, false);
        Pokemon pokemon4 = new Pokemon("Pika", "ELECTRICITY", 100, 20, 5, 70, "BASE", false, false);

        pokemons.add(pokemon);
        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);
        pokemons.add(pokemon4);
    }


    /**
     * List of registered Pokemons
     *
     * <p>All pokemons are returned</p>
     *
     * @return HTTP response containing the list of Pokemons
     */
    @Operation(
            summary = "Pokemon List",
            description = "Lists every single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on list all pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("")
    public ResponseEntity<List<Pokemon>> getAllPokemon() {
        return ResponseEntity.ok(pokemons);
    }

    /**
     * List a Pokemon
     *
     * <p>Returns a Pokemon</p>
     *
     * @param id ID used as filter (can be @code null)
     * @return HTTP response containing the list of pokemon
     */
    @Operation(
            summary = "Pokemon",
            description = "List a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on list pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Stream<Pokemon>> getPokemon(@Parameter(description = "Product Identifier", example = "1") @PathVariable int id) {
       if(pokemons.stream().noneMatch(pokemon -> pokemon.getId() == id)){
           return ResponseEntity.notFound().build();
       }

        return ResponseEntity.ok(pokemons.stream().filter(
                p -> p.getId() == id)
        );
    }

    /**
     * Register a Pokemon
     *
     * <p>When a pokemon is informed, it is added to the pokemon list</p>
     *
     * @param pokemon Pokemon object to be added
     * @return HTTP response containing the added pokemon
     */
    @Operation(
            summary = "Pokemon Register",
            description = "Registre single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on register pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @PostMapping
    public ResponseEntity<Pokemon> registerPokemon(@RequestBody Pokemon pokemon){
        pokemons.add(pokemon);

        return ResponseEntity.ok(pokemon);
    }

    /**
     * Update a Pokemon
     *
     * <p>When the pokemon's ID is informed a new Pokemon object is passed to update all of its contents</p>
     *
     * @param id
     * @param pokemon
     * @return HTTP response containing the updated pokemon
     */
    @Operation(
            summary = "Pokemon Update",
            description = "Update a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on update pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Stream<Pokemon>> updatePokemon(@Parameter(description = "Product Identifier", example = "1")
                                                             @PathVariable int id,@RequestBody Pokemon pokemon){
        return ResponseEntity.ok(pokemons.stream().filter(
                p -> p.getId() == id).map(p -> pokemons.set(p.getId()-1,pokemon)));
    }

    /**
     * Delete a Pokemon
     *
     * <p>When the pokemon's ID is informed the pokemon's data is to be deleted</p>
     *
     * @param id
     * @return HTTP response containing the deleted pokemon
     */
    @Operation(
            summary = "Delete Pokemon",
            description = "Delete a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on delete pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Stream<Boolean>> deletePokemon(@Parameter(description = "Product Identifier", example = "1")@PathVariable int id){
        return ResponseEntity.ok(pokemons.stream().filter(p -> p.getId() == id).map(p -> pokemons.remove(p)));
    }

    /**
     * Retrieve Pokemons by Type
     *
     * <p>When a type of pokemon is informed, it will query through the list and return the pokemons that are of the same type</p>
     *
     * @param type
     * @return HTTP response containing the pokemons related with the type
     */
    @Operation(
            summary = "List pokemon",
            description = "Lists a single pokemon by type"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on list pokemon by type"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("/type")
    public ResponseEntity<Stream<Pokemon>> getPokemonByType(@Parameter(description = "Type identifier", example = "ICE")@RequestParam String type){
        return ResponseEntity.ok(pokemons.stream().filter(p -> p.getType().toString().equals(type)));
    }

    /**
     * Retrieve Pokemons by Name
     *
     * <p>When a name of pokemon is informed, it will query through the list and return the pokemons that are of the same name</p>
     *
     * @param name
     * @return HTTP response containing the pokemons name
     */
    @Operation(
            summary = "List pokemon",
            description = "Lists a single pokemon by name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on list pokemon by name"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("/name")
    public ResponseEntity<Stream<Pokemon>> getPokemonByName(@Parameter(description = "Name identifier", example = "name")@RequestParam String name){
        return ResponseEntity.ok(pokemons.stream().filter(p -> p.getName().equals(name)));
    }

    /**
     * Retrieve Pokemons by Minimum Level
     *
     * <p>When a minimum level is informed, it will query through the list and return the pokemons that are of the same level or above</p>
     *
     * @param level
     * @return HTTP response containing a list of pokemon
     */
    @Operation(
            summary = "List pokemon",
            description = "Lists a single pokemon by level"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on list pokemon by level"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("/level")
    public ResponseEntity<Stream<Pokemon>> getPokemonByMinimumLevel(@Parameter(description = "Product Identifier", example = "1")@RequestParam int level){
        return ResponseEntity.ok(pokemons.stream().filter(p -> p.getLevel() > level));
    }

    /**
     * Update Pokemon Level
     *
     * <p>When an ID and level is informed, it will add the pokemon's level</p>
     *
     * @param id
     * @param level
     * @return HTTP response containing the updated pokemon
     */
    @Operation(
            summary = "Level Up Pokemon",
            description = "Level up a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on levelling up a pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @PatchMapping("/{id}/level")
    public ResponseEntity<Pokemon> updatePokemonLevelById(@Parameter(description = "Product Identifier", example = "1")@PathVariable long id, @Parameter(description = "Level Identifier", example = "10")@RequestParam int level) {
        Optional<Pokemon> pokemon = pokemons.stream().filter(p -> p.getId() == id).findFirst();

        Pokemon openedPokemon = pokemon.get();
        openedPokemon.setLevel(level);

        return ResponseEntity.ok(openedPokemon);
    }

    /**
     * Heal Pokemon
     *
     * <p>When an ID and healing is informed, it will add the pokemon's health</p>
     *
     * @param id
     * @param heal
     * @return HTTP response containing the healed pokemon
     */
    @Operation(
            summary = "Heal Up Pokemon",
            description = "Heal up a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on healing up a pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @PostMapping("/{id}/heal")
    public ResponseEntity<Pokemon> healPokemonById(@Parameter(description = "Product Identifier", example = "1")@PathVariable long id, @Parameter(description = "Heal identifier", example = "1")@RequestParam double heal){
        Optional<Pokemon> pokemon = pokemons.stream().filter(p -> p.getId() == id).findFirst();

        Pokemon openedPokemon = pokemon.get();
        openedPokemon.setHealth(heal);

        return ResponseEntity.ok(openedPokemon);
    }

    /**
     * Evolve Pokemon
     *
     * <p>When an ID and evolution is informed, it will evolve the pokemon corresponding its line of evolution</p>
     *
     * @param id
     * @param evolve
     * @return HTTP response containing the evolved pokemon
     */
    @Operation(
            summary = "Evolve a Pokemon",
            description = "Evolve a single pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on evolving a pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @PostMapping("/{id}/evolve")
    public ResponseEntity<Pokemon> evolvePokemonById(@Parameter(description = "Product Identifier", example = "1")@PathVariable long id, @RequestParam String evolve){
        Optional<Pokemon> pokemon = pokemons.stream().filter(p -> p.getId() == id).findFirst();

        Pokemon openedPokemon = pokemon.get();
        openedPokemon.setEvolution(Evolution.valueOf(Evolution.class, evolve));

        return ResponseEntity.ok(openedPokemon);
    }

    /**
     * Retrieve Strongest Pokemon
     *
     * <p>Queries through the list to search which Pokemon has the highest attack stat</p>
     *
     * @return HTTP response containing the strongest pokemon
     */
    @Operation(
            summary = "List Pokemon",
            description = "List the strongest pokemon"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success on listing a pokemon"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server side error"
            )
    })
    @GetMapping("/strongest")
    public ResponseEntity<Pokemon> getStrongestPokemon(){
        double strongest = 0;
        Pokemon strongestPokemon = null;

        for (Pokemon p : pokemons) {
            if (p.getAttack() > strongest) {
                strongestPokemon = p;
                strongest = p.getAttack();
            }
        }

        return ResponseEntity.ok(strongestPokemon);
    }


}
