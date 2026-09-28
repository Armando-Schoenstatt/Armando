public class OrdemServico {
private String codigo, cliente, modelo, placa, data, status;
private double ValorEsperado;
private Servico servico;
private Box box;

public OrdemServico(String codigo, String cliente, String modelo, String placa, String data, double valor, Servico servico) {
this.codigo = codigo; this.cliente = cliente; this.modelo = modelo; this.placa = placa;
this.data = data; this.ValorEsperado = valor; this.servico = servico;
this.status = "aberta";
this.box = null;
}

public String getCodigo() { return codigo; }
public String getCliente() {return cliente;}
public String getModelo() {return modelo;}
public String getPlaca() {return placa;}
public String getData() {return data;}
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public Servico getServico() { return servico; }
public Double GetValorEstimado() {return ValorEsperado;}
public Box getBox() { return box; }
public void setBox(Box box) { this.box = box; }


public String toString() {
return "OS: " + codigo + " | Cliente: " + cliente + " | Status: " + status +
" | Box: " + (box != null ? box.getNumero() : "Nenhum");
}
}





