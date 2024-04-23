package org.example.document_v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.document_v2.dto.CoffeeRequest;
import org.example.document_v2.model.Coffee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private ConcurrentHashMap<String, Coffee> coffees;
    public CoffeeController(){
        coffees = new ConcurrentHashMap<>();
        coffees.put("1", new Coffee("CPC_1", "Cappuccino", 5, "This is an espresso-based coffee drink"));
        coffees.put("2", new Coffee("TDN_2", "Traditional Coffee", 2, "This is an traditional coffee drink"));
    }
    @Operation(
            tags = "Coffee",
            summary = "Gets list of all coffee",
            description = "Coffee must exist",
            operationId = "listAllCoffee"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = Coffee.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public List<Coffee> getCoffees() {
        return coffees.values().stream().toList();
    }

    @Operation(
            tags = "Coffee",
            summary = "Create new Coffee",
            description = "Coffee's ID must exist",
            operationId = "searchByID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = Coffee.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid ID coffee", content = @Content),
            @ApiResponse(responseCode = "404", description = "Coffee not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public Coffee createCoffee(@RequestBody CoffeeRequest coffeeRequest) {
        String uuid = UUID.randomUUID().toString();
        Coffee newCoffee = new Coffee(uuid, coffeeRequest.type(), coffeeRequest.price(), coffeeRequest.description());
        coffees.put(uuid, newCoffee);
        return newCoffee;
    }


    @Operation(
            tags = "Coffee",
            summary = "Gets coffee by ID",
            description = "Coffee's ID must exist",
            operationId = "searchByID",
            parameters = {
                    @Parameter(name = "id", description = "The path variable",example = "CPC_1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = Coffee.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid ID coffee", content = @Content),
            @ApiResponse(responseCode = "404", description = "Coffee not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/{id}")
    public Coffee getCoffeeByID(@PathVariable String id) {
        return coffees.get(id);
    }

    @Operation(
            tags = {"Coffee"},
            operationId = "updateCoffee",
            summary = "Update exist Coffee",
            description = "Coffee's ID already exist",
            parameters = {
                    @Parameter(name = "id", description = "The path variable",example = "CPC_1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(
                            schema = @Schema(implementation = Coffee.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "400", description = "Invalid ID coffee", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Coffee not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PutMapping(value = "/{id}")
    public Coffee updateCoffeeByID(@PathVariable String id, @RequestBody CoffeeRequest coffeeRequest) {
        Coffee updateCoffee = new Coffee(id, coffeeRequest.type(), coffeeRequest.price(), coffeeRequest.description());
        coffees.put(id, updateCoffee);
        return updateCoffee;
    }
    @Operation(
            tags = {"Coffee"},
            operationId = "deleteCoffee",
            summary = "Delete exist Coffee",
            description = "Coffee's ID need to exist",
            parameters = {
                    @Parameter(name = "id", description = "The path variable",example = "CPC_1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete Successful", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid ID coffee", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Coffee not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @DeleteMapping(value = "/{id}")
    public Coffee deleteCoffeeByID(@PathVariable String id) {
        Coffee removedCoffee = coffees.remove(id);
        return removedCoffee;
    }

//    @PatchMapping("/{id}")
//    public Coffee updateCoffeePrice(@PathVariable String id, @RequestBody Map<String, Object> coffeePrice) {
//        for (Map.Entry<String, Object> entry : coffeePrice.entrySet()) {
//            String key = entry.getKey();
//            if(key.equals("price")) {
//                Integer newPrice = Integer.valueOf(String.valueOf(entry.getValue())) ;
//                coffeePrice.put(key, newPrice);
//            }
//        }
//        return updateCoffeePrice(id, coffeePrice);
//    }
}
