package com.alex.isthisactuallyabill.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    @NotBlank(message = "CPT API URL must not be blank.")
    private String cptUrl;

    @NotBlank(message = "ICD API URL must not be blank.")
    private String icdUrl;

    @NotBlank(message = "NPI API URL must not be blank.")
    private String npiUrl;
}