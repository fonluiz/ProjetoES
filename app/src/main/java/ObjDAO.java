import java.util.List;

/**
 * Created by Tuca on 07/03/2016.
 */
interface ObjDAO {
    void atualizar(Object obj);
    void salvar(Object obj);
    void remover(Object obj);
    List<Object> listaTodos();
    Object buscar(String id):
    List<Object> buscar(List<String> palavras_chave);
}
