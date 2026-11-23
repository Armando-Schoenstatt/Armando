public class Mecanico {
private String nome, cpf, Area, tel;
private Box boxResponsavel;

public Mecanico(String nome, String cpf, String Area, String telefone) {
this.nome = nome; this.cpf = cpf; this.Area = Area; this.tel = telefone;
}

public String getNome() { return nome; }
public String getCPF() {return cpf;}
public String getTelefone() {return tel;}
public Box getBoxResponsavel() { return boxResponsavel; }
public void setBoxResponsavel(Box box) { this.boxResponsavel = box; }

public String toString() {
return nome + " (Esp: " + Area + ") - Box: " + (boxResponsavel != null ? boxResponsavel.getNumero() : "Nenhum");
}
}
