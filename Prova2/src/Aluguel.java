import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Aluguel {
    private final int codigo;
    private final int dias;
    private final Map<Veiculo, Integer> quilometragensPrevistas = new LinkedHashMap<>();

    public Aluguel(int codigo, int dias) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O codigo do aluguel deve ser positivo.");
        }
        if (dias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias deve ser positiva.");
        }
        this.codigo = codigo;
        this.dias = dias;
    }

    public int getCodigo() { return codigo; }
    public int getDias() { return dias; }
    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(new ArrayList<>(quilometragensPrevistas.keySet()));
    }
    public int getKmPrevistos(Veiculo veiculo) {
        Integer km = quilometragensPrevistas.get(veiculo);
        if (km == null) throw new IllegalArgumentException("Veiculo nao faz parte deste aluguel.");
        return km;
    }


    public abstract String getTipo();
    public abstract String getBeneficios();


    protected void validarQuilometragem(int kmPrevistos) {
        if (kmPrevistos < 0) {
            throw new IllegalArgumentException("A quilometragem prevista nao pode ser negativa.");
        }
    }

    public void adicionarVeiculo(Veiculo veiculo, int kmPrevistos) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veiculo obrigatorio.");
        }
        if (quilometragensPrevistas.containsKey(veiculo)) {
            throw new IllegalStateException("Este veiculo ja esta associado ao aluguel.");
        }
        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("Veiculo indisponivel para aluguel.");
        }
        validarQuilometragem(kmPrevistos);
        veiculo.marcarAlugado();
        quilometragensPrevistas.put(veiculo, kmPrevistos);
    }

    public double calcularValor() {
        double total = 0.0;
        for (Veiculo veiculo : quilometragensPrevistas.keySet()) {
            total += veiculo.getValorDiaria() * dias;
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.forLanguageTag("pt-BR"),
                "%s | Codigo: %d | Dias: %d | %s | Total: R$ %.2f",
                getTipo(), codigo, dias, getBeneficios(), calcularValor()));
        if (quilometragensPrevistas.isEmpty()) {
            sb.append("\n   Nenhum veiculo associado.");
        } else {
            for (Map.Entry<Veiculo, Integer> item : quilometragensPrevistas.entrySet()) {
                Veiculo veiculo = item.getKey();
                sb.append("\n   - ").append(veiculo.getPlaca()).append(" - ")
                        .append(veiculo.getModelo()).append(" | KM previstos: ")
                        .append(item.getValue());
            }
        }
        return sb.toString();
    }
}
