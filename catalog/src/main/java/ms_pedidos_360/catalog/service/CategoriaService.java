package ms_pedidos_360.catalog.service;

import ms_pedidos_360.catalog.model.Categoria;
import ms_pedidos_360.catalog.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import ms_pedidos_360.catalog.exception.CategoriaDuplicadaException;
import ms_pedidos_360.catalog.exception.CategoriaNoEncontradaException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findByActivoTrue();
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new CategoriaNoEncontradaException(
                                "Categoría no encontrada con id: " + id
                        )
                );
    }

    public Categoria crear(Categoria categoria) {

        if (categoriaRepository.existsByNombreIgnoreCase(categoria.getNombre())) {
            throw new CategoriaDuplicadaException(
                    "Ya existe una categoría con el nombre: " + categoria.getNombre()
            );
        }

        categoria.setId(null);
        categoria.setActivo(true);

        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria datosCategoria) {

        Categoria categoria = buscarPorId(id);

        var categoriaMismoNombre =
                categoriaRepository.findByNombreIgnoreCase(datosCategoria.getNombre());

        if (categoriaMismoNombre.isPresent()
                && !categoriaMismoNombre.get().getId().equals(id)) {

            throw new CategoriaDuplicadaException(
                    "Ya existe una categoría con el nombre: "
                            + datosCategoria.getNombre()
            );
        }

        categoria.setNombre(datosCategoria.getNombre());
        categoria.setDescripcion(datosCategoria.getDescripcion());

        if (datosCategoria.getActivo() != null) {
            categoria.setActivo(datosCategoria.getActivo());
        }

        return categoriaRepository.save(categoria);
    }

    public void desactivar(Long id) {

        Categoria categoria = buscarPorId(id);

        categoria.setActivo(false);

        categoriaRepository.save(categoria);
    }
}