
public class AluguelExecutivo extends Aluguel implements Avaliavel {
    private Integer nota;

    public AluguelExecutivo(int codigo, int dias) {
        super(codigo, dias);
    }

    public Integer getNota() { return nota; }

    @Override
    public void avaliar(int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        this.nota = nota;
    }

    @Override
    public String getTipo() { return "EXECUTIVO"; }

    @Override
    public String getBeneficios() {
        return "KM ilimitados | Motorista particular | Avaliacao: "
                + (nota == null ? "Nao avaliado" : nota + "/10");
    }
}
