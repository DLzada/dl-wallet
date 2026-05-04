package br.com.daniel.dl_wallet.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DL Wallet API")
                        .version("1.0")
                        .description("API para gerenciamento de carteira financeira pessoal, com controle de entradas, saídas e resumos por categoria."));
    }
}
