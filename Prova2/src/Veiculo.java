import java.util.Locale;


public abstract class Veiculo {
    private final String placa;
    private final String modelo;
    private final int anoFabricacao;
    private final double valorDiaria;
    private double quilometragem;
    private StatusVeiculo status;

    public Veiculo(String placa, String modelo, int anoFabricacao,
                   double valorDiaria, double quilometragem) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa e obrigatoria.");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo e obrigatorio.");
        }
        if (anoFabricacao < 1886 || anoFabricacao > 2100) {
            throw new IllegalArgumentException("Ano de fabricacao invalido.");
        }
        if (!Double.isFinite(valorDiaria) || valorDiaria <= 0) {
            throw new IllegalArgumentException("A diaria deve ser maior que zero.");
        }
        if (!Double.isFinite(quilometragem) || quilometragem < 0) {
            throw new IllegalArgumentException("Quilometragem invalida.");
        }
        this.placa = placa.trim().toUpperCase(Locale.ROOT);
        this.modelo = modelo.trim();
        this.anoFabricacao = anoFabricacao;
        this.valorDiaria = valorDiaria;
        this.quilometragem = quilometragem;
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public int getAnoFabricacao() { return anoFabricacao; }
    public double getValorDiaria() { return valorDiaria; }
    public double getQuilometragem() { return quilometragem; }
    public StatusVeiculo getStatus() { return status; }


    public void marcarAlugado() {
        if (status != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("Veiculo indisponivel para aluguel: " + placa);
        }
        status = StatusVeiculo.ALUGADO;
    }

    public void colocarEmManutencao() {
        if (status == StatusVeiculo.ALUGADO) {
            throw new IllegalStateException("Devolva o veiculo antes de encaminha-lo para manutencao.");
        }
        if (status == StatusVeiculo.MANUTENCAO) {
            throw new IllegalStateException("O veiculo ja esta em manutencao.");
        }
        status = StatusVeiculo.MANUTENCAO;
    }

    protected void verificarPodeInspecionar() {
        if (status == StatusVeiculo.ALUGADO) {
            throw new IllegalStateException("Nao e possivel inspecionar um veiculo alugado.");
        }
    }

    protected static void validarNota(int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
    }

    protected String descricaoComum() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Placa: %s | Modelo: %s | Ano: %d | Diaria: R$ %.2f | KM: %.1f | Status: %s",
                placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
    }

    @Override
    public String toString() {
        return descricaoComum();
    }
}
