package br.com.appfutebol.api.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.responses.PlayersResponse;
import br.com.appfutebol.exceptions.ExceptionResponse;
import br.com.appfutebol.services.PlayersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Player", description = "Endpoints for managing players")
@RequestMapping("/v1/players")
@RestController
public class PlayersController {

  private final PlayersService playersService;

  @Operation(
    summary = "Save Player", description = "Create or update player if exists", tags = {
    "Player", "FootySpace"},
    responses = {
      @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PlayersResponse.class)), description = "Created"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @PostMapping("/{footySpaceId}")
  public ResponseEntity<PlayersResponse> save(@RequestBody PlayersRequest playerRequest,
    @PathVariable UUID footySpaceId,
    @RequestParam(required = false) UUID playerId,
    @RequestParam(required = false) UUID personId) {
    return ResponseEntity.status(CREATED)
      .body(playersService.save(playerRequest, footySpaceId, playerId, personId));
  }

  @Operation(
    summary = "Delete Player", description = "Delete player by id", tags = {"Player"},
    responses = {
      @ApiResponse(responseCode = "204", content = @Content, description = "Created"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @DeleteMapping("/{playerId}")
  public ResponseEntity<Void> deletePlayer(@PathVariable UUID playerId) {
    playersService.deletePlayer(playerId);
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Operation(
    summary = "Find All Players", description = "Find all players by FootySpace", tags = {
    "Player", "FootySpace"},
    responses = {
      @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PlayersResponse.class))), description = "Created"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @GetMapping("footy-space/{footySpaceId}")
  public ResponseEntity<List<PlayersResponse>> findAllByFootySpace(
    @PathVariable UUID footySpaceId) {
    return ResponseEntity.status(OK).body(playersService.findAllByFootySpace(footySpaceId));
  }

  @Operation(
    summary = "Find All Players By Name", description = "Find all players by FootySpace and name", tags = {
    "Player", "FootySpace"},
    responses = {
      @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PlayersResponse.class))), description = "Created"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @GetMapping("footy-space/{footySpaceId}/players")
  public ResponseEntity<List<PlayersResponse>> findAllByNameAndFootySpace(@RequestParam String name,
    @PathVariable UUID footySpaceId) {
    return ResponseEntity.status(OK)
      .body(playersService.findAllByNameAndFootySpace(name, footySpaceId));
  }
}
