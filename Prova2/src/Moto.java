
public class Moto extends Veiculo implements Inspecionavel {
    private final int cilindrada;
    private int quantidadeInspecoes;

    public Moto(String placa, String modelo, int anoFabricacao,
                double valorDiaria, double quilometragem, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
        if (cilindrada <= 0) {
            throw new IllegalArgumentException("A cilindrada deve ser positiva.");
        }
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }
    public int getQuantidadeInspecoes() { return quantidadeInspecoes; }

    @Override
    public void realizarInspecao() {
        verificarPodeInspecionar();
        quantidadeInspecoes++;
        System.out.println("Inspecao da moto " + getPlaca() + " realizada com sucesso.");
    }

    @Override
    public String toString() {
        return "MOTO | " + descricaoComum() + " | Cilindrada: " + cilindrada + " cc"
                + " | Inspecoes: " + quantidadeInspecoes;
    }
}
