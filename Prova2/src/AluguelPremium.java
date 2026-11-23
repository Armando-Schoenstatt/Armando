
public class AluguelPremium extends Aluguel {
    public AluguelPremium(int codigo, int dias) {
        super(codigo, dias);
    }

    @Override
    public String getTipo() { return "PREMIUM"; }

    @Override
    public String getBeneficios() {
        return "KM ilimitados | Seguro incluso";
    }
}
