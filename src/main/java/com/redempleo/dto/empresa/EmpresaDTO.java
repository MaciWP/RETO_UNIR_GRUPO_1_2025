package com.redempleo.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private String razonSocial;
    private String descripcion;
    private String direccionFiscal;
    private String pais;
    private Long id;

    // Constructor sin id
    public EmpresaDTO(String razonSocial, String descripcion, String direccionFiscal, String pais) {
        this.razonSocial = razonSocial;
        this.descripcion = descripcion;
        this.direccionFiscal = direccionFiscal;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + razonSocial;
    }
}