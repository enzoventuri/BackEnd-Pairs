package com.example.backendpares.controller;
import com.example.backendpares.Enum.Evolution;
import com.example.backendpares.Enum.Types;
import com.example.backendpares.model.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/v1/pokemons")
public class PokemonController {

    static List<Pokemon> pokemons = new CopyOnWriteArrayList<>();


    @GetMapping("")
    public List<Pokemon> getAllPokemon() {

        return pokemons;

    }

    @GetMapping("/{id]")
    public Pokemon getPokemon(@PathVariable long id){


        return null;
    }

    @PostMapping("")
    public ResponseEntity<Pokemon>  registerPokemon(@RequestBody Pokemon pokemon){

        pokemons.add(pokemon);

        return ResponseEntity.ok(pokemon);

    }

    @PutMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable long id,@RequestBody Pokemon pokemon){

        return null;

    }

    @DeleteMapping("/{id}")
    public Pokemon deletePokemon(@PathVariable long id){
        return null;
    }

    @GetMapping("")
    public List<Pokemon> getPokemonByType(@RequestParam(required = false)Enum<Types> type){
        return null;
    }

    @GetMapping("")
    public Pokemon getPokemonByName(@RequestParam(required = false)String name){
        return null;
    }

    @GetMapping("")
    public List<Pokemon> getPokemonByMinimumLevel(@RequestParam(required = false)String name){
        return null;
    }

    @PatchMapping("/{id}/{level}")
    public Pokemon updatePokemonLevelById(@PathVariable long id, @PathVariable int level){

        return null;
    }

    @PostMapping("/{id}/{heal}")
    public Pokemon healPokemonById(@PathVariable long id, @PathVariable double heal){

        return null;
    }

    @PostMapping("/{id}/{evolve}")
    public Pokemon healPokemonById(@PathVariable long id, @PathVariable Enum<Evolution> evolve){

        return null;
    }

    @GetMapping("/strongest")
    public Pokemon getStrongestPokemon(){

        return null;
    }


}
