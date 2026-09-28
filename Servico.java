class Servico {
private String nome, categoria;
private double tempo, valor;

public Servico(String nome, double tempo, double valor, String categoria) {
this.nome = nome; this.tempo = tempo; this.valor = valor; this.categoria = categoria;
}

public String getCategoria() { return categoria; }
public Double getTempo() {return tempo;}

public String toString() {
return nome + " (" + categoria + ") - R$ " + valor;
}
}



