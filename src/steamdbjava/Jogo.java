package steamdbjava;

public class Jogo {
    private int id;
    private String nome;
    private String dataLancamento;
    private int idadeRequerida;
    private int metacritic;
    private boolean singlePlayer;
    private boolean multiplayer;
    private boolean coop;
    private boolean vrSupport;
    private String descricao;
    private String headerImage;

    public Jogo(int id, String nome, String dataLancamento, int idadeRequerida,
                int metacritic, boolean singlePlayer, boolean multiplayer,
                boolean coop, boolean vrSupport, String descricao, String headerImage) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.idadeRequerida = idadeRequerida;
        this.metacritic = metacritic;
        this.singlePlayer = singlePlayer;
        this.multiplayer = multiplayer;
        this.coop = coop;
        this.vrSupport = vrSupport;
        this.descricao = descricao;
        this.headerImage = headerImage;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getMetacritic() { return metacritic; }
    public boolean isSinglePlayer() { return singlePlayer; }
    public boolean isMultiplayer() { return multiplayer; }
    public boolean isCoop() { return coop; }
    public boolean isVrSupport() { return vrSupport; }
    public String getDescricao() { return descricao; }
    public String getHeaderImage() { return headerImage; }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + dataLancamento + ") - Metacritic: " + metacritic;
    }
}
