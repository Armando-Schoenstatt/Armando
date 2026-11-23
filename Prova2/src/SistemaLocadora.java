import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SistemaLocadora {
    private final List<Veiculo> veiculos = new ArrayList<>();
    private final List<Aluguel> alugueis = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null) throw new IllegalArgumentException("Veiculo obrigatorio.");
        if (buscarVeiculo(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Ja existe veiculo com esta placa.");
        }
        veiculos.add(veiculo);
    }

    public void cadastrarAluguel(Aluguel aluguel) {
        if (aluguel == null) throw new IllegalArgumentException("Aluguel obrigatorio.");
        if (buscarAluguel(aluguel.getCodigo()) != null) {
            throw new IllegalArgumentException("Ja existe aluguel com este codigo.");
        }
        alugueis.add(aluguel);
    }

    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public List<Aluguel> getAlugueis() {
        return Collections.unmodifiableList(alugueis);
    }

    public Veiculo buscarVeiculo(String placa) {
        if (placa == null) return null;
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa.trim())) return veiculo;
        }
        return null;
    }

    public Aluguel buscarAluguel(int codigo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCodigo() == codigo) return aluguel;
        }
        return null;
    }


    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatus() == StatusVeiculo.DISPONIVEL) resultado.add(veiculo);
        }
        return resultado;
    }

    public List<Veiculo> listarInspecionaveis() {
        List<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Inspecionavel && veiculo.getStatus() != StatusVeiculo.ALUGADO) {
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public void associarVeiculo(String placa, int codigoAluguel, int kmPrevistos) {
        Veiculo veiculo = exigirVeiculo(placa);
        Aluguel aluguel = exigirAluguel(codigoAluguel);
        aluguel.adicionarVeiculo(veiculo, kmPrevistos);
    }

    public void avaliarVeiculo(String placa, int nota) {
        Veiculo veiculo = exigirVeiculo(placa);
        if (!(veiculo instanceof Avaliavel)) {
            throw new IllegalArgumentException("Este tipo de veiculo nao pode ser avaliado.");
        }
        ((Avaliavel) veiculo).avaliar(nota);
    }

    public void avaliarAluguel(int codigo, int nota) {
        Aluguel aluguel = exigirAluguel(codigo);
        if (!(aluguel instanceof Avaliavel)) {
            throw new IllegalArgumentException("Este tipo de aluguel nao pode ser avaliado.");
        }
        ((Avaliavel) aluguel).avaliar(nota);
    }

    public void realizarInspecao(String placa) {
        Veiculo veiculo = exigirVeiculo(placa);
        if (!(veiculo instanceof Inspecionavel)) {
            throw new IllegalArgumentException("Este tipo de veiculo nao aceita inspecao.");
        }
        ((Inspecionavel) veiculo).realizarInspecao();
    }

    public void colocarEmManutencao(String placa) {
        exigirVeiculo(placa).colocarEmManutencao();
    }


    public int removerEmManutencao() {
        int quantidadeAnterior = veiculos.size();
        veiculos.removeIf(veiculo -> veiculo.getStatus() == StatusVeiculo.MANUTENCAO);
        return quantidadeAnterior - veiculos.size();
    }

    private Veiculo exigirVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo == null) throw new IllegalArgumentException("Veiculo nao encontrado.");
        return veiculo;
    }

    private Aluguel exigirAluguel(int codigo) {
        Aluguel aluguel = buscarAluguel(codigo);
        if (aluguel == null) throw new IllegalArgumentException("Aluguel nao encontrado.");
        return aluguel;
    }
}
