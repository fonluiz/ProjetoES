import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Tuca on 07/03/2016.
 */
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String contato;
    private String foto;
    private Set<String> flags;
    private Set<Objeto> achados;
    private Set<Objeto> perdidos;
    private ObjDAO dao;
    public Usuario() {
    }

    public void criarObjeto(){
    }
    void addAchados(Objeto objeto){
    }
    void addPerdidos(Objeto objeto){
    }
}
