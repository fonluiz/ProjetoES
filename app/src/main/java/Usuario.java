import java.util.Set;
import java.util.UUID;
/**
 * Created by Tuca on 07/03/2016.
 */
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String contato;
    private String foto;
    private Set<String> flags;    //Acho que faz mais sentido que seja um set de strings ao invés de uma string apenas, mas fica aberto a discussão
    private Set<Objeto> achados;
    private Set<Objeto> perdidos;
    private ObjDAO dao;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setContato(String contato) {   //não seria melhor uma classe ou estrutura de dados como map para guardar contato? email não já é um contato?
        this.contato = contato;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setFlags(Set<String> flags) {
        this.flags = flags;
    }

    public String getNome() {

        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getContato() {
        return contato;
    }

    public String getFoto() {
        return foto;
    }

    public Set<String> getFlags() {
        return flags;
    }

    public Set<Objeto> getAchados() {
        return achados;
    }

    public Set<Objeto> getPerdidos() {
        return perdidos;
    }

    public void criarObjeto(){
    }
    void addAchados(Objeto objeto){
        this.achados.add(objeto);
    }
    void addPerdidos(Objeto objeto){
        this.perdidos.add(objeto);
    }

    UUID generateId(){ //gerar o Id único
        return UUID.randomUUID();
    }

    public Usuario(String nome, String email, String senha, String contato) {
        setNome(nome);
        setEmail(email);
        setSenha();
        setContato();
        this.id = generateId();
    }
}
