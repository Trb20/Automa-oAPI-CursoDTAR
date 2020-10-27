package com.montanha.isolada;

import com.montanha.config.Configuracoes;
import com.montanha.factory.UsuarioDataFactory;
import com.montanha.factory.ViagemDataFactory;
import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static io.restassured . RestAssured . *;
import static io.restassured.matcher . RestAssuredMatchers . *;
import static org.hamcrest . Matchers . *;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ViagensTest {

    private String token;
    private String tokenUsuario;

    @Before
    public void setUp() {
        //Configurações REST-Assured
        Configuracoes configuracoes = ConfigFactory.create(Configuracoes.class);
        baseURI = configuracoes.baseURI();
        port = configuracoes.port();
        basePath = configuracoes.basePath();

        Usuario usuarioAdmin = UsuarioDataFactory.criarUsuarioAdmin();

        this.token = given()
            .contentType(ContentType.JSON)
            .body(usuarioAdmin)
        .when()
            .post("/v1/auth")
        .then()
            .extract()
                .path("data.token");

        Usuario usuario = UsuarioDataFactory.criaUsuario();

        this.tokenUsuario = given()
            .contentType(ContentType.JSON)
            .body(usuario)
        .when()
            .post("/v1/auth")
        .then()
            .extract()
            .path("data.token");
    }

    @Test
    public void testCadastroViagemValida() throws IOException {
        Viagem cadViagem = ViagemDataFactory.cadastraViagemValida();

        given()
            .contentType(ContentType.JSON)
            .body(cadViagem)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(201)
                .body("data.localDeDestino", equalTo("Feira de santana"))
                .body("data.acompanhante", equalTo("Pai"))
                .body("data.regiao", equalToIgnoringCase("Sul"));
    }

    @Test
    public void testCadViagemSemDestino() throws IOException {
        Viagem cadViagem = ViagemDataFactory.cadViagemSemLocalDestino();

        given()
            .contentType(ContentType.JSON)
            .body(cadViagem)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(400)
                .body("errors.defaultMessage", hasItem("Local de Destino deve estar entre 3 e 40 caracteres"));
    }

    @Test
    public void testCadastroViagemContrato() throws IOException {
        Viagem cadViagem = ViagemDataFactory.cadastraViagemValida();

        given()
            .contentType(ContentType.JSON)
            .body(cadViagem)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/postV1Viagens.json"));
    }

    @Test
    public void testConsultaUmaViagem() throws IOException {
        given()
            .contentType(ContentType.JSON)
            .params("ID", 1)
            .header("Authorization", tokenUsuario)
        .when()
            .get("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(200)
                .body("data.acompanhante", hasItem("Leticia"));
    }
}
