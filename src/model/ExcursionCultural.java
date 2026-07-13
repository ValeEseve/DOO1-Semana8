package model;

public class ExcursionCultural {
    private String lugarHistorico;

    public ExcursionCultural(String lugarHistorico) {
        super();
        this.lugarHistorico = lugarHistorico;
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public String toString() {
        return "ExcursionCultural{" +
                "lugarHistorico='" + lugarHistorico + '\'' +
                '}';
    }
}
