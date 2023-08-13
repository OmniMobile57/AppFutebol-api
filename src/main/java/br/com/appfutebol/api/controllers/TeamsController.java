package br.com.appfutebol.api.controllers;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.appfutebol.api.requests.PlayersRequest;
import br.com.appfutebol.api.requests.PlayersToDrawRequest;
import br.com.appfutebol.api.responses.PlayersResponse;
import br.com.appfutebol.api.responses.TeamsResponse;
import br.com.appfutebol.exceptions.ExceptionResponse;
import br.com.appfutebol.services.TeamsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Team", description = "Endpoints for managing teams")
@RequestMapping("/v1/teams")
@RestController
public class TeamsController {

  private final TeamsService teamsService;

  @Operation(
    summary = "Draw Teams", description = "Draw times according to the score of the players", tags = {
    "Player", "FootySpace"},
    responses = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PlayersResponse.class))), description = "OK"),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Bad Request"),
      @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class)), description = "Internal Server Error")
    }
  )
  @PostMapping("/draw")
  public ResponseEntity<List<TeamsResponse>> drawTeams(
    @RequestBody List<PlayersToDrawRequest> playersRequests, @RequestParam int amountOfTeams) {
    return ResponseEntity.status(OK)
      .body(teamsService.drawTeams(playersRequests, amountOfTeams));
  }
}
