package com.ifce.edital360.model.edital;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

@Embeddable
public class Cota {
    @Min(0)
    private Integer vagasPcd;

    @Min(0)
    private Integer vagasNegros;

    @Min(0)
    private Integer vagasIndigenas;

    private String outrasCotas;

    public Integer getVagasPcd() {
        return vagasPcd;
    }

    public void setVagasPcd(Integer vagasPcd) {
        this.vagasPcd = vagasPcd;
    }

    public Integer getVagasNegros() {
        return vagasNegros;
    }

    public void setVagasNegros(Integer vagasNegros) {
        this.vagasNegros = vagasNegros;
    }

    public Integer getVagasIndigenas() {
        return vagasIndigenas;
    }

    public void setVagasIndigenas(Integer vagasIndigenas) {
        this.vagasIndigenas = vagasIndigenas;
    }

    public String getOutrasCotas() {
        return outrasCotas;
    }

    public void setOutrasCotas(String outrasCotas) {
        this.outrasCotas = outrasCotas;
    }
}
