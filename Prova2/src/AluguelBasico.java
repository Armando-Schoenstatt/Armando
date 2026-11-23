
public class AluguelBasico extends Aluguel {
    public static final int LIMITE_KM_POR_DIA = 100;

    public AluguelBasico(int codigo, int dias) {
        super(codigo, dias);
    }

    @Override
    protected void validarQuilometragem(int kmPrevistos) {
        super.validarQuilometragem(kmPrevistos);
        long limite = (long) getDias() * LIMITE_KM_POR_DIA;
        if (kmPrevistos > limite) {
            throw new IllegalArgumentException("Aluguel basico permite no maximo "
                    + limite + " km para " + getDias() + " dia(s), por veiculo.");
        }
    }

    @Override
    public String getTipo() { return "BASICO"; }

    @Override
    public String getBeneficios() {
        return "Ate 100 km/dia por veiculo | Seguro nao incluso";
    }
}
