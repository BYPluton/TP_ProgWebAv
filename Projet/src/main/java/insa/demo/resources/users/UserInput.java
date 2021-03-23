package insa.demo.resources.users;

public class UserInput {
    private String pseudo;
    private String mdp;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "pseudo='" + pseudo + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
