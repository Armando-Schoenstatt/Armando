
public class Carro extends Veiculo implements Avaliavel {
    private final int quantidadePortas;
    private Integer nota;

    public Carro(String placa, String modelo, int anoFabricacao,
                 double valorDiaria, double quilometragem, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        if (quantidadePortas <= 0) {
            throw new IllegalArgumentException("A quantidade de portas deve ser positiva.");
        }
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadePortas() { return quantidadePortas; }
    public Integer getNota() { return nota; }

    @Override
    public void avaliar(int nota) {
        validarNota(nota);
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "CARRO | " + descricaoComum() + " | Portas: " + quantidadePortas
                + " | Avaliacao: " + (nota == null ? "Nao avaliado" : nota + "/10");
    }
}
