package br.com.docdoctor.controller;

import br.com.docdoctor.dto.UserPaginationDTO;
import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(
        name = "Usuários",
        description = "API para gerenciamento de usuários"
)
@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "Criar novo usuário", description = "Registra um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Usuário já existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequest) {
        UserResponseDTO createdUser = service.create(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO updatedUser = service.update(id);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Listar usuários",
            description = "Retorna usuários com paginação. Sem parâmetros retorna página 1 com 10 itens.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página (0-based)", example = "0", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0")),
                    @Parameter(name = "size", description = "Quantidade de itens por página", example = "10", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10")),
                    @Parameter(name = "sort", description = "Critério de ordenação (campo,direção)", example = "nome,asc", in = ParameterIn.QUERY,schema = @Schema(type = "string")
                    )
            },
            responses = {@ApiResponse(responseCode = "200",description = "Usuários paginados",content = @Content(schema = @Schema(implementation = UserPaginationDTO.class))),
                    @ApiResponse(responseCode = "204",description = "Nenhum usuário encontrado")
            }
    )
    @GetMapping
    public ResponseEntity<UserPaginationDTO> listAll(
            @Parameter(description = "Parâmetros de paginação (opcional)")
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        UserPaginationDTO result = service.listAll(pageable);
        return ResponseEntity.ok(result);
    }
    
    @Operation(summary = "Excluir usuário", description = "Remove um usuário existente pelo seu ID")
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}