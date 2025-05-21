package model;

public class Historico {
    private int id;
    private String usuario;
    private String tipo;
    private String descricao;

    public Historico() {}

    public Historico(int id, String usuario, String tipo, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
