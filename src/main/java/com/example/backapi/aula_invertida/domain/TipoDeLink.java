package com.example.backapi.aula_invertida.domain;

public enum TipoDeLink {
    VIDEO (1),
    IMAGEM (2),
    TEXTO (3),
    QUESTIONARIO (4);


    private Integer codigo;

    TipoDeLink(Integer codigo) {

        this.codigo = codigo;
    }

    public static TipoDeLink toEnum (Integer codigo){
        for (TipoDeLink tipoDeLink : TipoDeLink.values()){
            if(codigo.equals(tipoDeLink.getCodigo())){
                return tipoDeLink;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getCodigo() {
        return this.codigo;

    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
