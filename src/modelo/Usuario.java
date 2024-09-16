package modelo;

/* @author patmi */
public class Usuario {
    
    //Atributos
    private int idUsuario;
    private String usuario;
    private String pass;
    private String estado;

    //Constructores
    public Usuario() {
        this.idUsuario = 0;
        this.usuario = "";
        this.pass = "";
        this.estado = "";
    }

    //Métodos getteres setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", pass=" + pass + ", estado=" + estado + '}';
    }
    
}
