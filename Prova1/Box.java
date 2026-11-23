public class Box {
private int num;
private int capacidade;
private String tipoServico, localizacao;
private Mecanico mecanico;

public Box(int numero, String tipoServico, int capacidade, String localizacao) {
this.num = numero; this.tipoServico = tipoServico; this.capacidade = capacidade; this.localizacao = localizacao;
}

public int getNumero() { return num; }
public String getTipoServico() { return tipoServico; }
public Mecanico getMecanico() { return mecanico; }
public void setMecanico(Mecanico m) { this.mecanico = m; }
public int getcapacidade() {return capacidade;}
public String getlocalizacao() {return localizacao;}

public String toString() {
return "Box " + num + " [" + tipoServico + "] - Mec: " + (mecanico != null ? mecanico.getNome() : "Nenhum");
}
}



