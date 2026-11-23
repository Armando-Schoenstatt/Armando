
public class Van extends Veiculo implements Avaliavel, Inspecionavel {
    private final int maxPassageiros;
    private Integer nota;
    private int quantidadeInspecoes;

    public Van(String placa, String modelo, int anoFabricacao,
               double valorDiaria, double quilometragem, int maxPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        if (maxPassageiros <= 0) {
            throw new IllegalArgumentException("O numero de passageiros deve ser positivo.");
        }
        this.maxPassageiros = maxPassageiros;
    }

    public int getMaxPassageiros() { return maxPassageiros; }
    public Integer getNota() { return nota; }
    public int getQuantidadeInspecoes() { return quantidadeInspecoes; }

    @Override
    public void avaliar(int nota) {
        validarNota(nota);
        this.nota = nota;
    }

    @Override
    public void realizarInspecao() {
        verificarPodeInspecionar();
        quantidadeInspecoes++;
        System.out.println("Inspecao da van " + getPlaca() + " realizada com sucesso.");
    }

    @Override
    public String toString() {
        return "VAN | " + descricaoComum() + " | Max. passageiros: " + maxPassageiros
                + " | Avaliacao: " + (nota == null ? "Nao avaliado" : nota + "/10")
                + " | Inspecoes: " + quantidadeInspecoes;
    }
}
