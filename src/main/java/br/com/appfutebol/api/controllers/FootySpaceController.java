package br.com.appfutebol.api.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.appfutebol.api.requests.FootySpaceRequest;
import br.com.appfutebol.api.responses.FootySpaceResponse;
import br.com.appfutebol.exceptions.ExceptionResponse;
import br.com.appfutebol.services.FootySpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "FootySpace", description = "Endpoints for managing footy spaces")
@RequestMapping("/v1/footy-space")
@RestController
public class FootySpaceController {

  private final FootySpaceService footySpaceService;


  @Operation(
    summary = "Save FootySpace", description = "Create or update footy space if exists", tags = {
    "FootySpace"},
    responses = {
      @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FootySpaceResponse.class)), description = "Created"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @PostMapping
  public ResponseEntity<FootySpaceResponse> save(
    @RequestBody @Valid FootySpaceRequest footySpaceRequest,
    @RequestParam(required = false) UUID footySpaceId,
    @RequestParam(required = false) UUID personId) {
    return ResponseEntity.status(CREATED)
      .body(footySpaceService.save(footySpaceRequest, footySpaceId, personId));
  }

  @Operation(
    summary = "Delete FootySpace", description = "Delete footy space by id", tags = {"FootySpace"},
    responses = {
      @ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Bad Request"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @DeleteMapping("/{ownerId}/{footySpaceId}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID ownerId,
    @PathVariable UUID footySpaceId) {
    footySpaceService.deleteById(ownerId, footySpaceId);
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Operation(
    summary = "Leave FootySpace", description = "Leave footy space", tags = {"FootySpace"},
    responses = {
      @ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Not Found"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @PutMapping("/{playerId}/{footySpaceId}")
  public ResponseEntity<Void> leaveFootySpace(@PathVariable UUID playerId, @PathVariable UUID footySpaceId) {
    footySpaceService.leaveFootySpace(playerId, footySpaceId);
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Operation(
    summary = "Find FootySpace", description = "Find footy space by id", tags = {"FootySpace"},
    responses = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FootySpaceResponse.class)), description = "OK"),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Bad Request"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @GetMapping("/{footySpaceId}")
  public ResponseEntity<FootySpaceResponse> findById(@PathVariable UUID footySpaceId) {
    return ResponseEntity.status(OK).body(footySpaceService.findById(footySpaceId));
  }

  @Operation(
    summary = "Find All FootySpace", description = "Find all footy space by person id", tags = {
    "FootySpace", "Person"},
    responses = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = FootySpaceResponse.class))), description = "OK"),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Bad Request"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @GetMapping("/person/{personId}")
  public ResponseEntity<List<FootySpaceResponse>> findAll(@PathVariable UUID personId) {
    return ResponseEntity.status(OK).body(footySpaceService.findByAllByPerson(personId));
  }
}
