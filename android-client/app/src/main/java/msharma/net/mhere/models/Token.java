package msharma.net.mhere.models;

public class Token {

    private String idToken;

    private String refreshToken;

    public Token() {}

    public Token(String idToken, String refreshToken) {
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "Token{" +
                "idToken='" + idToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

}