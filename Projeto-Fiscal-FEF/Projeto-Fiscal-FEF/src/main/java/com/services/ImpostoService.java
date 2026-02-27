package com.services;


import org.springframework.stereotype.Service;

@Service
public class ImpostoService {

    public double calcularImposto(double valor, String TipoTransacao) {

        if(valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
             }

        switch(TipoTransacao.toUpperCase()) {
            case "VENDA":
                return valor * 0.10;
                case "SERVICO":
                return valor * 0.15;
                case "PRODUTO":
                return valor * 0.08;

                default:
                    throw new IllegalArgumentException("Tipo de transação inválido.");
        }


    }


}
