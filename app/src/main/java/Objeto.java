import java.util.Date;
import java.util.UUID;

/**
 * Created by Tuca on 07/03/2016.
 */
public class Objeto {
    private UUID id;
    private String titulo;
    private String descricao;
    private Categoria categoria;
    private Date dataPublicacao;
    private float recompensa;
    private String lugar;
    private String foto;
    private Status status;

    public void setId(UUID id){
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setRecompensa(float recompensa) {
        this.recompensa = recompensa;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId(){
        return id;
    }
    public String getTitulo() {

        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public float getRecompensa() {
        return recompensa;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFoto() {
        return foto;
    }

    public Status getStatus() {
        return status;
    }

    UUID generateId(){ //gerar o Id único
        return UUID.randomUUID();
    }

    @Override
    public Boolean equals(Objeto outro_obj) {
        if (this.getId() == outro_obj.getId()){
            return true;
        }
        else{
            return false;
        }
    }


}
