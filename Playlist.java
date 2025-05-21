package model;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String nome;
    private String dono;
    private ArrayList<Musica> musicas;

    public Playlist(String nome, String dono) {
        this.nome = nome;
        this.dono = dono;
        this.musicas = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDono() { return dono; }
    public void setDono(String dono) { this.dono = dono; }

    public ArrayList<Musica> getMusicas() { return musicas; }
    public void setMusicas(ArrayList<Musica> musicas) { this.musicas = musicas; }
}
